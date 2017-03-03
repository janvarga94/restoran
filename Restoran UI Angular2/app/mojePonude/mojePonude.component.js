/**
 * Created by Stefan on 3/2/2017.
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
var notification_service_1 = require("../services/notification.service");
var router_1 = require("@angular/router");
var login_service_1 = require("../services/login.service");
var restorani_service_1 = require("../services/restorani.service");
var zaposleniDetail_service_1 = require("../services/zaposleniDetail.service");
var zaposleni_service_1 = require("../services/zaposleni.service");
var mojePonudeComponent = (function () {
    function mojePonudeComponent(_notificator, route, _loginService, _router, _restoranService, _zaposleniService, _zaposleniDetailService) {
        this._notificator = _notificator;
        this.route = route;
        this._loginService = _loginService;
        this._router = _router;
        this._restoranService = _restoranService;
        this._zaposleniService = _zaposleniService;
        this._zaposleniDetailService = _zaposleniDetailService;
    }
    mojePonudeComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            if (ulogovan) {
                _this.email = ulogovan.email;
                _this._restoranService.getMojePonude(_this.email).subscribe(function (ponude) {
                    _this.mojePonude = ponude;
                });
            }
        });
    };
    mojePonudeComponent.prototype.izmeni = function (id) {
        console.log(this.cenna);
        console.log(id);
        this._restoranService.izmeniPonudu(id, this.cenna).subscribe(function (vrednost) {
        });
    };
    return mojePonudeComponent;
}());
mojePonudeComponent = __decorate([
    core_1.Component({
        selector: 'mojeponude',
        templateUrl: 'app/mojeponude/mojeponude.component.html',
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, router_1.ActivatedRoute, login_service_1.LoginService, router_1.Router, restorani_service_1.RestoranService, zaposleni_service_1.ZaposleniService, zaposleniDetail_service_1.ZaposleniDetailService])
], mojePonudeComponent);
exports.mojePonudeComponent = mojePonudeComponent;
//# sourceMappingURL=mojePonude.component.js.map