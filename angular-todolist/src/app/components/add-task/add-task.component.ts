import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { TodoList } from 'src/app/models/todo-list.model';
import { TodoListService } from 'src/app/services/todo-list.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {
  
  taskForm = this.fb.group({
    title: ['', Validators.required],
    description: [''],
    priority: [null, Validators.required]
  });
  submitted = false;

  constructor(private fb: FormBuilder, private todoListService: TodoListService) {}

  ngOnInit(): void {
      
  }

  onSubmit() {
    if (this.taskForm.valid) {
      const taskData = this.taskForm.value;
      
      this.todoListService.createTask(taskData).subscribe({
        next: (res) => {
          console.log('Task created successfully:', res);
          this.submitted = true;
          this.taskForm.reset({ 
            title: '',
            description: '',
            priority: null  
          });
        },
        error: (err) => {
          console.error('Error creating task:', err);
          this.submitted = false;
        }
      });
    } else {
      
      this.taskForm.markAllAsTouched(); 
    }
  }

  newTask(): void {
    this.submitted = false; // Reset submitted flag for new task
    this.taskForm.reset({
      title: '',
      description: '',
      priority: null  
    });
  }
  
}
