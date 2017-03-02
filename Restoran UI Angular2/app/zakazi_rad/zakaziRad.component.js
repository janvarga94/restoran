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
var zakaziRad_service_1 = require("../services/zakaziRad.service");
var notification_service_1 = require("../services/notification.service");
var router_1 = require("@angular/router");
var zaposleniDetail_service_1 = require("../services/zaposleniDetail.service");
var restorani_service_1 = require("../services/restorani.service");
/**
 * Created by Svetozar Stojkovic on 3/2/2017.
 */
var ZakaziRadComponent = (function () {
    function ZakaziRadComponent(_zakaziRad, _restoraniService, _zaposleniDetailService, route, _notification) {
        this._zakaziRad = _zakaziRad;
        this._restoraniService = _restoraniService;
        this._zaposleniDetailService = _zaposleniDetailService;
        this.route = route;
        this._notification = _notification;
    }
    ZakaziRadComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            _this.email = atob(params['email']);
            console.log(_this.email);
            _this._zaposleniDetailService.getZaposlen(_this.email).subscribe(function (zaposlen) {
                _this.zaposlen = zaposlen;
                _this._zaposleniDetailService.getZanimanje(_this.email).subscribe(function (zanimanje) {
                    _this.zanimanjeInt = zanimanje; // 0 - konobar, 1 - kuvar, 2 - sanker
                    _this._restoraniService.getReoniForRestoran(zaposlen[5]).subscribe(function (reoni) {
                        _this.reoni = reoni;
                        console.log("Reoni:" + reoni);
                    });
                });
            });
        });
    };
    ZakaziRadComponent.prototype.zakaziDane = function () {
        var _this = this;
        if (this.datumPocetka == null) {
            this._notification.notifyError("Ne valjda datum");
            return;
        }
        else if (this.smena == null) {
            this._notification.notifyError("Ne valja smena");
            return;
        }
        else if (this.reon == null && this.zanimanjeInt == 0) {
            this._notification.notifyError("Ne valja reon");
            return;
        }
        console.log({ email: this.zaposlen[0], idRestorana: this.zaposlen[5], datumPocetka: this.datumPocetka, brojDana: this.brojDana, smena: Number(this.smena), reon: this.reon });
        this._zakaziRad.zakaziDane({ email: this.zaposlen[0], idRestorana: this.zaposlen[5], datumPocetka: this.datumPocetka, brojDana: this.brojDana, smena: Number(this.smena), reon: this.reon }).subscribe(function (response) {
            if (response == true) {
                _this._notification.notifySuccess("Dodavanje uspelo");
            }
            else {
                _this._notification.notifyError("Dodavanje nije uspelo");
            }
        });
    };
    return ZakaziRadComponent;
}());
ZakaziRadComponent = __decorate([
    core_1.Component({
        selector: 'zakazi_rad',
        templateUrl: 'app/zakazi_rad/zakaziRad.component.html',
        providers: [zakaziRad_service_1.ZakaziRadService]
    }),
    __metadata("design:paramtypes", [zakaziRad_service_1.ZakaziRadService, restorani_service_1.RestoranService, zaposleniDetail_service_1.ZaposleniDetailService, router_1.ActivatedRoute, notification_service_1.Notificator])
], ZakaziRadComponent);
exports.ZakaziRadComponent = ZakaziRadComponent;
//# sourceMappingURL=zakaziRad.component.js.map