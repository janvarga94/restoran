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
var PotraznjeComponent = (function () {
    function PotraznjeComponent(_notificator, _router, _restoranService, _loginService) {
        this._notificator = _notificator;
        this._router = _router;
        this._restoranService = _restoranService;
        this._loginService = _loginService;
    }
    PotraznjeComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._restoranService.getNamirniceUPotraznji().subscribe(function (potraznje) {
            _this.potraznje = potraznje;
        });
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            if (ulogovan)
                _this.emailPonudjaca = ulogovan.email;
        });
    };
    PotraznjeComponent.prototype.addPonuda = function (idR, dokad, cena) {
        var _this = this;
        this._restoranService.addPonuda({ email: this.emailPonudjaca, id: idR, datum: dokad, iznos: cena }).subscribe(function (response) {
            if (response.Success == true)
                _this._notificator.notifySuccess("Greska");
            else
                _this._notificator.notifyError("Uspesno");
        });
    };
    return PotraznjeComponent;
}());
PotraznjeComponent = __decorate([
    core_1.Component({
        selector: 'potraznje',
        templateUrl: 'app/potraznje/potraznje.component.html',
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, router_1.Router, restorani_service_1.RestoranService, login_service_1.LoginService])
], PotraznjeComponent);
exports.PotraznjeComponent = PotraznjeComponent;
//# sourceMappingURL=potraznje.component.js.map