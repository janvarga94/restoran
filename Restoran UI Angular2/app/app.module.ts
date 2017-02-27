import { RezervacijaComponent } from './rezervacija/rezervacija.component';
import { PrijateljstvoService } from './services/prijateljstvo.service';
import { LimitDuzineListe } from './pipes/LimitDuzineListe';
import { GostiService } from './services/gosti.service.';
import { ActivationComponent } from './accountActivation/activation.component';
import { ContainsString } from './pipes/ContainsString';
import { GostProfilComponent } from './gost profil/gostProfil.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { ToasterModule, ToasterService, ToasterConfig } from 'angular2-toaster';

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
import {LimitDuzineStringa} from "./pipes/LimitDuzineStringa";
import {NoviZaposleniComponent} from "./noviZaposleni/noviZaposleni.component";
import {ZaposleniService} from "./services/zaposleni.service";
import {NoviRestoranComponent} from "./noviRestoran/noviRestoran.component";
import {NoviMenadzerRestoranaComponent} from "./noviMenadzerRestorana/noviMenadzerRestorana.component";
import {NoviPonudjacComponent} from "./noviPonudjac/noviPonudjac.component";



@NgModule({
  imports: [
     BrowserModule,
     FormsModule,
     HttpModule,
     ToasterModule,
     RouterModule.forRoot([
        { path: 'restorani', component : RestoraniComponent},
        { path: 'restoran/:id', component : RestoranDetailComponent},
         { path: 'zaposleni', component : ZaposleniComponent},
         { path: 'zaposleni/:email', component : ZaposleniDetailComponent},
         { path: 'novizaposleni', component : NoviZaposleniComponent},
         {path: 'novirestoran', component: NoviRestoranComponent},
         { path: 'welcome', component : WelcomeComponent},
         { path: 'login', component : LoginComponent},
         { path: 'register', component : RegisterComponent},
         { path: 'gostProfil', component : GostProfilComponent},
        { path: 'sistemMenadzer', component : MenazerSistemaViewComponent},
        { path: 'activateAccount/:token', component : ActivationComponent},
        { path: 'rezervacija', component: RezervacijaComponent},
         { path: 'novimenadzerrestorana',component : NoviMenadzerRestoranaComponent},
         { path: 'noviponudjac', component: NoviPonudjacComponent},
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
    ActivationComponent,
    RezervacijaComponent,
    LimitDuzineStringa,
    LimitDuzineListe,
    ContainsString,
    NoviZaposleniComponent,
      NoviRestoranComponent,
      NoviMenadzerRestoranaComponent,
      NoviPonudjacComponent
   ],
   providers: [
     RestoranService,
     LoginService,
     WelcomeService,
     Notificator,
     GostiService,
     PrijateljstvoService,
     ToasterService,
       ZaposleniService,
   ],
    bootstrap: [ AppComponent ]
})
export class AppModule {

 }
