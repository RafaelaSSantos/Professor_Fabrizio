import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarGpComponent } from './listar-gp/listar-gp.component';
import { ProfessoresGpFormComponent } from './professores-gp-form/professores-gp-form.component';

const routes: Routes = [
  {path: 'professoresgp', component: ListarGpComponent},
  {path: 'professoresgp/add', component: ProfessoresGpFormComponent},
  {path: 'professoresgp/:id', component: ListarGpComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfessoresGpRoutingModule { }
