import { Material } from '../materiais/Material';

export interface Estoque {
    id: number;
    materiais?: Material[];
    quantidade: number;
}