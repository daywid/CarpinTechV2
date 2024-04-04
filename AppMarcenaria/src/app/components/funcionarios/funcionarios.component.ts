import { Funcionario } from './../../models/funcionario/Funcionario';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observer } from 'rxjs';
import { FuncionariosService } from 'src/app/models/funcionario/funcionarios.service';

@Component({
  selector: 'app-funcionarios',
  templateUrl: './funcionarios.component.html',
  styleUrls: ['./funcionarios.component.css'],
})
export class FuncionariosComponent implements OnInit {
  formulario!: FormGroup;
  formularioEdit!: FormGroup;
  tituloFormulario: string = '';
  meuFuncionario: any;
  funcionario: any;
  funcionariosList: Funcionario[] = [];
  funcionarioSelecionado: Funcionario | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private funcionariosService: FuncionariosService
  ) {}

  ngOnInit(): void {
    this.tituloFormulario = 'Novo Funcionario';
    this.formulario = this.formBuilder.group({
      nome: [null],
      funcao: [null],
      salario: [null],
      horasTrabalhadas: [null],
    });
  }

  enviarFormulario(): void {
    const funcionario: Funcionario = this.formulario.value;
    const observer: Observer<Funcionario> = {
      next(_result): void {
        alert('Funcionario salvo com sucesso.');
      },
      error(_error): void {
        alert('Erro ao salvar!');
      },
      complete(): void {},
    };

    if (funcionario.id && !isNaN(Number(funcionario.id))) {
      this.funcionariosService.atualizar(funcionario).subscribe(observer);
      this.listarFuncionarios();
    } else {
      this.funcionariosService.cadastrar(funcionario).subscribe(observer);
    }
    this.listarFuncionarios();
  }

  enviarFormularioEdit(): void {
    const funcionario: Funcionario = this.formularioEdit.value;
    const observer: Observer<Funcionario> = {
      next(_result): void {
        alert('Funcionário atualizado com sucesso.');
      },
      error(_error): void {
        alert('Erro ao atualizar!');
      },
      complete(): void {},
    };

    if (funcionario.id && !isNaN(Number(funcionario.id))) {
      this.funcionariosService.atualizar(funcionario).subscribe(observer);
      this.listarFuncionarios();
    } else {
      this.funcionariosService.cadastrar(funcionario).subscribe(observer);
      this.listarFuncionarios();
    }
  }

  listarFuncionarios(): void {
    this.funcionariosService.listar().subscribe(
      (funcionarios) => {
        this.funcionariosList = funcionarios;
      },
      (error) => {
        console.error('Erro ao listar funcionarios:', error);
      }
    );
  }

  editarFuncionario(funcionario: Funcionario): void {
    this.funcionarioSelecionado = funcionario;
    // Crie um novo FormGroup para o formulário de edição
    this.formularioEdit = this.formBuilder.group({
      id: [funcionario.id],
      nome: [funcionario.nome],
      funcao: [funcionario.funcao],
      salario: [funcionario.salario],
      horasTrabalhadas: [funcionario.horasTrabalhadas],
    });
  }

  voltarParaLista(): void {
    this.formularioEdit.reset(); // Limpa o formulário de edição
    this.funcionarioSelecionado = null; // Remove o funcionario selecionado
  }

  deletarFuncionario(id: number): void {
    // Confirmar a exclusão antes de prosseguir
    const confirmarExclusao = confirm(
      'Tem certeza que deseja excluir este funcionário?'
    );

    if (confirmarExclusao) {
      // Chamar o serviço para deletar o funcionario
      this.funcionariosService.excluir(id).subscribe(
        (_result) => {
          console.log('Funcionario deletado com sucesso:', id);
          // Atualizar a lista após a exclusão
          this.listarFuncionarios();
        },
        (error) => {
          console.error('Erro ao deletar funcionario:', error);
        }
      );
    }
  }
}
