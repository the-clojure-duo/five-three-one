(ns five-three-one.home.views
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [subscribe
                                   dispatch]]
            five-three-one.home.handlers
            five-three-one.home.subs))

(defn heading
  []
  "this is a heading")

(defn signin-handler []
  (.open js/window "/google_login" "Sign In" "width=800, height=600"))

(defn page []
  (let [count (subscribe [:home-count])]
    (fn []
      [:div [:h2 (heading)]
       [:div [:a {:href "#/about"} "go to about page"]]
       [:button {:on-click #(dispatch [:home-count-inc])}
        "Click here to increment the number!"]
       [:div (str @count)]
       [:div {:on-click signin-handler} "SIGN IN"]])))
