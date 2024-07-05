import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {
  public kwhPriceFromGrid: number = 0.25;
  public kwhPriceIntoGrid: number = 0.12;
}
