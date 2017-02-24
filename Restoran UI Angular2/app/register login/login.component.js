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
var login_service_1 = require('./../services/login.service');
var core_1 = require('@angular/core');
var notification_service_1 = require('../services/notification.service');
var LoginComponent = (function () {
    function LoginComponent(_notificator, _loginService) {
        this._notificator = _notificator;
        this._loginService = _loginService;
        this.rememberMe = false;
        this.email = '';
        this.password = '';
    }
    LoginComponent.prototype.ngOnInit = function () {
    };
    LoginComponent.prototype.doLogin = function () {
        this._loginService.loginKorisnika(this.email, this.password, this.rememberMe, true);
    };
    LoginComponent = __decorate([
        core_1.Component({
            templateUrl: 'app/register login/login.component.html'
        }), 
        __metadata('design:paramtypes', [notification_service_1.Notificator, login_service_1.LoginService])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map