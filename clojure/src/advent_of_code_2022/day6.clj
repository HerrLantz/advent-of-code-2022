(ns advent-of-code-2022.day6
  (:require [advent-of-code-2022.common :as common :refer [all-unique?]]
            [clojure.string :as str]))


(defn solve
  [mark-length]
  (loop [stream (common/file-to-string "day6.txt")
         index  mark-length]
    (if (all-unique? (take mark-length stream))
      index
      (recur (rest stream) (inc index)))))

(def part1 (solve 4))

(def part2 (solve 14))
