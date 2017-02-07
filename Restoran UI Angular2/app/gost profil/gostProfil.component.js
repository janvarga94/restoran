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
var gosti_service_1 = require("./../services/gosti.service.");
var core_1 = require("@angular/core");
var login_service_1 = require("../services/login.service");
var GostProfilComponent = (function () {
    function GostProfilComponent(_loginService, _gostService) {
        this._loginService = _loginService;
        this._gostService = _gostService;
        this.search = '';
        this.gost = {};
        this.prijatelji = [];
        this.nePrijatelji = [];
        this.oniKojimaJePoslatZahtev = [];
    }
    GostProfilComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            _this.gost = ulogovan;
            if (ulogovan == null)
                return;
            _this._gostService.GetPrijateljeOf(ulogovan.email).subscribe(function (prijatelji) {
                console.log(prijatelji);
                _this.prijatelji = prijatelji;
            });
            _this._gostService.GetNePrijateljeOf(ulogovan.email).subscribe(function (nePrijatelji) {
                _this.nePrijatelji = nePrijatelji;
            });
            _this._gostService.GetOneKojimaJePoslatZahtev(ulogovan.email).subscribe(function (oniKojimaJePoslatZahtev) {
                _this.oniKojimaJePoslatZahtev = oniKojimaJePoslatZahtev;
            });
        });
    };
    return GostProfilComponent;
}());
GostProfilComponent = __decorate([
    core_1.Component({
        templateUrl: './app/gost profil/gostProfil.component.html'
    }),
    __metadata("design:paramtypes", [login_service_1.LoginService, gosti_service_1.GostiService])
], GostProfilComponent);
exports.GostProfilComponent = GostProfilComponent;
//# sourceMappingURL=gostProfil.component.js.map