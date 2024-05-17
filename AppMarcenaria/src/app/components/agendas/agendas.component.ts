import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AgendasService } from 'src/app/models/agenda/agendas.service';
import { Agenda } from 'src/app/models/agenda/Agenda';
import { Observer } from 'rxjs';

@Component({
  selector: 'app-agendas',
  templateUrl: './agendas.component.html',
  styleUrls: ['./agendas.component.css']
})
export class AgendasComponent implements OnInit {
  formulario!: FormGroup;
  formularioEdit !: FormGroup;
  tituloFormulario: string = '';
  minhaAgenda: any;
  agenda: any;
  agendasList: Agenda[] = [];

  agendaSelecionada: Agenda | null = null;

  constructor(private formBuilder: FormBuilder, private agendasService: AgendasService) { }

  ngOnInit(): void {
    this.tituloFormulario = 'Nova Agenda';
    this.formulario = this.formBuilder.group({
      descricao: [null],
      data: [null],
      tipo: [null],
      funcionarioId: [null],
    });
  }

  enviarFormulario(): void {
    const agenda: Agenda = this.formulario.value;
    const observer: Observer<Agenda> = {



      next(_result): void {
        alert('Agenda salva com sucesso.'); // Atualiza a lista após salvar
      },

      error(_error): void {
        alert('Erro ao salvar!');
      },
      complete(): void {
      },
    };

    if (agenda.id && !isNaN(Number(agenda.id))) {
      this.agendasService.atualizar(agenda).subscribe(observer);
      this.listarAgendas();
    } else {
      this.agendasService.cadastrar(agenda).subscribe(observer);
      this.listarAgendas();
    }
  }

  enviarFormularioEdit(): void {
    const agenda: Agenda = this.formularioEdit.value;
    const observer: Observer<Agenda> = {
      next(_result): void {
        alert('Agenda atualizda com sucesso.');
      },
      error(_error): void {
        alert('Erro ao atualizar!');
      },
      complete(): void {
      },
    };

    if (agenda.id && !isNaN(Number(agenda.id))) {
      this.agendasService.atualizar(agenda).subscribe(observer);
      this.listarAgendas();
    } else {
      this.agendasService.cadastrar(agenda).subscribe(observer);
      this.listarAgendas();
    }
  }

  listarAgendas(): void {
    this.agendasService.listar().subscribe(
      (agendas) => {
        this.agendasList = agendas;
      },
      (error) => {
        console.error('Erro ao listar agendas:', error);
      }
    );
  }

  editarAgenda(agenda: Agenda): void {
    this.agendaSelecionada = agenda;
    // Crie um novo FormGroup para o formulário de edição
    this.formularioEdit = this.formBuilder.group({
      id: [agenda.id],
      descricao: [agenda.descricao],
      data: [agenda.data],
      tipo: [agenda.tipo],
      funcionarioId: [agenda.funcionarioId],
    });
  }

  voltarParaLista(): void {
    this.formularioEdit.reset(); // Limpa o formulário de edição
    this.agendaSelecionada = null; // Remove a agenda selecionada
  }

  deletarAgenda(id: number): void {
    // Confirmar a exclusão antes de prosseguir
    const confirmarExclusao = confirm('Tem certeza que deseja excluir esta agenda?');

    if (confirmarExclusao) {
      // Chamar o serviço para deletar a agenda
      this.agendasService.excluir(id).subscribe(
        (_result) => {
          console.log('Agenda deletada com sucesso:', id);
          // Atualizar a lista após a exclusão
          this.listarAgendas();
        },
        (error) => {
          console.error('Erro ao deletar agenda:', error);
        }
      );
    }
  }

}
