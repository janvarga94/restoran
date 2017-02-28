import { RezervacijaService } from './services/rezervacija.service';
import { GostPozvanPipe, GostNeozvanPipe } from './pipes/GostPozvanPipe';
import { RezervacijeComponent } from './rezervacije/rezervacije.component';
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
import {DodatiReonComponent} from "./dodavanjeReona/dodavanjeReona.component";
import {JelovnikComponent} from "./jelovnik/jelovnik.component";

import {PushNotificationsModule} from "angular2-notifications";
import {AgmCoreModule} from "angular2-google-maps/core";


@NgModule({
  imports: [
     BrowserModule,
      PushNotificationsModule,
      AgmCoreModule.forRoot({
          apiKey: 'AIzaSyAB6DgNAa-m2IHEzyFRUdV2bPTeIy0mjuc'
      }),
     FormsModule,
     HttpModule,
     ToasterModule,
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
        { path: 'activateAccount/:token', component : ActivationComponent},
        { path: 'rezervacija/:idRestorana', component: RezervacijaComponent},
         { path: 'rezervacije', component: RezervacijeComponent},
         { path: 'rezervacije/:gost', component: RezervacijeComponent},
        { path: '', redirectTo: 'login', pathMatch: 'full'},
         {path: 'dodavanjereona', component: DodatiReonComponent},
         {path: 'jelovnik', component: JelovnikComponent},
        { path: '', redirectTo: 'welcome', pathMatch: 'full'},
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
    RezervacijeComponent,
    GostPozvanPipe,
    GostNeozvanPipe,
    LimitDuzineStringa,
    LimitDuzineListe,
    ContainsString,
      DodatiReonComponent,
      JelovnikComponent

   ],
   providers: [
     RestoranService,
     LoginService,
     WelcomeService,
     Notificator,
     GostiService,
     PrijateljstvoService,
     ToasterService,
     RezervacijaService
   ],
    bootstrap: [ AppComponent ]
})
export class AppModule {

 }
