package Ben.workshop.crudbackend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Ben.workshop.crudbackend.model.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    
    List<TodoList> findByTitleContaining(String title);

}
