(ns five-three-one.model.migration
  (:require [migratus.core :as migratus]
            [five-three-one.model.queries :as queries]))

(def config {:store                :database
             :migration-dir        "migrations/"
             :migration-table-name "migrations"
             :db queries/spec})

(defn migrate []
  (migratus/migrate config))
