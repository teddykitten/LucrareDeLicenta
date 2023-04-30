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

@NgModule({
  declarations: [
    AppComponent,
    ActorListComponent,
    LoginComponent,
    ActorAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ActorService, AuthenticationService, httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
