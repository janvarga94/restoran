import {Component, OnInit} from "@angular/core";
import {selector} from "rxjs/operator/publish";
import {ZaposleniDetailService} from "../services/zaposleniDetail.service";
import {ZaposleniService} from "../services/zaposleni.service";
import {Notificator} from "../services/notification.service";
import {IZaposleni} from "../models/zaposleni";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable} from "rxjs";
import {PushNotificationsService} from "angular2-notifications";
/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */

@Component({
    selector: 'zaposleni/:email',
    templateUrl: 'app/zaposleniDetail/zaposleniDetail.component.html',
    providers: [ZaposleniDetailService]
})

export class ZaposleniDetailComponent implements OnInit{

    pageTitle : string = "Zaposleni";

    currentYear : number;
    currentMonth: number;
    currentDay : number;
    weekDay : number;

    _days : any[] = [];
    _neds : any[] = [];

    zaposlen : any;
    email : string;

    zaposleniDetailService : ZaposleniDetailService;

    smene : any[] = [];

    prvaSmena : any[] = [];
    drugaSmena : any[] = [];
    trecaSmena : any[] = [];

    idRestoran : number;

    zanimanjeInt : number;

    stolovi : any[] = [];

    reon : number;

    jela : any[] = [];
    prihvacena : any[] = [];
    neprihvacena : any[] = [];

    pica : any[] = [];

