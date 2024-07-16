import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import {PricesService} from "../service/prices.service";

@Injectable({
  providedIn: 'root'
})
export class PriceResolver implements Resolve<boolean> {

  constructor(private priceService: PricesService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    this.priceService.loadLastPrice();
    return of(true);
  }
}
