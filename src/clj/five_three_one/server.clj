(ns five-three-one.server
  (:require [five-three-one.handler :refer [app]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]
            [five-three-one.model.migration :refer [migrate]])
  (:gen-class))

(defn -main [& args]
  (let [port (Integer/parseInt (or (env :port) "3000"))]
    (migrate)
    (run-jetty app {:port port :join? false})))
