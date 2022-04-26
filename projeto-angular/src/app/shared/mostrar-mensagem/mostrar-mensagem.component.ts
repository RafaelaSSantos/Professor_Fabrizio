import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormControlName, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-mostrar-mensagem',
  templateUrl: './mostrar-mensagem.component.html',
  styleUrls: ['./mostrar-mensagem.component.scss']
})
export class MostrarMensagemComponent implements OnInit {

  @Input()
  controles: any;

  @Input()
  mensagem : string = 'Erro ao preencher campo' ;

  @Input()
  debug : boolean = false;

  constructor() { }

  ngOnInit(): void {  }
}
