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
var restorani_service_1 = require("../services/restorani.service");
var login_service_1 = require("../services/login.service");
/**
 * Created by Stefan on 3/1/2017.
 */
var StatistikaComponent = (function () {
    function StatistikaComponent(_notificator, _router, _restoranService, _loginService) {
        this._notificator = _notificator;
        this._router = _router;
        this._restoranService = _restoranService;
        this._loginService = _loginService;
    }
    StatistikaComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            if (ulogovan != null) {
                _this.emailMenazderaRestorana = ulogovan.email;
                _this._restoranService.getOcenaRestorana(_this.emailMenazderaRestorana).subscribe(function (ocena) {
                    if (ocena != null)
                        _this.ocenaRestorana = ocena;
                });
            }
        });
        console.log(this.ocenaRestorana);
    };
    StatistikaComponent.prototype.pretraziJelo = function () {
        var _this = this;
        if (this.emailMenazderaRestorana != null) {
            this._restoranService.getOcenaJela(this.emailMenazderaRestorana, this.searchJela).subscribe(function (ocenaJ) {
                if (ocenaJ != null)
                    _this.ocenaJela = ocenaJ;
            });
        }
    };
    return StatistikaComponent;
}());
StatistikaComponent = __decorate([
    core_1.Component({
        selector: 'statistika',
        templateUrl: 'app/statistika/statistika.component.html',
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, router_1.Router, restorani_service_1.RestoranService, login_service_1.LoginService])
], StatistikaComponent);
exports.StatistikaComponent = StatistikaComponent;
//# sourceMappingURL=statistika.component.js.map