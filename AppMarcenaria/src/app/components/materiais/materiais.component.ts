import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Estoque } from 'src/app/models/estoque/Estoque';
import { EstoquesService } from 'src/app/models/estoque/estoques.service';
import { MateriaisService } from 'src/app/models/materiais/materiais.service';
import { Material } from 'src/app/models/materiais/Material';

@Component({
  selector: 'app-materiais',
  templateUrl: './materiais.component.html',
  styleUrls: ['./materiais.component.css']
})
export class MateriaisComponent implements OnInit {
  formulario!: FormGroup;
  estoques: Array<Estoque> | undefined;
  formularioEdit !: FormGroup;
  tituloFormulario: string = '';
  materialSelecionado: Material | null = null;
  materiaisList: Material[] = [];

  constructor(private formBuilder: FormBuilder, private materiaisService: MateriaisService, private estoquesService: EstoquesService) { }

  ngOnInit(): void {
    this.tituloFormulario = 'Novo Material';
    this.estoquesService.listar().subscribe(estoques => {
      this.estoques = estoques;
      if (this.estoques && this.estoques.length > 0){
        this.formulario.get('estoqueId')?.setValue(this.estoques[0].id)
      }

    })
    this.formulario = new FormGroup({
      id: new FormControl(null),
      nome: new FormControl(null),
      custo: new FormControl(null),
      estoqueId: new FormControl(null),
      estoque: new FormControl(null)
    });

    this.formularioEdit = new FormGroup({
      id:new FormControl(null),
      nome:new FormControl(null),
      custo:new FormControl(null),
      estoqueId:new FormControl(null),
      estoque:new FormControl(null)
    });

    this.listarMateriais();
  }

  enviarFormulario(): void {
    const material: Material = this.formulario.value;

    this.materiaisService.cadastrar(material).subscribe(
      () => {
        alert('Material cadastrado com sucesso.');
        this.listarMateriais();
      },
      (error) => alert('Erro ao cadastrar o material...')
    );
  }

  salvarEdicao(): void {
    const material: Material = this.formularioEdit.value;
    console.log(JSON.stringify(material))

    this.materiaisService.atualizar(material).subscribe(
      () => {
        alert('Material atualizado com sucesso.');
        this.listarMateriais();
      },
      () => alert('Erro ao atualizar o material!')
    );
  }

  listarMateriais(): void {
    this.materiaisService.listar().subscribe(
      (materiais) => this.materiaisList = materiais,
      () => console.error('Erro ao listar materiais')
    );
  }

  editarMaterial(material: Material): void {
    this.materialSelecionado = material;
    this.formularioEdit.patchValue(material);
  }

  excluirMaterial(id: number): void {
    if (confirm('Tem certeza que deseja excluir este material?')) {
      this.materiaisService.excluir(id).subscribe(
        () => {
          console.log('Material excluído com sucesso:', id);
          this.listarMateriais();
        },
        () => console.error('Erro ao excluir o material')
      );
    }
  }

  limparFormulario(): void {
    this.formulario.reset();
    this.materialSelecionado = null;
  }

  voltarParaLista(): void {
    this.formulario.reset();
    this.materialSelecionado = null; 
  }
}
