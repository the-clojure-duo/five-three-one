(ns five-three-one.model.queries
  (:require [environ.core :refer [env]]
            [yesql.core :refer [defqueries]]))

(def spec 
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname (:database-url env)
   :user (:pg-user env)
   :password (:pg-password env)})

(defqueries "queries.sql" {:connection spec})
