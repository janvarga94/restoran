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
var router_1 = require('@angular/router');
var core_1 = require('@angular/core');
var login_service_1 = require("../services/login.service");
var ActivationComponent = (function () {
    function ActivationComponent(_loginService, route) {
        this._loginService = _loginService;
        this.route = route;
    }
    ActivationComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            var param = params["token"];
            _this.token = param;
            _this._loginService.activateKorisnika(param);
        });
    };
    ActivationComponent = __decorate([
        core_1.Component({
            templateUrl: 'app/accountActivation/activation.component.html'
        }), 
        __metadata('design:paramtypes', [login_service_1.LoginService, router_1.ActivatedRoute])
    ], ActivationComponent);
    return ActivationComponent;
}());
exports.ActivationComponent = ActivationComponent;
//# sourceMappingURL=activation.component.js.map