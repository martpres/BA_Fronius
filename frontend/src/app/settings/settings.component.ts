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
  private _priceFromGrid: number = 0.25;
  private _priceIntoGrid: number = 0.12;


  get priceFromGrid(): number {
    return this._priceFromGrid;
  }

  set priceFromGrid(value: number) {
    this._priceFromGrid = value;
  }

  get priceIntoGrid(): number {
    return this._priceIntoGrid;
  }

  set priceIntoGrid(value: number) {
    this._priceIntoGrid = value;
  }
}
