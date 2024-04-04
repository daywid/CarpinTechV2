import { Status } from '../status/Status';
import { Projeto } from '../projetos/Projeto';
import { Material } from '../materiais/Material';

export interface Tarefa {
    id: number;
    nome: string;
    desc: string;
    status: Status;
    dataInicio: Date;
    dataFinalizacao: Date;
    projetoId: number;
    projeto?: Projeto;
}
