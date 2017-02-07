import { IUlogovan } from './../models/ulogovan';
import { Notificator } from './../services/notification.service';
import { GostiService } from './../services/gosti.service.';
import { Component } from '@angular/core';
import {IRestoran} from "../models/restoran";
import {WelcomeService} from "../services/welcome.service";
import {RestoranService} from "../services/restorani.service";
import {LoginResponse} from "../models/loginResponse";
import {LoginService} from "../services/login.service";


@Component({
    templateUrl: './app/gost profil/gostProfil.component.html'
})
export class GostProfilComponent {

    search : string = '';

    gost : IUlogovan = null;

    prijatelji : any[] = [];
    nePrijatelji : any[] = [];
    oniKojimaJePoslatZahtev : any[] = [];


    constructor(private _loginService : LoginService, private _gostService : GostiService, private _notificator : Notificator) {

    }

    ngOnInit() : void{
        this._loginService.ulogovan.subscribe(ulogovan =>{
            this.gost = ulogovan;

            if(ulogovan == null) return;

            this._gostService.GetPrijateljeOf(ulogovan.email).subscribe(prijatelji => {
                console.log(prijatelji);
                this.prijatelji = prijatelji;
                
            });

            
            this._gostService.GetNePrijateljeOf(ulogovan.email).subscribe(nePrijatelji => {
                this.nePrijatelji = nePrijatelji;
                
            });
            
             this._gostService.GetOneKojimaJePoslatZahtev(ulogovan.email).subscribe(oniKojimaJePoslatZahtev => {
                this.oniKojimaJePoslatZahtev = oniKojimaJePoslatZahtev;
                
            });

        });

    }

    modifyGosta(): void{
         this._gostService.ModifyGosta(this.gost['ime'], this.gost['prezime'], this.gost['email']).subscribe(response => {
             if(response.Success == true){
                 this._notificator.notifySuccess("Uspesna izmena!");
             }else{
                 this._notificator.notifyInfo("Izmena nije izvrsena: " + response.Message);
             }
         })
    }
}
