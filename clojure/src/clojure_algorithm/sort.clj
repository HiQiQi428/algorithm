; (:ns algorithm.sort.bubble-sort)

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

(defn bubble-sort [data]
    (def limit (alength data))
    (def i 0)
    (while (< i limit)
        (do
            (def j (+ i 1))
            (while (< j limit)
                (do
                    (if (> (aget data i) (aget data j))
                        (do
                            (def tmp (aget data i))
                            (aset data i (aget data j))
                            (aset data j tmp)))
                    (def j (+ j 1))))
            (def i (+ i 1))))
    data)

(defn insert-sort [data]
    (print-array data)
    (def limit (alength data))
    (def l (make-array Integer/TYPE limit))
    (def i 0)
    (while (< i limit)
        (do
            (def j 0)
            (aset l i (aget data i))
            (while (< j i)
                (do
                    (if (> (aget l j) (aget l i))
                        (do
                            (def tmp (aget l i))
                            (aset l i (aget l j))
                            (aset l j tmp)))
                    (def j (+ j 1))))
            (def i (+ i 1))))
    l)

(defn fast-sort [data])

; (print-array (bubble-sort (gen-data 10)))
; (print-array (insert-sort (gen-data 10)))