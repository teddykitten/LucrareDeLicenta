import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ActorListComponent} from './actor-list/actor-list.component';
import {LoginComponent} from "./login/login.component";
import {ActorAddComponent} from "./actor-add/actor-add.component";


const routes: Routes = [
  {path: 'actor-list', component: ActorListComponent},
  {path: 'login', component: LoginComponent},
  {path: 'actor-add', component: ActorAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
