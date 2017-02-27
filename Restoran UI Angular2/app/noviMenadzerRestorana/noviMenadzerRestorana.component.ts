/**
 * Created by Stefan on 2/27/2017.
 */
import {Component, OnInit} from "@angular/core";
import {RestoranService} from "../services/restorani.service";
import {IRestoran} from "../models/restoran";
import {Router} from "@angular/router";
import {ZaposleniService} from "../services/zaposleni.service";
import {Notificator} from "../services/notification.service";


@Component({
    selector: 'novimenadzerrestorana',
    templateUrl : 'app/noviMenadzerRestorana/noviMenadzerRestorana.component.html',

})

export class NoviMenadzerRestoranaComponent implements OnInit {

    ime: string;
    prezime : string;
    password: string;
    email: string;
    restorani : IRestoran[];
    selectedRestoran : IRestoran;


    constructor(private _restoranService : RestoranService,private _router: Router,private _zaposleniService : ZaposleniService,private _notificator : Notificator) {

    }


    ngOnInit(): void {
        this._restoranService.getRestorani().subscribe( restorani =>{
            //   this.restorani = restorani;
            this.restorani = restorani;
        });
    }

    addMenadzera() {
        this._zaposleniService.addMenadzera({ime : this.ime,prezime : this.prezime, password : this.password, email : this.email, id : this.selectedRestoran['idRestorana'] }).subscribe(response =>{
            if(response.Succes == true)  this._notificator.notifySuccess("Usposno dodat zaposlen");
            else this._notificator.notifyError("Greska");
        });

    }

}