(ns five-three-one.handlers
  (:require [re-frame.core :refer [register-handler]]
            [five-three-one.db :as db]
            [five-three-one.home.handlers]))

(register-handler
 :init
 ;; event takes variable number of params representing
 ;; initial state of each page
 (fn [db [_ & new-state]]
   (apply merge db new-state)))

(register-handler
 :set-active-page
 (fn [db [_ new-page]]
   (assoc db :active-page new-page)))
