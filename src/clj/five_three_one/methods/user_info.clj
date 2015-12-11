(ns five-three-one.methods.user-info
  (:require [five-three-one.model.user :as model]))

(defn user-info [params {session :session}]
  (let [uuid (:user session)
        info (model/get-user-by-uuid uuid)]
    (println "user-info: " uuid)
    (dissoc info :uuid)))
