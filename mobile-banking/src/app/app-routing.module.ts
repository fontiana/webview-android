import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { CartoesComponent } from './cartoes/cartoes.component';
import { PixComponent } from './pix/pix.component';

const routes: Routes = [];

@NgModule({
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      {path: 'cartoes', component: CartoesComponent},
      {path: 'pix', component: PixComponent},
    ]),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
