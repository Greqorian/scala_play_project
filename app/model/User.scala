package model

case class User(
    id: Int,
    name: String,
    surname: String,
    email: String,
    password: String,
    street: String,
    city: String
    // phone: Int,
    // birthdate: Int
)
