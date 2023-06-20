import {Component, OnInit} from '@angular/core';
import {UserService} from "../_service/user.service";
import {UserAuthService} from "../_service/user-auth.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  login(loginForm: NgForm) {
    this.userService.login(loginForm.value).subscribe(
      {
        next: (res: any) => {
          console.log(res.jwtToken);
          console.log(res.user.role);

          this.userAuthService.setRoles(res.user.role);
          this.userAuthService.setToken(res.user.jwtToken);

          const role = res.user.role[0].roleName;
          if (role == 'Admin') {
            this.router.navigate(['/admin']);
          } else {
            this.router.navigate(['/user']);
          }
        },
        error: (e) => console.error(e)
      });
  }


}
