import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss']
})
export class ListarComponent implements OnInit {

  @Input()
  empresaFilho : string = '';

  professores =[
    {id: 1, nome: 'Rafa', cpf: '123.741.852-50', rua: 'José Carvalho', numero : 45, cep :'0741-963'},
    {id: 2, nome: 'Mario', cpf: '895.741.142-55', rua: 'Max Carvalho', numero :111, cep :'0641-900'}
  ];

  //modificador de acesso, nome da variavel e Classe do objeto a ser injetado
  constructor(private activatedRoute: ActivatedRoute) {
    //this.activatedRoute = new ActivatedRoute();
   }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (data) => {
        console.log(data);
      }
    );
  }
}
