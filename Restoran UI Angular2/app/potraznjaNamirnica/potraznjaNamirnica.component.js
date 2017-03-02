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
 * Created by Stefan on 2/28/2017.
 */
var core_1 = require("@angular/core");
var notification_service_1 = require("../services/notification.service");
var router_1 = require("@angular/router");
var restorani_service_1 = require("../services/restorani.service");
var login_service_1 = require("../services/login.service");
var PotraznjaNamirnicaComponent = (function () {
    function PotraznjaNamirnicaComponent(_notificator, _router, _restoranService, _loginService) {
        this._notificator = _notificator;
        this._router = _router;
        this._restoranService = _restoranService;
        this._loginService = _loginService;
        this.lista = [];
    }
    PotraznjaNamirnicaComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            if (ulogovan != null) {
                _this.emailMenazderaRestorana = ulogovan.email;
                _this._restoranService.getNamirnice().subscribe(function (namirnice) {
                    if (namirnice != null)
                        _this.namirnice = namirnice;
                });
            }
        });
    };
    PotraznjaNamirnicaComponent.prototype.add = function (event, id) {
        this.lista.push(id);
        console.log(this.lista);
    };
    PotraznjaNamirnicaComponent.prototype.addNamirnicu = function () {
        this._restoranService.addNamirnica({ lista: this.lista, email: this.emailMenazderaRestorana, datum: this.datum });
    };
    return PotraznjaNamirnicaComponent;
}());
PotraznjaNamirnicaComponent = __decorate([
    core_1.Component({
        selector: 'potraznjanamirnica',
        templateUrl: 'app/potraznjaNamirnica/potraznajNamirnica.component.html',
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, router_1.Router, restorani_service_1.RestoranService, login_service_1.LoginService])
], PotraznjaNamirnicaComponent);
exports.PotraznjaNamirnicaComponent = PotraznjaNamirnicaComponent;
//# sourceMappingURL=potraznjaNamirnica.component.js.map