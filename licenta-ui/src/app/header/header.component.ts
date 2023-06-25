import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserAuthService} from '../_service/user-auth.service';
import {UserService} from "../_service/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  // @ts-ignore
  constructor(
    private userAuthService: UserAuthService,
    private router: Router,
    public userService: UserService
  ) {
  }

  ngOnInit(): void {
  }

  public isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  public logout() {
    this.userAuthService.clear();
    this.router.navigate(['/cinema']);
  }
}
