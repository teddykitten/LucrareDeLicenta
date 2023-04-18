import {Component, OnInit} from '@angular/core';
import {first} from 'rxjs/operators';

import {User} from '../model/user';
import {UserService} from '../service/user-service';

@Component({templateUrl: 'home.component.html'})
export class HomeComponent implements OnInit {
  currentUser: User;
  users: User[] = [];

  constructor(private userService: UserService) {
    // @ts-ignore
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

}
