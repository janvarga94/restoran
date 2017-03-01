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
var Rx_1 = require("rxjs/Rx");
var notification_service_1 = require("./../services/notification.service");
var router_1 = require("@angular/router");
var rezervacija_service_1 = require("./../services/rezervacija.service");
var login_service_1 = require("./../services/login.service");
var prijateljstvo_service_1 = require("./../services/prijateljstvo.service");
var core_1 = require("@angular/core");
var restorani_service_1 = require("../services/restorani.service");
var RezervacijaComponent = (function () {
    function RezervacijaComponent(_router, _notificator, route, _rezervacijaService, _restoranService, _loginService, _prijateljstvoService) {
        this._router = _router;
        this._notificator = _notificator;
        this.route = route;
        this._rezervacijaService = _rezervacijaService;
        this._restoranService = _restoranService;
        this._loginService = _loginService;
        this._prijateljstvoService = _prijateljstvoService;
        this.danas = new Date(Date.now());
        this.stage = 1;
        this._stoloviResponse = [];
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
        this.gostSaKojimRadimoSubject = new Rx_1.BehaviorSubject(null);
        this.gostSaKojimRadimo = this.gostSaKojimRadimoSubject.asObservable();
        this._odabraniDatum = new Date();
    }
    ;
    RezervacijaComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.casDolaska = 12;
        this.minutDolaska = 0;
        this.duzinaBoravka = 2;
        this.danas = new Date(Date.now());
        this.route.params.subscribe(function (params) {
            var gost = params['gost'];
            if (gost != undefined && gost != null) {
                _this.gostSaKojimRadimoSubject.next({ email: atob(gost) });
            }
            else {
                _this._loginService.ulogovan.subscribe(function (ulogovan) {
                    _this.gostSaKojimRadimoSubject.next(ulogovan);
                });
            }
        });
        this.gostSaKojimRadimo.subscribe(function (user) {
            if (user != null) {
                _this._prijateljstvoService.GetPrijateljeOf(user.email).subscribe(function (prijatelji) {
                    _this._prijatelji = prijatelji;
                    _this.nepozvaniPrijatelji = _this._nepozvaniPrijatelji = prijatelji;
                });
            }
        });
        this.route.params.subscribe(function (params) {
            _this._rezervacijaService.getStolovi(params['idRestorana']).subscribe(function (stolovi) {
                _this._stoloviResponse = stolovi;
                _this.stolovi = stolovi.map(function (sto) { return { idStola: sto.idStola, zauzet: false }; });
            });
        });
    };
    RezervacijaComponent.prototype.proveriZauzetostStolovaZaOdabraniDolazakOdlazak = function () {
        this._odabraniDatum.setHours(this.casDolaska);
        this._odabraniDatum.setMinutes(this.minutDolaska);
        var dolazakGosta = this._odabraniDatum.getTime();
        var odlazakGosta = this._odabraniDatum.getTime() + this.duzinaBoravka * 60 * 60 * 1000;
        this.stolovi = this._stoloviResponse.map(function (sto) {
            var slobodneZauzetosti = sto.zauzetost
                .filter(function (z) {
                var pocetak = new Date(z.pocetak).getTime();
                var kraj = new Date(z.kraj).getTime();
                return (pocetak < dolazakGosta && kraj < dolazakGosta) || (pocetak > odlazakGosta && kraj > odlazakGosta);
            });
            return {
                idStola: sto.idStola,
                zauzet: slobodneZauzetosti.length != sto.zauzetost.length
            };
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
        this.odabraniSto = sto;
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
    RezervacijaComponent.prototype.rezervisi = function () {
        var _this = this;
        this.gostSaKojimRadimo.subscribe(function (gost) {
            _this._rezervacijaService.rezervisi({ pocetak: _this._odabraniDatum.getTime(),
                kraj: _this._odabraniDatum.getTime() + +_this.duzinaBoravka * 60 * 60 * 1000,
                rezervant: gost.email,
                idStola: _this.odabraniSto.idStola,
                pozvaniPrijatelji: _this._pozvaniPrijatelji.map(function (p) { return p.email; }),
            }).subscribe(function (response) {
                if (response['Success'] == true) {
                    _this._notificator.notifySuccess("Uspesno data rezervacija");
                    _this.route.params.subscribe(function (params) {
                        var gost = params['gost'];
                        if (gost != undefined && gost != null) {
                            _this._router.navigate(['/rezervacije/' + gost]);
                        }
                        else {
                            _this._router.navigate(['/rezervacije']);
                        }
                    });
                }
                else {
                    _this._notificator.notifyError(response['Message']);
                }
            });
        });
    };
    return RezervacijaComponent;
}());
RezervacijaComponent = __decorate([
    core_1.Component({
        selector: 'restorani',
        templateUrl: 'app/rezervacija/rezervacija.component.html',
        styles: ['.disabledElement {  pointer-events:none; opacity:0.5 }']
    }),
    __metadata("design:paramtypes", [router_1.Router, notification_service_1.Notificator, router_1.ActivatedRoute, rezervacija_service_1.RezervacijaService, restorani_service_1.RestoranService, login_service_1.LoginService, prijateljstvo_service_1.PrijateljstvoService])
], RezervacijaComponent);
exports.RezervacijaComponent = RezervacijaComponent;
//# sourceMappingURL=rezervacija.component.js.map