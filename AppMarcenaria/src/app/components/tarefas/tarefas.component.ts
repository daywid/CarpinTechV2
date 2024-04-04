import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Projeto } from 'src/app/models/projetos/Projeto';
import { ProjetosService } from 'src/app/models/projetos/projetos.service';
import { Material } from 'src/app/models/materiais/Material';
import { MateriaisService } from 'src/app/models/materiais/materiais.service';
import { TarefasService } from 'src/app/models/tarefas/tarefas.service';
import { Tarefa } from 'src/app/models/tarefas/Tarefa';

@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.component.html',
  styleUrls: ['./tarefas.component.css']
})
export class TarefasComponent implements OnInit {
  formulario!: FormGroup;
  projetos: Array<Projeto> | undefined;
  materiais: Array<Material> | null | undefined;
  formularioEdit !: FormGroup;
  tituloFormulario: string = '';
  tarefaSelecionado: Tarefa | null = null;
  tarefasList: Tarefa[] = [];

  constructor(private formBuilder: FormBuilder, private tarefasService: TarefasService, private projetosService: ProjetosService, private materiaisService: MateriaisService) { }

  ngOnInit(): void {
    this.tituloFormulario = 'Novo Tarefa';
    this.projetosService.listar().subscribe(projetos => {
      this.projetos = projetos;
      if (this.projetos && this.projetos.length > 0){
        this.formulario.get('projetoId')?.setValue(this.projetos[0].id)
      }

    })
    this.materiaisService.listar().subscribe(materiais => {
      this.materiais = materiais;
      if (this.projetos && this.projetos.length > 0){
        this.formulario.get('materiais')?.setValue(this.projetos[0].id)
      }

    })
    this.formulario = new FormGroup({
      id: new FormControl(null),
      nome: new FormControl(null),
      desc: new FormControl(null),
      status: new FormControl(null),
      dataInicio: new FormControl(null),
      dataFinalizacao: new FormControl(null),
      projetoId: new FormControl(null),
      projeto: new FormControl(null),
      materiais: new FormControl(null)
    });

    this.formularioEdit = new FormGroup({
      id: new FormControl(null),
      nome: new FormControl(null),
      desc: new FormControl(null),
      status: new FormControl(null),
      dataInicio: new FormControl(null),
      dataFinalizacao: new FormControl(null),
      projetoId: new FormControl(null),
      projeto: new FormControl(null),
      materiais: new FormControl(null)
    });

    this.listarTarefas();
  }

  enviarFormulario(): void {
    const tarefa: Tarefa = this.formulario.value;
    tarefa.status     = Math.floor(tarefa.status)

    this.tarefasService.cadastrar(tarefa).subscribe(
      () => {
        alert('Tarefa cadastrado com sucesso.');
        this.listarTarefas();
      },
      (error) => alert('Erro ao cadastrar o tarefa...')
    );
  }

  salvarEdicao(): void {
    const tarefa: Tarefa = this.formularioEdit.value;
    tarefa.status = Math.floor(tarefa.status)
    console.log(JSON.stringify(tarefa))

    this.tarefasService.atualizar(tarefa).subscribe(
      () => {
        alert('Tarefa atualizado com sucesso.');
        this.listarTarefas();
      },
      () => alert('Erro ao atualizar o tarefa!')
    );
  }

  listarTarefas(): void {
    this.tarefasService.listar().subscribe(
      (tarefas) => this.tarefasList = tarefas,
      () => console.error('Erro ao listar tarefas')
    );
  }

  editarTarefa(tarefa: Tarefa): void {
    this.tarefaSelecionado = tarefa;
    this.formularioEdit.patchValue(tarefa);
  }

  excluirTarefa(id: number): void {
    if (confirm('Tem certeza que deseja excluir este tarefa?')) {
      this.tarefasService.excluir(id).subscribe(
        () => {
          console.log('Tarefa excluído com sucesso:', id);
          this.listarTarefas();
        },
        () => console.error('Erro ao excluir o tarefa')
      );
    }
  }

  limparFormulario(): void {
    this.formulario.reset();
    this.tarefaSelecionado = null;
  }

  voltarParaLista(): void {
    this.formulario.reset();
    this.tarefaSelecionado = null; 
  }
}
