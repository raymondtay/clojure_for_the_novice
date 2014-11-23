;; to define your own functions, use 'defn'
;; which has the following form
;; (defn name doc-string? attr-map? [param*] body)
;; the 'attr-map' associates metadata with the function's var
;; 
(defn greeting 
    "returns a greeting of the form 'hello, username'"
    [username]
    (str "Hello, " username))
;; an example use case is as follows:
;;(load-file "exploring.clj") ;; would this send the load-file into a infinite loop?
;; yes, it actually does and fails with a error message
;; user=> (load-file "exploring.clj")
;; CompilerException java.lang.StackOverflowError, compiling:(/Users/raymondtay/clojure_for_the_novice/src/_2/exploring.clj:6:1)

(greeting "reader")

;; You can create a function with variable arity by including an ampersand in
;; the parameter list. Clojure will bind the name after the ampersand to a 
;; sequence of all the remaining parameters.
(defn date [person-1 person-2 & chaperones]
    (println person-1 "and" person-2 
        "went out with" (count chaperones) "other people."))

;; Filter functions are often self-explanatory and part of the reason is because
;; they are written concisely. You can write an indexable-word? like this
(defn indexable-word? [word] (> (count word) 2 ))

;; below are the 4 use cases for this particular function and they describe
;; (in the order you see them here) the following scenarios:
;; + when the input is expected
;; + when there is no input
;; + when the input is not expected, infact another type
;; + when the input is expected, but the semantics is not what we are expecting
;;		user=> (indexable-word? "hi there people")
;;		true
;;		user=> (indexable-word? )
;;		ArityException Wrong number of args (0) passed to: user/indexable-word?  clojure.lang.AFn.throwArity (AFn.java:429)
;;		user=> (indexable-word? 1)
;;		UnsupportedOperationException count not supported on this type: Long  clojure.lang.RT.countFrom (RT.java:556)
;;		user=> (indexable-word? "1")
;;		false

(require '[clojure.string :as string])
(filter indexable-word? (string/split "A fine day it is to learn clojure" #"\W+"))

;; the equivalent form is as follows:
(filter (fn [w] (> (count w) 2)) (str/split "A find day it is to learn Clojure" #"\W+"))

;; A second motivation for anon-fns is wanting a named function 
;; but only inside the scope of another function. Here's another form that's equivalent to the previous 2 forms
(defn indexable-words [text]
    (let [indexable-word? (fn [w] (> (count w) 2))]
        (filter indexable-word? (str/split text #"\W+"))))

(defn make-greeter [aprefix]
    (fn [username] (str aprefix ", " username)))

(defn default-greeting (make-greeter "Hello"))
(defn aloha-greeting (make-greeter "Aloha!"))

(defn square-corners [bottom left size]
    (let [top (+ bottom size)
          right (+ left size)]
        [[bottom left] [top left] [top right] [bottom right]]))


