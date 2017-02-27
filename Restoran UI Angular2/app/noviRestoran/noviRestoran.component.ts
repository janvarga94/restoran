/**
 * Created by Stefan on 2/27/2017.
 */
import {Component, OnInit} from "@angular/core";
import {RestoranService} from "../services/restorani.service";
import {Router} from "@angular/router";
import {LoginService} from "../services/login.service";
import {Notificator} from "../services/notification.service";


@Component({
    selector: 'novirestoran',
    templateUrl: 'app/noviRestoran/noviRestoran.component.html',

})

export class NoviRestoranComponent implements OnInit {

    id: number;
    vrsta : string;
    naziv : string;
    opis : string;

    ngOnInit(): void {

    }

    constructor(private _notificator : Notificator,private _loginService : LoginService, private _router: Router,private _restoranService : RestoranService ){

    }

    addRestoran(){
        this._restoranService.addRestoran({idRestorana: this.id, vrsta : this.vrsta, naziv : this.naziv, opis : this.opis}).subscribe(response =>{
            if(response.Succes == true)  this._notificator.notifySuccess("Usposno dodat restoran");
            else this._notificator.notifyError("Greska");});

    }

}