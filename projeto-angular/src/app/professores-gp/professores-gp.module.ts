import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfessoresGpRoutingModule } from './professores-gp-routing.module';
import { ListarComponent } from './listar/listar.component';

@NgModule({
  declarations: [
    ListarComponent
  ],
  imports: [
    CommonModule,
    ProfessoresGpRoutingModule
  ]
})
export class ProfessoresGpModule { }
