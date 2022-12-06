(ns advent-of-code-2022.day4
  (:require [advent-of-code-2022.common :as common]
            [clojure.string :as str]
            [clojure.set :as set]))

(defn range-to-set
  [[a b]]
  (set (range a (inc b))))

(defn range-contains?
  [a b]
  (let [set1 (range-to-set a)
        set2 (range-to-set b)]
    (or (set/subset? set2 set1)
        (set/subset? set1 set2))))

(defn ranges-intersects?
  [a b]
  (not-empty (set/intersection (range-to-set a)
                               (range-to-set b))))

(defn partition-input
  [file-name]
  (->> (common/file-to-string-list file-name)
       (map common/numbers-in-string->number-seq)
       (map (fn [m] (partition 2 m)))))

(defn solve
  [venn-fn]
  (->> (partition-input "day4.txt")
       (map (fn [m] (apply venn-fn m)))
       (filter identity)
       count))

(def part1 (solve range-contains?))
(def part2 (solve ranges-intersects?))
