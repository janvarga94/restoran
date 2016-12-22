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
var http_1 = require("@angular/http");
var Rx_1 = require("rxjs/Rx");
require("rxjs/add/operator/do");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/map");
var notification_service_1 = require("./notification.service");
var app_config_1 = require("../app.config");
var LoginService = (function () {
    function LoginService(_http, _notificator) {
        this._http = _http;
        this._notificator = _notificator;
        this.emailUlogovanog = "email0";
        this._restoraniUrl = 'api/loginResponse.json';
        this.bSubject = new Rx_1.BehaviorSubject({ ime: "Neko" });
        this._registerUrl = app_config_1.Config.BackendUrl + '/auth/register';
        this.ulogovan = this.bSubject.asObservable();
    }
    LoginService.prototype.ngOnInit = function () {
        //provera kesiranog
    };
    LoginService.prototype.loginKorisnika = function (email, password) {
        // this._http.get(this._restoraniUrl)
        //     .map((response: Response) => <LoginResponse> response.json())
        //     .catch(this.handleError)
        //     .subscribe(response  => {
        //         if(response.success){
        //              this.bSubject.next({ ime : username, uloga: response.uloga });
        //         }
        //     });
        this.bSubject.next({ email: email, ime: email });
    };
    LoginService.prototype.logoutKorisnika = function () {
        this.bSubject.next(null);
    };
    LoginService.prototype.registerKorisnika = function (email, password) {
        var _this = this;
        var params = new http_1.URLSearchParams();
        params.set('email', email);
        params.set('password', password);
        this._http.get(this._registerUrl + "?email=" + email + "&password=" + password)
            .map(function (response) { return response.json(); })
            .catch(this.handleError)
            .subscribe(function (response) {
            if (response.success) {
                _this.bSubject.next({ email: email, uloga: "gost" });
                _this._notificator.notifySuccess("Registrovan");
            }
        });
    };
    LoginService.prototype.handleError = function (error) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Rx_1.Observable.throw(error.json().error || 'Server error');
    };
    return LoginService;
}());
LoginService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.Notificator])
], LoginService);
exports.LoginService = LoginService;
//# sourceMappingURL=login.service.js.map