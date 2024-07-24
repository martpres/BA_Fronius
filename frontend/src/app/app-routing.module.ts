import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DcPowerPvComponent} from "./dc-power-pv/dc-power-pv.component";
import {AcPowerInverterComponent} from "./ac-power-inverter/ac-power-inverter.component";
import {DcVoltagePvComponent} from "./dc-voltage-pv/dc-voltage-pv.component";
import {AcPowerLoadComponent} from "./ac-power-load/ac-power-load.component";
import {AutonomyComponent} from "./autonomy/autonomy.component";
import {SelfConsumptionComponent} from "./self-consumption/self-consumption.component";
import {StateOfChargeAkkuComponent} from "./state-of-charge-akku/state-of-charge-akku.component";
import {AcPowerGridPhasesComponent} from "./ac-power-grid-phases/ac-power-grid-phases.component";
import {AcPowerIntoGridComponent} from "./ac-power-into-grid/ac-power-into-grid.component";
import {AcPowerFromGridComponent} from "./ac-power-from-grid/ac-power-from-grid.component";
import {SettingsComponent} from "./settings/settings.component";
import {DcPowerIntoAkkuComponent} from "./dc-power-into-akku/dc-power-into-akku.component";
import {DcPowerFromAkkuComponent} from "./dc-power-from-akku/dc-power-from-akku.component";
import {PriceResolver} from "./resolver/price.resolver";

const routes: Routes = [
  {
    path: 'dc-power-pv',
    component: DcPowerPvComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'ac-power-from-grid',
    component: AcPowerFromGridComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'ac-power-into-grid',
    component: AcPowerIntoGridComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'ac-power-inverter',
    component: AcPowerInverterComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'ac-power-load',
    component: AcPowerLoadComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'dc-power-from-akku',
    component: DcPowerFromAkkuComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'dc-power-into-akku',
    component: DcPowerIntoAkkuComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'dc-voltage-pv',
    component: DcVoltagePvComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'state-of-charge-akku',
    component: StateOfChargeAkkuComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'autonomy',
    component: AutonomyComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'self-consumption',
    component: SelfConsumptionComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'ac-power-grid-phases',
    component: AcPowerGridPhasesComponent,
    resolve: [PriceResolver]
  },
  {
    path: 'settings',
    component: SettingsComponent,
    resolve: [PriceResolver]
  },
  {
    path: '**',
    redirectTo: 'dc-power-pv',
    resolve: [PriceResolver]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
