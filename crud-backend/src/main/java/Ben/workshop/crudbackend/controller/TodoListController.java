package Ben.workshop.crudbackend.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Ben.workshop.crudbackend.model.TodoList;
import Ben.workshop.crudbackend.service.TodoListService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TodoListController {
    
    @Autowired
    private TodoListService todoListService;

    //show full list
    @GetMapping("/todo-list")
    public ResponseEntity<List<TodoList>> getAllTasks(@RequestParam(required = false) String title) {
        List<TodoList> tasks = todoListService.findAllTasks(title);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    //show one task
    @GetMapping("/todo-list/{id}")
    public ResponseEntity<TodoList> getTaskById(@PathVariable("id") long id) {
        try {
            TodoList task = todoListService.findTaskById(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //add a task
    @PostMapping("/todo-list")
    public ResponseEntity<TodoList> createTask(@RequestBody TodoList todoList) {
        TodoList newTask = todoListService.createTask(todoList);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    //update a task
    @PutMapping("/todo-list/{id}")
    public ResponseEntity<TodoList> updateTask(@PathVariable("id") long id, @RequestBody TodoList todoList) {
        try {
            TodoList updatedTask = todoListService.updateTask(id, todoList);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //delete a task
    @DeleteMapping("/todo-list/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
        try {
            todoListService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}