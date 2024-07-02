import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {BackendApiService} from "../service/backend-api.service";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {AcPowerLoad} from "../dto/acPowerLoad.model";
import * as moment from "moment";
import {EnergyDay} from "../dto/energyDay.model";

@Component({
  selector: 'app-ac-power-load',
  templateUrl: './ac-power-load.component.html',
  styleUrls: ['./ac-power-load.component.scss']
})
export class AcPowerLoadComponent implements OnInit, OnDestroy {
  public calculatedAcEnergyLoadDay?: EnergyDay;
  public chartData?: any[];
  public initialDate = new Date();
  public maxDate = new Date();
  private sub1?: Subscription;
  private sub2?: Subscription;
  private data?: QueryDslResponse<AcPowerLoad>;
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
    clearInterval(this.interval);
  }

  public convertTime(value: Date): string {
    return formatDate(value, timeFormat, localId);
  }

  public sendRequest(): void {
    const endDate = moment(this.initialDate).endOf('day').utc();
    const startDate = moment(this.initialDate).startOf('day').utc();

    this.sub1 = this.backendService.loadAcPowerLoad(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.data = e;
      this.mapRequestToChart();
    });

    this.sub2 = this.backendService.loadCalculatedAcEnergyLoadDay(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.calculatedAcEnergyLoadDay = e;
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
      if (typeof e.acPowerLoad === 'number') {
        array.push({name: date, value: -e.acPowerLoad});
      }
    });
    this.chartData?.push({name: 'Power Load', series: array});
  }

  public convertAndRoundEnergy(energyDay: EnergyDay): number {
    const kiloWatts = (energyDay?.energyDay ?? 0) / (1000 * 3600);
    return Math.round(kiloWatts*1000)/1000;
  }

}
