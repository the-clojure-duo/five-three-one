(ns five-three-one.home.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [register-sub]]))

(register-sub
 :home-count
 (fn
   [db _]
   (reaction (get-in @db [:home :count]))))
