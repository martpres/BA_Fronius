import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CurrentACComponent} from "./current-ac/current-ac.component";
import {PowerDCComponent} from "./power-dc/power-dc.component";
import {PowerAcGridComponent} from "./power-ac-grid/power-ac-grid.component";

const routes: Routes = [
  {
    path: 'current-ac',
    component: CurrentACComponent
  },
  {
    path: 'power-dc',
    component: PowerDCComponent
  },
  {
    path: 'power-ac-grid',
    component: PowerAcGridComponent
  },
  {
    path: '**',
    redirectTo: 'current-ac'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
