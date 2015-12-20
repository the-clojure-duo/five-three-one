(ns five-three-one.onboarding.views
  (:require [re-frame.core :refer [subscribe]]
            [five-three-one.onboarding.views.find-1rm :as find-1rm]))

(defmulti section identity)
(defmethod section :find-1rm [] [find-1rm/page])
(defmethod section :default [] [:div])

;; TODO: Create a vector of view names

(defn page []
  (let [section-name (subscribe [:onboarding/section])]
    [:div
     (section @section-name)
     [:div "Prev"]
     [:div "Next"]]))
