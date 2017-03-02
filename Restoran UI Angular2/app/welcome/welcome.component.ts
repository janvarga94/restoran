import { RestoranService } from './../services/restorani.service';
import { RezervacijaService } from './../services/rezervacija.service';
import {Component, OnInit} from '@angular/core';
import {IRestoran} from "../models/restoran";
import {WelcomeService} from "../services/welcome.service";
import {LoginResponse} from "../models/loginResponse";
import {LoginService} from "../services/login.service";


@Component({
    templateUrl: 'app/welcome/welcome.component.html'
})
export class WelcomeComponent implements OnInit{
    public pageTitle: string = 'Welcome people';

    poseceniRestorani : any[] = [];
    sviRestorani : any[] = [];
    ulogovan : any = null;

    gostEmail : string;

    jela : any[] = [];

    constructor(private _restoranService : RestoranService, private _rezervacijeService : RezervacijaService, private _welcomeService : WelcomeService, public _loginService : LoginService) {

    }

    ngOnInit() : void{
        
        this._restoranService.getSviRestorani().subscribe(restorani => {
            this.sviRestorani = restorani;
        });

        this._loginService.ulogovan.subscribe(ulogovan => {
            this.ulogovan = ulogovan;
            if(ulogovan){
                this.gostEmail = ulogovan.email;
                this._rezervacijeService.getRezervacije(ulogovan.email).subscribe(rezervacijeIRezervacije => {      
                    for(var i = 0; i < rezervacijeIRezervacije.length; i++){
                        var curr = rezervacijeIRezervacije[i];
                        if(this.poseceniRestorani.filter(t => t.restoranId == curr.restoranId).length == 0){
                            this.poseceniRestorani.push(curr);
                        }
                    }           
                });
            }
        });
    }

    rate(restoranId : number, gostEmail : string, ocena : number){
        console.log(restoranId + " , " + gostEmail + " , "+ocena);

        this._welcomeService.postOcenaForRestoran(restoranId, ocena, gostEmail).subscribe(response => {
            for (let restoran of this.poseceniRestorani) {
                if (restoran.restoranId == restoranId) {
                    let ocena = this._welcomeService.getOcenaForRestoran(restoranId);
                    console.log(ocena);
                    restoran.ocena = ocena;
                }
            }
        });


    }

    getJela(restoran : any) {
        console.log(restoran);
        this._welcomeService.getJelaForRestoran(restoran.restoranId, this.gostEmail).subscribe(response => {
            this.jela = response;
        });
    }

    rateJelo(jelo : any, ocena : number){
        this._welcomeService.oceniJelo(jelo[0], jelo[1], this.gostEmail, ocena).subscribe(response => {

        });
    }
    
    getDatum(milli : any){
        return new Date(milli).toLocaleString();
    }
}
