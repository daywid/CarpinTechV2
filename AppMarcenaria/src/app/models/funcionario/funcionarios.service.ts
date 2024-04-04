import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Funcionario } from './Funcionario';

const httpOptions = {
headers: new HttpHeaders({
'Content-Type' : 'application/json'
})
}
@Injectable({
providedIn: 'root'
})
export class FuncionariosService {
apiUrl = 'http://localhost:5000/api/Funcionario';
constructor(private http: HttpClient) { }

listar(): Observable<Funcionario[]> {
  const url = `${this.apiUrl}/listar`;
  return this.http.get<Funcionario[]>(url);
}

buscar(id: number): Observable<Funcionario> {
  const url = `${this.apiUrl}/buscar/${id}`;
  return this.http.get<Funcionario>(url);
}

cadastrar(funcionario: Funcionario): Observable<any> {
  const url = `${this.apiUrl}/cadastrar`;
  return this.http.post<Funcionario>(url, funcionario, httpOptions);
}

atualizar(funcionario: Funcionario): Observable<any> {
  const url = `${this.apiUrl}/atualizar/${funcionario.id}`;
  return this.http.put<Funcionario>(url, funcionario, httpOptions);
}

excluir(id: number): Observable<any> {
  const url = `${this.apiUrl}/deletar/${id}`;
  return this.http.delete<string>(url, httpOptions);
}


}
