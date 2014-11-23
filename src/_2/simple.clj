;; when (load-file <filename>) is invoked, the last expression
;; evaluated in the file is returned

;; a map where the key and value pairs are implicit
(def inventors {"Scala" "Martin Odersky" "Java" "James Gosling" "Lisp" "John McCarthy"})

;; another map where the semantics are the same but easier to read
(def inventors {"Scala" "Martin Odersky", "Java" "James Gosling", "Lisp" "John McCarthy"})

;; obtain the value for a key 
(inventors "Scala")

;; equivalently, we can use 
(println (format "Programming language %s was invented by %s" "Java" (inventors "Java")))
(println (format "Programming language %s was invented by %s" "Scala" (inventors "Scala")))
(println (format "Programming language %s was invented by %s" "Lisp" (inventors "Lisp")))
