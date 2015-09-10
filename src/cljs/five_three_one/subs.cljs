(ns five-three-one.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]
            [five-three-one.home.subs]
            [five-three-one.routes :as routes]))

(register-sub
 :active-page
 (fn [db _]
   (reaction (:active-page @db))))
