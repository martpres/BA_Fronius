import { Component } from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent {
  public priceFromGrid: number = 0.25;
  public priceIntoGrid: number = 0.12;

}
