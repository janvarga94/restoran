"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var forms_1 = require("@angular/forms");
var http_1 = require("@angular/http");
var router_1 = require("@angular/router");
var ng2_toastr_1 = require("ng2-toastr/ng2-toastr");
var app_component_1 = require("./app.component");
var header_component_1 = require("./header/header.component");
var welcome_component_1 = require("./home/welcome.component");
var restorani_component_1 = require("./restorani/restorani.component");
var restoranDetail_component_1 = require("./restoran detail/restoranDetail.component");
var pageNotFound_component_1 = require("./page not found/pageNotFound.component");
var menazerView_component_1 = require("./menazerSistemaView/menazerView.component");
var LimitDuzineStringa_1 = require("./pipes/LimitDuzineStringa");
var restorani_service_1 = require("./services/restorani.service");
var login_service_1 = require("./services/login.service");
var notification_service_1 = require("./services/notification.service");
var zaposleni_component_1 = require("./zaposleni/zaposleni.component");
var zaposleniDetail_component_1 = require("./zaposleniDetail/zaposleniDetail.component");
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
            ng2_toastr_1.ToastModule,
            router_1.RouterModule.forRoot([
                { path: 'restorani', component: restorani_component_1.RestoraniComponent },
                { path: 'restoran/:id', component: restoranDetail_component_1.RestoranDetailComponent },
                { path: 'zaposleni', component: zaposleni_component_1.ZaposleniComponent },
                { path: 'zaposleni/:mbr', component: zaposleniDetail_component_1.ZaposleniDetailComponent },
                { path: 'welcome', component: welcome_component_1.WelcomeComponent },
                { path: 'sistemMenadzer', component: menazerView_component_1.MenazerSistemaViewComponent },
                { path: '', redirectTo: 'welcome', pathMatch: 'full' },
                { path: '**', component: pageNotFound_component_1.PageNotFoundComponent },
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
            pageNotFound_component_1.PageNotFoundComponent,
            menazerView_component_1.MenazerSistemaViewComponent,
            LimitDuzineStringa_1.LimitDuzineStringa
        ],
        providers: [
            restorani_service_1.RestoranService,
            login_service_1.LoginService,
            notification_service_1.Notificator
        ],
        bootstrap: [app_component_1.AppComponent]
    }),
    __metadata("design:paramtypes", [])
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map