(ns five-three-one.views
  (:require [re-frame.core :refer [subscribe]]
            [five-three-one.home.views :as home]
            [five-three-one.onboarding.views :as onboarding]))

(defmulti pages identity)
(defmethod pages :home [] [home/page])
(defmethod pages :onboarding [] [onboarding/page])
(defmethod pages :default [] [:div])

(defn current-page []
  (let [active-page (subscribe [:active-page])]
    (fn []
      (pages @active-page))))
