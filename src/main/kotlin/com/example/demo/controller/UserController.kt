package com.example.demo.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.example.demo.model.User
import com.example.demo.repository.UserRepository

@RestController
@RequestMapping("/users")
class UserController(private val userRepository: UserRepository) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> {
        val users = userRepository.findAll()
        return ResponseEntity.ok(users)
    }

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userRepository.save(user)
        return ResponseEntity.ok(createdUser)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody user: User): ResponseEntity<User> {
        val existingUser = userRepository.findById(id)

        return if (existingUser.isPresent) {
            val updatedUser = existingUser.get().copy(name = user.name, email = user.email) // Ajusta según tus propiedades
            userRepository.save(updatedUser)
            ResponseEntity.ok(updatedUser)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<Map<String, String>> {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            ResponseEntity.ok(mapOf("message" to "User with ID $id has been deleted successfully"))
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
