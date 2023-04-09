import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routin.module';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {ActorListComponent} from './actor-list/actor-list.component';
import {ActorServiceService} from './actor-service.service';

@NgModule({
  declarations: [
    AppComponent,
    ActorListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ActorServiceService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
