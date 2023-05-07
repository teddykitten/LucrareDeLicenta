import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ActorListComponent} from './actor-list/actor-list.component';
import {LoginComponent} from "./login/login.component";
import {ActorAddComponent} from "./actor-add/actor-add.component";
import {MovieListComponent} from "./movie-list/movie-list.component";
import {MovieAddComponent} from "./movie-add/movie-add.component";
import {HallListComponent} from "./hall-list/hall-list.component";
import {HallAddComponent} from "./hall-add/hall-add.component";
import {ActorDetailsComponent} from "./actor-details/actor-details.component";


const routes: Routes = [
  {path: 'actor-list', component: ActorListComponent},
  {path: 'login', component: LoginComponent},
  {path: 'actor-add', component: ActorAddComponent},
  {path: 'movie-list', component: MovieListComponent},
  {path: 'movie-add', component: MovieAddComponent},
  {path: 'hall-list', component: HallListComponent},
  {path: 'hall-add', component: HallAddComponent},
  {path: 'actor-details', component: ActorDetailsComponent},
  {path: 'actor/:id', component: ActorDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
