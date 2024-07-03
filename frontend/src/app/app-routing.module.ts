import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DcPowerPvComponent} from "./dc-power-pv/dc-power-pv.component";
import {AcPowerInverterComponent} from "./ac-power-inverter/ac-power-inverter.component";
import {DcVoltagePvComponent} from "./dc-voltage-pv/dc-voltage-pv.component";
import {DcPowerAkkuComponent} from "./dc-power-akku/dc-power-akku.component";
import {AcPowerLoadComponent} from "./ac-power-load/ac-power-load.component";
import {AutonomyComponent} from "./autonomy/autonomy.component";
import {SelfConsumptionComponent} from "./self-consumption/self-consumption.component";
import {StateOfChargeAkkuComponent} from "./state-of-charge-akku/state-of-charge-akku.component";
import {AcPowerGridPhasesComponent} from "./ac-power-grid-phases/ac-power-grid-phases.component";
import {AcPowerIntoGridComponent} from "./ac-power-into-grid/ac-power-into-grid.component";
import {AcPowerFromGridComponent} from "./ac-power-from-grid/ac-power-from-grid.component";
import {SettingsComponent} from "./settings/settings.component";

const routes: Routes = [
  {
    path: 'dc-power-pv',
    component: DcPowerPvComponent
  },
  {
    path: 'ac-power-from-grid',
    component: AcPowerFromGridComponent
  },
  {
    path: 'ac-power-into-grid',
    component: AcPowerIntoGridComponent
  },
  {
    path: 'ac-power-inverter',
    component: AcPowerInverterComponent
  },
  {
    path: 'ac-power-load',
    component: AcPowerLoadComponent
  },
  {
    path: 'dc-power-akku',
    component: DcPowerAkkuComponent
  },
  {
    path: 'dc-voltage-pv',
    component: DcVoltagePvComponent
  },
  {
    path: 'state-of-charge-akku',
    component: StateOfChargeAkkuComponent
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
    path: 'ac-power-grid-phases',
    component: AcPowerGridPhasesComponent
  },
  {
    path: 'settings',
    component: SettingsComponent
  },
  {
    path: '**',
    redirectTo: 'dc-power-pv'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
