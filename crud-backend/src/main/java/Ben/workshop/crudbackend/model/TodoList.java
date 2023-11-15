package Ben.workshop.crudbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todoList")
public class TodoList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;
    
    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    private String priority;

    public TodoList() {

    }

    public TodoList(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String isPriority() {
        return this.priority;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
