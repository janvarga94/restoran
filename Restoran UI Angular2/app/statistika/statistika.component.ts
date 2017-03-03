import {Component, OnInit} from "@angular/core";
import {Notificator} from "../services/notification.service";
import {Router} from "@angular/router";
import {RestoranService} from "../services/restorani.service";
import {LoginService} from "../services/login.service";
/**
 * Created by Stefan on 3/1/2017.
 */


@Component({
    selector: 'statistika',
    templateUrl: 'app/statistika/statistika.component.html',

})

export class StatistikaComponent implements OnInit{

    ocenaRestorana : number;
    ocenajela : number;
    emailMenazderaRestorana : string;
    searchJela : string;
    ocenaJela : number;

    constructor(private _notificator : Notificator,private _router: Router,private _restoranService : RestoranService,private _loginService : LoginService){

    }

    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan => {
            if(ulogovan!= null) {
                this.emailMenazderaRestorana = ulogovan.email;
                this._restoranService.getOcenaRestorana(this.emailMenazderaRestorana).subscribe(ocena => {
                    if (ocena != null) this.ocenaRestorana = ocena;
                });
            }
        });



        console.log(this.ocenaRestorana);
    }

    pretraziJelo(){
     if(this.emailMenazderaRestorana != null) {
         this._restoranService.getOcenaJela(this.emailMenazderaRestorana, this.searchJela).subscribe(ocenaJ => {
             if (ocenaJ != null) this.ocenaJela = ocenaJ;
         });
        }
    }

}