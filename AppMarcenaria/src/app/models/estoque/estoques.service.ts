import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Estoque } from './Estoque';
const httpOptions = {
headers: new HttpHeaders({
'Content-Type' : 'application/json'
})
}
@Injectable({
providedIn: 'root'
})
export class EstoquesService {
  apiUrl = 'http://localhost:5000/api/Estoque';
  constructor(private http: HttpClient) { }

  listar(): Observable<Estoque[]> {
    const url = `${this.apiUrl}/listar`;
    return this.http.get<Estoque[]>(url);
  }

  buscar(id: number): Observable<Estoque> {
    const url = `${this.apiUrl}/buscar/${id}`;
    return this.http.get<Estoque>(url);
  }

  cadastrar(estoque: Estoque): Observable<any> {
    const url = `${this.apiUrl}/cadastrar`;
    return this.http.post<Estoque>(url, estoque, httpOptions);
  }

  atualizar(estoque: Estoque): Observable<any> {
    const url = `${this.apiUrl}/atualizar/${estoque.id}`;
    return this.http.put<Estoque>(url, estoque, httpOptions);
  }

  excluir(id: number): Observable<any> {
    const url = `${this.apiUrl}/deletar/${id}`;
    return this.http.delete<string>(url, httpOptions);
  }


  }
