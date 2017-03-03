import {OnInit, Component} from "@angular/core";
import {Notificator} from "../services/notification.service";
import {Router} from "@angular/router";
import {RestoranService} from "../services/restorani.service";
import {LoginService} from "../services/login.service";
import {IRestoran} from "../models/restoran";
import {INamirnicaP} from "../models/namirnicaP";
/**
 * Created by Stefan on 3/1/2017.
 */


@Component({

    selector : 'potraznje',
    templateUrl: 'app/potraznje/potraznje.component.html',

})

export class PotraznjeComponent implements OnInit {

    restorani : IRestoran[];
    potraznje : INamirnicaP[];
    iznos : number;
    emailPonudjaca : string;

    constructor(private _notificator : Notificator,private _router: Router,private _restoranService : RestoranService,private _loginService : LoginService) {

    }

    ngOnInit(): void {

           this._restoranService.getNamirniceUPotraznji().subscribe(potraznje =>
           {
               this.potraznje = potraznje;
           });

           this._loginService.ulogovan.subscribe(ulogovan =>{
               if (ulogovan)
                   this.emailPonudjaca = ulogovan.email;

           });


    }

    addPonuda(idR: number ,dokad : Date,cena : number){
        this._restoranService.addPonuda({email : this.emailPonudjaca, id : idR, datum : dokad, iznos : cena}).subscribe(response =>{
            if(response.Success == true)  this._notificator.notifySuccess("Greska");
            else this._notificator.notifyError("Uspesno");
        });
    }

}