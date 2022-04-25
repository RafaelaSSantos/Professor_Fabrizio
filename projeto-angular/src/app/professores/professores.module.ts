import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListarComponent } from './listar/listar.component';
import { ProfessoresRoutingModule } from './professores-routing.module';

@NgModule({
  declarations: [
    ListarComponent
  ],
  imports: [
    CommonModule,
    ProfessoresRoutingModule
  ],
  exports:[
    ListarComponent
  ]
})
export class ProfessoresModule { }
