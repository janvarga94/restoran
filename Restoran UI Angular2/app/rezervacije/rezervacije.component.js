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
var login_service_1 = require("./../services/login.service");
var rezervacija_service_1 = require("./../services/rezervacija.service");
var router_1 = require("@angular/router");
var core_1 = require("@angular/core");
var restorani_service_1 = require("../services/restorani.service");
var RezervacijeComponent = (function () {
    function RezervacijeComponent(route, _notificator, _loginService, _restoranService, _rezervacijaService) {
        this.route = route;
        this._notificator = _notificator;
        this._loginService = _loginService;
        this._restoranService = _restoranService;
        this._rezervacijaService = _rezervacijaService;
        this._search1 = "";
        this._search2 = "";
        this.rezervacije = [];
        this._jela = [];
        this.jela = [];
        this._pica = [];
        this.pica = [];
        this.porucenaJela = [];
        this.porucenaPica = [];
        this.randomClasses = [];
        this.gostSaKojimRadimoSubject = new Rx_1.BehaviorSubject(null);
        this.gostSaKojimRadimo = this.gostSaKojimRadimoSubject.asObservable();
        for (var i = 0; i < 20; i++) {
            this.randomClasses.push(this.generateRandomClassButton());
        }
    }
    ;
    RezervacijeComponent.prototype.ngOnInit = function () {
        var _this = this;
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
        this.gostSaKojimRadimo.subscribe(function (ulogovan) {
            if (ulogovan != null) {
                _this._rezervacijaService.getRezervacije(ulogovan.email).subscribe(function (rezervacije) {
                    _this.rezervacije = rezervacije;
                });
            }
        });
    };
    Object.defineProperty(RezervacijeComponent.prototype, "search1", {
        get: function () {
            return this._search1;
        },
        set: function (e) {
            this._search1 = e;
            this.jela = this._jela.filter(function (j) { return j.nazivJela.toLowerCase().indexOf(e) > -1; });
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(RezervacijeComponent.prototype, "search2", {
        get: function () {
            return this._search2;
        },
        set: function (e) {
            this._search2 = e;
            this.pica = this._pica.filter(function (j) { return j.nazivPica.toLowerCase().indexOf(e.toLowerCase()) > -1; });
        },
        enumerable: true,
        configurable: true
    });
    RezervacijeComponent.prototype.ucitajJelaIPicaZaRestoran = function (restoranId) {
        var _this = this;
        this.odabranRestoran = restoranId;
        this._rezervacijaService.getJela(restoranId).subscribe(function (jela) {
            _this._jela = jela;
            _this.search1 = "";
        });
        this._rezervacijaService.getPica(restoranId).subscribe(function (pica) {
            _this._pica = pica;
            _this.search2 = "";
        });
        this.gostSaKojimRadimo.subscribe(function (ulogovan) {
            if (ulogovan) {
                _this._rezervacijaService.porucenaJela(_this.odabranaRezervacija['idRezervacije'], encodeURIComponent(ulogovan.email)).subscribe(function (porucena) {
                    _this.porucenaJela = porucena;
                });
                _this._rezervacijaService.porucenaPica(_this.odabranaRezervacija['idRezervacije'], encodeURIComponent(ulogovan.email)).subscribe(function (porucena) {
                    _this.porucenaPica = porucena;
                });
            }
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
    RezervacijeComponent.prototype.dodajUPorucenaJela = function (nazivJela) {
        this.porucenaJela.push(nazivJela);
    };
    RezervacijeComponent.prototype.dodajUPorucenaPica = function (nazivPica) {
        this.porucenaPica.push(nazivPica);
    };
    RezervacijeComponent.prototype.poruciIzmeni = function () {
        var _this = this;
        this.gostSaKojimRadimo.subscribe(function (ulogovan) {
            if (ulogovan) {
                var request = {
                    email: ulogovan.email,
                    rezervacijaId: _this.odabranaRezervacija['idRezervacije'],
                    spremnoKadaSeDodje: false,
                    naziviJela: _this.porucenaJela.map(function (j) { return j.nazivJela; }),
                    restoranId: _this.odabranRestoran
                };
                _this._rezervacijaService.poruciJela(request).subscribe(function (resp) {
                    if (resp['Success'] == true) {
                        _this._notificator.notifySuccess("Uspesna porudzbina jela!");
                    }
                    else {
                        _this._notificator.notifyError(resp['Message']);
                    }
                });
                var request2 = {
                    email: ulogovan.email,
                    rezervacijaId: _this.odabranaRezervacija['idRezervacije'],
                    spremnoKadaSeDodje: false,
                    naziviPica: _this.porucenaPica.map(function (j) { return j.nazivPica; }),
                    restoranId: _this.odabranRestoran
                };
                _this._rezervacijaService.poruciPica(request2).subscribe(function (resp) {
                    if (resp['Success'] == true) {
                        _this._notificator.notifySuccess("Uspesna porudzbina pica!");
                    }
                    else {
                        _this._notificator.notifyError(resp['Message']);
                    }
                });
            }
        });
    };
    RezervacijeComponent.prototype.izbaciJelo = function (indx) {
        this.porucenaJela.splice(indx, 1);
    };
    RezervacijeComponent.prototype.izbaciPice = function (indx) {
        this.porucenaPica.splice(indx, 1);
    };
    return RezervacijeComponent;
}());
RezervacijeComponent = __decorate([
    core_1.Component({
        selector: 'restorani',
        templateUrl: 'app/rezervacije/rezervacije.component.html'
    }),
    __metadata("design:paramtypes", [router_1.ActivatedRoute, notification_service_1.Notificator, login_service_1.LoginService, restorani_service_1.RestoranService, rezervacija_service_1.RezervacijaService])
], RezervacijeComponent);
exports.RezervacijeComponent = RezervacijeComponent;
//# sourceMappingURL=rezervacije.component.js.map