    constructor(private _notificator: Notificator, private _zaposleniDetailService : ZaposleniDetailService, private route: ActivatedRoute, private _pushNotifications: PushNotificationsService) {
        this.zaposleniDetailService = _zaposleniDetailService;
        console.log("constructor")
    }


    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.email = atob(params['email']);
            console.log(this.email);
        });

        // this._pushNotifications.requestPermission();

        let date = new Date();
        this.currentYear = date.getFullYear();
        this.currentMonth = date.getMonth() + 1;
        this.currentDay = date.getDate();
        this.weekDay = date.getDay();

        this._zaposleniDetailService.getZaposlen(this.email).subscribe( zaposleni =>{
            //   this.restorani = restorani;
            this.zaposlen = zaposleni;
            this.idRestoran = zaposleni[5];


            this.changeDate(this.currentDay, this.currentMonth, this.currentYear);

            this.refreshStolovi();
            this.refreshJela();
            this.refreshPica();

        });
    }

    changeDate(day: number, month : number, year : number) {
        let newDate = new Date();
        // this.currentDay = day;

        this.currentYear = year;
        this._days = [];
        if (month < 1){
            this.currentYear -= 1;
            this.currentMonth = 12;
        } else if (month > 12) {
            this.currentYear += 1;
            this.currentMonth = 1;
        } else {
            this.currentMonth = month;
        }
        this.clickedOnDay(day);

        newDate.setFullYear(this.currentYear, this.currentMonth-1, 1);
        let danUNedelji = newDate.getDay();
        if (danUNedelji < 1)
            danUNedelji += 7;
        for (let _j = danUNedelji; _j > 1; _j--){
            this._days.push("")
        }

        for (let _i = 1; _i <= this.getNumberOfDaysForMonth(); _i++ ) {
            try {
                newDate.setFullYear(this.currentYear, this.currentMonth-1, _i);
                this._days.push(_i)
            } catch (e) {
                this._days.push(_i)
            }

        }

    }

    createWebSocket(){
        // console.log(example(3));
    }

    sendNotification(){
        this._pushNotifications.create('Test', {body: 'something'}).subscribe(
            (res : any) => console.log(res),
            (err : any) => console.log(err)
        )
    }


    clickedOnDay(clickedDay : number) {

        this.currentDay = clickedDay;
        this.smene = [];
        this.prvaSmena = [];
        this.drugaSmena = [];
        this.trecaSmena = [];

        this.zaposleniDetailService.getZanimanje(this.email).subscribe(zanimanje =>{
            this.zanimanjeInt = zanimanje;
                console.log("Parametri za smenu"+this.idRestoran, this.currentYear, this.currentMonth, this.currentDay);
                this.zaposleniDetailService.getSmene(this.idRestoran, this.currentYear, this.currentMonth, this.currentDay).subscribe( smene => {
                    console.log("izvrsio smene");
                    for (let smena of smene) {
                        console.log(smena[3]);
                        if (smena[3] == this.email){
                            console.log("Entered if for reon");
                            this.zaposleniDetailService.getReon(smena[1], smena[0], this.email).subscribe(reon => {
                                //console.log(sm[1], sm[0], this.email);
                                this.reon = reon;
                            })
                        }
                        smena.push(zanimanje);
                        this.smene.push(smena);
                        if (smena[7] == this.zanimanjeInt) {
                            if (smena[4] == 0) {
                                this.prvaSmena.push(smena);
                            } else if (smena[4] == 1) {
                                this.drugaSmena.push(smena);
                            } else if (smena[4] == 2) {
                                this.trecaSmena.push(smena);
                            }
                        }
                    }
                });

            });
    }

    refreshStolovi() {
        this.stolovi = [];
        this.zaposleniDetailService.getStolovi(this.idRestoran).subscribe(stolovi => {
            this.stolovi = stolovi;
        });
    }

    refreshJela() {
        this.jela = [];
        this.prihvacena = [];
        this.neprihvacena = [];
        this.zaposleniDetailService.getJela(this.idRestoran, this.email).subscribe(jela => {
            for (let jelo of jela) {
                console.log("Kreirana: "+new Date(jelo[7]).toLocaleDateString());
                console.log("Prihvaceno: "+new Date(jelo[10]).toLocaleDateString());
                jelo[8] = this.getDatum(jelo[8]);
                if (jelo[10]==null || jelo[10]<jelo[7]) {
                    this.neprihvacena.push(jelo);
                } else if (jelo[10]<jelo[9]) {
                } else if (jelo[0] == this.email){
                    this.prihvacena.push(jelo);
                }
            }
            this.jela = jela;
            console.log(this.jela);
        });
    }

    refreshPica() {
        this.pica = [];
        this.zaposleniDetailService.getPica(this.idRestoran, this.email).subscribe(pica => {
            // for (let pice of pica) {
            //     pice[8] = this.getDatum(pice[8]);
            //     console.log("Pice: "+pice[0]);
            //     if (pice[9]==null || pice[9]<pice[7]){
            //         this.pica.push(pice);
            //     }
            // }
            this.pica = pica;
        });
    }

    getDatum(broj : number) : string{
        let datum = new Date(broj);
        let dan = datum.getDate();
        let mesec = datum.getMonth() + 1;
        let godina = datum.getFullYear();
        let sat = datum.getHours();
        let minut = datum.getMinutes();

        return dan+'.'+mesec+'.'+godina+'. '+sat+':'+minut
    }

    napravljenoJelo(jelo : any){
        console.log(jelo);
        this.zaposleniDetailService.skuvanoJelo(jelo[1]).subscribe(jelo => {
            this.refreshJela();
        });
    }

    prihvacenoJelo(jelo : any){
        console.log(jelo);
        this.zaposleniDetailService.prihvacenoJelo(jelo[1]).subscribe(jelo => {
            this.refreshJela();
        });
    }

    porudzbina(sto : any) {
        // = "'/rezervacije/' + getBase(sto[4])"
        console.log(sto)
    }

    connectToWebSocket(){

        // var socket = new SockJS('http://localhost:8080/stomp');
        // var stompClient = Stomp.over(socket);
        // stompClient.connect({}, function(frame) {
        //     stompClient.subscribe("/topic/message", function(data) {
        //         var message = data.body;
        //
        //     });
        // });

    }

    napravljenoPice(pice : any){
        console.log(pice);
        this.zaposleniDetailService.napravljenoPice(pice[1]).subscribe(pice => {
            this.refreshPica();

        });
    }

    getBase(url : string) {
        return btoa(url);
    }

    mapNumberZanimanje(zan : number){
        if (zan == 0) {
            return "Konobar";
        } else if (zan == 1) {
            return "Kuvar"
        } else if (zan == 2) {
            return "Šanker"
        } else
            return "Nema zanimanje"
    }

    mapNumbersToMonth(broj : number) {
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
    }

    mapNumbersToWeek(broj : number){
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
    }

    getNumberOfDaysForMonth() {
        if (this.currentMonth == 1 || this.currentMonth == 3 || this.currentMonth == 5 || this.currentMonth == 7 || this.currentMonth == 8 || this.currentMonth == 10 || this.currentMonth == 12) {
            return 31;
        } else if (this.currentMonth == 4 || this.currentMonth == 6 || this.currentMonth == 9 || this.currentMonth == 11) {
            return 30;
        } else if (this.currentMonth == 2 && this.currentYear % 4 == 0) {
            return 29;
        } else {
            return 28;
        }

    }

}
