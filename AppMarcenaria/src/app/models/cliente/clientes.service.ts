import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from './Cliente';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ClientesService {
  apiUrl = 'http://localhost:5000/Cliente';

  constructor(private http: HttpClient) { }

  listar(): Observable<Cliente[]> {
    const url = `${this.apiUrl}/listar`;
    return this.http.get<Cliente[]>(url);
  }

  buscar(id: number): Observable<Cliente> {
    const url = `${this.apiUrl}/buscar/${id}`;
    return this.http.get<Cliente>(url);
  }

  cadastrar(cliente: Cliente): Observable<any> {
    const url = `${this.apiUrl}/cadastrar`;
    return this.http.post<Cliente>(url, cliente, httpOptions);
  }

  atualizar(cliente: Cliente): Observable<any> {
    const url = `${this.apiUrl}/atualizar/${cliente.Id}`;
    return this.http.put<Cliente>(url, cliente, httpOptions);
  }

  excluir(id: number): Observable<any> {
    const url = `${this.apiUrl}/excluir/${id}`;
    return this.http.delete<string>(url, httpOptions);
  }
}
