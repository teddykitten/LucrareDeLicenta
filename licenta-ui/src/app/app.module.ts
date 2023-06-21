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
import {ActorDetailsComponent} from "./actor-details/actor-details.component";
import {MovieDetailsComponent} from "./movie-details/movie-details.component";
import {MatSelectModule} from "@angular/material/select";
import {MatFormFieldModule} from "@angular/material/form-field";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NgxMatSelectSearchModule} from "ngx-mat-select-search";
import {MatIconModule} from "@angular/material/icon";
import {MatGridListModule} from "@angular/material/grid-list";
import {LoginComponent} from "./login/login.component";
import {RouterModule} from "@angular/router";
import {ActorService} from "./_service/actor-service";
import {MovieService} from "./_service/movie-service";
import {HeaderComponent} from "./header/header.component";

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        ActorAddComponent,
        MovieListComponent,
        MovieAddComponent,
        ActorDetailsComponent,
        ActorListComponent,
        MovieDetailsComponent,
        HeaderComponent,
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
  providers: [ActorService, MovieService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
