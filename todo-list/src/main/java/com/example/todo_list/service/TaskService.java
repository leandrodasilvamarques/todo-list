package com.example.todo_list.service;

import com.example.todo_list.model.Task;
import com.example.todo_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        taskRepository.save(task);
        return task;
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

}
