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
/**
 * Created by Stefan on 2/27/2017.
 */
var core_1 = require("@angular/core");
var restorani_service_1 = require("../services/restorani.service");
var router_1 = require("@angular/router");
var login_service_1 = require("../services/login.service");
var notification_service_1 = require("../services/notification.service");
var NoviRestoranComponent = (function () {
    function NoviRestoranComponent(_notificator, _loginService, _router, _restoranService) {
        this._notificator = _notificator;
        this._loginService = _loginService;
        this._router = _router;
        this._restoranService = _restoranService;
    }
    NoviRestoranComponent.prototype.ngOnInit = function () {
    };
    NoviRestoranComponent.prototype.addRestoran = function () {
        var _this = this;
        this._restoranService.addRestoran({ idRestorana: this.id, vrsta: this.vrsta, naziv: this.naziv, opis: this.opis, adresa: this.adresa }).subscribe(function (response) {
            if (response.Success == true)
                _this._notificator.notifySuccess("Usposno dodat restoran");
            else
                _this._notificator.notifyError("Greska");
        });
    };
    return NoviRestoranComponent;
}());
NoviRestoranComponent = __decorate([
    core_1.Component({
        selector: 'novirestoran',
        templateUrl: 'app/noviRestoran/noviRestoran.component.html',
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, login_service_1.LoginService, router_1.Router, restorani_service_1.RestoranService])
], NoviRestoranComponent);
exports.NoviRestoranComponent = NoviRestoranComponent;
//# sourceMappingURL=noviRestoran.component.js.map