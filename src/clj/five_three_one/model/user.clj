(ns five-three-one.model.user
  (:import java.util.UUID)
  (:require [five-three-one.model.queries :as queries]
            [five-three-one.util.uuid :as uuid]))

(defn get-user-by-email [email]
  (first (queries/get-user-by-email queries/spec email)))

(defn create-user! [{:keys [first-name last-name email]}]
  "creates new user and returns the created user"
  (let [uuid (uuid/random)]
    (queries/create-user! queries/spec uuid first-name last-name email)
    {:uuid uuid
     :first-name first-name
     :last-name last-name
     :email email}))
