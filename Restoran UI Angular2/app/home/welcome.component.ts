import { RestoranService } from './../services/restorani.service';
import { RezervacijaService } from './../services/rezervacija.service';
import {Component, OnInit} from '@angular/core';
import {IRestoran} from "../models/restoran";
import {WelcomeService} from "../services/welcome.service";
import {RestoranService} from "../services/restorani.service";
import {LoginResponse} from "../models/loginResponse";
import {LoginService} from "../services/login.service";


@Component({
    templateUrl: 'app/home/welcome.component.html'
})
export class WelcomeComponent implements OnInit{
    public pageTitle: string = 'Welcome people';

    poseceniRestorani : any[];
    sviRestorani : any[];
    ulogovan : any = null;

    constructor(private _restoranService : RestoranService, private _rezervacijeService : RezervacijaService, private _welcomeService : WelcomeService, public _loginService : LoginService) {

    }

    ngOnInit() : void{
        
        this._restoranService.getSviRestorani().subscribe(restorani => {
            this.sviRestorani = restorani;
        });

        this._loginService.ulogovan.subscribe(ulogovan => {
            this.ulogovan = ulogovan;
            if(ulogovan){
                this._rezervacijeService.getRezervacije(ulogovan.email).subscribe(rezervacijeIRezervacije => {                 
                    this.poseceniRestorani = rezervacijeIRezervacije; 
                });
            }
        });
    }

    rate(restoranId : number, gostEmail : string, ocena : number){
        console.log(restoranId + " , " + gostEmail + " , "+ocena);

        this._welcomeService.postOcenaForRestoran({ocena : ocena, restoranId : restoranId, gostEmail : gostEmail});
        for (let restoran of this.poseceniRestorani) {
            if (restoran.restoranId == restoranId) {
                let ocena = this._welcomeService.getOcenaForRestoran(restoranId);
                console.log(ocena);
                restoran.ocena = ocena;
            }
        }
    }
    
    getDatum(milli : any){
        return new Date(milli).toLocaleString();
    }
}
