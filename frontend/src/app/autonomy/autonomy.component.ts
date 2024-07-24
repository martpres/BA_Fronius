import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {BackendApiService} from "../service/backend-api.service";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {Autonomy} from "../dto/autonomy.model";
import * as moment from "moment";

@Component({
  selector: 'app-autonomy',
  templateUrl: './autonomy.component.html',
  styleUrls: ['./autonomy.component.scss']
})
export class AutonomyComponent implements OnInit, OnDestroy {
  public chartData?: any[];
  public initialDate = new Date();
  public maxDate = new Date();
  private sub?: Subscription;
  private data?: QueryDslResponse<Autonomy>;
  private refreshMilliSeconds = 60000;
  private interval?: any;

  constructor(private backendService: BackendApiService, private dateTimeService: DateTimeService) {
  }

  ngOnInit(): void {
    this.sendRequest();
    this.interval = setInterval(() => {
      this.sendRequest();
    }, this.refreshMilliSeconds);
  }

  ngOnDestroy(): void {
    this.sub?.unsubscribe();
    clearInterval(this.interval);
  }

  public convertTime(value: Date): string {
    return formatDate(value, timeFormat, localId);
  }

  public sendRequest(): void {
    const endDate = moment(this.initialDate).endOf('day').utc();
    const startDate = moment(this.initialDate).startOf('day').utc();
    this.sub = this.backendService.loadAutonomy(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.data = e;
      this.mapRequestToChart();
    });
  }

  private mapRequestToChart(): void {
    if (this.data?.content?.length === 0) {
      this.chartData = undefined;
      return;
    }
    this.chartData = [];
    const array: any[] = [];
    this.data?.content?.forEach((e) => {
      let date = this.dateTimeService.convertUtcToLocalTimeZone(e.timestamp)
      array.push({name: date, value: e.autonomy});
    });
    this.chartData?.push({name: 'Autonomy', series: array});
  }

  public calculateAverage(chartData?: any[]): number {
    if (chartData === undefined || !Array.isArray(chartData) || chartData.length === 0) {
      return 0;
    }
    const totalSum = chartData.reduce((sum, data) => {
      const seriesSum = data.series.reduce((seriesSum: any, obj: { value: any; }) => seriesSum + obj.value, 0);
      return sum + seriesSum;
    }, 0);
    const totalCount = chartData.reduce((count, data) => count + data.series.length, 0);
    return totalSum / totalCount;
  }

}
