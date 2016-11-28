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
var core_1 = require('@angular/core');
require('rxjs/add/operator/do');
require('rxjs/add/operator/catch');
require('rxjs/add/operator/map');
var ng2_toastr_1 = require('ng2-toastr/ng2-toastr');
var Notificator = (function () {
    function Notificator(_toastr) {
        this._toastr = _toastr;
        this._restoraniUrl = 'api/restorani.json';
    }
    Notificator.prototype.notifySuccess = function (message) {
        this._toastr.success(message);
    };
    Notificator.prototype.notifyInfo = function (message) {
        this._toastr.info(message);
    };
    Notificator.prototype.notifyError = function (message) {
        this._toastr.error(message);
    };
    Notificator = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [ng2_toastr_1.ToastsManager])
    ], Notificator);
    return Notificator;
}());
exports.Notificator = Notificator;
//# sourceMappingURL=notification.service.js.map