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
var zaposleniDetail_service_1 = require("../services/zaposleniDetail.service");
var notification_service_1 = require("../services/notification.service");
var router_1 = require("@angular/router");
/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
var ZaposleniDetailComponent = (function () {
    function ZaposleniDetailComponent(_notificator, _zaposleniDetailService, route) {
        this._notificator = _notificator;
        this._zaposleniDetailService = _zaposleniDetailService;
        this.route = route;
        this.pageTitle = "Zaposleni";
    }
    ZaposleniDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            _this.email = params['email'];
        });
        console.log(this.email);
        this._zaposleniDetailService.getZaposlen(this.email).subscribe(function (zaposleni) {
            //   this.restorani = restorani;
            _this.zaposlen = zaposleni;
            console.log(_this.zaposlen.radnikEmail);
        });
    };
    return ZaposleniDetailComponent;
}());
ZaposleniDetailComponent = __decorate([
    core_1.Component({
        selector: 'zaposleni/:email',
        templateUrl: 'app/zaposleniDetail/zaposleniDetail.component.html',
        providers: [zaposleniDetail_service_1.ZaposleniDetailService]
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, zaposleniDetail_service_1.ZaposleniDetailService, router_1.ActivatedRoute])
], ZaposleniDetailComponent);
exports.ZaposleniDetailComponent = ZaposleniDetailComponent;
//# sourceMappingURL=zaposleniDetail.component.js.map