(ns five-three-one.model.queries
  (:require [environ.core :refer [env]]
            [yesql.core :refer [defqueries]]))

(def spec (:database-url env))

(defqueries "queries.sql")
