import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarComponent } from './listar/listar.component';
import { ProfessorFormComponent } from './professor-form/professor-form.component';

const routes: Routes = [
    {path: 'professores', component: ListarComponent},
    {path: 'professores/criar', component: ProfessorFormComponent},
    {path: 'professores/:id', component: ListarComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfessoresRoutingModule { }
