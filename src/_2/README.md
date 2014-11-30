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

another one is the following (remember we are still referencing the m)
(let [{f "foo"} m] (+ f 12)) <-- gives 100
(let [{v 42} m] (if v 1 0)) <-- gives 0

this surprised me a little which is destructuring by indexing into a structure i.e. m
(let [{ x 3 y 8} [12 0 0 -18 44 6 0 0 1]] x) <-- gives -18 (the 3rd position in m)
(let [{ x 3 y 8} [12 0 0 -18 44 6 0 0 1]] y) <-- gives 1 (the 8th position in m)

The following example has to do with using the let-forms to capture
the rest of the sequence i.e. (let [... & param-name] data])

```
(def user-info ["testdata1" 2010 :name "Ray" :city "Singapore"])
(let [[username account-year & extra-info] user-info
      {:keys [name city]) (apply hash-map extra-info)]
    (format "%s is in %s" name city))
```

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

## About anonymous functions in Clojure

The anon-function has the following forms:
```
user=> (doc fn)
-------------------------
clojure.core/fn
  (fn name? [params*] exprs*)
  (fn name? ([params*] exprs*) +)
Special Form
  params => positional-params* , or positional-params* & next-param
  positional-param => binding-form
  next-param => binding-form
  name => symbol

  Defines a function

  Please see http://clojure.org/special_forms#fn
```
A contrived example is the following:
```
(println (format "running anon-fn is %d" ((fn [x] x) 42)))
```
The function `fn` listed here is simply a value that hashes the given 
argument to itself.
Turns out we can define anon-fns which can consumes multiple arguments.
and the way Clojure allows you to accomplish this is via a small trick
i.e. allowing the name to be referenced; here's an example:
```
(def a-adder (fn adder 
                ([x] (adder x 1))
                ([x y] (+ x y))))
```

Here's an example of a mutually recursive function using the `letfn` form
and before we do that, let's take a look at the documentation
```
user=> (doc letfn)
-------------------------
clojure.core/letfn
  (letfn [fnspecs*] exprs*)
Special Form
  fnspec ==> (fname [params*] exprs) or (fname ([params*] exprs)+)

  Takes a vector of function specs and a body, and generates a set of
  bindings of functions to their names. All of the names are available
  in all of the definitions of the functions, as well as the body.
nil
```
Continuing with the example ...
```
(letfn [(odd? [n] (even? (dec n))) 
        (even? [n] (or (zero? n) (odd? (dec n))))] (odd? 11))
```
## Variadic functions

Functions can optionally gather all additional requirements used
in calls to it into a sequence; this uses the same mechanisms
and such fns are called "variadic" with gathered arguments
usually called 'rest arguments' or 'varargs'.
```
(defn concat-rest
    [x & rest]
    (apply str (butlast rest)))
;; the reference to `butlast` is actually a macro, try (doc butlast).
```
The 'seq' formed for the rest arguments can be destructured just like any other
sequence, here we're destructuring arguments to make a function behave as if it
had an explicitly defined zero-arg arity:
```
(defn make-user
    [x & [rest]]
    {:user-id (or user-id (str (java.util.UUID/randomUUID)))})
```
and a slightly more elaborate `make-user` function can be written (as in the book).
```
(defn make-user
    [username & {:keys [email join-date]
                :or {join-date (java.util.Date.)}}] 
    {:username username
     :join-date join-date
     :email email
     :exp-date (java.util.Date. (long (+ 2.592e9 (.getTime join-date))))})
```

## Looping: loop and recur

Clojure provides looping constructs and `doseq` and `dotimes` 
all of which are built upon `recur`. `recur` transfers control to the local-most
loop head without consuming stack space, which is defined either by `loop` or a function.

```
(loop [x 5] (if (neg? x) x (recur (dec x))))
;; returns -1 when done
```
Loop heads are established by functions, in which case 'recur' rebinds the function's parameters using
the values provided as arguments to recur:
```
(defn countdown [x]
    (if (zero? x) :blastoff! 
        (do (println x)
            (recur (dec x)))))
```

