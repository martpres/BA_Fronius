import {Component, LOCALE_ID, OnDestroy, OnInit} from '@angular/core';
import {BackendApiService} from "../service/backend-api.service";
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {CurrentAC} from "../dto/currentAC.model";
import {CurrentAcFilter} from "../dto/current-ac-filter.model";
import {DatePipe, formatDate} from "@angular/common";

@Component({
  selector: 'app-current-ac',
  templateUrl: './current-ac.component.html',
  styleUrls: ['./current-ac.component.scss']
})
export class CurrentACComponent implements OnInit, OnDestroy{
  private sub?: Subscription;
  private data?: QueryDslResponse<CurrentAC>;
  public lineChartData?: any[];

  constructor(private backendService: BackendApiService, private datePipe: DatePipe) {
  }

  ngOnInit(): void {
    this.sendRequest();
  }

  ngOnDestroy(): void {
    this.sub?.unsubscribe();
  }

  public formatTime(value:Date):string {
    console.log(formatDate(value, 'HH:mm', 'de-DE'))
    return formatDate(value, 'HH:mm', 'de-DE');
  }

  private sendRequest(): void {
    const endDate = new Date(new Date().toUTCString());
    const startDate = new Date(new Date().toUTCString());
    startDate.setUTCHours(0,0,0,0)
    const filter = {startDate: startDate.toISOString(), endDate: endDate.toISOString()} as CurrentAcFilter;
    this.sub = this.backendService.loadCurrentAC(filter).subscribe((e)=> {
      this.data=e;
      this.mapRequestToLineChart();
    });
  }

  private mapRequestToLineChart(): void {
    if (this.data?.content?.length===0) {
      this.lineChartData=undefined;
      return;
    }
    this.lineChartData = [];
    const arrayPhase1: any[] = [];
    const arrayPhase2: any[] = [];
    const arrayPhase3: any[] = [];
    this.data?.content?.forEach((e)=>{
      let date = new Date(e.timestamp ?? new Date());
      date=new Date(date.toLocaleString('de-De', {timeZone: 'Europe/Berlin'}))
      arrayPhase1.push({name: date, value: e.acPhase1});
      arrayPhase2.push({name: date, value: e.acPhase2});
      arrayPhase3.push({name: date, value: e.acPhase3});
    });
    this.lineChartData?.push({name: 'Phase 1', series: arrayPhase1});
    this.lineChartData?.push({name: 'Phase 2', series: arrayPhase2});
    this.lineChartData?.push({name: 'Phase 3', series: arrayPhase3});
  }
}
