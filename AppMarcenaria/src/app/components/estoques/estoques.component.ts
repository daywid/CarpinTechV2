import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { EstoquesService } from 'src/app/models/estoque/estoques.service';
import { Estoque } from 'src/app/models/estoque/Estoque';

@Component({
  selector: 'app-estoques',
  templateUrl: './estoques.component.html',
  styleUrls: ['./estoques.component.css']
})
export class EstoquesComponent implements OnInit {
  formulario!: FormGroup;
  formularioEdit !: FormGroup;
  tituloFormulario: string = '';
  estoqueSelecionado: Estoque | null = null;
  estoquesList: Estoque[] = [];

  constructor(private formBuilder: FormBuilder, private estoquesService: EstoquesService) { }

  ngOnInit(): void {
    this.tituloFormulario = 'Novo Estoque';
    this.formulario = this.formBuilder.group({
      id: [null],
      materiais: [null],
      quantidade: [null],
    });
    this.formularioEdit = this.formBuilder.group({
      id: [null],
      materiais: [null],
      quantidade: [null],
    });

    this.listarEstoques();
  }

  enviarFormulario(): void {
    const estoque: Estoque = this.formulario.value;

    this.estoquesService.cadastrar(estoque).subscribe(
      () => {
        alert('Estoque cadastrado com sucesso.');
        this.listarEstoques();
      },
      (error) => alert('Erro ao cadastrar o estoque...')
    );
  }

  salvarEdicao(): void {
    const estoque: Estoque = this.formularioEdit.value;

    this.estoquesService.atualizar(estoque).subscribe(
      () => {
        alert('Estoque atualizado com sucesso.');
        this.listarEstoques();
      },
      () => alert('Erro ao atualizar o estoque!')
    );
  }

  listarEstoques(): void {
    this.estoquesService.listar().subscribe(
      (estoques) => this.estoquesList = estoques,
      () => console.error('Erro ao listar estoques')
    );
  }

  editarEstoque(estoque: Estoque): void {
    this.estoqueSelecionado = estoque;
    this.formularioEdit.patchValue(estoque);
  }

  excluirEstoque(id: number): void {
    if (confirm('Tem certeza que deseja excluir este estoque?')) {
      this.estoquesService.excluir(id).subscribe(
        () => {
          console.log('Estoque excluído com sucesso:', id);
          this.listarEstoques();
        },
        () => console.error('Erro ao excluir o estoque')
      );
    }
  }

  limparFormulario(): void {
    this.formulario.reset();
    this.estoqueSelecionado = null;
  }

  voltarParaLista(): void {
    this.formulario.reset();
    this.estoqueSelecionado = null; 
  }
}
