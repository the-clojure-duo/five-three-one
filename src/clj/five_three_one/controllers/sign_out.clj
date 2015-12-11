(ns five-three-one.controllers.sign-out
  (:require [compojure.core :refer [defroutes GET]]
            [ring.util.response :refer [redirect]]))

(def route
  (GET "/signout" [] (assoc (redirect "/") :session nil)))

