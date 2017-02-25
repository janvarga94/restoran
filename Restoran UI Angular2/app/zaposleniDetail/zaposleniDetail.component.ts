import {Component, OnInit} from "@angular/core";
import {selector} from "rxjs/operator/publish";
import {ZaposleniDetailService} from "../services/zaposleniDetail.service";
import {ZaposleniService} from "../services/zaposleni.service";
import {Notificator} from "../services/notification.service";
import {IZaposleni} from "../models/zaposleni";
import {ActivatedRoute} from "@angular/router";
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

    zaposlen : IZaposleni;
    email : string;

    constructor(private _notificator: Notificator, private _zaposleniDetailService : ZaposleniDetailService, private route: ActivatedRoute) {

    }


    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.email = params['email'];
        });

        console.log(this.email);

        this._zaposleniDetailService.getZaposlen(this.email).subscribe( zaposleni =>{
            //   this.restorani = restorani;
            this.zaposlen = zaposleni;
            console.log(this.zaposlen.radnikEmail)
        });


        let date = new Date();
        this.currentYear = date.getFullYear();
        this.currentMonth = date.getMonth() + 1;
        this.currentDay = date.getDate();
        this.weekDay = date.getDay();

        this.changeDate(this.currentDay, this.currentMonth, this.currentYear)


    }

    changeDate(day: number, month : number, year : number) {
        let newDate = new Date();
        this.currentDay = day;

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

        console.log(this.currentMonth + "_"+ this.currentYear);

        newDate.setFullYear(this.currentYear, this.currentMonth-1, 1);
        let danUNedelji = newDate.getDay();
        console.log(danUNedelji);
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

    clickedOnDay(clickedDay : number) {
        console.log(clickedDay);

        this.currentDay = clickedDay;
    }

}
