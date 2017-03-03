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
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var app_config_1 = require("./../app.config");
var Observable_1 = require("rxjs/Observable");
require("rxjs/add/operator/do");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/map");
var notification_service_1 = require("./notification.service");
var RestoranService = (function () {
    function RestoranService(_http, _notificator) {
        this._http = _http;
        this._notificator = _notificator;
        this._restoraniUrl = app_config_1.Config.BackendUrl + '/resursi/restorani';
        this._restoraniSviUrl = app_config_1.Config.BackendUrl + '/restorani/getAll';
        this.dodaj = app_config_1.Config.BackendUrl + '/resursi/add';
        this._managerRestoranaUrl = app_config_1.Config.BackendUrl + '/menadzerRestorana/getRestoranID';
        this._addPonudjac = app_config_1.Config.BackendUrl + '/menadzerRestorana/addPonudjac';
        this._addReon = app_config_1.Config.BackendUrl + '/menadzerRestorana/addReon';
        this._addStol = app_config_1.Config.BackendUrl + '/menadzerRestorana/addStol';
        this._addNamirnica = app_config_1.Config.BackendUrl + '/menadzerRestorana/addNamirnica';
        this._addJelo = app_config_1.Config.BackendUrl + '/menadzerRestorana/addJelo';
        this._addPonuda = app_config_1.Config.BackendUrl + '/menadzerRestorana/addPonuda';
        this._izmeniPonudu = app_config_1.Config.BackendUrl + '/menadzerRestorana/izmeniPonudu';
        this._prihvacena = app_config_1.Config.BackendUrl + '/menadzerRestorana/prihvacena';
        this._getJelovnik = app_config_1.Config.BackendUrl + '/menadzerRestorana/getJelovnik';
        this._getOcenaRestorana = app_config_1.Config.BackendUrl + '/menadzerRestorana/getOcenaRestorana';
        this._getOcenaJela = app_config_1.Config.BackendUrl + '/menadzerRestorana/getOcenaJela';
        this._getReoni = app_config_1.Config.BackendUrl + '/menadzerRestorana/getReoni';
        this._getNamirnice = app_config_1.Config.BackendUrl + '/menadzerRestorana/getNamirnice';
        this._getNamirniceUPotraznji = app_config_1.Config.BackendUrl + '/menadzerRestorana/getNamirniceUPotraznji';
        this._getDobivenePonude = app_config_1.Config.BackendUrl + '/menadzerRestorana/getDobivenePonude';
        this._getMojePonude = app_config_1.Config.BackendUrl + '/menadzerRestorana/getMojePonude';
    }
    RestoranService.prototype.getRestorani = function () {
        return this._http.get(this._restoraniUrl)
            .map(function (response) {
            var restorani = response.json();
            // for(var i = 0; i < 10; i++)
            //     restorani.push(restorani[0]);
            console.log(restorani.length);
            return restorani;
        })
            .catch(this.handleError);
    };
    /* getRestoran(id: string): Observable<IRestoran> {
     return this.getRestorani()email
     .map((restorani: IRestoran[]) => restorani.find(r => r.naziv === id))
     .catch(this.handleError);
     } */
    RestoranService.prototype.getManagerRestoranID = function (email) {
        return this._http.get(this._managerRestoranaUrl + "?email=" + email)
            .map(function (response) {
            var id = response.json();
            return id;
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.getRestoran = function () {
    };
    /* addRestoran(restoran : IRestoran): Observable<ISuccess>{
     return this._http.get("api/successResponse.json")
     .map((response: Response) => {   return <ISuccess> response.json(); })
     .catch(this.handleError);
     } */
    /* addRestoran(restoran : any) {

     } */
    RestoranService.prototype.addRestoran = function (restoran) {
        return this._http.post(this.dodaj, restoran).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.addPonudjac = function (ponudjac) {
        return this._http.post(this._addPonudjac, ponudjac).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.addReon = function (reon) {
        return this._http.post(this._addReon, reon).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.addSto = function (sto) {
        return this._http.post(this._addStol, sto).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.addNamirnica = function (namirnica) {
        return this._http.post(this._addNamirnica, namirnica).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.prihvacena = function (id) {
        return this._http.get(this._prihvacena + "?id=" + id).map(function (response) {
            // return <any> response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.addJelo = function (jelo) {
        return this._http.post(this._addJelo, jelo).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.addPonuda = function (ponuda) {
        return this._http.post(this._addPonuda, ponuda).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.izmeniPonudu = function (id, cena) {
        return this._http.get(this._izmeniPonudu + "?id=" + id + "&cena=" + cena).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.getJelovnik = function (email) {
        return this._http.get(this._getJelovnik + "?email=" + encodeURIComponent(email))
            .map(function (response) {
            var jelovnik = response.json();
            // for(var i = 0; i < 10; i++)
            //     restorani.push(restorani[0]);
            console.log(jelovnik.length);
            return jelovnik;
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.getLongLat = function (adresa) {
        return this._http.get("https://maps.googleapis.com/maps/api/geocode/json?address=" + encodeURIComponent(adresa) + "&key=AIzaSyAB6DgNAa-m2IHEzyFRUdV2bPTeIy0mjuc")
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    RestoranService.prototype.getReoni = function (email) {
        return this._http.get(this._getReoni + "?email=" + encodeURIComponent(email))
            .map(function (response) {
            var reoni = response.json();
            // for(var i = 0; i < 10; i++)
            //     restorani.push(restorani[0]);
            //console.log(jelovnik.length);
            return reoni;
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.getMojePonude = function (email) {
        return this._http.get(this._getMojePonude + "?email=" + encodeURIComponent(email))
            .map(function (response) {
            var mojeponude = response.json();
            // for(var i = 0; i < 10; i++)
            //     restorani.push(restorani[0]);
            //console.log(jelovnik.length);
            return mojeponude;
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.getNamirnice = function () {
        return this._http.get(this._getNamirnice)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.getOcenaRestorana = function (email) {
        return this._http.get(this._getOcenaRestorana + "?email=" + encodeURIComponent(email))
            .map(function (response) {
            var ocena = response.json();
            // for(var i = 0; i < 10; i++)
            //     restorani.push(restorani[0]);
            //     console.log(jelovnik.length);
            return ocena;
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.getOcenaJela = function (email, jelo) {
        return this._http.get(this._getOcenaJela + "?email=" + encodeURIComponent(email) + "&jelo=" + encodeURIComponent(jelo))
            .map(function (response) {
            var ocena = response.json();
            // for(var i = 0; i < 10; i++)
            //     restorani.push(restorani[0]);
            //     console.log(jelovnik.length);
            return ocena;
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.getSviRestorani = function () {
        return this._http.get(this._restoraniSviUrl)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.getNamirniceUPotraznji = function () {
        return this._http.get(this._getNamirniceUPotraznji)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.getDobivenePonude = function (email) {
        return this._http.get(this._getDobivenePonude + "?email=" + encodeURIComponent(email))
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.handleError = function (error) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable_1.Observable.throw(error.json().error || 'Server error');
    };
    return RestoranService;
}());
RestoranService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.Notificator])
], RestoranService);
exports.RestoranService = RestoranService;
//# sourceMappingURL=restorani.service.js.map