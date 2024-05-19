import {LOCALE_ID, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import {MaterialModule} from "./material/material.module";
import { AcCurrentGridComponent } from './ac-current-grid/ac-current-grid.component';
import {HttpClientModule} from "@angular/common/http";
import {CommonModule, DatePipe, registerLocaleData} from "@angular/common";
import {NgxChartsModule} from "@swimlane/ngx-charts";
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import { DcPowerPvComponent } from './dc-power-pv/dc-power-pv.component';
import { AcPowerGridComponent } from './ac-power-grid/ac-power-grid.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AcCurrentGridComponent,
    DcPowerPvComponent,
    AcPowerGridComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    NgxChartsModule
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
