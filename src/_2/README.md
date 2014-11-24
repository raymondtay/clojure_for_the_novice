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


## Immutability

Because Clojure data structures are immutable and implemented hashCode
correctly, any Clojure data structure can be a key in a map. That said,
a very common key type is the Clojure keyword.

A keyword is a like a symbole, except that keywords begin with a colon (:).

## Destructuring

In many programming languages, you bind a variable to an 
entire collection when you need to access only part of that strucutre.

```
(defn greet-author-1 [author]
    (println "Hello, " (:first-name author))
```

Having to bind author is unsatisfying. You don't need the author; all you need
is the first-name. Clojure solves this with destructuring. Any place that you bind
names, you can nest a vector or a mpa in the binding to reach into 
a colelction and bind only the part you want. 

```
(defn greet-author-2 [{fname :first-name}]
    (println "Hello, " fname))
```
The binding of the form `{fname :first-name}` tells Clojure 
to bind the `fname` to the `first-name` of the funciton argument.


## About `Vars` in Clojure

Vars should only ever be defined in an interactive context - such as a REPL
- or within a CLojure source file as a way of defining named functions, other constant
values and the like. In particular, top-level vars (that's globally accessiable vars mapping
wihtin namespaces, as defined by `def` and its variants) should only ever be defined by 
top-level expresions, never in the bodies of functions in the normal course of operation
of a Clojure program


