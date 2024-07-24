import {Component} from '@angular/core';
import {PricesService} from "../service/prices.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {PricesModel} from "../dto/prices.model";
import {firstValueFrom} from "rxjs";
import {BackendApiService} from "../service/backend-api.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent {
  public fg?: FormGroup;

  constructor(public pricesService: PricesService,
              private formBuilder: FormBuilder,
              private backendApi: BackendApiService,
              private toaster: ToastrService
  ) {
    pricesService.price.subscribe(model => this.buildFormGroup(model))
  }

  public buildFormGroup(prices: PricesModel) {
    this.fg = new FormGroup({
      kwhPriceFromGrid: new FormControl(prices.kwhPriceFromGrid, Validators.required),
      kwhPriceIntoGrid: new FormControl(prices.kwhPriceIntoGrid, Validators.required)
    })
    return this.fg
  }

  public update() {
    const request = this.fg?.value as PricesModel;
    firstValueFrom(this.backendApi.updatePrice(request)).then(() => {
      this.toaster.success("Successfully updated price")
    });
  }

}
