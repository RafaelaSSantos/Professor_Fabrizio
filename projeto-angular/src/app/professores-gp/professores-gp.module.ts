import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfessoresGpRoutingModule } from './professores-gp-routing.module';
import { ListarGpComponent } from './listar-gp/listar-gp.component';
import { SharedModule } from '../shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { ProfessoresGpFormComponent } from './professores-gp-form/professores-gp-form.component';

@NgModule({
  declarations: [
    ListarGpComponent,
   ProfessoresGpFormComponent
  ],
  imports: [
    CommonModule,
    ProfessoresGpRoutingModule,
    SharedModule,
    HttpClientModule
  ],
  exports: [ListarGpComponent]
})
export class ProfessoresGpModule { }
