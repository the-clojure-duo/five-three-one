(ns five-three-one.db
  (:require [re-frame.core :refer [dispatch-sync]]
            [five-three-one.home.db :as home]
            [five-three-one.onboarding.db :as onboarding]))

(def initial-state
  {:user-preferences {:units :lb}})

(defn init []
  (dispatch-sync [:init
                  initial-state
                  home/initial-state
                  onboarding/initial-state]))
