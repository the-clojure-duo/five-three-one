(ns five-three-one.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [goog.events :as events]
              [goog.history.EventType :as EventType])
            [cljsjs.web-animations :as animation]
    (:import goog.History))

(defn signin-handler []
  (.open js/window "/google_login" "Sign In" "width=800, height=600"))

;; -------------------------
;; Views

(defn heading
  []
  "this is a heading.")

(def home-page
  (let [state (atom 0)]
    (fn [] [:div [:h2 (heading)]
            [:div [:a {:href "#/about"} "go to about page"]]
            [:button {:on-click #(do (swap! state inc)
                                     (println state))}
             "Click here to increment the number!"]
            [:div (str @state)]
            [:div {:on-click signin-handler} "SIGN IN"]])))

(defn about-page []
  [:div [:h2 "About five-three-one"]
   [:div [:a {:href "#/"} "go to the home page"]]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
  (session/put! :current-page #'home-page))

(secretary/defroute "/about" []
  (session/put! :current-page #'about-page))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (hook-browser-navigation!)
  (mount-root))
