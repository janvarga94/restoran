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
var welcome_service_1 = require("../services/welcome.service");
var login_service_1 = require("../services/login.service");
var WelcomeComponent = (function () {
    function WelcomeComponent(_welcomeService, _loginService) {
        this._welcomeService = _welcomeService;
        this._loginService = _loginService;
        this.pageTitle = 'Welcome people';
        this.ulogovan = null;
    }
    WelcomeComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._welcomeService.getRestoraniForUser(this._loginService.emailUlogovanog).subscribe(function (restorani) {
            //   this.restorani = restorani;
            _this.restorani = restorani;
        });
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            _this.ulogovan = ulogovan;
        });
    };
    WelcomeComponent.prototype.rate = function (idRestorana, gostEmail, ocena) {
        console.log(idRestorana + " , " + gostEmail + " , " + ocena);
        this._welcomeService.postOcenaForRestoran({ ocena: ocena, idRestorana: idRestorana, gostEmail: gostEmail });
    };
    return WelcomeComponent;
}());
WelcomeComponent = __decorate([
    core_1.Component({
        templateUrl: 'app/home/welcome.component.html'
    }),
    __metadata("design:paramtypes", [welcome_service_1.WelcomeService, login_service_1.LoginService])
], WelcomeComponent);
exports.WelcomeComponent = WelcomeComponent;
//# sourceMappingURL=welcome.component.js.map