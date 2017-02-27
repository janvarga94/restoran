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
var router_1 = require("@angular/router");
var login_service_1 = require("../services/login.service");
var restorani_service_1 = require("../services/restorani.service");
var zaposleni_service_1 = require("../services/zaposleni.service");
var notification_service_1 = require("../services/notification.service");
/**
 * Created by Stefan on 2/23/2017.
 */
var NoviZaposleniComponent = (function () {
    function NoviZaposleniComponent(_notificator, _loginService, _router, _restoranService, _zaposleniService) {
        this._notificator = _notificator;
        this._loginService = _loginService;
        this._router = _router;
        this._restoranService = _restoranService;
        this._zaposleniService = _zaposleniService;
        this.ime = '';
        this.prezime = '';
        this.email = '';
        this.password = '';
        this.jobs = [
            { id: 0, name: "Konobar" },
            { id: 1, name: "Kuvar" },
            { id: 2, name: "Sanker" }
        ];
    }
    NoviZaposleniComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._loginService.ulogovan.subscribe(function (ulogovan) {
            _this.emailMenazderaRestorana = ulogovan.email;
        });
    };
    NoviZaposleniComponent.prototype.addZaposlenog = function () {
        /*  this._restoranService.getManagerRestoranID(this.emailMenazderaRestorana).subscribe((id:any)=>{
              this.idRestorana = id;
              console.log(this.idRestorana);
  
              this._zaposleniService.addZaposlen({idRestorana : this.idRestorana,ime: this.ime,prezime : this.prezime,email :this.email,pass : this.password,konfenkcijskiBroj :this.konfekcijski,velicinaObuce : this.obuca,job : this.selectedJob}).subscribe(response =>{
                  this._notificator.notifySuccess("Usposno dodat zaposlen");
              });
  
          }); */
        var _this = this;
        this._zaposleniService.addZaposlen({ emailM: this.emailMenazderaRestorana, ime: this.ime, prezime: this.prezime, email: this.email, pass: this.password, konfenkcijskiBroj: this.konfekcijski, velicinaObuce: this.obuca, selectedJob: this.selectedJob['name'] }).subscribe(function (response) {
            if (response.Succes == true)
                _this._notificator.notifySuccess("Usposno dodat zaposlen");
            else
                _this._notificator.notifyError("Greska");
        });
        console.log(this.emailMenazderaRestorana);
    };
    return NoviZaposleniComponent;
}());
NoviZaposleniComponent = __decorate([
    core_1.Component({
        selector: 'novizaposleni',
        templateUrl: 'app/noviZaposleni/noviZaposleni.component.html',
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, login_service_1.LoginService, router_1.Router, restorani_service_1.RestoranService, zaposleni_service_1.ZaposleniService])
], NoviZaposleniComponent);
exports.NoviZaposleniComponent = NoviZaposleniComponent;
//# sourceMappingURL=noviZaposleni.component.js.map