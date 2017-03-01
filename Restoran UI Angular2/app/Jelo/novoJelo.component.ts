/**
 * Created by Stefan on 3/1/2017.
 */

import {Component, OnInit} from "@angular/core";
import {RestoranService} from "../services/restorani.service";
import {Router} from "@angular/router";
import {Notificator} from "../services/notification.service";
import {LoginService} from "../services/login.service";
@Component({
    selector: 'novojelo',
    templateUrl : 'app/Jelo/novoJelo.component.html',

})

export class NovoJeloComponent implements OnInit{

    naziv : string;
    opis : string;
    cena : number;
    email : string;


    constructor(private _restoranService : RestoranService,private _router: Router,private _notificator : Notificator,private _loginService : LoginService) {

    }

    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan =>{
            this.email = ulogovan.email;

        });
    }

    addJelo(){
        this._restoranService.addJelo({naziv:this.naziv,opis:this.opis,cena:this.cena,email:this.email}).subscribe(response =>{
            if(response.Succes == true)  this._notificator.notifySuccess("Usposno dodat ponudjac");
            else this._notificator.notifyError("Greska");
        })
    }



}