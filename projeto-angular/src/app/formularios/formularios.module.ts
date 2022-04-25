import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormulariosRoutingModule } from './formularios-routing.module';
import { ReativoFormComponent } from './reativo-form/reativo-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MostrarMensagemComponent } from './mostrar-mensagem/mostrar-mensagem.component';

@NgModule({
  declarations: [
    ReativoFormComponent,
    MostrarMensagemComponent
  ],
  imports: [
    CommonModule,
    FormulariosRoutingModule,
    ReactiveFormsModule
  ]
})
export class FormulariosModule { }
