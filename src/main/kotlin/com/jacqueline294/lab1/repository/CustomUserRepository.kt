package com.jacqueline294.lab1.repository

import com.jacqueline294.lab1.model.CustomUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


interface CustomUserRepository: JpaRepository<CustomUser, Long> {

}