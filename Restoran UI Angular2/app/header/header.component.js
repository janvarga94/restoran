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
var router_1 = require("@angular/router");
var core_1 = require("@angular/core");
var login_service_1 = require("../services/login.service");
var HeaderComponent = (function () {
    function HeaderComponent(_loginService, _router) {
        this._loginService = _loginService;
        this._router = _router;
        this.username = "";
        this.password = "";
        this.ulogovan = null;
        this.asdf = "asfd";
    }
    HeaderComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            _this.ulogovan = ulogovan;
        });
        //proverimo dali je neko prethodno ulogovan na "rememberMe"
        this._loginService.loginAkoJeRememberMeBio();
    };
    HeaderComponent.prototype.doLogin = function () {
        //this._loginService.loginKorisnika(this.username,this.password);
    };
    HeaderComponent.prototype.doLogout = function () {
        this._loginService.logoutKorisnika();
        this._router.navigate(['/']);
    };
    return HeaderComponent;
}());
HeaderComponent = __decorate([
    core_1.Component({
        selector: 'app-header',
        templateUrl: 'app/header/header.component.html'
    }),
    __metadata("design:paramtypes", [login_service_1.LoginService, router_1.Router])
], HeaderComponent);
exports.HeaderComponent = HeaderComponent;
//# sourceMappingURL=header.component.js.map