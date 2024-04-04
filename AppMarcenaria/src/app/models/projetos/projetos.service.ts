import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Projeto } from './Projeto';
const httpOptions = {
headers: new HttpHeaders({
'Content-Type' : 'application/json'
})
}
@Injectable({
providedIn: 'root'
})
export class ProjetosService {
  apiUrl = 'http://localhost:5000/api/Projeto';
  constructor(private http: HttpClient) { }

  listar(): Observable<Projeto[]> {
    const url = `${this.apiUrl}/listar`;
    return this.http.get<Projeto[]>(url);
  }

  buscar(id: number): Observable<Projeto> {
    const url = `${this.apiUrl}/buscar/${id}`;
    return this.http.get<Projeto>(url);
  }

  cadastrar(projeto: Projeto): Observable<any> {
    console.log(JSON.stringify(projeto));
    const url = `${this.apiUrl}/cadastrar`;
    return this.http.post<Projeto>(url, projeto, httpOptions);
  }

  atualizar(projeto: Projeto): Observable<any> {
    const url = `${this.apiUrl}/atualizar/${projeto.id}`;
    return this.http.put<Projeto>(url, projeto, httpOptions);
  }

  excluir(id: number): Observable<any> {
    const url = `${this.apiUrl}/deletar/${id}`;
    return this.http.delete<string>(url, httpOptions);
  }


  }
