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

    restorani : any[];
    ulogovan : any = null;

    constructor(private _welcomeService : WelcomeService, public _loginService : LoginService) {

    }

    ngOnInit() : void{


        this._welcomeService.getRestoraniForUser(this._loginService.emailUlogovanog).subscribe( restorani =>{
            //   this.restorani = restorani;
            this.restorani = restorani;
        });

        this._loginService.ulogovan.subscribe(ulogovan => {
            this.ulogovan = ulogovan;
        });
    }

    rate(idRestorana : number, gostEmail : string, ocena : number){
        console.log(idRestorana + " , " + gostEmail + " , "+ocena);

        this._welcomeService.postOcenaForRestoran({ocena : ocena, idRestorana : idRestorana, gostEmail : gostEmail});
        for (let restoran of this.restorani) {
            if (restoran.idRestorana == idRestorana) {
                let ocena = this._welcomeService.getOcenaForRestoran(idRestorana);
                console.log(ocena);
                restoran.ocena = ocena;
            }
        }
    }
}
