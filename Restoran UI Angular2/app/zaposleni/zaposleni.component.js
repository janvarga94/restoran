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
var zaposleni_service_1 = require("../services/zaposleni.service");
var notification_service_1 = require("../services/notification.service");
/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */
var ZaposleniComponent = (function () {
    function ZaposleniComponent(_notificator, _zaposleniService) {
        this._notificator = _notificator;
        this._zaposleniService = _zaposleniService;
        this.pageTitle = "Zaposleni";
    }
    ZaposleniComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._zaposleniService.getZaposleni().subscribe(function (zaposleni) {
            //   this.restorani = restorani;
            _this.zaposleni = zaposleni;
        });
    };
    ZaposleniComponent.prototype.obrisiZaposlenog = function (zaposleni) {
        var index = this.zaposleni.indexOf(zaposleni);
        if (index > -1) {
            this.zaposleni.splice(index, 1);
            this._notificator.notifySuccess("Zaposleni obrisan");
        }
    };
    ZaposleniComponent.prototype.detaljiZaposlenog = function (zaposleni) {
        console.log(zaposleni.radnikEmail);
    };
    return ZaposleniComponent;
}());
ZaposleniComponent = __decorate([
    core_1.Component({
        selector: 'zaposleni',
        templateUrl: 'app/zaposleni/zaposleni.component.html',
        providers: [zaposleni_service_1.ZaposleniService]
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, zaposleni_service_1.ZaposleniService])
], ZaposleniComponent);
exports.ZaposleniComponent = ZaposleniComponent;
//# sourceMappingURL=zaposleni.component.js.map