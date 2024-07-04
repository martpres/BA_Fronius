import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {
  public priceFromGrid: number = 0.25;
  public priceIntoGrid: number = 0.12;
}
