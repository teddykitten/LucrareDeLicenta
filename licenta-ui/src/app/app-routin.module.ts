import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ActorListComponent} from './actor-list/actor-list.component';
import {ActorAddComponent} from "./actor-add/actor-add.component";
import {MovieListComponent} from "./movie-list/movie-list.component";
import {MovieAddComponent} from "./movie-add/movie-add.component";
import {ActorDetailsComponent} from "./actor-details/actor-details.component";
import {MovieDetailsComponent} from "./movie-details/movie-details.component";
import {LoginComponent} from "./login/login.component";


const routes: Routes = [
  {path: '', redirectTo: '', pathMatch: 'full'},
  {path: 'actor', component: ActorListComponent},
  {path: 'actor/:id', component: ActorDetailsComponent},
  {path: 'login', component: LoginComponent},
  {path: 'movie', component: MovieListComponent},
  {path: 'movie-add', component: MovieAddComponent},
  {path: 'movie/:id', component: MovieDetailsComponent},
  {path: 'actor-add', component: ActorAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
