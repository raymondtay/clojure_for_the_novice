// to load this file, do
(load-file "intro.clj")

// define a record named Person 
// with two fields: first name and last name
(defrecord Person [firstname lastname])

// here is how to use it
Person{:firstname "Raymond", :lastname "Tay")
// or
Person{"Raymond", "Tay"}

// note to self:
// Turns out that the defrecord is actually a macro in clojure
// and it has the following form
// (defrecord name [arg1 arg2 arg3])
// if you need different semantics, write your own macro. 
// If you want a variant of records with strong typing
// and configurable null-checking for all fields. 
// you can create your own defrecord macro to be used like this:
// (defrecord name [Type :arg1 Type :arg2 Type :arg3] :allow-nulls false)
//
// This ability to reprogram the language from within the langauge is the 
// unique advantage of Lisp. 
// + Lisp is homoiconic. That is, Lisp code is just Lisp data. This makes
//   it easy for programs to write other programs.
// + The whole language is therem, all the time. Paul Graham's essay "Revenge of the nerds"
//   explains why this concept is so powerful


// here's how to define a function named "hello-world"
(defn hello-world [username] (println (format "Hello, %" username)))

(def accounts (ref #{}))
(defrecord Account [id balance])

// The 'ref' function creates a transactionally protected reference to the current
// state of the database. Updating is trivial. the following code adds a new account
// to the database:

(dosync 
    (alter accounts conj (->Account "CLJ" 1000.00)))

// dosync causes the update to accounts to execute within a transaction. 
// this obviously guarantees thread safety, and it avoids locking.


(.start (new Thread (fn [] (println "hello"))))

// apparently does the same thing as 

(.start (new Thread (fn [] (println "hello" (Thread/currentThread)))))


// The #{} is a literal for an empty set. And if you like to join items
// to a collection and in our case the set, you need to understand conjoin function
// which takes the form:
// (conj <collection> <item1> <item2> etc)

(conj #{} "hi" "there" "this is" "raymond" "tay" ".")

// Now that you can build new sets, you need some way to keep track 
// of the current set of visitors. Clojure provides several reference types
// for this purpose
(atom initial-state)

// e.g. (atom (conj #{} "hi" "there"))

// 'def' is like 'defn' but more general. A def can define functions or dat.
// Use atom to create an atom, and use def to bind the atom to the name visitors
(def visitors (atom #{} "john" "doe"))

// To update a refernence, you must use a function such as swap!
// the form is as follows: 
// (swap! r update-fn & args) 
// where 'update-fn' applies to referenjce 'r', with optional 'args' if necessary. 
// below is an example of swapping the variable 'visitors'

// e.g. assume we have the following :
// #> (def visitors (atom #{} "john" "doe")
// #> (swap! visitors conj "foo")
// #> visitors // returns "foo"



