import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ActorListComponent} from './actor-list/actor-list.component';
import {UserLoginComponent} from "./user-login/user-login.component";

const routes: Routes = [
  {path: 'actor-list', component: ActorListComponent},
  {path: 'login', component: UserLoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
