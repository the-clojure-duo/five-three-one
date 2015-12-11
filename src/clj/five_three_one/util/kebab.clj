(ns five-three-one.util.kebab
  (:require [camel-snake-kebab.core :refer [->kebab-case-keyword]]))

(defn kebabify-keys
  "changes map keys to kebab-case keywords"
  [m]
  (reduce (fn [acc [key val]]
            (assoc acc (->kebab-case-keyword key) val)) {} m))

