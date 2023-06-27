import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ActorListComponent} from './actor-list/actor-list.component';
import {ActorAddComponent} from "./actor-add/actor-add.component";
import {MovieListComponent} from "./movie-list/movie-list.component";
import {MovieAddComponent} from "./movie-add/movie-add.component";
import {ActorDetailsComponent} from "./actor-details/actor-details.component";
import {MovieDetailsComponent} from "./movie-details/movie-details.component";
import {LoginComponent} from "./login/login.component";
import {HallAddComponent} from "./hall-add/hall-add.component";
import {HallListComponent} from "./hall-list/hall-list.component";
import {HallDetailsComponent} from "./hall-details/hall-details.component";
import {ScheduleAddComponent} from "./schedule-add/schedule-add.component";
import {ScheduleListComponent} from "./schedule-list/schedule-list.component";


const routes: Routes = [
  {path: '', redirectTo: '', pathMatch: 'full'},
  {path: 'actor', component: ActorListComponent},
  {path: 'actor/:id', component: ActorDetailsComponent},
  {path: 'actor-add', component: ActorAddComponent},
  {path: 'login', component: LoginComponent},
  {path: 'movie', component: MovieListComponent},
  {path: 'movie-add', component: MovieAddComponent},
  {path: 'movie/:id', component: MovieDetailsComponent},
  {path: 'hall', component: HallListComponent},
  {path: 'hall/:id', component: HallDetailsComponent},
  {path: 'hall-add', component: HallAddComponent},
  {path: 'schedule-add', component: ScheduleAddComponent},
  {path: 'schedule', component: ScheduleListComponent},
  {path: 'schedule/:id', component: ScheduleListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
