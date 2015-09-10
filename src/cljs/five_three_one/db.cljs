(ns five-three-one.db
  (:require [re-frame.core :refer [dispatch-sync]]
            [five-three-one.home.db :as home]))

(def initial-state
  {:active-page :home})

(defn init []
  (dispatch-sync [:init
                  home/initial-state]))
