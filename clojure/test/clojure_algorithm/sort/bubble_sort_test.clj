(ns clojure-algorithm.sort.bubble-sort-test
    (:require [clojure.test :refer :all]
              [clojure-algorithm.core :refer :all]
              [clojure-algorithm.sort.bubble-sort :refer :all]))

(defn print-array [l]
    (def limit (alength l))
    (def i 0)
    (while (< i limit)
        (do
            (print (aget l i) " " )
            (def i (+ i 1))))
    (print "\n"))

(defn gen-data [n]
    (def l (make-array Integer/TYPE n))
    (loop [i (- n 1)]
        (when (> i -1)
            (aset l i (- (rand-int 100) 50))
            (recur (- i 1))))
    l)

(deftest a-test
    (testing "FIXME, I fail."
        (def sort (new bubble-sort))
        (.sort sort (gen-data 10))))
  