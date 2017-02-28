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
var core_1 = require("@angular/core");
var restorani_service_1 = require("../services/restorani.service");
var router_1 = require("@angular/router");
var notification_service_1 = require("../services/notification.service");
/**
 * Created by Stefan on 2/25/2017.
 */
var NoviPonudjacComponent = (function () {
    function NoviPonudjacComponent(_restoranService, _router, _notificator) {
        this._restoranService = _restoranService;
        this._router = _router;
        this._notificator = _notificator;
    }
    NoviPonudjacComponent.prototype.ngOnInit = function () {
    };
    NoviPonudjacComponent.prototype.addMenadzera = function () {
        var _this = this;
        this._restoranService.addPonudjac({ naziv: this.naziv, email: this.email, lozinka: this.lozinka }).subscribe(function (response) {
            if (response.Succes == true)
                _this._notificator.notifySuccess("Usposno dodat ponudjac");
            else
                _this._notificator.notifyError("Greska");
        });
    };
    return NoviPonudjacComponent;
}());
NoviPonudjacComponent = __decorate([
    core_1.Component({
        selector: 'noviponudjac',
        templateUrl: 'app/noviPonudjac/noviPonudjac.component.html',
    }),
    __metadata("design:paramtypes", [restorani_service_1.RestoranService, router_1.Router, notification_service_1.Notificator])
], NoviPonudjacComponent);
exports.NoviPonudjacComponent = NoviPonudjacComponent;
//# sourceMappingURL=noviPonudjac.component.js.map