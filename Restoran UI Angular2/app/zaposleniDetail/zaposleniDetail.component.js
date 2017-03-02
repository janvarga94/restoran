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
var zaposleniDetail_service_1 = require("../services/zaposleniDetail.service");
var notification_service_1 = require("../services/notification.service");
var router_1 = require("@angular/router");
var angular2_notifications_1 = require("angular2-notifications");
/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
var ZaposleniDetailComponent = (function () {
    function ZaposleniDetailComponent(_notificator, _zaposleniDetailService, route, _pushNotifications) {
        this._notificator = _notificator;
        this._zaposleniDetailService = _zaposleniDetailService;
        this.route = route;
        this._pushNotifications = _pushNotifications;
        this.pageTitle = "Zaposleni";
        this._days = [];
        this._neds = [];
        this.smene = [];
        this.prvaSmena = [];
        this.drugaSmena = [];
        this.trecaSmena = [];
        this.stolovi = [];
        this.jela = [];
        this.prihvacena = [];
        this.neprihvacena = [];
        this.pica = [];
        this.zaposleniDetailService = _zaposleniDetailService;
        console.log("constructor");
    }
    ZaposleniDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            _this.email = atob(params['email']);
            console.log(_this.email);
        });
        // this._pushNotifications.requestPermission();
        var date = new Date();
        this.currentYear = date.getFullYear();
        this.currentMonth = date.getMonth() + 1;
        this.currentDay = date.getDate();
        this.weekDay = date.getDay();
        this._zaposleniDetailService.getZaposlen(this.email).subscribe(function (zaposleni) {
            //   this.restorani = restorani;
            _this.zaposlen = zaposleni;
            _this.idRestoran = zaposleni[5];
            _this.changeDate(_this.currentDay, _this.currentMonth, _this.currentYear);
            _this.refreshStolovi();
            _this.refreshJela();
            _this.refreshPica();
        });
    };
    ZaposleniDetailComponent.prototype.changeDate = function (day, month, year) {
        var newDate = new Date();
        // this.currentDay = day;
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
        this.clickedOnDay(day);
        newDate.setFullYear(this.currentYear, this.currentMonth - 1, 1);
        var danUNedelji = newDate.getDay();
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
    ZaposleniDetailComponent.prototype.createWebSocket = function () {
        // console.log(example(3));
    };
    ZaposleniDetailComponent.prototype.sendNotification = function () {
        this._pushNotifications.create('Test', { body: 'something' }).subscribe(function (res) { return console.log(res); }, function (err) { return console.log(err); });
    };
    ZaposleniDetailComponent.prototype.clickedOnDay = function (clickedDay) {
        var _this = this;
        this.currentDay = clickedDay;
        this.smene = [];
        this.prvaSmena = [];
        this.drugaSmena = [];
        this.trecaSmena = [];
        this.zaposleniDetailService.getZanimanje(this.email).subscribe(function (zanimanje) {
            _this.zanimanjeInt = zanimanje;
            console.log("Parametri za smenu" + _this.idRestoran, _this.currentYear, _this.currentMonth, _this.currentDay);
            _this.zaposleniDetailService.getSmene(_this.idRestoran, _this.currentYear, _this.currentMonth, _this.currentDay).subscribe(function (smene) {
                console.log("izvrsio smene");
                for (var _a = 0, smene_1 = smene; _a < smene_1.length; _a++) {
                    var smena = smene_1[_a];
                    console.log(smena[3]);
                    if (smena[3] == _this.email) {
                        console.log("Entered if for reon");
                        _this.zaposleniDetailService.getReon(smena[1], smena[0], _this.email).subscribe(function (reon) {
                            //console.log(sm[1], sm[0], this.email);
                            _this.reon = reon;
                        });
                    }
                    smena.push(zanimanje);
                    _this.smene.push(smena);
                    if (smena[7] == _this.zanimanjeInt) {
                        if (smena[4] == 0) {
                            _this.prvaSmena.push(smena);
                        }
                        else if (smena[4] == 1) {
                            _this.drugaSmena.push(smena);
                        }
                        else if (smena[4] == 2) {
                            _this.trecaSmena.push(smena);
                        }
                    }
                }
            });
        });
    };
    ZaposleniDetailComponent.prototype.refreshStolovi = function () {
        var _this = this;
        this.stolovi = [];
        this.zaposleniDetailService.getStolovi(this.idRestoran).subscribe(function (stolovi) {
            _this.stolovi = stolovi;
        });
    };
    ZaposleniDetailComponent.prototype.refreshJela = function () {
        var _this = this;
        this.jela = [];
        this.prihvacena = [];
        this.neprihvacena = [];
        this.zaposleniDetailService.getJela(this.idRestoran, this.email).subscribe(function (jela) {
            for (var _a = 0, jela_1 = jela; _a < jela_1.length; _a++) {
                var jelo = jela_1[_a];
                console.log("Kreirana: " + new Date(jelo[7]).toLocaleDateString());
                console.log("Prihvaceno: " + new Date(jelo[10]).toLocaleDateString());
                jelo[8] = _this.getDatum(jelo[8]);
                if (jelo[10] == null || jelo[10] < jelo[7]) {
                    _this.neprihvacena.push(jelo);
                }
                else if (jelo[10] < jelo[9]) {
                }
                else if (jelo[0] == _this.email) {
                    _this.prihvacena.push(jelo);
                }
            }
            _this.jela = jela;
            console.log(_this.jela);
        });
    };
    ZaposleniDetailComponent.prototype.refreshPica = function () {
        var _this = this;
        this.pica = [];
        this.zaposleniDetailService.getPica(this.idRestoran, this.email).subscribe(function (pica) {
            // for (let pice of pica) {
            //     pice[8] = this.getDatum(pice[8]);
            //     console.log("Pice: "+pice[0]);
            //     if (pice[9]==null || pice[9]<pice[7]){
            //         this.pica.push(pice);
            //     }
            // }
            _this.pica = pica;
        });
    };
    ZaposleniDetailComponent.prototype.getDatum = function (broj) {
        var datum = new Date(broj);
        var dan = datum.getDate();
        var mesec = datum.getMonth() + 1;
        var godina = datum.getFullYear();
        var sat = datum.getHours();
        var minut = datum.getMinutes();
        return dan + '.' + mesec + '.' + godina + '. ' + sat + ':' + minut;
    };
    ZaposleniDetailComponent.prototype.napravljenoJelo = function (jelo) {
        var _this = this;
        console.log(jelo);
        this.zaposleniDetailService.skuvanoJelo(jelo[1]).subscribe(function (jelo) {
            _this.refreshJela();
        });
    };
    ZaposleniDetailComponent.prototype.prihvacenoJelo = function (jelo) {
        var _this = this;
        console.log(jelo);
        this.zaposleniDetailService.prihvacenoJelo(jelo[1]).subscribe(function (jelo) {
            _this.refreshJela();
        });
    };
    ZaposleniDetailComponent.prototype.porudzbina = function (sto) {
        // = "'/rezervacije/' + getBase(sto[4])"
        console.log(sto);
    };
    ZaposleniDetailComponent.prototype.connectToWebSocket = function () {
        // var socket = new SockJS('http://localhost:8080/stomp');
        // var stompClient = Stomp.over(socket);
        // stompClient.connect({}, function(frame) {
        //     stompClient.subscribe("/topic/message", function(data) {
        //         var message = data.body;
        //
        //     });
        // });
    };
    ZaposleniDetailComponent.prototype.napravljenoPice = function (pice) {
        var _this = this;
        console.log(pice);
        this.zaposleniDetailService.napravljenoPice(pice[1]).subscribe(function (pice) {
            _this.refreshPica();
        });
    };
    ZaposleniDetailComponent.prototype.getBase = function (url) {
        return btoa(url);
    };
    ZaposleniDetailComponent.prototype.mapNumberZanimanje = function (zan) {
        if (zan == 0) {
            return "Konobar";
        }
        else if (zan == 1) {
            return "Kuvar";
        }
        else if (zan == 2) {
            return "Šanker";
        }
        else
            return "Nema zanimanje";
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
    return ZaposleniDetailComponent;
}());
ZaposleniDetailComponent = __decorate([
    core_1.Component({
        selector: 'zaposleni/:email',
        templateUrl: 'app/zaposleniDetail/zaposleniDetail.component.html',
        providers: [zaposleniDetail_service_1.ZaposleniDetailService]
    }),
    __metadata("design:paramtypes", [notification_service_1.Notificator, zaposleniDetail_service_1.ZaposleniDetailService, router_1.ActivatedRoute, angular2_notifications_1.PushNotificationsService])
], ZaposleniDetailComponent);
exports.ZaposleniDetailComponent = ZaposleniDetailComponent;
//# sourceMappingURL=zaposleniDetail.component.js.map