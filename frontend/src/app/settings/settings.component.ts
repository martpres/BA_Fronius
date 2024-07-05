import { Component, Injectable } from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})

@Injectable({
  providedIn: 'root'
})
export class SettingsComponent {
  private _kwhPriceFromGrid: number = 0.25;
  private _kwhPriceIntoGrid: number = 0.12;

  get kwhPriceFromGrid(): number {
    return this._kwhPriceFromGrid;
  }

  set kwhPriceFromGrid(value: number) {
    this._kwhPriceFromGrid = value;
  }

  get kwhPriceIntoGrid(): number {
    return this._kwhPriceIntoGrid;
  }

  set kwhPriceIntoGrid(value: number) {
    this._kwhPriceIntoGrid = value;
  }

}
