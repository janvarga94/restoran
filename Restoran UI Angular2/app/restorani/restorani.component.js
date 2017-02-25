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
var restorani_service_1 = require('../services/restorani.service');
var RestoraniComponent = (function () {
    function RestoraniComponent(_restoranService) {
        this._restoranService = _restoranService;
    }
    RestoraniComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._restoranService.getRestorani().subscribe(function (restorani) {
            //   this.restorani = restorani;
            _this.restorani = restorani;
        });
        //   console.log(this.restorani.length);
    };
    RestoraniComponent = __decorate([
        core_1.Component({
            selector: 'restorani',
            templateUrl: 'app/restorani/restorani.component.html'
        }), 
        __metadata('design:paramtypes', [restorani_service_1.RestoranService])
    ], RestoraniComponent);
    return RestoraniComponent;
}());
exports.RestoraniComponent = RestoraniComponent;
//# sourceMappingURL=restorani.component.js.map