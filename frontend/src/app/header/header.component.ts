import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  public router: Router;

  constructor(private routerService: Router) {
    this.router = routerService;
  }

  isActive(route: string): boolean {
    return this.router.url === route;
  }
}
