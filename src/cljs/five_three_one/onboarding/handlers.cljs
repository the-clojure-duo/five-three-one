(ns five-three-one.onboarding.handlers
  (:require [re-frame.core :refer [register-handler]]))

(register-handler
 :onboarding/update-max
 (fn [db [_ exercise weight]]
   (assoc-in db [:onboarding :real-max exercise] weight)))
