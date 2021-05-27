package model

case class User(
    key: Int,
    name: String,
    surname: String,
    email: String,
    password: String,
    street: String,
    city: String,
    phone: Int,
    birthdate: Int
)
