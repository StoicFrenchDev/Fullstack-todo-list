package Ben.workshop.crudbackend.service;

import Ben.workshop.crudbackend.model.TodoList;
import java.util.List;

public interface TodoListService {
    List<TodoList> findAllTasks(String title);
    TodoList findTaskById(long id);
    TodoList createTask(TodoList todoList);
    TodoList updateTask(long id, TodoList todoList);
    void deleteTask(long id);
}