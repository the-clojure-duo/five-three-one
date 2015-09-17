(ns five-three-one.model.user
  (:import java.util.UUID)
  (:require [five-three-one.model.queries :as queries]))

(defn get-user-by-email [email]
  (update-in (first (queries/get-user-by-email queries/spec email))
             [:uuid]
             str))

(defn create-user! [{:keys [first-name last-name email]}]
  "creates new user and returns the created user"
  (let [uuid (java.util.UUID/randomUUID)]
    (queries/create-user! queries/spec uuid first-name last-name email)
    {:uuid (str uuid)
     :first-name first-name
     :last-name last-name
     :email email}))
