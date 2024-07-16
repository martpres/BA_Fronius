import { Component, Injectable } from '@angular/core';
import {PricesService} from "../service/prices.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})

@Injectable({
  providedIn: 'root'
})
export class SettingsComponent {

  constructor(public pricesService: PricesService) {
  }
}
