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
var restorani_service_1 = require("../services/restorani.service");
var MenazerSistemaViewComponent = (function () {
    function MenazerSistemaViewComponent(_notificator, _restoraniService) {
        this._notificator = _notificator;
        this._restoraniService = _restoraniService;
    }
    MenazerSistemaViewComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._restoraniService.getRestorani().subscribe(function (restorani) {
            _this.restorani = restorani;
        });
    };
    MenazerSistemaViewComponent.prototype.dodaj = function () {
        var _this = this;
        if (this.noviNaziv && this.novaVrsta && this.noviVlasnik) {
            var noviRestoran = {
                naziv: this.noviNaziv,
                vrsta: this.novaVrsta,
                idRestorana: 10,
                opis: ""
            };
            this._restoraniService
                .addRestoran(noviRestoran)
                .subscribe(function (response) {
                if (response.success) {
                    _this._notificator.notifySuccess('Uspesno dodat restoran');
                    _this.restorani.push(noviRestoran);
                }
            });
        }
    };
    MenazerSistemaViewComponent.prototype.obrisiRestoran = function (restoran) {
        var index = this.restorani.indexOf(restoran);
        if (index > -1) {
            this.restorani.splice(index, 1);
            this._notificator.notifySuccess("Restoran obrisan");
        }
    };
    return MenazerSistemaViewComponent;
}());
MenazerSistemaViewComponent = __decorate([
    core_1.Component({
        templateUrl: 'app/menazerSistemaView/menazerView.component.html'
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, restorani_service_1.RestoranService])
], MenazerSistemaViewComponent);
exports.MenazerSistemaViewComponent = MenazerSistemaViewComponent;
//# sourceMappingURL=menazerView.component.js.map