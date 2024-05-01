import {Component, OnDestroy, OnInit} from '@angular/core';
import {BackendApiService} from "../service/backend-api.service";
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {CurrentAC} from "../dto/currentAC.model";
import {DatePipe} from "@angular/common";
import {CurrentAcFilter} from "../dto/current-ac-filter.model";

@Component({
  selector: 'app-current-ac',
  templateUrl: './current-ac.component.html',
  styleUrls: ['./current-ac.component.scss']
})
export class CurrentACComponent implements OnInit, OnDestroy{
  private sub?: Subscription;
  private data?: QueryDslResponse<CurrentAC>;
  public lineChartData?: any[];

  constructor(private backendService: BackendApiService) {
  }

  ngOnInit(): void {
    this.sendRequest();
  }

  ngOnDestroy(): void {
    this.sub?.unsubscribe();
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

  }
}
