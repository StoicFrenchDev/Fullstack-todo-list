import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TodoList } from '../models/todo-list.model';


const baseUrl = 'http://localhost:8080/api/todo-list';

@Injectable({
  providedIn: 'root'
})
export class TodoListService {

  constructor(private http: HttpClient) { }

  getAllTasks(): Observable<TodoList[]> {
    return this.http.get<TodoList[]>(baseUrl);
  }

  getTaskById(id: any): Observable<TodoList> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  createTask(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  updateTask(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  deleteTask(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  findByTitle(title: any): Observable<TodoList[]> {
    return this.http.get<TodoList[]>(`${baseUrl}?title=${title}`);
  }

}
