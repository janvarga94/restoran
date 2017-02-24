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
var ZaposleniDetailService = (function () {
    function ZaposleniDetailService(_http, _notificator) {
        this._http = _http;
        this._notificator = _notificator;
        this._zaposleniUrl = 'http://localhost:8080/resursi/get_zaposlen';
    }
    ZaposleniDetailService.prototype.getZaposlen = function (email) {
        return this._http.get(this._zaposleniUrl + "?radnikEmail=" + email)
            .map(function (response) {
            var zaposleni = response.json();
            // for(var i = 0; i < 10; i++)
            //     restorani.push(restorani[0]);
            return zaposleni;
        })
            .catch(this.handleError);
    };
    ZaposleniDetailService.prototype.getParam = function () {
        var params = new http_1.URLSearchParams(window.location.search);
        var someParam = params.get('re');
        return someParam;
    };
    ZaposleniDetailService.prototype.handleError = function (error) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable_1.Observable.throw(error.json().error || 'Server error');
    };
    return ZaposleniDetailService;
}());
ZaposleniDetailService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.Notificator])
], ZaposleniDetailService);
exports.ZaposleniDetailService = ZaposleniDetailService;
//# sourceMappingURL=zaposleniDetail.service.js.map