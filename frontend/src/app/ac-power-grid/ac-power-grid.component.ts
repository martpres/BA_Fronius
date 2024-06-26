import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {BackendApiService} from "../service/backend-api.service";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {AcPowerGrid} from "../dto/acPowerGrid.model";
import * as moment from "moment";
import * as d3 from 'd3';

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
    const positiveFillColor = '#0000ff'; // Blue for positive
    const negativeFillColor = '#ff0000'; // Red for negative
    const zeroColor = 'transparent'; // Transparent for zero

    this.chartColors = [];
    const colorScale = d3.scaleLinear() // Create a linear color scale
      .domain([-1, 0, 1]) // Define the domain for positive, zero, negative values
      // @ts-ignore
      .range([negativeFillColor, zeroColor, positiveFillColor]); // Set color mapping
    this.data?.content?.forEach((e) => {
      if (e.acPowerGrid === undefined) {
        return;
      }
      let date = this.dateTimeService.convertUtcToLocalTimeZone(e.timestamp);
      // @ts-ignore
      this.chartColors.push({
        name: date,
        value: colorScale(e.acPowerGrid), // Use color scale to determine color based on value
      });
    });
  }
}
