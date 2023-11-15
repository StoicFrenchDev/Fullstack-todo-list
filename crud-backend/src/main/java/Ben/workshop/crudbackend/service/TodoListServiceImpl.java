package Ben.workshop.crudbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ben.workshop.crudbackend.model.TodoList;
import Ben.workshop.crudbackend.repository.TodoListRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoListServiceImpl implements TodoListService {
    
    @Autowired
    private TodoListRepository todoListRepository;

    @Override
    public List<TodoList> findAllTasks(String title) {
        if (title != null && !title.isEmpty()) {
            return todoListRepository.findByTitleContaining(title);
        } else {
            return todoListRepository.findAll();
        }
    }

    @Override
    public TodoList findTaskById(long id) {
        return todoListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + id));
    }

    @Override
    public TodoList createTask(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Override
    public TodoList updateTask(long id, TodoList todoList) {
        return todoListRepository.findById(id)
                .map(existingTask -> {
                    // Update the existing task with the provided details
                    // This is where you set the properties from todoList to existingTask
                    existingTask.setTitle(todoList.getTitle());
                    existingTask.setDescription(todoList.getDescription());
                    existingTask.setPriority(todoList.getPriority());
    
                    // Save and return the updated task
                    return todoListRepository.save(existingTask);
                })
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + id));
    }

    @Override
    public void deleteTask(long id) {
        if (!todoListRepository.existsById(id)) {
            throw new NoSuchElementException("Task not found with id: " + id);
        }
        todoListRepository.deleteById(id);
    }
}