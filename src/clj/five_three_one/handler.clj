(ns five-three-one.handler
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :refer [not-found resources]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [include-js include-css]]
            [prone.middleware :refer [wrap-exceptions]]
            [ring.middleware.reload :refer [wrap-reload]]
            [environ.core :refer [env]]
            [five-three-one.controllers.google-auth :as google-auth]
            [five-three-one.middleware.oauth2 :as oauth2]
            [ring.middleware.transit :refer [wrap-transit-response wrap-transit-body]]
            [five-three-one.methods :as methods]
            [ring.util.response :refer [response redirect content-type]]
            [five-three-one.model.user :as user]
            [five-three-one.util :refer [embed-json-in-dom]]
            [five-three-one.controllers.sign-out :as sign-out]))

(defn home-page [{{user-id :user} :session}]
  (html
   [:html
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]
     (include-css "css/main.css")
     (when user-id
       (embed-json-in-dom "userData"
                          (-> user-id
                              (user/get-user-by-uuid)
                              (user/stringify-uuid))))]
    [:body
     (when user-id
       (let [user (user/get-user-by-uuid user-id)
             first-name (:first-name user)
             last-name (:last-name user)
             email (:email user)]
         [:div (str "You are signed in as " email "!!!")]))
     [:div#app
      [:h3 "ClojureScript has not been compiled!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]]
     (include-js "js/app.js")]]))

(def welcome-page
  (html
   [:html
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]
     (include-css "css/main.css")]
    [:body
     [:a {:href "/google_login"} "Sign in!"]]]))

(defn home-handler [{{user-id :user} :session :as request}]
  (if user-id
    (-> (home-page request)
        (response)
        (content-type "text/html"))
    (redirect "/welcome")))

(defroutes routes
  (GET "/" request (home-handler request))
  (GET "/welcome" request welcome-page)
  google-auth/signin-route
  methods/route
  sign-out/route
  (resources "/")
  (not-found "Not Found"))

(defn wrap-logging [handler]
  (fn [request]
    (let [response (handler request)]
      (println (str (:uri request) ": " response))
      response)))

(def app
  (let [handler (-> #'routes
                    (oauth2/wrap-oauth2 google-auth/oauth-config)
                    (wrap-logging)
                    (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
                    (wrap-transit-body)
                    (wrap-transit-response {:encoding :json}))]
    (if (env :dev) (-> handler wrap-exceptions wrap-reload) handler)))
