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
var router_1 = require("@angular/router");
var Rx_1 = require("rxjs/Rx");
require("rxjs/add/operator/do");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/map");
var notification_service_1 = require("./notification.service");
var app_config_1 = require("../app.config");
var PrijateljstvoService = (function () {
    function PrijateljstvoService(_http, _notificator, _router) {
        this._http = _http;
        this._notificator = _notificator;
        this._router = _router;
        this.emailUlogovanog = 'email0';
        this.prijateljsvoUrl = app_config_1.Config.BackendUrl + '/prijateljstvo';
        this.posaljiZahtevUrl = this.prijateljsvoUrl + '/posaljiZahtevZaPrijateljstvo';
        this.prihvatiZahtevUrl = this.prijateljsvoUrl + '/prihvatiZahtevZaPrijateljstvo';
        this.prekiniPrijateljstvoUrl = this.prijateljsvoUrl + '/prekiniPrijateljstvo';
        this._prijateljiUrl = this.prijateljsvoUrl + '/getPrijatelje';
        this._nepozvaniUPrijateljstvo = this.prijateljsvoUrl + '/getNepozvaneUPrijateljstvo';
        this._pozvaniUPrijateljstvo = this.prijateljsvoUrl + '/getPozvaneUPrijateljstvo';
        this._gostPozvanUPrijateljstvoOd = this.prijateljsvoUrl + '/gostPozvanUPrijateljstvoOd';
    }
    PrijateljstvoService.prototype.PosaljiZahtev = function (emailPrvog, emailDrugog) {
        return this._http.get(this.posaljiZahtevUrl + "?emailPrvog=" + emailPrvog + "&emailDrugog=" + emailDrugog)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    PrijateljstvoService.prototype.PrihvatiZahtev = function (emailPrvog, emailDrugog) {
        return this._http.get(this.prihvatiZahtevUrl + "?emailPrvog=" + emailPrvog + "&emailDrugog=" + emailDrugog)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    PrijateljstvoService.prototype.PrekiniZahtev = function (emailPrvog, emailDrugog) {
        return this.PrekiniPrijateljstvo(emailPrvog, emailDrugog);
    };
    PrijateljstvoService.prototype.PrekiniPrijateljstvo = function (emailPrvog, emailDrugog) {
        return this._http.get(this.prekiniPrijateljstvoUrl + "?emailPrvog=" + emailPrvog + "&emailDrugog=" + emailDrugog)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    PrijateljstvoService.prototype.GetPrijateljeOf = function (email) {
        return this._http.get(this._prijateljiUrl + "?email=" + email)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    PrijateljstvoService.prototype.GetNepozvaneUPrijateljstvo = function (email) {
        return this._http.get(this._nepozvaniUPrijateljstvo + "?email=" + email)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    PrijateljstvoService.prototype.GetPozvaneUPrijateljstvo = function (email) {
        return this._http.get(this._pozvaniUPrijateljstvo + "?email=" + email)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    PrijateljstvoService.prototype.GetGostPozvanUPrijateljstvoOd = function (email) {
        return this._http.get(this._gostPozvanUPrijateljstvoOd + "?email=" + email)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    PrijateljstvoService.prototype.handleError = function (error) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Rx_1.Observable.throw(error.json().error || 'Server error');
    };
    return PrijateljstvoService;
}());
PrijateljstvoService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.Notificator, router_1.Router])
], PrijateljstvoService);
exports.PrijateljstvoService = PrijateljstvoService;
//# sourceMappingURL=prijateljstvo.service.js.map