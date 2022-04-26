import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-reativo-form',
  templateUrl: './reativo-form.component.html',
  styleUrls: ['./reativo-form.component.scss']
})
export class ReativoFormComponent implements OnInit {

  // inicializando a variável meuForm com um objet com controles vazios -> {}
  meuForm: FormGroup = new FormGroup({});

  // FormBuilder é um serviço utilizado para ajudar a criar os mecanismos de controles do formulário
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {

    this.meuForm = this.formBuilder.group({
      email : [ null, [ Validators.required, Validators.email ] ],
      password : [ null, [ Validators.required ] ]
    });

    console.log(this.meuForm);
  }
}
