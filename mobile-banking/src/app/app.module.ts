import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CartoesComponent } from './cartoes/cartoes.component';
import { PixComponent } from './pix/pix.component';

@NgModule({
  declarations: [
    AppComponent,
    CartoesComponent,
    PixComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
