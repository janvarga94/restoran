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
var login_service_1 = require("./../services/login.service");
var rezervacija_service_1 = require("./../services/rezervacija.service");
var core_1 = require("@angular/core");
var restorani_service_1 = require("../services/restorani.service");
var RezervacijeComponent = (function () {
    function RezervacijeComponent(_loginService, _restoranService, _rezervacijaService) {
        this._loginService = _loginService;
        this._restoranService = _restoranService;
        this._rezervacijaService = _rezervacijaService;
        this.odabranaRezervacija = {};
        this.rezervacije = [];
        this.jela = [];
        this.pica = [];
        this.randomClasses = [];
        for (var i = 0; i < 20; i++) {
            this.randomClasses.push(this.generateRandomClassButton());
        }
    }
    RezervacijeComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            if (ulogovan != null) {
                _this._rezervacijaService.getRezervacije(ulogovan.email).subscribe(function (rezervacije) {
                    _this.rezervacije = rezervacije;
                });
            }
        });
    };
    RezervacijeComponent.prototype.ucitajJelaIPicaZaRestoran = function (restoranId) {
        var _this = this;
        this._rezervacijaService.getJela(restoranId).subscribe(function (jela) {
            _this.jela = jela;
        });
        this._rezervacijaService.getPica(restoranId).subscribe(function (pica) {
            _this.pica = pica;
        });
    };
    RezervacijeComponent.prototype.randomClassButton = function (index) {
        return this.randomClasses[index];
    };
    RezervacijeComponent.prototype.generateRandomClassButton = function () {
        switch (this.getRandomInt(0, 6)) {
            case 0: return "btn-default";
            case 1: return "btn-primary";
            case 2: return "btn-success";
            case 3: return "btn-info";
            case 4: return "btn-warning";
            case 5: return "btn-danger";
            case 6: return "btn-link";
            default: return "btn-default";
        }
    };
    RezervacijeComponent.prototype.getRandomInt = function (min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min)) + min;
    };
    RezervacijeComponent.prototype.getDate = function (date) {
        return (new Date(date)).toISOString().slice(0, 16);
    };
    RezervacijeComponent.prototype.toFixed = function (num) {
        return num.toFixed(1);
    };
    return RezervacijeComponent;
}());
RezervacijeComponent = __decorate([
    core_1.Component({
        selector: 'restorani',
        templateUrl: 'app/rezervacije/rezervacije.component.html'
    }),
    __metadata("design:paramtypes", [login_service_1.LoginService, restorani_service_1.RestoranService, rezervacija_service_1.RezervacijaService])
], RezervacijeComponent);
exports.RezervacijeComponent = RezervacijeComponent;
//# sourceMappingURL=rezervacije.component.js.map