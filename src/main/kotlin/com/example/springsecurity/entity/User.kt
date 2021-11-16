package com.example.springsecurity.entity

import javax.persistence.*


@Entity
@Table(name = "user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0
    private val login: String? = null
    private val role: String? = null
}