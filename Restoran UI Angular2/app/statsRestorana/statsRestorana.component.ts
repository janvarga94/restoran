import { StatService } from './../services/stat.service';
import { RestoranService } from './../services/restorani.service';
import { RezervacijaService } from './../services/rezervacija.service';
import {Component, OnInit} from '@angular/core';
import {IRestoran} from "../models/restoran";
import {WelcomeService} from "../services/welcome.service";
import {LoginResponse} from "../models/loginResponse";
import {LoginService} from "../services/login.service";


@Component({
    templateUrl: 'app/statsRestorana/statsRestorana.component.html'
})
export class StatsRestoranaComponent implements OnInit{


    constructor(private _statsService : StatService) {

    }

    ngOnInit(){
        this._statsService.getAllStats(3).subscribe(stats => {
            console.log(stats);
        })
    }
}