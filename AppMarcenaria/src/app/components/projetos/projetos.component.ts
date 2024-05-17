import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ProjetosService } from 'src/app/models/projetos/projetos.service';
import { Projeto } from 'src/app/models/projetos/Projeto';

@Component({
  selector: 'app-projetos',
  templateUrl: './projetos.component.html',
  styleUrls: ['./projetos.component.css']
})
export class ProjetosComponent implements OnInit {
  formulario!: FormGroup;
  formularioEdit!: FormGroup;
  tituloFormulario: string = '';
  projetoSelecionado: Projeto | null = null;
  projetosList: Projeto[] = [];

  constructor(private formBuilder: FormBuilder, private projetosService: ProjetosService) { }

  ngOnInit(): void {
    this.tituloFormulario = 'Novo Projeto';
    this.formulario = this.formBuilder.group({
      id: new FormControl(null),
      nome: new FormControl(null),
      desc: new FormControl(null),
      valor: new FormControl(null),
      status: new FormControl(null),
      dataCadastro: new FormControl(null, Validators.required),
      dataPrazo: new FormControl(null),
      dataFinalizacao: new FormControl(null),
    }, { validators: this.dataValidator });

    this.formularioEdit = this.formBuilder.group({
      id: new FormControl(null),
      nome: new FormControl(null),
      desc: new FormControl(null),
      valor: new FormControl(null),
      status: new FormControl(null),
      dataCadastro: new FormControl(null, Validators.required),
      dataPrazo: new FormControl(null),
      dataFinalizacao: new FormControl(null),
    }, { validators: this.dataValidator });

    this.listarProjetos();
  }

  dataValidator(group: FormGroup): { [key: string]: boolean } | null {
    const dataCadastro = group.get('dataCadastro')?.value;
    const dataFinalizacao = group.get('dataFinalizacao')?.value;
    return dataCadastro && dataFinalizacao && dataFinalizacao < dataCadastro ? { dataInvalida: true } : null;
  }

  enviarFormulario(): void {
    if (this.formulario.invalid) return;
    const projeto: Projeto = this.formulario.value;
    projeto.status = Math.floor(projeto.status);

    this.projetosService.cadastrar(projeto).subscribe(
      () => {
        alert('Projeto cadastrado com sucesso.');
        this.listarProjetos();
      },
      (error) => alert('Erro ao cadastrar o projeto...')
    );
  }

  salvarEdicao(): void {
    if (this.formularioEdit.invalid) return;
    const projeto: Projeto = this.formularioEdit.value;
    projeto.status = Math.floor(projeto.status);
    console.log(JSON.stringify(projeto));

    this.projetosService.atualizar(projeto).subscribe(
      () => {
        alert('Projeto atualizado com sucesso.');
        this.listarProjetos();
      },
      () => alert('Erro ao atualizar o projeto!')
    );
  }

  listarProjetos(): void {
    this.projetosService.listar().subscribe(
      (projetos) => this.projetosList = projetos,
      () => console.error('Erro ao listar projetos')
    );
  }

  editarProjeto(projeto: Projeto): void {
    this.projetoSelecionado = projeto;
    this.formularioEdit.patchValue(projeto);
  }

  excluirProjeto(id: number): void {
    if (confirm('Tem certeza que deseja excluir este projeto?')) {
      this.projetosService.excluir(id).subscribe(
        () => {
          console.log('Projeto excluído com sucesso:', id);
          this.listarProjetos();
        },
        () => console.error('Erro ao excluir o projeto')
      );
    }
  }

  limparFormulario(): void {
    this.formulario.reset();
    this.projetoSelecionado = null;
  }

  voltarParaLista(): void {
    this.formulario.reset();
    this.projetoSelecionado = null;
  }
}
