import {Component, OnInit} from '@angular/core';
import {BackendApiService} from "../service/backend-api.service";

@Component({
  selector: 'app-current-ac',
  templateUrl: './current-ac.component.html',
  styleUrls: ['./current-ac.component.scss']
})
export class CurrentACComponent implements OnInit{

  constructor(private backendService: BackendApiService) {
  }

  ngOnInit(): void {
    this.backendService.loadCurrentAC().subscribe((e)=> console.log(e));
  }
}
