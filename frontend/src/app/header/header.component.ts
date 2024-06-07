import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  public router: Router;

  constructor(private routerService: Router) {
    this.router = routerService;
  }

  ngOnInit() {
    // ...
  }

  isActive(route: string): boolean {
    return this.router.url.startsWith(route);
  }
}
