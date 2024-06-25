import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AcCurrentGridPhasesComponent} from "./ac-current-grid-phases/ac-current-grid-phases.component";
import {DcPowerPvComponent} from "./dc-power-pv/dc-power-pv.component";
import {AcPowerGridComponent} from "./ac-power-grid/ac-power-grid.component";
import {AcPowerInverterComponent} from "./ac-power-inverter/ac-power-inverter.component";
import {DcVoltagePvComponent} from "./dc-voltage-pv/dc-voltage-pv.component";
import {DcPowerAkkuComponent} from "./dc-power-akku/dc-power-akku.component";
import {AcPowerLoadComponent} from "./ac-power-load/ac-power-load.component";
import {AutonomyComponent} from "./autonomy/autonomy.component";
import {SelfConsumptionComponent} from "./self-consumption/self-consumption.component";
import {StateOfChargeAkkuComponent} from "./state-of-charge-akku/state-of-charge-akku.component";
import {AcPowerGridPhasesComponent} from "./ac-power-grid-phases/ac-power-grid-phases.component";

const routes: Routes = [
  {
    path: 'ac-current-grid-phases',
    component: AcCurrentGridPhasesComponent
  },
  {
    path: 'ac-power-grid-phases',
    component: AcPowerGridPhasesComponent
  },
  {
    path: 'dc-power-pv',
    component: DcPowerPvComponent
  },
  {
    path: 'dc-voltage-pv',
    component: DcVoltagePvComponent
  },
  {
    path: 'ac-power-grid',
    component: AcPowerGridComponent
  },
  {
    path: 'ac-power-inverter',
    component: AcPowerInverterComponent
  },
  {
    path: 'dc-power-akku',
    component: DcPowerAkkuComponent
  },
  {
    path: 'ac-power-load',
    component: AcPowerLoadComponent
  },
  {
    path: 'autonomy',
    component: AutonomyComponent
  },
  {
    path: 'self-consumption',
    component: SelfConsumptionComponent
  },
  {
    path: 'state-of-charge-akku',
    component: StateOfChargeAkkuComponent
  },
  {
    path: '**',
    redirectTo: 'ac-power-inverter'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
