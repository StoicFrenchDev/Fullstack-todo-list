import { Component, OnInit } from '@angular/core';
import { TodoList } from 'src/app/models/todo-list.model';
import { TodoListService } from 'src/app/services/todo-list.service';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit{

  todoList?: TodoList[];
  currentTask: TodoList = {};
  currentIndex = -1;
  title = '';

  constructor(private todoListService: TodoListService) { }

  ngOnInit(): void {
    this.retrieveTasks();
  }

  retrieveTasks(): void {
    this.todoListService.getAllTasks()
      .subscribe({
        next: (data) => {
          this.todoList = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  setActiveTask(todoList: TodoList, index: number): void {
    this.currentTask = todoList;
    this.currentIndex = index;
  }
}
