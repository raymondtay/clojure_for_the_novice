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

Another example is the following 
```
(def m {:a 5 :b 6 :c [7 8 9] :d {:e 10 :f 11} "foo" 88 42 false})
(let [{a :a b :b} m] (+ a b)) 
```
Here we are binding the value for :a in the map to a, and the value for :b in the map to b.
Going back to our visual alignment of the destructuring form with the (in this case partial)
{a :a b :b} <-- in the expression
{:a 5 :b 6} <-- in 'm'

## About `Vars` in Clojure

Vars should only ever be defined in an interactive context - such as a REPL
- or within a CLojure source file as a way of defining named functions, other constant
values and the like. In particular, top-level vars (that's globally accessiable vars mapping
wihtin namespaces, as defined by `def` and its variants) should only ever be defined by 
top-level expresions, never in the bodies of functions in the normal course of operation
of a Clojure program


## About `let` in clojure

Let allows you to define named references that are lexically scoped to the extend ot eh 
let expression. Said in another way, let defines locals. For example
```
(defn hypot 
    [x y]
    (let [x2 (* x x)
          y2 (* y y)]
         (Math/sqrt (+ x2 y2))))
```

There are many terms used to talk about named references established by let in Clojure parlance
+ Locals 
+ Local bindings
+ particular values are said to be let-bound

Bindings and bo8und used in connection with let are entirely distinct
from the binding macro, which controls scoped thread-local variables.

`let` has two particular semantic wrinkles that are very different from locals you may be
used to in other languages:
+ All locals are immutable. You can override a local binding within a nested let form
  or a later binding of the same name within the same binding vector, but there is no way to bash out
  a bound name and change its value within the scope of a single let form. This eliminates a source of common
  errors and bugs w/o sacrificing capability:
+ let's binding vector is interpreted at compile time to provide optional destructuring of common collection types.
  Destructuring can aid substantially in eliminating certain types of verbose code often associated with
  working with collections provided as arguments to functions.


