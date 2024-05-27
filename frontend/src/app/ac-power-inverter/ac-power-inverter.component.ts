import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {BackendApiService} from "../service/backend-api.service";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {AcPowerInverter} from "../dto/acPowerInverter.model";
import {AcEnergyInverterDay} from "../dto/acEnergyInverterDay.model";

@Component({
  selector: 'app-ac-power-inverter',
  templateUrl: './ac-power-inverter.component.html',
  styleUrls: ['./ac-power-inverter.component.scss']
})
export class AcPowerInverterComponent implements OnInit, OnDestroy {

  public acEnergyInverterDayData?: AcEnergyInverterDay;
  public lineChartData?: any[];
  public initialDate = new Date();
  private sub?: Subscription;
  private data?: QueryDslResponse<AcPowerInverter>;
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

  private sendRequest(): void {
    this.initialDate = new Date();
    const endDate = this.dateTimeService.convertToUtcDate(this.initialDate);
    const startDate = this.dateTimeService.convertToStartOfDayUtc(this.dateTimeService.convertToUtcDate(this.initialDate));
    this.sub = this.backendService.loadAcPowerInverter(this.dateTimeService.createFilter(startDate, endDate)).subscribe((e) => {
      this.data = e;
      this.mapRequestToLineChart();
    });
    this.sub.add(this.backendService.loadAcEnergyInverterDay().subscribe((e) => this.acEnergyInverterDayData = e))
  }

  private mapRequestToLineChart(): void {
    if (this.data?.content?.length === 0) {
      this.lineChartData = undefined;
      return;
    }
    this.lineChartData = [];
    const arrayPower1: any[] = [];
    this.data?.content?.forEach((e) => {
      let date = this.dateTimeService.convertUtcToLocalTimeZone(e.timestamp)
      arrayPower1.push({name: date, value: e.acPowerInverter});
    });
    this.lineChartData?.push({name: 'AC Power Inverter', series: arrayPower1});
  }


}
