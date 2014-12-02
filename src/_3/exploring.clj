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

;; turns out clojure supports function composition very much like how Haskell does it
;; and Scala does it through the `comp`
;; An example is the following

(require '[clojure.string :as str]) ;; we want to load the clojure.string namespace and give a short name
(def camel->keyword (comp keyword 
        str/join
        (partial interpose \-)
        (partial map str/lower-case)
        #(str/split % #"(?<=[a-z])(?=[A-Z])")))

;; according to the book, we can achieve the previous camel->keyword 
;; through the -> and ->> macros. Macros do not operate over functions
;; rather, they re-arrange the code you provide to "thread" a value
;; or collection as either the first/last argument in each form.

(defn camel->keyword-2
    [s]
    (->> (str/split s #"(?<=[a-z])(?=[A-Z])")
        (map str/lower-case)
        (interpose \-)
        str/join
        keyword))

