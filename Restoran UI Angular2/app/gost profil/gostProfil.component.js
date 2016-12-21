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
var GostProfilComponent = (function () {
    function GostProfilComponent() {
        this.search = '';
        this.gost = {
            ime: "Stefan",
            prezime: "Boskovic",
            email: "as;ldkjfsadl;kfjsda@gmail.com",
        };
        this.sviGosti = [
            {
                ime: "Jan",
                prezime: "Ga",
                email: "as;ldkjfsadl;kfjsda@gmail.com",
                uPrijateljstvu: false
            },
            {
                ime: "Svet",
                prezime: "Ar",
                email: "as;ldkjfsadl;kfjsda@gmail.com",
                uPrijateljstvu: true
            }
        ];
    }
    GostProfilComponent.prototype.ngOnInit = function () {
    };
    return GostProfilComponent;
}());
GostProfilComponent = __decorate([
    core_1.Component({
        templateUrl: './app/gost profil/gostProfil.component.html'
    }),
    __metadata("design:paramtypes", [])
], GostProfilComponent);
exports.GostProfilComponent = GostProfilComponent;
//# sourceMappingURL=gostProfil.component.js.map