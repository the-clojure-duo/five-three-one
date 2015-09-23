(ns five-three-one.model.max
  (:require [five-three-one.model.queries :as queries :refer [spec]]
            [five-three-one.util.uuid :as uuid]
            [clj-time.core :as time]
            [clj-time.coerce :as coerce]))

(defn get-last-max [user exercise]
  "Accepts a UUID object for user and a keyword letter for exercise.
   Returns an int or nil if there are no maxes"
  (let [data (queries/get-last-max spec user (name exercise))]
    (when-not (empty? data)
      (-> data
          (first)
          :weight
          int))))

(defn get-max-by-date [user exercise & [date]]
  "Accepts a UUID object for user, a keyword letter for exercise, and a
   sql timestamp for the date. Returns an int or nil if there are no maxes. If no date specified,
   returns most recent max."
  (if-not date
    (get-last-max user exercise)
    (let [data (queries/get-max-by-date spec user (name exercise) date)]
      (when-not (empty? data)
        (-> data
            (first)
            :weight
            int)))))

(defn add-max! [user exercise weight]
  "Accepts a UUID object for user, keyword letter for exercise, int for weight"
  (queries/add-max! spec (uuid/random) user (name exercise) weight))
