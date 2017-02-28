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
var zaposleni_service_1 = require("../services/zaposleni.service");
var notification_service_1 = require("../services/notification.service");
var NoviMenadzerRestoranaComponent = (function () {
    function NoviMenadzerRestoranaComponent(_restoranService, _router, _zaposleniService, _notificator) {
        this._restoranService = _restoranService;
        this._router = _router;
        this._zaposleniService = _zaposleniService;
        this._notificator = _notificator;
    }
    NoviMenadzerRestoranaComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._restoranService.getRestorani().subscribe(function (restorani) {
            //   this.restorani = restorani;
            _this.restorani = restorani;
        });
    };
    NoviMenadzerRestoranaComponent.prototype.addMenadzera = function () {
        var _this = this;
        this._zaposleniService.addMenadzera({ ime: this.ime, prezime: this.prezime, password: this.password, email: this.email, id: this.selectedRestoran['idRestorana'] }).subscribe(function (response) {
            if (response.Succes == true)
                _this._notificator.notifySuccess("Usposno dodat zaposlen");
            else
                _this._notificator.notifyError("Greska");
        });
    };
    return NoviMenadzerRestoranaComponent;
}());
NoviMenadzerRestoranaComponent = __decorate([
    core_1.Component({
        selector: 'novimenadzerrestorana',
        templateUrl: 'app/noviMenadzerRestorana/noviMenadzerRestorana.component.html',
    }),
    __metadata("design:paramtypes", [restorani_service_1.RestoranService, router_1.Router, zaposleni_service_1.ZaposleniService, notification_service_1.Notificator])
], NoviMenadzerRestoranaComponent);
exports.NoviMenadzerRestoranaComponent = NoviMenadzerRestoranaComponent;
//# sourceMappingURL=noviMenadzerRestorana.component.js.map