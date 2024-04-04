import { Estoque } from '../estoque/Estoque';
import { Tarefa } from '../tarefas/Tarefa';

export interface Material {
    id: number;
    nome: string;
    custo: number;
    estoqueId: number;
    estoque: Estoque;
}
