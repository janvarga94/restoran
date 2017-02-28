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
var restorani_service_1 = require("../services/restorani.service");
var router_1 = require("@angular/router");
var login_service_1 = require("../services/login.service");
/**
 * Created by Stefan on 2/27/2017.
 */
var DodatiReonComponent = (function () {
    function DodatiReonComponent(_notificator, _router, _restoranService, _loginService) {
        /*  this._restoranService.getManagerRestoranID(this.emailMenazderaRestorana).subscribe((id:any)=>{
         this.idRestorana = id;
         console.log(this.idRestorana);
         }); */
        this._notificator = _notificator;
        this._router = _router;
        this._restoranService = _restoranService;
        this._loginService = _loginService;
    }
    DodatiReonComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            if (ulogovan != null)
                _this.emailMenazderaRestorana = ulogovan.email;
        });
    };
    DodatiReonComponent.prototype.addReon = function () {
        var _this = this;
        this._restoranService.addReon({ emailMenadzeraRestorana: this.emailMenazderaRestorana, id: this.id, opis: this.opis }).subscribe(function (response) {
            if (response.Succes == true)
                _this._notificator.notifySuccess("Usposno dodat zaposlen");
            else
                _this._notificator.notifyError("Greska");
        });
    };
    return DodatiReonComponent;
}());
DodatiReonComponent = __decorate([
    core_1.Component({
        selector: 'dodavanjereona',
        templateUrl: 'app/dodavanjeReona/dodavanjeReona.component.html',
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, router_1.Router, restorani_service_1.RestoranService, login_service_1.LoginService])
], DodatiReonComponent);
exports.DodatiReonComponent = DodatiReonComponent;
//# sourceMappingURL=dodavanjeReona.component.js.map