import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { ToastModule} from 'ng2-toastr/ng2-toastr';

import { AppComponent }  from './app.component';
import { HeaderComponent } from './header/header.component';
import { WelcomeComponent } from './home/welcome.component';
import { RestoraniComponent} from './restorani/restorani.component';
import { RestoranDetailComponent} from './restoran detail/restoranDetail.component';
import { PageNotFoundComponent} from './page not found/pageNotFound.component';
import { MenazerSistemaViewComponent} from './menazerSistemaView/menazerView.component';

import { LimitDuzineStringa} from './pipes/LimitDuzineStringa';

import {RestoranService} from './services/restorani.service';
import {LoginService} from './services/login.service';
import {Notificator} from './services/notification.service';
import {ZaposleniComponent} from "./zaposleni/zaposleni.component";


@NgModule({
  imports: [
     BrowserModule,
     FormsModule,
     HttpModule,
     ToastModule,
     RouterModule.forRoot([
        { path: 'restorani', component : RestoraniComponent},
        { path: 'restoran/:id', component : RestoranDetailComponent},
         { path: 'zaposleni', component : ZaposleniComponent},
        { path: 'welcome', component : WelcomeComponent},
        { path: 'sistemMenadzer', component : MenazerSistemaViewComponent},
        { path: '', redirectTo: 'welcome', pathMatch: 'full'},
        { path: '**', component : PageNotFoundComponent},

     ]), 
  ],
  declarations: [
    AppComponent,
    WelcomeComponent,
    RestoraniComponent,
    ZaposleniComponent,
    RestoranDetailComponent,
    HeaderComponent,
    PageNotFoundComponent,
    MenazerSistemaViewComponent,
    LimitDuzineStringa
   ],
   providers: [
     RestoranService,
     LoginService,
     Notificator,   
   ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
