(ns five-three-one.model.user
  (:import java.util.UUID)
  (:require [five-three-one.model.queries :as queries]
            [five-three-one.util.uuid :as uuid]
            [five-three-one.util.kebab :refer [kebabify-keys]]))

(defn get-user-by-email [email]
  (when-let [user-map (first (queries/get-user-by-email queries/spec email))]
    (kebabify-keys user-map)))

(defn get-user-by-uuid [uuid]
  (when-let [user-map (first (queries/get-user-by-uuid queries/spec uuid))]
    (kebabify-keys user-map)))

(defn create-user! [{:keys [first-name last-name email]}]
  "creates new user and returns the created user"
  (let [uuid (uuid/random)]
    (queries/create-user! queries/spec uuid first-name last-name email)
    {:uuid uuid
     :first-name first-name
     :last-name last-name
     :email email}))
