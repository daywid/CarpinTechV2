import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { FornecedoresService } from 'src/app/models/fornecedor/fornecedores.service';
import { Fornecedor } from 'src/app/models/fornecedor/fornecedor';
import { Observer } from 'rxjs';

@Component({
  selector: 'app-fornecedores',
  templateUrl: './fornecedores.component.html',
  styleUrls: ['./fornecedores.component.css']
})
export class FornecedoresComponent implements OnInit {
  formulario!: FormGroup;
  formularioEdit !: FormGroup;
  tituloFormulario: string = '';
  meuFornecedor: any;
  fornecedor: any;
  fornecedoresList: Fornecedor[] = [];

  fornecedorSelecionado: Fornecedor | null = null;

  constructor(private formBuilder: FormBuilder, private fornecedoresService: FornecedoresService) { }

  ngOnInit(): void {
    this.tituloFormulario = 'Novo Fornecedor';
    this.formulario = this.formBuilder.group({
      nome: [null],
      email: [null],
      telefone: [null],
      endereco: [null],
    });
  }

  enviarFormulario(): void {
    const fornecedor: Fornecedor = this.formulario.value;
    const observer: Observer<Fornecedor> = {
      next(_result): void {
        alert('Fornecedor salvo com sucesso.'); // Atualiza a lista após salvar
      },
      error(_error): void {
        alert('Erro ao salvar!');
      },
      complete(): void {
      },
    };

    if (fornecedor.id && !isNaN(Number(fornecedor.id))) {
      this.fornecedoresService.atualizar(fornecedor).subscribe(observer);
      this.listarFornecedores();
    } else {
      this.fornecedoresService.cadastrar(fornecedor).subscribe(observer);
      this.listarFornecedores();
    }
  }

  enviarFormularioEdit(): void {
    const fornecedor: Fornecedor = this.formularioEdit.value;
    const observer: Observer<Fornecedor> = {
      next(_result): void {
        alert('Fornecedor atualizado com sucesso.');
      },
      error(_error): void {
        alert('Erro ao atualizar!');
      },
      complete(): void {
      },
    };

    if (fornecedor.id && !isNaN(Number(fornecedor.id))) {
      this.fornecedoresService.atualizar(fornecedor).subscribe(observer);
      this.listarFornecedores();
    } else {
      this.fornecedoresService.cadastrar(fornecedor).subscribe(observer);
      this.listarFornecedores();
    }
  }

  listarFornecedores(): void {
    this.fornecedoresService.listar().subscribe(
      (fornecedores) => {
        this.fornecedoresList = fornecedores;
      },
      (error) => {
        console.error('Erro ao listar fornecedores:', error);
      }
    );
  }

  editarFornecedor(fornecedor: Fornecedor): void {
    this.fornecedorSelecionado = fornecedor;
    // Crie um novo FormGroup para o formulário de edição
    this.formularioEdit = this.formBuilder.group({
      id: [fornecedor.id],
      nome: [fornecedor.nome],
      email: [fornecedor.email],
      telefone: [fornecedor.telefone],
      endereco: [fornecedor.endereco],
    });
  }

  voltarParaLista(): void {
    this.formularioEdit.reset(); // Limpa o formulário de edição
    this.fornecedorSelecionado = null; // Remove o fornecedor selecionado
  }

  deletarFornecedor(id: number): void {
    // Confirmar a exclusão antes de prosseguir
    const confirmarExclusao = confirm('Tem certeza que deseja excluir este fornecedor?');

    if (confirmarExclusao) {
      // Chamar o serviço para deletar o fornecedor
      this.fornecedoresService.excluir(id).subscribe(
        (_result) => {
          console.log('Fornecedor deletado com sucesso:', id);
          // Atualizar a lista após a exclusão
          this.listarFornecedores();
        },
        (error) => {
          console.error('Erro ao deletar fornecedor:', error);
        }
      );
    }
  }

}
