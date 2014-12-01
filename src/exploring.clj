;; reduction is a common principle in functional programming
;; though it predates functional programming and was 
;; founded by Belloch (or used heavily) in his research in 
;; parallel programming.

(reduce (fn [x y] (+ x y)) (range 1 10 1))

;; what i'm doing (above) is to find out how much
;; does (1 + 2 + .. + 10) equal to? not surprisingly, its 45

