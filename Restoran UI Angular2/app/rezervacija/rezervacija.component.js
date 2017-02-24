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
var router_1 = require("@angular/router");
var rezervacija_service_1 = require("./../services/rezervacija.service");
var login_service_1 = require("./../services/login.service");
var prijateljstvo_service_1 = require("./../services/prijateljstvo.service");
var core_1 = require("@angular/core");
var restorani_service_1 = require("../services/restorani.service");
var RezervacijaComponent = (function () {
    function RezervacijaComponent(route, _rezervacijaService, _restoranService, _loginService, _prijateljstvoService) {
        this.route = route;
        this._rezervacijaService = _rezervacijaService;
        this._restoranService = _restoranService;
        this._loginService = _loginService;
        this._prijateljstvoService = _prijateljstvoService;
        this.danas = new Date(Date.now());
        this.ulogovan = null;
        this.stage = 1;
        this.stolovi = [
            { zauzet: false },
            { zauzet: true },
            { zauzet: true },
            { zauzet: false },
            { zauzet: false },
            { zauzet: true },
            { zauzet: true },
            { zauzet: false },
        ];
        this._pozvaniPrijatelji = [];
        this._nepozvaniPrijatelji = [];
        this.pozvaniPrijatelji = [];
        this.nepozvaniPrijatelji = [];
        this._prijatelji = [];
        this._search1 = "";
        this._odabraniDatum = new Date();
    }
    RezervacijaComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.casDolaska = 12;
        this.minutDolaska = 0;
        this.duzinaBoravka = 2;
        this.danas = new Date(Date.now());
        this._loginService.ulogovan.subscribe(function (user) {
            _this.ulogovan = user;
            if (user != null) {
                _this._prijateljstvoService.GetPrijateljeOf(user.email).subscribe(function (prijatelji) {
                    _this._prijatelji = prijatelji;
                    _this.nepozvaniPrijatelji = _this._nepozvaniPrijatelji = prijatelji;
                });
            }
        });
        this.route.params.subscribe(function (params) {
            _this._rezervacijaService.getStolovi(params['idRestorana']).subscribe(function (stolovi) {
                console.log(stolovi);
            });
        });
    };
    Object.defineProperty(RezervacijaComponent.prototype, "search1", {
        get: function () {
            return this._search1;
        },
        set: function (e) {
            this._search1 = e;
            this.pozvaniPrijatelji = this._pozvaniPrijatelji.filter(function (p) {
                return (p.email + " " + p.ime + " " + p.prezime).toLowerCase().indexOf(e.toLowerCase()) > -1;
            });
            this.nepozvaniPrijatelji = this._nepozvaniPrijatelji.filter(function (p) {
                return (p.email + " " + p.ime + " " + p.prezime).toLowerCase().indexOf(e.toLowerCase()) > -1;
            });
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(RezervacijaComponent.prototype, "odabraniDatum", {
        get: function () {
            return this._odabraniDatum.toISOString().substring(0, 10);
        },
        set: function (e) {
            e = e.split('-');
            var d = new Date(Date.UTC(e[0], e[1] - 1, e[2]));
            this._odabraniDatum.setFullYear(d.getUTCFullYear(), d.getUTCMonth(), d.getUTCDate());
        },
        enumerable: true,
        configurable: true
    });
    RezervacijaComponent.prototype.odaberiSto = function (sto) {
        this.odaberiSto = sto;
        this.stage = 3;
    };
    RezervacijaComponent.prototype.pozovi = function (index) {
        this._pozvaniPrijatelji.push(this.nepozvaniPrijatelji[index]);
        this._nepozvaniPrijatelji.splice(index, 1);
        this.search1 = this.search1;
    };
    RezervacijaComponent.prototype.nepozovi = function (index) {
        this._nepozvaniPrijatelji.push(this.pozvaniPrijatelji[index]);
        this._pozvaniPrijatelji.splice(index, 1);
        this.search1 = this.search1;
    };
    return RezervacijaComponent;
}());
RezervacijaComponent = __decorate([
    core_1.Component({
        selector: 'restorani',
        templateUrl: 'app/rezervacija/rezervacija.component.html',
        styles: ['.disabledElement {  pointer-events:none; opacity:0.5 }']
    }),
    __metadata("design:paramtypes", [router_1.ActivatedRoute, rezervacija_service_1.RezervacijaService, restorani_service_1.RestoranService, login_service_1.LoginService, prijateljstvo_service_1.PrijateljstvoService])
], RezervacijaComponent);
exports.RezervacijaComponent = RezervacijaComponent;
//# sourceMappingURL=rezervacija.component.js.map