import {LOCALE_ID, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HeaderComponent} from './header/header.component';
import {MaterialModule} from "./material/material.module";
import {AcCurrentGridPhasesComponent} from './ac-current-grid-phases/ac-current-grid-phases.component';
import {HttpClientModule} from "@angular/common/http";
import {CommonModule, DatePipe, registerLocaleData} from "@angular/common";
import {NgxChartsModule} from "@swimlane/ngx-charts";
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import {DcPowerPvComponent} from './dc-power-pv/dc-power-pv.component';
import {AcPowerInverterComponent} from './ac-power-inverter/ac-power-inverter.component';
import {DcVoltagePvComponent} from './dc-voltage-pv/dc-voltage-pv.component';
import {DcPowerAkkuComponent} from './dc-power-akku/dc-power-akku.component';
import {AutonomyComponent} from './autonomy/autonomy.component';
import {SelfConsumptionComponent} from './self-consumption/self-consumption.component';
import {AcPowerLoadComponent} from './ac-power-load/ac-power-load.component';
import {StateOfChargeAkkuComponent} from './state-of-charge-akku/state-of-charge-akku.component';
import {FormsModule} from "@angular/forms";
import { AcPowerGridPhasesComponent } from './ac-power-grid-phases/ac-power-grid-phases.component';
import { AcPowerIntoGridComponent } from './ac-power-into-grid/ac-power-into-grid.component';
import { AcPowerFromGridComponent } from './ac-power-from-grid/ac-power-from-grid.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AcCurrentGridPhasesComponent,
    DcPowerPvComponent,
    AcPowerInverterComponent,
    DcVoltagePvComponent,
    DcPowerAkkuComponent,
    AutonomyComponent,
    SelfConsumptionComponent,
    AcPowerLoadComponent,
    StateOfChargeAkkuComponent,
    AcPowerGridPhasesComponent,
    AcPowerIntoGridComponent,
    AcPowerFromGridComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    NgxChartsModule,
    FormsModule
  ],
  providers: [
    {provide: LOCALE_ID, useValue: 'de-DE'},
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
    registerLocaleData(localeDe, 'de-DE', localeDeExtra);
  }
}
