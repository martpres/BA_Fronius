import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {BackendApiService} from "../service/backend-api.service";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {AcPowerGrid} from "../dto/acPowerGrid.model";
import * as moment from "moment";
import {EnergyDay} from "../dto/energyDay.model";
import {PricesService} from "../service/prices.service";

@Component({
  selector: 'app-ac-power-into-grid',
  templateUrl: './ac-power-into-grid.component.html',
  styleUrls: ['./ac-power-into-grid.component.scss']
})
export class AcPowerIntoGridComponent implements OnInit, OnDestroy {
  public calculatedAcEnergyIntoGridDay?: EnergyDay;
  public chartData?: any[];
  public initialDate = new Date();
  public maxDate = new Date();
  private sub1?: Subscription;
  private sub2?: Subscription;
  private data?: QueryDslResponse<AcPowerGrid>;
  private refreshMilliSeconds = 60000;
  private interval?: any;

  constructor(private backendService: BackendApiService,
              private dateTimeService: DateTimeService,
              public pricesService: PricesService) {
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

  public loadPriceForDate(): void {
    const startDate = moment(this.initialDate).utc();
    this.pricesService.loadPriceForDate(startDate.format());
  }

  public sendRequest(): void {
    const startDate = moment(this.initialDate).startOf('day').utc();
    const endDate = moment(this.initialDate).endOf('day').utc();

    this.sub1 = this.backendService.loadAcPowerGrid(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.data = e;
      this.mapRequestToChart();
    });

    this.sub2 = this.backendService.loadCalculatedAcEnergyIntoGridDay(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.calculatedAcEnergyIntoGridDay = e;
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
      let date = this.dateTimeService.convertUtcToLocalTimeZone(e.timestamp);
      if (typeof e.acPowerGrid === 'number') {
        const value = e.acPowerGrid > 0 ? 0 : e.acPowerGrid;
        array.push({name: date, value: -value});
      }
    });
    this.chartData?.push({name: 'Power into Grid', series: array});
  }

  public convertAndRoundEnergy(energyDay: EnergyDay): number {
    const kiloWatts = (energyDay?.energyDay ?? 0) / (3600000);
    return Math.round(kiloWatts * 100) / 100;
  }

  public allValuesAreZero(chartData?: any[]): boolean {
    if (chartData === undefined || !Array.isArray(chartData)) {
      return false;
    }
    return chartData.every(data => data.series.every((obj: any) => obj.value === 0));
  }

}
