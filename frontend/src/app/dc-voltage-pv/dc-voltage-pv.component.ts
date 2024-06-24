import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {BackendApiService} from "../service/backend-api.service";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {DcVoltagePv} from "../dto/dcVoltagePv.model";
import * as moment from "moment/moment";

@Component({
  selector: 'app-dv-voltage-pv',
  templateUrl: './dc-voltage-pv.component.html',
  styleUrls: ['./dc-voltage-pv.component.scss']
})
export class DcVoltagePvComponent implements OnInit, OnDestroy {
  public chartData?: any[];
  public initialDate = new Date();
  public maxDate = new Date();
  private sub?: Subscription;
  private data?: QueryDslResponse<DcVoltagePv>;
  private refreshMilliSeconds = 60000;
  private interval?: any;

    constructor(private backendService: BackendApiService, private dateTimeService: DateTimeService) {
    }

    ngOnInit(): void {
      this.sendRequest();
      this.interval = setInterval(()=> {
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
      this.sub = this.backendService.loadDcVoltagePv(this.dateTimeService.createFilterForMoment(startDate.format(),
        endDate.format())).subscribe((e)=> {
        this.data = e;
        this.mapRequestToLineChart();
      });
    }

  private mapRequestToLineChart(): void {
      if (this.data?.content?.length===0) {
      this.chartData=undefined;
      return;
    }
    this.chartData = [];
    const array: any[] = [];
    this.data?.content?.forEach((e)=>{
      let date = this.dateTimeService.convertUtcToLocalTimeZone(e.timestamp)
      array.push({name: date, value: e.dcVoltagePv});
    });
    this.chartData?.push({name: 'Voltage PV-Modules', series: array});
  }

}
