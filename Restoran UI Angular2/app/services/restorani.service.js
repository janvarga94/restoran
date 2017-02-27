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
var http_1 = require("@angular/http");
var Observable_1 = require("rxjs/Observable");
require("rxjs/add/operator/do");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/map");
var notification_service_1 = require("./notification.service");
var app_config_1 = require("../app.config");
var RestoranService = (function () {
    function RestoranService(_http, _notificator) {
        this._http = _http;
        this._notificator = _notificator;
        this._restoraniUrl = 'http://localhost:8080/resursi/restorani';
        this.dodaj = app_config_1.Config.BackendUrl + '/resursi/add';
        this._managerRestoranaUrl = app_config_1.Config.BackendUrl + '/menadzerRestorana/getRestoranID';
        this._addPonudjac = app_config_1.Config.BackendUrl + '/menadzerRestorana/addPonudjac';
    }
    RestoranService.prototype.getRestorani = function () {
        return this._http.get(this._restoraniUrl)
            .map(function (response) {
            var restorani = response.json();
            // for(var i = 0; i < 10; i++)
            //     restorani.push(restorani[0]);
            console.log(restorani.length);
            return restorani;
        })
            .catch(this.handleError);
    };
    /* getRestoran(id: string): Observable<IRestoran> {
         return this.getRestorani()email
             .map((restorani: IRestoran[]) => restorani.find(r => r.naziv === id))
             .catch(this.handleError);
     } */
    RestoranService.prototype.getManagerRestoranID = function (email) {
        return this._http.get(this._managerRestoranaUrl + "?email=" + email)
            .map(function (response) {
            var id = response.json();
            return id;
        })
            .catch(this.handleError);
    };
    RestoranService.prototype.getRestoran = function () {
    };
    /* addRestoran(restoran : IRestoran): Observable<ISuccess>{
           return this._http.get("api/successResponse.json")
             .map((response: Response) => {   return <ISuccess> response.json(); })
             .catch(this.handleError);
     } */
    /* addRestoran(restoran : any) {
  
     } */
    RestoranService.prototype.addRestoran = function (restoran) {
        return this._http.post(this.dodaj, restoran).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.addPonudjac = function (ponudjac) {
        return this._http.post(this._addPonudjac, ponudjac).map(function (response) {
            return response.json();
        }).catch(this.handleError);
    };
    RestoranService.prototype.handleError = function (error) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable_1.Observable.throw(error.json().error || 'Server error');
    };
    return RestoranService;
}());
RestoranService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.Notificator])
], RestoranService);
exports.RestoranService = RestoranService;
//# sourceMappingURL=restorani.service.js.map