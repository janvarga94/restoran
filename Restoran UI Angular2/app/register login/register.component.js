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
var notification_service_1 = require("../services/notification.service");
var login_service_1 = require("../services/login.service");
var router_1 = require("@angular/router");
var RegisterComponent = (function () {
    function RegisterComponent(_notificator, _router, _loginService) {
        this._notificator = _notificator;
        this._router = _router;
        this._loginService = _loginService;
        this.passwordMinLength = 4;
        this.email = "";
        this.password = "";
        this.password2 = "";
    }
    RegisterComponent.prototype.ngOnInit = function () {
    };
    RegisterComponent.prototype.doRegister = function () {
        this._loginService.registerKorisnika(this.email, this.password);
        this._router.navigateByUrl("/login");
    };
    return RegisterComponent;
}());
RegisterComponent = __decorate([
    core_1.Component({
        templateUrl: 'app/register login/register.component.html'
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, router_1.Router, login_service_1.LoginService])
], RegisterComponent);
exports.RegisterComponent = RegisterComponent;
//# sourceMappingURL=register.component.js.map