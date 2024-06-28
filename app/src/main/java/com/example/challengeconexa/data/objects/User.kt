package com.example.challengeconexa.data.objects

class User {
    val id: Int? = null
    val firstname: String? = null
    val lastname: String? = null
    val email: String? = null
    val birthDate: String?= null
    val login: Login?= null
    val address: Address?= null
    val phone: String?= null
    val website: String?= null
    val company: Company?= null

    inner class Login {
        val uuid: String?= null
        val username: String?= null
        val password: String?= null
        val md5: String?= null
        val sha1: String?= null
        val registered: String?= null
    }

    inner class Address {
        val street: String?= null
        val suite: String?= null
        val city: String?= null
        val zipcode: String?= null
        val geo: Geo?= null

        inner class Geo {
            val lat: String?= null
            val lng: String?= null
        }
    }

    inner class Company {
        val name: String?= null
        val catchPhrase: String?= null
        val bs: String?= null
    }
}