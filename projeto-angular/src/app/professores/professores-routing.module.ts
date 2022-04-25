import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarComponent } from './listar/listar.component';

const routes: Routes = [
  {path: 'professores', component:ListarComponent},
  {path: 'professores/:id', component:ListarComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfessoresRoutingModule { }
