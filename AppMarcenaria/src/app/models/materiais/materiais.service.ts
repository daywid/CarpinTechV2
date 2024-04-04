import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Material } from './Material';
const httpOptions = {
headers: new HttpHeaders({
'Content-Type' : 'application/json'
})
}
@Injectable({
providedIn: 'root'
})
export class MateriaisService {
  apiUrl = 'http://localhost:5000/api/Material';
  constructor(private http: HttpClient) { }

  listar(): Observable<Material[]> {
    const url = `${this.apiUrl}/listar`;
    return this.http.get<Material[]>(url);
  }

  buscar(id: number): Observable<Material> {
    const url = `${this.apiUrl}/buscar/${id}`;
    return this.http.get<Material>(url);
  }

  cadastrar(material: Material): Observable<any> {
    const url = `${this.apiUrl}/cadastrar`;
    return this.http.post<Material>(url, material, httpOptions);
  }

  atualizar(material: Material): Observable<any> {
    const url = `${this.apiUrl}/atualizar/${material.id}`;
    return this.http.put<Material>(url, material, httpOptions);
  }

  excluir(id: number): Observable<any> {
    const url = `${this.apiUrl}/deletar/${id}`;
    return this.http.delete<string>(url, httpOptions);
  }


  }
