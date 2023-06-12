import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routin.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {ActorListComponent} from './actor-list/actor-list.component';
import {ActorAddComponent} from "./actor-add/actor-add.component";
import {MovieListComponent} from "./movie-list/movie-list.component";
import {MovieAddComponent} from "./movie-add/movie-add.component";
import {HallListComponent} from "./hall-list/hall-list.component";
import {HallAddComponent} from "./hall-add/hall-add.component";
import {HallService} from "./service/hall-service";
import {SeatService} from "./service/seat-service";
import {ActorDetailsComponent} from "./actor-details/actor-details.component";
import {MovieDetailsComponent} from "./movie-details/movie-details.component";
import {ActorService} from "./service/actor-service";
import {MovieService} from "./service/movie-service";
import {MatSelectModule} from "@angular/material/select";
import {MatFormFieldModule} from "@angular/material/form-field";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NgxMatSelectSearchModule} from "ngx-mat-select-search";
import {MatIconModule} from "@angular/material/icon";
import {MatGridListModule} from "@angular/material/grid-list";
import {LoginComponent} from "./login/login.component";
import {AdminComponent} from "./admin/admin.component";
import {UserComponent} from "./user/user.component";
import {ForbiddenComponent} from "./forbidden/forbidden.component";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    UserComponent,
    ForbiddenComponent,
    ActorAddComponent,
    MovieListComponent,
    MovieAddComponent,
    HallListComponent,
    HallAddComponent,
    ActorDetailsComponent,
    ActorListComponent,
    MovieDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatFormFieldModule,
    NgxMatSelectSearchModule,
    MatIconModule,
    MatGridListModule,
    RouterModule
  ],
  providers: [ActorService, MovieService, ActorService, HallService, SeatService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
