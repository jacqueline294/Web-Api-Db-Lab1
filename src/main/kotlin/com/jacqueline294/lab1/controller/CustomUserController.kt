package com.jacqueline294.lab1.controller

import com.jacqueline294.lab1.model.CustomUser
import com.jacqueline294.lab1.repository.CustomUserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class CustomUserController(
    private val customUserRepository: CustomUserRepository
) {
    @GetMapping
    fun getAllUsers(): List<CustomUser> = customUserRepository.findAll()


    @GetMapping
    fun getUserByUserId(@RequestParam("id") id: Long): ResponseEntity<CustomUser> {
        val foundUser = customUserRepository.findById(id)
        return foundUser.map { user -> ResponseEntity.ok(user) }
            .orElse(ResponseEntity.notFound().build())
    }


    @PostMapping
    fun postNewUser(@RequestBody customUser: CustomUser): ResponseEntity<String> {
        customUserRepository.save(customUser)
        return ResponseEntity.status(201).body("User Created Successfully")
    }


    @PutMapping("/{id}")
    fun putUser(
        @PathVariable("id") id: Long,
        @RequestBody updatedUser: CustomUser
    ): ResponseEntity<String> {
        val existingUser = customUserRepository.findById(id)

        return if (existingUser.isPresent) {
            val userToSave = existingUser.get().copy(
                username = updatedUser.username,
                password = updatedUser.password,
                isEnabled = updatedUser.isEnabled
            )
            customUserRepository.save(userToSave)
            ResponseEntity.ok("User Updated Successfully")
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Long): ResponseEntity<String> {
        return if (customUserRepository.existsById(id)) {
            customUserRepository.deleteById(id)
            ResponseEntity.ok("User Deleted Successfully")
        } else {
            ResponseEntity.notFound().build()
        }
    }
}



