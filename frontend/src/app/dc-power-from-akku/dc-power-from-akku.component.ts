import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {BackendApiService} from "../service/backend-api.service";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {DcPowerAkku} from "../dto/dcPowerAkku.model";
import * as moment from "moment";
import {EnergyDay} from "../dto/energyDay.model";

@Component({
  selector: 'app-dc-power-from-akku',
  templateUrl: './dc-power-from-akku.component.html',
  styleUrls: ['./dc-power-from-akku.component.scss']
})
export class DcPowerFromAkkuComponent implements OnInit, OnDestroy {
  public calculatedDcEnergyFromAkkuDay?: EnergyDay;
  public chartData?: any[];
  public initialDate = new Date();
  public maxDate = new Date();
  private sub1?: Subscription;
  private sub2?: Subscription;
  private data?: QueryDslResponse<DcPowerAkku>;
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
    const startDate = moment(this.initialDate).startOf('day').utc();
    const endDate = moment(this.initialDate).endOf('day').utc();

    this.sub1 = this.backendService.loadDcPowerAkku(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.data = e;
      this.mapRequestToChart();
    });

    this.sub2 = this.backendService.loadCalculatedDcEnergyFromAkkuDay(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.calculatedDcEnergyFromAkkuDay = e;
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
      if (typeof e.dcPowerAkku === 'number') {
        const value = e.dcPowerAkku < 0 ? 0 : e.dcPowerAkku;
        array.push({ name: date, value: value });
      }
    });
    this.chartData?.push({name: 'Power from Akku', series: array});
  }

  public convertAndRoundEnergy(energyDay: EnergyDay): number {
    const kiloWatts = (energyDay?.energyDay ?? 0) / (1000 * 3600);
    return Math.round(kiloWatts*1000)/1000;
  }

}

