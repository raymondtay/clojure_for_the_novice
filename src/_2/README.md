## About Strings in Clojure

Strings are sequences of characters. When you call Clojure sequence 
functions on a string, you get a sequence of characters back. Imagine 
that you wanted to conceal a secret mesage by interleaving it
with a second, innocuous message. You could use interleave to combine
the two messages:

Take note that interleave will execute as many times as the 
min(len(lhs), len(rhs))

(interleave "raymond can be funny at times" "hahaha") ;; this is good

(str (interleave "raymond can be funny at times" "hahaha")) ;; this creates an expression that's lazily evaluated.



