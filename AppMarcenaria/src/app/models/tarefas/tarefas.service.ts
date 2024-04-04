import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tarefa } from './Tarefa';
const httpOptions = {
headers: new HttpHeaders({
'Content-Type' : 'application/json'
})
}
@Injectable({
providedIn: 'root'
})
export class TarefasService {
  apiUrl = 'http://localhost:5000/api/Tarefa';
  constructor(private http: HttpClient) { }

  listar(): Observable<Tarefa[]> {
    const url = `${this.apiUrl}/listar`;
    return this.http.get<Tarefa[]>(url);
  }

  buscar(id: number): Observable<Tarefa> {
    const url = `${this.apiUrl}/buscar/${id}`;
    return this.http.get<Tarefa>(url);
  }

  cadastrar(tarefa: Tarefa): Observable<any> {
    console.log(JSON.stringify(tarefa));
    const url = `${this.apiUrl}/cadastrar`;
    return this.http.post<Tarefa>(url, tarefa, httpOptions);
  }

  atualizar(tarefa: Tarefa): Observable<any> {
    const url = `${this.apiUrl}/atualizar/${tarefa.id}`;
    return this.http.put<Tarefa>(url, tarefa, httpOptions);
  }

  excluir(id: number): Observable<any> {
    const url = `${this.apiUrl}/deletar/${id}`;
    return this.http.delete<string>(url, httpOptions);
  }


  }
