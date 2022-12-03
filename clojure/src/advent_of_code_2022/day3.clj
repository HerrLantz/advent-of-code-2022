(ns advent-of-code-2022.day2
  (:require [advent-of-code-2022.common :as common]
            [clojure.string :as str]
            [clojure.set :as set]))

(defn priority
  [item]
  (let [char-number (int item)]
    (if (common/lower-case? item)
      (- (inc char-number) (int \a))
      (- (+ 27 char-number) (int \A)))))

(def part1 (->> (common/file-to-string-list "day3.txt")
                (map common/split-str-in-middle)
                (map (fn [bag] (apply set/intersection bag)))
                (apply concat)
                (map priority)
                (apply +)))

(def part2 (->> (common/file-to-string-list "day3.txt")
                (partition 3)
                (map (fn [group] (apply set/intersection (map set group))))
                (apply concat)
                (map priority)
                (apply +)))
