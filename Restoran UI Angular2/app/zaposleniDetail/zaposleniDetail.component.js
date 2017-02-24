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
var zaposleniDetail_service_1 = require("../services/zaposleniDetail.service");
var notification_service_1 = require("../services/notification.service");
var router_1 = require("@angular/router");
/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
var ZaposleniDetailComponent = (function () {
    function ZaposleniDetailComponent(_notificator, _zaposleniDetailService, route) {
        this._notificator = _notificator;
        this._zaposleniDetailService = _zaposleniDetailService;
        this.route = route;
        this.pageTitle = "Zaposleni";
        this._days = [];
        this._neds = [];
    }
    ZaposleniDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            _this.email = params['email'];
        });
        console.log(this.email);
        this._zaposleniDetailService.getZaposlen(this.email).subscribe(function (zaposleni) {
            //   this.restorani = restorani;
            _this.zaposlen = zaposleni;
            console.log(_this.zaposlen.radnikEmail);
        });
        var date = new Date();
        this.currentYear = date.getFullYear();
        this.currentMonth = date.getMonth() + 1;
        this.currentDay = date.getDate();
        this.weekDay = date.getDay();
        this.changeDate(this.currentDay, this.currentMonth, this.currentYear);
    };
    ZaposleniDetailComponent.prototype.changeDate = function (day, month, year) {
        var newDate = new Date();
        this.currentDay = day;
        this.currentYear = year;
        this._days = [];
        if (month < 1) {
            this.currentYear -= 1;
            this.currentMonth = 12;
        }
        else if (month > 12) {
            this.currentYear += 1;
            this.currentMonth = 1;
        }
        else {
            this.currentMonth = month;
        }
        console.log(this.currentMonth + "_" + this.currentYear);
        newDate.setFullYear(this.currentYear, this.currentMonth - 1, 1);
        var danUNedelji = newDate.getDay();
        console.log(danUNedelji);
        if (danUNedelji < 1)
            danUNedelji += 7;
        for (var _j = danUNedelji; _j > 1; _j--) {
            this._days.push("");
        }
        for (var _i = 1; _i <= this.getNumberOfDaysForMonth(); _i++) {
            try {
                newDate.setFullYear(this.currentYear, this.currentMonth - 1, _i);
                this._days.push(_i);
            }
            catch (e) {
                this._days.push(_i);
            }
        }
    };
    ZaposleniDetailComponent.prototype.mapNumbersToWeek = function (broj) {
        if (broj == 0)
            return "Nedelja";
        else if (broj == 1)
            return "Ponedeljak";
        else if (broj == 2)
            return "Utorak";
        else if (broj == 3)
            return "Sreda";
        else if (broj == 4)
            return "Cetvrtak";
        else if (broj == 5)
            return "Petak";
        else if (broj == 6)
            return "Subota";
    };
    ZaposleniDetailComponent.prototype.mapNumbersToMonth = function (broj) {
        if (broj == 1)
            return "Januar";
        else if (broj == 2)
            return "Februar";
        else if (broj == 3)
            return "Mart";
        else if (broj == 4)
            return "April";
        else if (broj == 5)
            return "Maj";
        else if (broj == 6)
            return "Jun";
        else if (broj == 7)
            return "Jul";
        else if (broj == 8)
            return "Avgust";
        else if (broj == 9)
            return "Septembar";
        else if (broj == 10)
            return "Oktobar";
        else if (broj == 11)
            return "Novembar";
        else if (broj == 12)
            return "Decembar";
    };
    ZaposleniDetailComponent.prototype.getNumberOfDaysForMonth = function () {
        if (this.currentMonth == 1 || this.currentMonth == 3 || this.currentMonth == 5 || this.currentMonth == 7 || this.currentMonth == 8 || this.currentMonth == 10 || this.currentMonth == 12) {
            return 31;
        }
        else if (this.currentMonth == 4 || this.currentMonth == 6 || this.currentMonth == 9 || this.currentMonth == 11) {
            return 30;
        }
        else if (this.currentMonth == 2 && this.currentYear % 4 == 0) {
            return 29;
        }
        else {
            return 28;
        }
    };
    ZaposleniDetailComponent.prototype.clickedOnDay = function (clickedDay) {
        console.log(clickedDay);
        this.currentDay = clickedDay;
    };
    ZaposleniDetailComponent = __decorate([
        core_1.Component({
            selector: 'zaposleni/:email',
            templateUrl: 'app/zaposleniDetail/zaposleniDetail.component.html',
            providers: [zaposleniDetail_service_1.ZaposleniDetailService]
        }), 
        __metadata('design:paramtypes', [notification_service_1.Notificator, zaposleniDetail_service_1.ZaposleniDetailService, router_1.ActivatedRoute])
    ], ZaposleniDetailComponent);
    return ZaposleniDetailComponent;
}());
exports.ZaposleniDetailComponent = ZaposleniDetailComponent;
//# sourceMappingURL=zaposleniDetail.component.js.map