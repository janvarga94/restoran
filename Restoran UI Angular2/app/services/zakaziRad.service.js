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
var Observable_1 = require("rxjs/Observable");
var notification_service_1 = require("./notification.service");
var http_1 = require("@angular/http");
var app_config_1 = require("../app.config");
/**
 * Created by Svetozar Stojkovic on 3/2/2017.
 */
var ZakaziRadService = (function () {
    function ZakaziRadService(_http, _notificator) {
        this._http = _http;
        this._notificator = _notificator;
    }
    ZakaziRadService.prototype.zakaziDane = function (postObj) {
        var zakazi_rad = app_config_1.Config.BackendUrl + '/resursi/zakazi_dane';
        return this._http.post(zakazi_rad, postObj).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    ZakaziRadService.prototype.handleError = function (error) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable_1.Observable.throw(error.json().error || 'Server error');
    };
    return ZakaziRadService;
}());
ZakaziRadService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.Notificator])
], ZakaziRadService);
exports.ZakaziRadService = ZakaziRadService;
//# sourceMappingURL=zakaziRad.service.js.map