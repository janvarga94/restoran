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
var restorani_service_1 = require("../services/restorani.service");
var RestoraniComponent = (function () {
    function RestoraniComponent(_restoranService) {
        this._restoranService = _restoranService;
        this.lat = 45.229264;
        this.lng = 19.8516435;
        this.lokacije = [];
    }
    RestoraniComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._restoranService.getRestorani().subscribe(function (restorani) {
            //   this.restorani = restorani;
            _this.restorani = restorani;
            for (var _i = 0, restorani_1 = restorani; _i < restorani_1.length; _i++) {
                var rest = restorani_1[_i];
                _this.getLongLat(rest['adresa']);
            }
        });
        //   console.log(this.restorani.length);
    };
    RestoraniComponent.prototype.getLongLat = function (adresa) {
        var _this = this;
        this._restoranService.getLongLat(adresa).subscribe(function (mapsResp) {
            //   this.restorani = restorani;
            if (mapsResp['status'] == 'OK') {
                var lat = mapsResp['results'][0]['geometry']['location']['lat'];
                var lng = mapsResp['results'][0]['geometry']['location']['lng'];
                _this.lokacije.push({ lat: lat, lng: lng });
                console.log(mapsResp['results'][0]['geometry']['location']);
            }
        });
    };
    RestoraniComponent.prototype.prikaziMapu = function () {
    };
    return RestoraniComponent;
}());
RestoraniComponent = __decorate([
    core_1.Component({
        selector: 'restorani',
        templateUrl: 'app/restorani/restorani.component.html'
    }),
    __metadata("design:paramtypes", [restorani_service_1.RestoranService])
], RestoraniComponent);
exports.RestoraniComponent = RestoraniComponent;
//# sourceMappingURL=restorani.component.js.map