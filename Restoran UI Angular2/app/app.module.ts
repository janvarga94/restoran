import { ContainsString } from './pipes/ContainsString';
import { GostProfilComponent } from './gost profil/gostProfil.component';
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
//import { PageNotFoundComponent} from './page not found/pageNotFound.component';
import { MenazerSistemaViewComponent} from './menazerSistemaView/menazerView.component';
import { LoginComponent} from './register login/login.component';
import { RegisterComponent} from './register login/register.component';
//import { LimitDuzineStringa} from './pipes/LimitDuzineStringa';

import {RestoranService} from './services/restorani.service';
import {LoginService} from './services/login.service';
import {Notificator} from './services/notification.service';
import {ZaposleniComponent} from "./zaposleni/zaposleni.component";
import {ZaposleniDetailComponent} from "./zaposleniDetail/zaposleniDetail.component";
import {WelcomeService} from "./services/welcome.service";


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
         { path: 'zaposleni/:email', component : ZaposleniDetailComponent},
         { path: 'welcome', component : WelcomeComponent},
         { path: 'login', component : LoginComponent},
         { path: 'register', component : RegisterComponent},
         { path: 'gostProfil', component : GostProfilComponent},
        { path: 'sistemMenadzer', component : MenazerSistemaViewComponent},
        { path: '', redirectTo: 'welcome', pathMatch: 'full'},
  //      { path: '**', component : PageNotFoundComponent},

     ]), 
  ],
  declarations: [
    AppComponent,
    WelcomeComponent,
    RestoraniComponent,
      ZaposleniComponent,
      ZaposleniDetailComponent,
    RestoranDetailComponent,
    HeaderComponent,
    MenazerSistemaViewComponent,
    RegisterComponent,
    LoginComponent,
    GostProfilComponent,

    ContainsString

   ],
   providers: [
     RestoranService,
     LoginService,
       WelcomeService,
     Notificator
   ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
