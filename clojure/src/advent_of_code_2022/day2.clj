(ns advent-of-code-2022.day2
  (:require [advent-of-code-2022.common :as common]
            [clojure.string :as str]))

(defn shape-score
  [game]
  (condp = (second (str/split game #"\s"))
    "X" 1
    "Y" 2
    "Z" 3))

(defn outcome-score
  [game]
  (cond
    (re-find #"A Y|B Z|C X" game) 6
    (re-find #"A X|B Y|C Z" game) 3
    (re-find #"A Z|B X|C Y" game) 0))

(defn outcome-score2
  [game]
  (cond
    (= "A X" game) 3
    (= "B X" game) 1
    (= "C X" game) 2
    (= "A Y" game) 4
    (= "B Y" game) 5
    (= "C Y" game) 6
    (= "A Z" game) 8
    (= "B Z" game) 9
    (= "C Z" game) 7))

(def part1 (->> (common/file-to-string-list "day2.txt")
                (map (fn [game] (+ (shape-score game)
                                  (outcome-score game))))
                (apply +)))

(def part2 (->> (common/file-to-string-list "day2.txt")
                (map outcome-score2)
                (apply +)))
