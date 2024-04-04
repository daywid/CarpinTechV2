import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ClientesService } from 'src/app/models/cliente/clientes.service';
import { Cliente } from 'src/app/models/cliente/Cliente';
import { Observer } from 'rxjs';


@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {
  formulario!: FormGroup;
  tituloFormulario: string = '';

  constructor(private clientesService: ClientesService) { }

  ngOnInit(): void {
    this.tituloFormulario = 'Novo Cliente';
    this.formulario = new FormGroup({
      id: new FormControl(null),
      nome: new FormControl(null),
      telefone: new FormControl(null),
      endereco: new FormControl(null)
    });
  }

  enviarFormulario(): void {
    const cliente: Cliente = this.formulario.value;
    this.clientesService.cadastrar(cliente).subscribe(result => {
      alert('Cliente inserido com sucesso.');
    });
  }
}

