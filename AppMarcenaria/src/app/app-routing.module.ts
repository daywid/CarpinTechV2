import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AgendasComponent } from './components/agendas/agendas.component';
import { FuncionariosComponent } from './components/funcionarios/funcionarios.component';

// import { ClientesComponent } from './components/clientes/clientes.component';
// // import { FornecedoresComponent } from './components/fornecedores/fornecedores.component';

// ADICIONANDO COMPONENTES DO ESTOQUE, MATERIAL, PROJETO E TAREFA
import { EstoquesComponent } from './components/estoques/estoques.component';
import { MateriaisComponent } from './components/materiais/materiais.component';
import { ProjetosComponent } from './components/projetos/projetos.component';
import { TarefasComponent } from './components/tarefas/tarefas.component';



const routes: Routes = [
  {path: 'agendas', component:AgendasComponent},
  {path: 'funcionarios', component:FuncionariosComponent},

  // {path:'fornecedor', component:FornecedoresComponent},
  // {path:'cliente', component:ClientesComponent},

  // ADICIONANDO ROTAS DE ESTOQUE, MATERIAL, PROJETO E TAREFA
  {path: 'estoque', component:EstoquesComponent},
  {path: 'materiais', component:MateriaisComponent},
  {path: 'projetos', component:ProjetosComponent},
  {path: 'tarefas', component:TarefasComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
