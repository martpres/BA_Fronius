import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AcCurrentGridComponent} from "./ac-current-grid/ac-current-grid.component";
import {DcPowerPvComponent} from "./dc-power-pv/dc-power-pv.component";
import {AcPowerGridComponent} from "./ac-power-grid/ac-power-grid.component";

const routes: Routes = [
  {
    path: 'ac-current-grid',
    component: AcCurrentGridComponent
  },
  {
    path: 'dc-power-pv',
    component: DcPowerPvComponent
  },
  {
    path: 'ac-power-grid',
    component: AcPowerGridComponent
  },
  {
    path: '**',
    redirectTo: 'ac-current-grid'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
