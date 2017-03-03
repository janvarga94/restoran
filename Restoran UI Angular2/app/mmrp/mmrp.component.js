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
 * Created by Stefan on 3/1/2017.
 */
var core_1 = require("@angular/core");
var notification_service_1 = require("../services/notification.service");
var router_1 = require("@angular/router");
var login_service_1 = require("../services/login.service");
var restorani_service_1 = require("../services/restorani.service");
var zaposleni_service_1 = require("../services/zaposleni.service");
var zaposleniDetail_service_1 = require("../services/zaposleniDetail.service");
var MMRPComponent = (function () {
    function MMRPComponent(_notificator, route, _loginService, _router, _restoranService, _zaposleniService, _zaposleniDetailService) {
        this._notificator = _notificator;
        this.route = route;
        this._loginService = _loginService;
        this._router = _router;
        this._restoranService = _restoranService;
        this._zaposleniService = _zaposleniService;
        this._zaposleniDetailService = _zaposleniDetailService;
    }
    MMRPComponent.prototype.ngOnInit = function () {
    };
    return MMRPComponent;
}());
MMRPComponent = __decorate([
    core_1.Component({
        selector: 'mmrp',
        templateUrl: 'app/mmrp/mmrp.component.html',
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, router_1.ActivatedRoute, login_service_1.LoginService, router_1.Router, restorani_service_1.RestoranService, zaposleni_service_1.ZaposleniService, zaposleniDetail_service_1.ZaposleniDetailService])
], MMRPComponent);
exports.MMRPComponent = MMRPComponent;
//# sourceMappingURL=mmrp.component.js.map