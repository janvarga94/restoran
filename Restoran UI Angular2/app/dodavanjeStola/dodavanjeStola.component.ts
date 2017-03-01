/**
 * Created by Stefan on 3/1/2017.
 */
import {Component, OnInit} from "@angular/core";
import {IReon} from "../models/reon";
import {Notificator} from "../services/notification.service";
import {Router} from "@angular/router";
import {RestoranService} from "../services/restorani.service";
import {LoginService} from "../services/login.service";

@Component({
    selector: 'dodavanjestola',
    templateUrl : 'app/dodavanjeStola/dodavanjeStola.component.html',

})

export class DodavanjeStolaComponent implements OnInit{

    reoni : IReon[];
    id : number;
    selectedReon : IReon;
    emailMenazderaRestorana : string;



    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan => {
            if(ulogovan!= null)  this.emailMenazderaRestorana = ulogovan.email;

            if(ulogovan!= null) {
                this._restoranService.getReoni(ulogovan.email).subscribe(reoni => {
                    //   this.restorani = restorani;
                    this.reoni = reoni;
                });
            }
        });

    }

    constructor(private _notificator : Notificator,private _router: Router,private _restoranService : RestoranService,private _loginService : LoginService) {
        /*  this._restoranService.getManagerRestoranID(this.emailMenazderaRestorana).subscribe((id:any)=>{
         this.idRestorana = id;
         console.log(this.idRestorana);
         }); */

    }

    addSto(){
        this._restoranService.addSto({brojStola : this.id,email:this.emailMenazderaRestorana,idReona : this.selectedReon['idReona']}).subscribe(response =>{
            if(response.Succes == true)  this._notificator.notifySuccess("Usposno dodat zaposlen");
            else this._notificator.notifyError("Greska");
        });

    }
}