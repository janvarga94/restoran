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
var notification_service_1 = require("./notification.service");
var http_1 = require("@angular/http");
/**
 * Created by Svetozar Stojkovic on 12/20/2016.
 */
var Rx_1 = require("rxjs/Rx");
require("rxjs/add/operator/do");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/map");
var WelcomeService = (function () {
    function WelcomeService(_http, _notificator) {
        this._http = _http;
        this._notificator = _notificator;
        this._restorani_for_user_url = 'http://localhost:8080/resursi/restorani_for_user';
    }
    WelcomeService.prototype.getRestoraniForUser = function (email) {
        return this._http.get(this._restorani_for_user_url + "?email=" + email)
            .map(function (response) {
            var restoraniOcena = response.json();
            return restoraniOcena;
        })
            .catch(this.handleError);
    };
    WelcomeService.prototype.postOcenaForRestoran = function (ocena) {
        var _this = this;
        console.log(ocena.ocena);
        var ocenaUrl = 'http://localhost:8080/resursi/add_ocena_restoran';
        var creds = JSON.stringify(ocena);
        console.log(creds);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        this._http.post(ocenaUrl, creds, {
            headers: headers
        })
            .subscribe(function (data) {
        }, function (err) { return _this.handleError(err.json().message); }, function () { return console.log('Authentication Complete'); });
    };
    WelcomeService.prototype.getOcenaForRestoran = function (id) {
        var ocenaUrl = 'http://localhost:8080/resursi/ocena_for_restoran';
        return this._http.get(ocenaUrl + "?id=" + id)
            .map(function (response) {
            var restoraniOcena = response.json();
            return restoraniOcena;
        })
            .catch(this.handleError);
    };
    WelcomeService.prototype.handleError = function (error) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Rx_1.Observable.throw(error.json().error || 'Server error');
    };
    return WelcomeService;
}());
WelcomeService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.Notificator])
], WelcomeService);
exports.WelcomeService = WelcomeService;
//# sourceMappingURL=welcome.service.js.map