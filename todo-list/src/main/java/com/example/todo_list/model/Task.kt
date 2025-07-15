package com.example.todo_list.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
data class Task(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:NotBlank(message = "A descrição da tarefa é obrigatoria.")
    @field:Size(min = 3, message = "A descrição deve ter no minimo 3 caracteres.")
    var description: String,

    var completed: Boolean = false
)
