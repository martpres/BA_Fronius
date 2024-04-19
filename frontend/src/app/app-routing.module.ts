import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CurrentACComponent} from "./current-ac/current-ac.component";

const routes: Routes = [
  {
    path: 'current-ac',
    component: CurrentACComponent
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
