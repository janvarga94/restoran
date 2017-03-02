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
var login_service_1 = require("../services/login.service");
var router_1 = require("@angular/router");
var restorani_service_1 = require("../services/restorani.service");
var zaposleni_service_1 = require("../services/zaposleni.service");
var RestoranDetailComponent = (function () {
    function RestoranDetailComponent(_loginService, _router, _restoranService, _zaposleniService) {
        this._loginService = _loginService;
        this._router = _router;
        this._restoranService = _restoranService;
        this._zaposleniService = _zaposleniService;
    }
    RestoranDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            _this.email = ulogovan.email;
            _this._zaposleniService.getZaposleni().subscribe(function (zaposleni) {
                //   this.restorani = restorani;
                _this.zaposleni = zaposleni;
            });
        });
        console.log(this.email);
    };
    return RestoranDetailComponent;
}());
RestoranDetailComponent = __decorate([
    core_1.Component({
        selector: 'restoran-detail',
        templateUrl: 'app/restoran detail/restoranDetail.component.html'
    }),
    __metadata("design:paramtypes", [login_service_1.LoginService, router_1.Router, restorani_service_1.RestoranService, zaposleni_service_1.ZaposleniService])
], RestoranDetailComponent);
exports.RestoranDetailComponent = RestoranDetailComponent;
//# sourceMappingURL=restoranDetail.component.js.map