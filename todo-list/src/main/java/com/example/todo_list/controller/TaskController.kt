package com.example.todo_list.controller

import com.example.todo_list.model.Task
import com.example.todo_list.service.TaskService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController(private val taskService: TaskService) {

    @PostMapping
    fun createTask(@Valid @RequestBody task: Task): ResponseEntity<Task> {
        val savedTask = taskService.saveTask(task)
        return ResponseEntity(savedTask, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllTasks(): List<Task> = taskService.findAllTasks()

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {
        return taskService.findTaskById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @Valid @RequestBody taskDetails: Task): ResponseEntity<Task> {
        return taskService.findTaskById(id)
            .map { existingTask ->
                existingTask.description = taskDetails.description
                existingTask.completed = taskDetails.completed

                val updatedTask = taskService.saveTask(existingTask)
                ResponseEntity.ok(updatedTask)
            }
            .orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Void> {
        return taskService.findTaskById(id)
            .map {
                taskService.deleteTask(id)
                ResponseEntity.noContent().build<Void>()
            }
            .orElse(ResponseEntity.notFound().build())
    }
}