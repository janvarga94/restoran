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
var rezervacija_service_1 = require("./../services/rezervacija.service");
var prijateljstvo_service_1 = require("./../services/prijateljstvo.service");
var notification_service_1 = require("./../services/notification.service");
var gosti_service_1 = require("./../services/gosti.service.");
var core_1 = require("@angular/core");
var login_service_1 = require("../services/login.service");
var GostProfilComponent = (function () {
    function GostProfilComponent(_rezervacijaService, _loginService, _gostService, _notificator, _prijateljstvoService) {
        this._rezervacijaService = _rezervacijaService;
        this._loginService = _loginService;
        this._gostService = _gostService;
        this._notificator = _notificator;
        this._prijateljstvoService = _prijateljstvoService;
        this.search = '';
        this.gost = null;
        this.prijatelji = [];
        this.nepozvaniUPrijateljstvo = [];
        this.pozvaniUPrijateljstvo = [];
        this.gostPozvanUPrijateljstvoOd = [];
        this.poziviURestorane = [];
        this.search1 = '';
        this.search2 = '';
        this.search3 = '';
        this.search4 = '';
        this._prijatelji = [];
        this._nepozvaniUPrijateljstvo = [];
        this._pozvaniUPrijateljstvo = [];
        this._gostPozvanUPrijateljstvoOd = [];
    }
    GostProfilComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            _this.gost = ulogovan;
            if (ulogovan == null)
                return;
            _this._prijateljstvoService.GetPrijateljeOf(ulogovan.email).subscribe(function (prijatelji) {
                _this._prijatelji = prijatelji;
                _this.IzmenaListe();
            });
            _this._prijateljstvoService.GetNepozvaneUPrijateljstvo(ulogovan.email).subscribe(function (nePrijatelji) {
                _this._nepozvaniUPrijateljstvo = nePrijatelji;
                _this.IzmenaListe();
            });
            _this._prijateljstvoService.GetPozvaneUPrijateljstvo(ulogovan.email).subscribe(function (oniKojimaJePoslatZahtev) {
                _this._pozvaniUPrijateljstvo = oniKojimaJePoslatZahtev;
                _this.IzmenaListe();
            });
            _this._prijateljstvoService.GetGostPozvanUPrijateljstvoOd(ulogovan.email).subscribe(function (pozivaociUPrijateljstvo) {
                _this._gostPozvanUPrijateljstvoOd = pozivaociUPrijateljstvo;
                _this.IzmenaListe();
            });
            _this._rezervacijaService.getPoziveURestorane(ulogovan.email).subscribe(function (pozivi) {
                _this.poziviURestorane = pozivi;
            });
        });
    };
    GostProfilComponent.prototype.IzmenaListe = function () {
        var _this = this;
        this.prijatelji = this._prijatelji.filter(function (p) { return (p['ime'] + " " + p['prezime']).toLowerCase().indexOf(_this.search2.toLowerCase()) > -1; });
        this.pozvaniUPrijateljstvo = this._pozvaniUPrijateljstvo.filter(function (p) { return (p['ime'] + " " + p['prezime']).toLowerCase().indexOf(_this.search1.toLowerCase()) > -1; });
        this.nepozvaniUPrijateljstvo = this._nepozvaniUPrijateljstvo.filter(function (p) { return (p['ime'] + " " + p['prezime']).toLowerCase().indexOf(_this.search3.toLowerCase()) > -1; });
        this.gostPozvanUPrijateljstvoOd = this._gostPozvanUPrijateljstvoOd.filter(function (p) { return (p['ime'] + " " + p['prezime']).toLowerCase().indexOf(_this.search4.toLowerCase()) > -1; });
    };
    GostProfilComponent.prototype.modifyGosta = function () {
        var _this = this;
        this._gostService.ModifyGosta(this.gost['ime'], this.gost['prezime'], this.gost['email']).subscribe(function (response) {
            if (response.Success) {
                _this._notificator.notifySuccess("Uspesna izmena!");
            }
            else {
                _this._notificator.notifyInfo("Izmena nije izvrsena: " + response.Message);
            }
        });
    };
    GostProfilComponent.prototype.posaljiZahtev = function (kome, index) {
        var _this = this;
        console.log(index);
        this._prijateljstvoService.PosaljiZahtev(this.gost['email'], kome.email).subscribe(function (response) {
            if (response.Success) {
                _this._notificator.notifySuccess("Zahtev poslat");
                _this._nepozvaniUPrijateljstvo.splice(index, 1);
                _this._pozvaniUPrijateljstvo.push(kome);
                _this.IzmenaListe();
            }
            else {
                _this._notificator.notifyInfo("Problem: " + response.Message);
            }
        });
    };
    GostProfilComponent.prototype.prihvatiZahtev = function (kome, index) {
        var _this = this;
        this._prijateljstvoService.PrihvatiZahtev(kome.email, this.gost['email']).subscribe(function (response) {
            if (response.Success) {
                _this._notificator.notifySuccess("Prijateljstvo napravljeno :)");
                _this._gostPozvanUPrijateljstvoOd.splice(index, 1);
                _this._prijatelji.push(kome);
                _this.IzmenaListe();
            }
            else {
                _this._notificator.notifyInfo("Problem: " + response.Message);
            }
        });
    };
    GostProfilComponent.prototype.prekiniZahtev = function (kome, index) {
        var _this = this;
        this._prijateljstvoService.PrekiniZahtev(this.gost['email'], kome.email).subscribe(function (response) {
            if (response.Success) {
                _this._notificator.notifySuccess("Zahtev prekinut");
                _this._pozvaniUPrijateljstvo.splice(index, 1);
                _this._nepozvaniUPrijateljstvo.push(kome);
                _this.IzmenaListe();
            }
            else {
                _this._notificator.notifyInfo("Problem: " + response.Message);
            }
        });
    };
    GostProfilComponent.prototype.prekiniPrijateljstvo = function (kome, index) {
        var _this = this;
        this._prijateljstvoService.PrekiniPrijateljstvo(this.gost['email'], kome.email).subscribe(function (response) {
            if (response.Success) {
                _this._notificator.notifySuccess("Prijateljstvo prekinuto");
                _this._prijatelji.splice(index, 1);
                _this._nepozvaniUPrijateljstvo.push(kome);
                _this.IzmenaListe();
            }
            else {
                _this._notificator.notifyInfo("Problem: " + response.Message + " Verovatno ste prijatelja pozvali u restoran pa prvo obrisite poziv.");
            }
        });
    };
    GostProfilComponent.prototype.odbijZahtev = function (kome, index) {
        var _this = this;
        this._prijateljstvoService.PrekiniZahtev(this.gost['email'], kome.email).subscribe(function (response) {
            if (response.Success) {
                _this._notificator.notifySuccess("Zahtev odbijen");
                _this._gostPozvanUPrijateljstvoOd.splice(index, 1);
                _this._nepozvaniUPrijateljstvo.push(kome);
                _this.IzmenaListe();
            }
            else {
                _this._notificator.notifyInfo("Problem: " + response.Message);
            }
        });
    };
    GostProfilComponent.prototype.getVreme = function (vreme) {
        return new Date(vreme).toLocaleDateString() + " " + new Date(vreme).toLocaleTimeString();
    };
    GostProfilComponent.prototype.prihvatiPozivURestoran = function (idPoziva) {
        var _this = this;
        this._rezervacijaService.prihvatiOdbij(idPoziva, true).subscribe(function (resp) {
            if (resp['Success'] == true) {
                _this._notificator.notifySuccess("Poziv prihvacen!");
            }
            else {
                _this._notificator.notifyError(resp["Message"]);
            }
        });
    };
    GostProfilComponent.prototype.odbijPozivURestoran = function (idPoziva) {
        var _this = this;
        this._rezervacijaService.prihvatiOdbij(idPoziva, false).subscribe(function (resp) {
            if (resp['Success'] == true) {
                _this._notificator.notifyInfo("Poziv odbijen");
            }
            else {
                _this._notificator.notifyError(resp["Message"]);
            }
        });
    };
    return GostProfilComponent;
}());
GostProfilComponent = __decorate([
    core_1.Component({
        templateUrl: './app/gost profil/gostProfil.component.html'
    }),
    __metadata("design:paramtypes", [rezervacija_service_1.RezervacijaService, login_service_1.LoginService, gosti_service_1.GostiService, notification_service_1.Notificator, prijateljstvo_service_1.PrijateljstvoService])
], GostProfilComponent);
exports.GostProfilComponent = GostProfilComponent;
//# sourceMappingURL=gostProfil.component.js.map