import {Injectable} from '@angular/core';
import {BehaviorSubject, firstValueFrom, Observable} from "rxjs";
import {PricesModel} from "../dto/prices.model";
import {BackendApiService} from "./backend-api.service";

@Injectable({
  providedIn: 'root'
})
export class PricesService {
  private pricesSubject = new BehaviorSubject<PricesModel>({kwhPriceFromGrid: 0, kwhPriceIntoGrid: 0} as PricesModel);
  private pricesObservable = this.pricesSubject.asObservable();

  constructor(private apiService: BackendApiService) {
  }

  get price(): Observable<PricesModel> {
    return this.pricesObservable;
  }

  public loadLastPrice(): void {
    firstValueFrom(this.apiService.loadLastPrice()).then((res: PricesModel)=> this.pricesSubject.next(res));
  }

  public loadPriceForDate(date: Date): void {
    firstValueFrom(this.apiService.loadPriceForDate(date)).then((res: PricesModel)=> this.pricesSubject.next(res));
  }



}
