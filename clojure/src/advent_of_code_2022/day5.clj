(ns advent-of-code-2022.day5
  (:require [advent-of-code-2022.common :as common]
            [clojure.string :as str]))

(defn create-crates
  [s]
  (->> (partition 4 s)
       (map (fn [a] (apply str a)))
       (partition 9)
       common/transpose
       (map (fn [a] (filter (fn [b] (re-find #"\w" b)) a)))
       (map (fn [a] (map (fn [b] (re-find #"\w" b)) a)))
       (into [])))

(defn move-order
  [boxes part]
  (if (= part :part1) (reverse boxes) boxes))

(defn procedure
  [part stacks amount from-stack to-stack]
  (let [moved-boxes (take amount (get stacks (dec from-stack)))]
    (-> (update stacks (dec to-stack) (fn [s] (concat (move-order moved-boxes part) s)))
        (update (dec from-stack) (fn [s] (drop amount s))))))

(defn parse-procedure
  [p]
  (common/numbers-in-string->number-seq p))

(defn solve
  [part]
  (let [[s p]  (-> (common/file-to-string "day5.txt")
                   common/split-empty-lines)
        stacks (create-crates s)
        procedures (map parse-procedure (str/split-lines p))]
    (->> (reduce (fn [acc curr] (apply procedure part acc curr)) stacks procedures)
         (map first)
         (apply str))))

(def part1 (solve :part1))

(def part2 (solve :part2))
