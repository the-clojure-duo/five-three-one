(ns five-three-one.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [include-js include-css]]
            [prone.middleware :refer [wrap-exceptions]]
            [ring.middleware.reload :refer [wrap-reload]]
            [environ.core :refer [env]]
            [five-three-one.controllers.google-auth :as google-auth]
            [five-three-one.middleware.oauth2 :as oauth2]))

(def home-page
  (html
   [:html
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]
     (include-css "css/main.css")]
    [:body
     [:div#app
      [:h3 "ClojureScript has not been compiled!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]]
     (include-js "js/app.js")]]))

(defroutes routes
  (GET "/" [] home-page)
  google-auth/signin-route
  (resources "/")
  (not-found "Not Found"))

(def app
  (let [handler (-> #'routes
                    (oauth2/wrap-oauth2 google-auth/oauth-config)
                    (wrap-defaults site-defaults))]
    (if (env :dev) (-> handler wrap-exceptions wrap-reload) handler)))
