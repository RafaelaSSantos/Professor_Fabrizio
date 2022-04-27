import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProfessoresService } from '../professores.service';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss']
})
export class ListarComponent implements OnInit {

  @Input()
  empresaFilho : string = '';

  // professores = [
  //   {
  //     id: 1,
  //     nome: 'Rafa',
  //     cpf: '123.741.852-50',
  //     rua: 'José Carvalho',
  //     numero: 45,
  //     cep: '0741-963',
  //   },
  //   {
  //     id: 2,
  //     nome: 'Mario',
  //     cpf: '895.741.142-55',
  //     rua: 'Max Carvalho',
  //     numero: 111,
  //     cep: '0641-900',
  //   },
  // ];

  professores : any = [];

  //private activatedRoute : ActivatedRoute
  // modificador de acesso, nome da variável e Classe do objeto a ser injetado
  constructor(
    private activatedRoute: ActivatedRoute,
    private professoresService: ProfessoresService
  ) {
    // this.activatedRoute = new ActivatedRoute();
  }

  ngOnInit(): void {
    this.getAll();
    this.activatedRoute.params.subscribe(
      (data) => {
        console.log(data);
      }
    );
  }

  onDelete(id: number){
    this.professoresService.delete(id)
      .subscribe(
        ()=>{
          console.log(`deletou registro com id ${id}`);
          //this.router.navigate(['/professores/#']);
          this.getAll();
        }
      );
  }

  private getAll(){
    this.professoresService.getAll()
    .subscribe(
      (data) => {
        console.log(data);
        this.professores = data;
      }
    );
  }


}
