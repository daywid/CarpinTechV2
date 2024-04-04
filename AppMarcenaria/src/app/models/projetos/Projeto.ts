import { Tarefa } from '../tarefas/Tarefa';

export interface Projeto {
    id: number;
    nome: string;
    desc: string;
    valor: number;
    tarefas?: Tarefa[];
    status: number;
    dataCadastro: Date;
    dataPrazo: Date;
    dataFinalizacao: Date;
}
