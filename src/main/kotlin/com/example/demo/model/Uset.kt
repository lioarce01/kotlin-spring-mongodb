package com.example.demo.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users") //clase que representa un documento en MongoDB
data class User(
    @Id 
    val id: String? = null, //generado por mongodb

    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    val name: String,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email should be valid")
    val email: String,

    @field:NotBlank(message = "Password cannot be blank")
    @field:Size(min = 6, message = "Password must be at least 6 characters")
    val password: String
)