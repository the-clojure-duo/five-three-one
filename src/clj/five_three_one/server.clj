(ns five-three-one.server
  (:require [five-three-one.handler :refer [app]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]
            [five-three-one.model.migration :as migration :refer [migrate]]
            [migratus.core :as migratus])
  (:gen-class))

(defn -main [& args]
  (case (some #{"migrate" "create-migration"} args)
    "migrate" (migrate)
    "create-migration" (migratus/create migration/config (first (rest args)))
    (let [port (Integer/parseInt (or (env :port) "3000"))]
      (run-jetty app {:port port :join? false}))))
