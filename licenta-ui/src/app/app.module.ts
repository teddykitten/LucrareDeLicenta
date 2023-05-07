import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routin.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {ActorListComponent} from './actor-list/actor-list.component';
import {ActorService} from './service/actor-service';
import {LoginComponent} from "./login/login.component";
import {AuthenticationService} from "./login/authentication-service";
import {httpInterceptorProviders} from "./login/authguard";
import {ActorAddComponent} from "./actor-add/actor-add.component";
import {MovieListComponent} from "./movie-list/movie-list.component";
import {MovieService} from "./service/movie-service";
import {MovieAddComponent} from "./movie-add/movie-add.component";
import {HallListComponent} from "./hall-list/hall-list.component";
import {HallAddComponent} from "./hall-add/hall-add.component";
import {HallService} from "./service/hall-service";
import {SeatService} from "./service/seat-service";
import {ActorDetailsComponent} from "./actor-details/actor-details.component";

@NgModule({
  declarations: [
    AppComponent,
    ActorListComponent,
    LoginComponent,
    ActorAddComponent,
    MovieListComponent,
    MovieAddComponent,
    HallListComponent,
    HallAddComponent,
    ActorDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ActorService, MovieService, AuthenticationService, HallService, SeatService, httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
