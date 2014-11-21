// define a record named Person 
// with two fields: first name and last name
(defrecord Person [firstname lastname])

// here is how to use it
Person{:firstname "Raymond", :lastname "Tay")
// or
Person{"Raymond", "Tay"}

