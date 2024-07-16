import { Component, Injectable } from '@angular/core';
import {PricesService} from "../service/prices.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PricesModel} from "../dto/prices.model";
import {firstValueFrom} from "rxjs";
import {BackendApiService} from "../service/backend-api.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent {

 private fg?: FormGroup;

  constructor(public pricesService: PricesService, private formBuilder: FormBuilder, private backendApi: BackendApiService) {
  }

  public buildFormGroup(prices: PricesModel) {
    this.fg = this.formBuilder.group({
      kwhPriceFromGrid: [prices?.kwhPriceFromGrid ?? 0, [Validators.required]],
      kwhPriceIntoGrid: [prices?.kwhPriceIntoGrid ?? 0, [Validators.required]],
    });
    return this.fg;
  }

  public update() {
    const request = this.fg?.value as PricesModel;
    firstValueFrom(this.backendApi.updatePrice(request));
  }

}
