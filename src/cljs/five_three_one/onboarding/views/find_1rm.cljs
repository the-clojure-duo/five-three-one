(ns five-three-one.onboarding.views.find-1rm
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [dispatch
                                   subscribe]]))

(def exercise->name
  {:bench-press "Bench Press"
   :deadlift "Deadlift"
   :squat "Squat"
   :press "Military Press"})

(defn row [exercise]
  (let [reps (atom 1)
        weight (atom nil)
        exercise-name (exercise->name exercise)
        max (subscribe [:onboarding/real-max exercise])]
    (fn []
      [:div {:class "row"}
       [:div exercise-name]
       [:div
        [:input {:type "text"
                 :placeholder "add"
                 :value @weight
                 :on-change #(do
                               (reset! weight (-> % .-target .-value))
                               (dispatch [:onboarding/update-max exercise (* @reps @weight)]))}]
        [:span "x"]
        [:input {:type "text"
                 :value @reps
                 :on-change #(do
                               (reset! reps (-> % .-target .-value))
                               (dispatch [:onboarding/update-max exercise (* @reps @weight)]))}]]
       [:div
        [:span @max]]])))

(defn unit-toggler []
  (let [unit (subscribe [:unit-preference])]
    (fn []
      [:input {:type "checkbox"
               :checked (= @unit :lb)
               :on-change #(if (-> % .-target .-checked)
                             (dispatch [:set-units :lb])
                             (dispatch [:set-units :kg]))}])))

(defn page []
  (let []
    (fn []
      [:div
       [:h2 "Find your 1RM"]
       [unit-toggler]
       [:label "Imperial"]
       [row :bench-press]
       [row :deadlift]
       [row :squat]
       [row :press]])))
