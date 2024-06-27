import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {BackendApiService} from "../service/backend-api.service";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {AcPowerInverter} from "../dto/acPowerInverter.model";
import {AcEnergyInverterDay} from "../dto/acEnergyInverterDay.model";
import * as moment from "moment";
import {EnergyDay} from "../dto/energyDay.model";

@Component({
  selector: 'app-ac-power-inverter',
  templateUrl: './ac-power-inverter.component.html',
  styleUrls: ['./ac-power-inverter.component.scss']
})
export class AcPowerInverterComponent implements OnInit, OnDestroy {
  public acCalculated?: EnergyDay;
  public acEnergyInverterDayData?: QueryDslResponse<AcEnergyInverterDay>;
  public chartData?: any[];
  public initialDate = new Date();
  public maxDate = new Date();
  private sub1?: Subscription;
  private sub2?: Subscription;
  private sub3?: Subscription;
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
    this.sub1?.unsubscribe();
    this.sub2?.unsubscribe();
    this.sub3?.unsubscribe();
    clearInterval(this.interval);
  }

  public convertTime(value: Date): string {
    return formatDate(value, timeFormat, localId);
  }

  public sendRequest(): void {
    const endDate = moment(this.initialDate).endOf('day').utc();
    const startDate = moment(this.initialDate).startOf('day').utc();
    this.sub1 = this.backendService.loadAcPowerInverter(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.data = e;
      this.mapRequestToChart();
    });

    this.sub2 = this.backendService.loadAcEnergyInverterDay(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.acEnergyInverterDayData = e;
    });

    this.sub3 = this.backendService.loadAcEnergyDay(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.acCalculated = e;
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
      array.push({name: date, value: e.acPowerInverter});
    });
    this.chartData?.push({name: 'AC Power Inverter', series: array});
  }

  public ConvertAndRoundEnergy(energyDay: any): EnergyDay {
    if (!energyDay?.energyDay) {
      return { energyDay: 0 };
    }
    const kiloWatts = energyDay.energyDay / (1000 * 3600);
    return { energyDay: Math.round(kiloWatts) };
  }

}
