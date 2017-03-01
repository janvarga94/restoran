/**
 * Created by Stefan on 3/1/2017.
 */
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
var restorani_service_1 = require("../services/restorani.service");
var router_1 = require("@angular/router");
var notification_service_1 = require("../services/notification.service");
var login_service_1 = require("../services/login.service");
var NovoJeloComponent = (function () {
    function NovoJeloComponent(_restoranService, _router, _notificator, _loginService) {
        this._restoranService = _restoranService;
        this._router = _router;
        this._notificator = _notificator;
        this._loginService = _loginService;
    }
    NovoJeloComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            _this.email = ulogovan.email;
        });
    };
    NovoJeloComponent.prototype.addJelo = function () {
        var _this = this;
        this._restoranService.addJelo({ naziv: this.naziv, opis: this.opis, cena: this.cena, email: this.email }).subscribe(function (response) {
            if (response.Succes == true)
                _this._notificator.notifySuccess("Usposno dodat ponudjac");
            else
                _this._notificator.notifyError("Greska");
        });
    };
    return NovoJeloComponent;
}());
NovoJeloComponent = __decorate([
    core_1.Component({
        selector: 'novojelo',
        templateUrl: 'app/Jelo/novoJelo.component.html',
    }),
    __metadata("design:paramtypes", [restorani_service_1.RestoranService, router_1.Router, notification_service_1.Notificator, login_service_1.LoginService])
], NovoJeloComponent);
exports.NovoJeloComponent = NovoJeloComponent;
//# sourceMappingURL=novoJelo.component.js.map