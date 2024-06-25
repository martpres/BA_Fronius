import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {BackendApiService} from "../service/backend-api.service";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {AcPowerGrid} from "../dto/acPowerGrid.model";
import * as moment from "moment";
import {isNumber} from "@swimlane/ngx-charts/lib/utils/types";
import {DEBUG} from "@angular/compiler-cli/src/ngtsc/logging/src/console_logger";

// Define an interface for chart data point
interface ChartDataPoint {
  name: string;
  value: number;
}

@Component({
  selector: 'app-ac-power-grid',
  templateUrl: './ac-power-grid.component.html',
  styleUrls: ['./ac-power-grid.component.scss']
})
export class AcPowerGridComponent implements OnInit, OnDestroy {
  public acEnergyGridDayData? = "xxx";
  public chartData?: any[];
  public chartColors?: any[];
  public initialDate = new Date();
  public maxDate = new Date();
  private sub?: Subscription;
  private data?: QueryDslResponse<AcPowerGrid>;
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
    this.sub = this.backendService.loadAcPowerGrid(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e)=> {
      this.data = e;
      this.mapRequestToLineChart();
      this.mapColors();
      console.log(this.chartData)
      console.log(this.chartColors)
    });
  }

  private mapRequestToLineChart(): void {
    if (this.data?.content?.length === 0) {
      this.chartData = undefined;
      return;
    }
    this.chartData = [];
    const array: any[] = [];
    this.data?.content?.forEach((e)=>{
      let date = this.dateTimeService.convertUtcToLocalTimeZone(e.timestamp)
      array.push({name: date, value: e.acPowerGrid});
    });
    this.chartData?.push({name: 'Power Grid', series: array });
  }

  public mapColors(): void {
    if (this.data?.content?.length === 0) {
      this.chartColors = undefined;
      return;
    }
    this.chartColors = [];
    this.data?.content?.forEach((e)=>{
      if(e.acPowerGrid === undefined){
        return;
      }
      let date = this.dateTimeService.convertUtcToLocalTimeZone(e.timestamp)
      if ( e.acPowerGrid >  0 || isNaN(e.acPowerGrid)){
        // @ts-ignore
        this.chartColors.push({name: date, value: "#0000ff"});
      } else
        { // @ts-ignore
          this.chartColors.push({name: date, value: "#ff0000"});
        }
      // return this.chartColors;
    });
  }

}
