; (:ns clojure-algorithm.choice)

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

(defn max-in-array [data]
    (def max-item (aget data 0))
    (def limit (alength data))
    (def i 1)
    (while (< i limit)
        (do
            (if (> (aget data i) max-item)
                (def max-item (aget data i)))
            (def i (+ i 1))))
    max-item)

(defn max-in-array-1 [data]
    (def limit (alength data))
    (def max-item (aget data (- limit 1)))
    (loop [i (- limit 2)]
        (if (> i -1)
            (do
                (if (> (aget data i) max-item)
                    (def max-item (aget data i)))
                (recur (- i 1)))
            max-item)))

(defn max-and-min [data]
    (def limit (alength data))
    (def max-item (aget data (- limit 1)))
    (def min-item (aget data (- limit 1)))
    (loop [i (- limit 2)]
        (if (> i -1)
            (do
                (if (> (aget data i) max-item)
                    (def max-item (aget data i)))
                (if (< (aget data i) min-item)
                    (def min-item (aget data i)))
                (recur (- i 1)))
            [max-item min-item])))

(defn max-and-min-1 [data]
    (def limit (alength data))
    (def max-item (aget data (- limit 1)))
    (def min-item (aget data (- limit 1)))
    (if (= (rem limit 2) 1)
        (def limit (- limit 1)))
    (def i 0)
    (while (< i limit)
        (do
            (if (< (aget data i) (aget data (+ i 1)))
                (do 
                    (if (< (aget data i) min-item)
                        (def min-item (aget data i)))
                    (if (> (aget data (+ i 1)) max-item)
                        (def max-item (aget data i))))
                (do
                    (if (< (aget data (+ i 1)) min-item)
                        (def min-item (aget data (+ i 1))))
                    (if (> (aget data i) max-item)
                        (def max-item (aget data i)))))
            (def i (+ i 2))))
    [max-item min-item])

(def data (gen-data 11))
(print-array data)
(println (max-in-array data))
(println (max-in-array-1 data))
(println (max-and-min data))
(println (max-and-min-1 data))