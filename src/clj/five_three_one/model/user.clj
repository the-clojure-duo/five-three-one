(ns five-three-one.model.user
  (:import java.util.UUID)
  (:require [five-three-one.model.queries :as queries]
            [five-three-one.util.uuid :as uuid]
            [five-three-one.util.kebab :refer [kebabify-keys]]))

;; (defn stringify-uuid [{uuid :uuid :as m}]
;;   (assoc m :uuid (.toString uuid)))

(defn get-user-by-email [email]
  (when-let [user-map (first (queries/get-user-by-email {:email email}))]
    (kebabify-keys user-map)))

(defn get-user-by-uuid [uuid]
  (when-let [user-map (first (queries/get-user-by-uuid {:uuid uuid}))]
    (kebabify-keys user-map)))

(defn create-user! [{:keys [first-name last-name email]}]
  "creates new user and returns the created user"
  (let [uuid (uuid/random)]
    (queries/create-user! {:uuid uuid
                           :first_name first-name
                           :last_name last-name
                           :email email})
    {:uuid uuid
     :first-name first-name
     :last-name last-name
     :email email}))
