(ns five-three-one.model.max
  (:require [five-three-one.model.queries :as queries :refer [spec]]
            [five-three-one.util.uuid :as uuid]))

(defn get-last-max [user exercise]
  "Accepts a UUID object for user and a keyword letter for exercise.
   Returns an int or nil if there are no maxes"
  (let [data (queries/get-last-max spec user (name exercise))]
    (if-not (empty? data)
      (-> data
          (first)
          :weight
          int)
      nil)))

(defn add-max! [user exercise weight]
  "Accepts a UUID object for user, keyword letter for exercise, int for weight"
  (queries/add-max! spec (uuid/random) user (name exercise) weight))
