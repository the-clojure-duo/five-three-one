(ns five-three-one.views
  (:require [re-frame.core :refer [subscribe]]
            [five-three-one.home.views :as home]
            [five-three-one.about.views :as about]))

(defmulti pages identity)
(defmethod pages :home [] [home/page])
(defmethod pages :about [] [about/page])
(defmethod pages :default [] [:div])

(defn current-page []
  (let [active-page (subscribe [:active-page])]
    (fn []
      (pages @active-page))))
