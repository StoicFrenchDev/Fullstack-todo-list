import { Component, Input, OnInit } from '@angular/core';
import { TodoList } from 'src/app/models/todo-list.model';
import { TodoListService } from 'src/app/services/todo-list.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {

  @Input() viewMode = false;

  @Input() currentTask: TodoList = {
    title: '',
    description: '',
    priority: undefined
  };
  message = '';

  constructor(
    private todoListService: TodoListService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getTask(this.route.snapshot.params["id"]);
    }
  }

  getTask(id: string): void {
    this.todoListService.getTaskById(id)
      .subscribe({
        next: (data) => {
          this.currentTask = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateTask(): void {
    this.message = '';

    this.todoListService.updateTask(this.currentTask.id, this.currentTask)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This task was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteTask(): void {
    this.message = '';

    this.todoListService.deleteTask(this.currentTask.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/todo-list']);
          this.message = res.message ? res.message : 'This task was deleted successfully!';
        },
        error: (e) => console.error(e)
      });
  }

}
