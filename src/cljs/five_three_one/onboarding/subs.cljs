(ns five-three-one.onboarding.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(register-sub
 :onboarding/real-max
 (fn [db [_ exercise]]
   (reaction
    (get-in @db [:onboarding :real-max exercise]))))

(register-sub
 :onboarding/section
 (fn [db]
   (reaction
    (get-in @db [:onboarding :section]))))
