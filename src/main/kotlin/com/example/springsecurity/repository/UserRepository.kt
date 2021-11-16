package com.example.springsecurity.repository

import com.example.springsecurity.entity.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<User?, Long?> {

    @Query("SELECT l FROM User l WHERE l.login = :login")
    fun getUserByUsername(@Param("login") login: String?): User?
}