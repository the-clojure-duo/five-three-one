(ns five-three-one.home.handlers
  (:require [re-frame.core :refer [register-handler]]))

(register-handler
 :count-inc
 (fn [db [_ path]]
   (update-in db path inc)))
