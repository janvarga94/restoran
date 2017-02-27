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
var app_config_1 = require("./../app.config");
/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Observable_1 = require("rxjs/Observable");
require("rxjs/add/operator/do");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/map");
var notification_service_1 = require("./notification.service");
var RezervacijaService = (function () {
    function RezervacijaService(_http, _notificator) {
        this._http = _http;
        this._notificator = _notificator;
        this._stoloviUrl = app_config_1.Config.BackendUrl + '/rezervacija/stolovi';
        this._rezervisiUrl = app_config_1.Config.BackendUrl + '/rezervacija/rezervisi';
        this._jelaUrl = app_config_1.Config.BackendUrl + '/rezervacija/jela';
        this._picaUrl = app_config_1.Config.BackendUrl + '/rezervacija/pica';
        this._rezervaicjeUrl = app_config_1.Config.BackendUrl + '/rezervacija/rezervacije';
    }
    RezervacijaService.prototype.getStolovi = function (restoran) {
        return this._http.get(this._stoloviUrl + "?restoran=" + restoran)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    RezervacijaService.prototype.rezervisi = function (rezervisiReq) {
        return this._http.post(this._rezervisiUrl, rezervisiReq)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    RezervacijaService.prototype.getJela = function (restoran) {
        return this._http.get(this._jelaUrl + "?restoran=" + restoran)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    RezervacijaService.prototype.getPica = function (restoran) {
        return this._http.get(this._picaUrl + "?restoran=" + restoran)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    RezervacijaService.prototype.getRezervacije = function (email) {
        return this._http.get(this._rezervaicjeUrl + "?email=" + encodeURIComponent(email))
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    RezervacijaService.prototype.handleError = function (error) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable_1.Observable.throw(error.json().error || 'Server error');
    };
    return RezervacijaService;
}());
RezervacijaService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.Notificator])
], RezervacijaService);
exports.RezervacijaService = RezervacijaService;
//# sourceMappingURL=rezervacija.service.js.map