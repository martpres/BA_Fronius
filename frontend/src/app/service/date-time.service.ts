import { Injectable } from '@angular/core';
import {formatDate} from "@angular/common";
import {localId, timeFormat, timeZone} from "../dto/const";

@Injectable({
  providedIn: 'root'
})
export class DateTimeService {

  constructor() {
  }

  public convertToUtcString(date: Date = new Date()): string {
    return date.toUTCString();
  }

  public convertToUtcDate(date: Date = new Date()): Date {
    return new Date(date.toUTCString());
  }

  public convertToStartOfDayUtc(date: Date = new Date()): Date {
    date.setUTCHours(0,0,0,0);
    return date;
  }

  public createFilter(startDate: Date = new Date(), endDate: Date = new Date()): any {
    return {startDate: startDate.toISOString(), endDate: endDate.toISOString()};
  }

  public convertUtcToLocalTimeZone(date: Date = new Date()): Date {
    return new Date(date.toLocaleString(localId, {timeZone}))
  }

}
