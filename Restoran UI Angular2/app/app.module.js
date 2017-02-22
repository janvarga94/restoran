"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var rezervacija_component_1 = require("./rezervacija/rezervacija.component");
var prijateljstvo_service_1 = require("./services/prijateljstvo.service");
var LimitDuzineListe_1 = require("./pipes/LimitDuzineListe");
var gosti_service_1 = require("./services/gosti.service.");
var activation_component_1 = require("./accountActivation/activation.component");
var ContainsString_1 = require("./pipes/ContainsString");
var gostProfil_component_1 = require("./gost profil/gostProfil.component");
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var forms_1 = require("@angular/forms");
var http_1 = require("@angular/http");
var router_1 = require("@angular/router");
var angular2_toaster_1 = require("angular2-toaster");
var app_component_1 = require("./app.component");
var header_component_1 = require("./header/header.component");
var welcome_component_1 = require("./home/welcome.component");
var restorani_component_1 = require("./restorani/restorani.component");
var restoranDetail_component_1 = require("./restoran detail/restoranDetail.component");
//import { PageNotFoundComponent} from './page not found/pageNotFound.component';
var menazerView_component_1 = require("./menazerSistemaView/menazerView.component");
var login_component_1 = require("./register login/login.component");
var register_component_1 = require("./register login/register.component");
//import { LimitDuzineStringa} from './pipes/LimitDuzineStringa';
var restorani_service_1 = require("./services/restorani.service");
var login_service_1 = require("./services/login.service");
var notification_service_1 = require("./services/notification.service");
var zaposleni_component_1 = require("./zaposleni/zaposleni.component");
var zaposleniDetail_component_1 = require("./zaposleniDetail/zaposleniDetail.component");
var welcome_service_1 = require("./services/welcome.service");
var LimitDuzineStringa_1 = require("./pipes/LimitDuzineStringa");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [
            platform_browser_1.BrowserModule,
            forms_1.FormsModule,
            http_1.HttpModule,
            angular2_toaster_1.ToasterModule,
            router_1.RouterModule.forRoot([
                { path: 'restorani', component: restorani_component_1.RestoraniComponent },
                { path: 'restoran/:id', component: restoranDetail_component_1.RestoranDetailComponent },
                { path: 'zaposleni', component: zaposleni_component_1.ZaposleniComponent },
                { path: 'zaposleni/:email', component: zaposleniDetail_component_1.ZaposleniDetailComponent },
                { path: 'welcome', component: welcome_component_1.WelcomeComponent },
                { path: 'login', component: login_component_1.LoginComponent },
                { path: 'register', component: register_component_1.RegisterComponent },
                { path: 'gostProfil', component: gostProfil_component_1.GostProfilComponent },
                { path: 'sistemMenadzer', component: menazerView_component_1.MenazerSistemaViewComponent },
                { path: 'activateAccount/:token', component: activation_component_1.ActivationComponent },
                { path: 'rezervacija', component: rezervacija_component_1.RezervacijaComponent },
                { path: '', redirectTo: 'welcome', pathMatch: 'full' },
            ]),
        ],
        declarations: [
            app_component_1.AppComponent,
            welcome_component_1.WelcomeComponent,
            restorani_component_1.RestoraniComponent,
            zaposleni_component_1.ZaposleniComponent,
            zaposleniDetail_component_1.ZaposleniDetailComponent,
            restoranDetail_component_1.RestoranDetailComponent,
            header_component_1.HeaderComponent,
            menazerView_component_1.MenazerSistemaViewComponent,
            register_component_1.RegisterComponent,
            login_component_1.LoginComponent,
            gostProfil_component_1.GostProfilComponent,
            activation_component_1.ActivationComponent,
            rezervacija_component_1.RezervacijaComponent,
            LimitDuzineStringa_1.LimitDuzineStringa,
            LimitDuzineListe_1.LimitDuzineListe,
            ContainsString_1.ContainsString
        ],
        providers: [
            restorani_service_1.RestoranService,
            login_service_1.LoginService,
            welcome_service_1.WelcomeService,
            notification_service_1.Notificator,
            gosti_service_1.GostiService,
            prijateljstvo_service_1.PrijateljstvoService,
            angular2_toaster_1.ToasterService
        ],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map