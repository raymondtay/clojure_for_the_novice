;; reduction is a common principle in functional programming
;; though it predates functional programming and was 
;; founded by Belloch (or used heavily) in his research in 
;; parallel programming.

(reduce (fn [x y] (+ x y)) (range 1 10 1))

;; what i'm doing (above) is to find out how much
;; does (1 + 2 + .. + 10) equal to? not surprisingly, its 45

;; Another example is the following, where we build
;; a map on the fly (building ADTs on the fly isn't that cool
;; anymore isn't it? rather it was cool when i first heard of 
;; it when i started programming in 1997
(reduce (fn [m v] (assoc m v (* v v))) {} [ 1 2 3 4])

;; returns {4 16, 3 9, 2 4, 1 1}

