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
var RezervacijaComponent = (function () {
    function RezervacijaComponent(_restoranService) {
        this._restoranService = _restoranService;
        this.danas = new Date();
        this.odabraniDatum = new Date();
        this.stolovi = [
            {
                zauzet: false
            },
            {
                zauzet: true
            },
            {
                zauzet: true
            },
            {
                zauzet: false
            },
            {
                zauzet: false
            },
            {
                zauzet: true
            },
            {
                zauzet: true
            },
            {
                zauzet: false
            },
        ];
        this.prijatelji = [
            { email: "asd@fds", ime: "jfjsa", prezime: "asdf" },
            { email: "asd@fds", ime: "asd", prezime: "asdf" },
            { email: "asd@fds", ime: "sdfa", prezime: "asdf" },
            { email: "asd@fds", ime: "fdsa", prezime: "asdf" },
            { email: "asd@fds", ime: "asdf", prezime: "asdf" },
            { email: "asd@fds", ime: "asdf", prezime: "asdf" },
        ];
    }
    RezervacijaComponent.prototype.ngOnInit = function () {
    };
    RezervacijaComponent = __decorate([
        core_1.Component({
            selector: 'restorani',
            templateUrl: 'app/rezervacija/rezervacija.component.html'
        }), 
        __metadata('design:paramtypes', [restorani_service_1.RestoranService])
    ], RezervacijaComponent);
    return RezervacijaComponent;
}());
exports.RezervacijaComponent = RezervacijaComponent;
//# sourceMappingURL=rezervacija.component.js.map