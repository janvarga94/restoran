import { StatsRestoranaComponent } from './statsRestorana/statsRestorana.component';
import { StatService } from './services/stat.service';
import { WelcomeComponent } from './welcome/welcome.component';
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
import {AgmCoreModule} from "angular2-google-maps/core";
import {ZaposleniDetailService} from "./services/zaposleniDetail.service";
import {PushNotificationsService} from "angular2-notifications";
import {NoviZaposleniComponent} from "./noviZaposleni/noviZaposleni.component";
import {ZaposleniService} from "./services/zaposleni.service";
import {NoviRestoranComponent} from "./noviRestoran/noviRestoran.component";
import {DodatiReonComponent} from "./dodavanjeReona/dodavanjeReona.component";
import {JelovnikComponent} from "./jelovnik/jelovnik.component";
import {StatistikaComponent} from "./statistika/statistika.component";
import {DodavanjeStolaComponent} from "./dodavanjeStola/dodavanjeStola.component";
import {PotraznjaNamirnicaComponent} from "./potraznjaNamirnica/potraznjaNamirnica.component";
import {NovoJeloComponent} from "./Jelo/novoJelo.component";
import {ZakaziRadComponent} from "./zakazi_rad/zakaziRad.component";
import {ZakaziRadService} from "./services/zakaziRad.service";
import {PotraznjeComponent} from "./potraznje/potraznje.component";
import {PrimljenePonudeComponent} from "./primljenePonude/primljenePonude.component";
import {mojePonudeComponent} from "./mojePonude/mojePonude.component";
import {MMRPComponent} from "./mmrp/mmrp.component";



@NgModule({
  imports: [
     BrowserModule,
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
         {path: 'novirestoran', component : NoviRestoranComponent},
         {path: 'novizaposleni', component : NoviZaposleniComponent},
         {path: 'novizaposleni/:email', component : NoviZaposleniComponent},
         {path: 'zakazi_rad/:email', component : ZakaziRadComponent},
         { path: 'welcome', component : WelcomeComponent},
         { path: 'login', component : LoginComponent},
         { path: 'register', component : RegisterComponent},
         { path: 'gostProfil', component : GostProfilComponent},
        { path: 'sistemMenadzer', component : MenazerSistemaViewComponent},
        { path: 'activateAccount/:token', component : ActivationComponent},
        { path: 'rezervacija/:idRestorana', component: RezervacijaComponent},
         { path: 'rezervacija/:idRestorana/:gost', component: RezervacijaComponent},
         { path: 'rezervacije', component: RezervacijeComponent},
         { path: 'rezervacije/:gost', component: RezervacijeComponent},
         { path: 'stats/:idRestorana', component : StatsRestoranaComponent},
        { path: '', redirectTo: 'login', pathMatch: 'full'},
         {path: 'dodavanjereona', component: DodatiReonComponent},
         {path:'statistika', component: StatistikaComponent},
         {path: 'jelovnik', component: JelovnikComponent},
         {path: 'dodavanjestola', component: DodavanjeStolaComponent },
         {path: 'potraznajnamirnica', component: PotraznjaNamirnicaComponent},
         {path: 'novojelo', component: NovoJeloComponent},
         {path: 'potraznje', component: PotraznjeComponent},
         {path: 'primljeneponude', component: PrimljenePonudeComponent},
         {path: 'mojeponude', component : mojePonudeComponent},
         {path: 'mmrp', component : MMRPComponent},
        { path: '', redirectTo: 'welcome', pathMatch: 'full'},
  //      { path: '**', component : PageNotFoundComponent},

     ]), 
  ],
  declarations: [
    AppComponent,
    WelcomeComponent,
    RestoraniComponent,
      NoviZaposleniComponent,
      NoviRestoranComponent,
      ZakaziRadComponent,
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
    StatsRestoranaComponent,
    GostPozvanPipe,
    GostNeozvanPipe,
    LimitDuzineStringa,
    LimitDuzineListe,
    ContainsString,
      DodatiReonComponent,
      JelovnikComponent,
      StatistikaComponent,
      DodavanjeStolaComponent,
      PotraznjaNamirnicaComponent,
      NovoJeloComponent,
      PotraznjeComponent,
      PrimljenePonudeComponent,
      mojePonudeComponent,
      MMRPComponent


   ],
   providers: [
     RestoranService,
     LoginService,
     WelcomeService,
     Notificator,
     GostiService,
     PrijateljstvoService,
     ToasterService,
     RezervacijaService,
       ZakaziRadService,
       PushNotificationsService,
       ZaposleniService,
       ZaposleniDetailService,
       StatService
   ],
    bootstrap: [ AppComponent ]
})
export class AppModule {

 }
