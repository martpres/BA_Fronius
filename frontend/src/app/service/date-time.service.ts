import {Injectable} from '@angular/core';
import {localId, timeFormat, timeZone} from "../dto/const";

@Injectable({
  providedIn: 'root'
})
export class DateTimeService {

  constructor() {
  }

  public createFilterForMoment(startDate: string, endDate: string): any {
    return {startDate: startDate, endDate: endDate};
  }

  public convertUtcToLocalTimeZone(date: Date = new Date()): Date {
    return new Date(date.toLocaleString(localId, {timeZone}))
  }

}
