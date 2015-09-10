(ns five-three-one.home.handlers
  (:require [re-frame.core :refer [register-handler]]))

(register-handler
 :home-count-inc
 (fn [db _]
   (.log js/console "home-count-inc")
   (update-in db [:home :count] inc)))
