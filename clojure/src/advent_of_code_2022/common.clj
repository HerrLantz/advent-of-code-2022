(ns advent-of-code-2022.common
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.data :as data]
            [clojure.data.json :as json]))

(defn file-to-string-list
  [input-file-name]
  (->> (io/resource input-file-name)
       slurp
       str/split-lines))

(defn file-to-string
  [input-file-name]
  (->> (io/resource input-file-name)
       slurp))

(defn split-empty-lines
  [s]
  (str/split s #"\n{2,}"))

(defn string-list->number-list
  [l]
  (map (fn [s] (Integer. s)) l))

(defn number-list->string-list
  [s & [separator]]
  (let [value-separator (or separator " ")]
    (->> (repeat value-separator)
         (interleave s)
         drop-last
         (apply str))))

(defn list-of-bits->number
  [l]
  (-> (apply str l)
      (Integer/parseInt 2)))

(defn transpose
  [m]
  (apply mapv vector m))

(defn upper-case?
  [s]
  (-> (str/upper-case s)
      (= s)))

(defn lower-case?
  [s]
  (-> (str/lower-case s)
      (= s)))

(defn sum-numbers-in-str
  [s]
  (->> (re-seq #"\d+" s)
       (map (fn [n] (Integer. n)))
       (apply +)))

(defn hex?
  [s]
  (re-matches #"[0-9a-fA-F]+" s))

(defn sum-hex->number
  [h & h2]
  (->> (conj h2 h)
       (map (fn [hex] (Integer/parseInt hex 16)))
       (apply +)))

(defn sum-hex->hex
  [h & h2]
  (->> (apply sum-hex->number (conj h2 h))
       (format "%X")))


(defn string->binary-string-list
  [s]
  (->> (sequence s)
       (map int)
       (map (fn [n] (Integer/toString n 2)))
       (map (fn [n] (str (apply str (repeat (- 8 (count n)) "0"))
                        n)))))

(defn string->binary-string
  [s]
  (apply str (string->binary-string-list s)))

(defn binary-string->character-string
  [binary-string]
  (->> (re-seq #".{8}" binary-string)
       (map (fn [b] (char (Long/parseLong b 2))))
       (apply str)))


