(ns advent-of-code-2022.day1
  (:require [advent-of-code-2022.common :as common]
            [clojure.string :as str]))


(def part1 (->> (common/file-to-string "day1.txt")
                common/split-empty-lines
                (map common/sum-numbers-in-str)
                (reduce max)))

(def star2 (->> (common/file-to-string "day1.txt")
                common/split-empty-lines
                (map common/sum-numbers-in-str)
                (sort >)
                (take 3)
                (apply +)))


