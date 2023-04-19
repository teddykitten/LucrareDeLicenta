import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ActorListComponent} from './actor-list/actor-list.component';
import {LoginComponent} from "./login/login.component";


const routes: Routes = [
  {path: 'actor-list', component: ActorListComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
