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
Object.defineProperty(exports, "__esModule", { value: true });
var restorani_service_1 = require("./../services/restorani.service");
var rezervacija_service_1 = require("./../services/rezervacija.service");
var core_1 = require("@angular/core");
var welcome_service_1 = require("../services/welcome.service");
var login_service_1 = require("../services/login.service");
var WelcomeComponent = (function () {
    function WelcomeComponent(_restoranService, _rezervacijeService, _welcomeService, _loginService) {
        this._restoranService = _restoranService;
        this._rezervacijeService = _rezervacijeService;
        this._welcomeService = _welcomeService;
        this._loginService = _loginService;
        this.pageTitle = 'Welcome people';
        this.poseceniRestorani = [];
        this.sviRestorani = [];
        this.ulogovan = null;
        this.jela = [];
    }
    WelcomeComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._restoranService.getSviRestorani().subscribe(function (restorani) {
            _this.sviRestorani = restorani;
        });
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            _this.ulogovan = ulogovan;
            if (ulogovan) {
                _this.gostEmail = ulogovan.email;
                // this._rezervacijeService.getRezervacije(ulogovan.email).subscribe(rezervacijeIRezervacije => {
                //     for(var i = 0; i < rezervacijeIRezervacije.length; i++){
                //         var curr = rezervacijeIRezervacije[i];
                //         if(this.poseceniRestorani.filter(t => t.restoranId == curr.restoranId).length == 0){
                //             this.poseceniRestorani.push(curr);
                //         }
                //     }
                // });
                _this._welcomeService.getRestoraniForUser(_this.gostEmail).subscribe(function (restorani) {
                    _this.poseceniRestorani = restorani;
                    console.log(restorani);
                });
            }
        });
    };
    WelcomeComponent.prototype.rate = function (restoranId, gostEmail, ocena) {
        var _this = this;
        console.log(restoranId + " , " + gostEmail + " , " + ocena);
        this._welcomeService.postOcenaForRestoran(restoranId, ocena, gostEmail).subscribe(function (response) {
            // for (let restoran of this.poseceniRestorani) {
            //     if (restoran.restoranId == restoranId) {
            //         let ocena = this._welcomeService.getOcenaForRestoran(restoranId);
            //         console.log(ocena);
            //         restoran.ocena = ocena;
            //     }
            // }
            _this._welcomeService.getRestoraniForUser(_this.gostEmail).subscribe(function (restorani) {
                _this.poseceniRestorani = restorani;
                console.log(restorani);
            });
        });
    };
    WelcomeComponent.prototype.getJela = function (restoran) {
        var _this = this;
        console.log(restoran);
        this.jela = [];
        this._welcomeService.getJelaForRestoran(restoran.restoranId, this.gostEmail).subscribe(function (response) {
            _this.jela = response;
        });
    };
    WelcomeComponent.prototype.rateJelo = function (jelo, ocena) {
        var _this = this;
        this._welcomeService.oceniJelo(jelo[0], jelo[1], this.gostEmail, ocena).subscribe(function (response) {
            _this._welcomeService.getJelaForRestoran(jelo[1], _this.gostEmail).subscribe(function (response) {
                _this.jela = response;
            });
        });
    };
    WelcomeComponent.prototype.getDatum = function (milli) {
        return new Date(milli).toLocaleString();
    };
    return WelcomeComponent;
}());
WelcomeComponent = __decorate([
    core_1.Component({
        templateUrl: 'app/welcome/welcome.component.html'
    }),
    __metadata("design:paramtypes", [restorani_service_1.RestoranService, rezervacija_service_1.RezervacijaService, welcome_service_1.WelcomeService, login_service_1.LoginService])
], WelcomeComponent);
exports.WelcomeComponent = WelcomeComponent;
//# sourceMappingURL=welcome.component.js.map