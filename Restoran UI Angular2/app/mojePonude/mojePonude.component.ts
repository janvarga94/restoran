/**
 * Created by Stefan on 3/2/2017.
 */

import {Component, OnInit} from "@angular/core";
import {Notificator} from "../services/notification.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../services/login.service";
import {RestoranService} from "../services/restorani.service";
import {ZaposleniDetailService} from "../services/zaposleniDetail.service";
import {ZaposleniService} from "../services/zaposleni.service";
import {IMojaPonuda} from "../models/mojaPonuda";
@Component({

    selector : 'mojeponude',
    templateUrl: 'app/mojeponude/mojeponude.component.html',

})

export class mojePonudeComponent implements OnInit{

    email : string;
    mojePonude : IMojaPonuda[];
    cenna : number;


    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan =>{
            if (ulogovan) {
                this.email = ulogovan.email;
                this._restoranService.getMojePonude(this.email).subscribe(ponude=>{
                   this.mojePonude = ponude;
                });
            }

        });
    }

    constructor(private _notificator : Notificator, private route: ActivatedRoute, private _loginService : LoginService, private _router: Router,private _restoranService : RestoranService, private _zaposleniService : ZaposleniService, private _zaposleniDetailService : ZaposleniDetailService ){

    }

    izmeni(id : number){
        console.log(this.cenna);
        console.log(id);
        this._restoranService.izmeniPonudu(id ,this.cenna).subscribe(vrednost=>
        {

        });
    }

}


