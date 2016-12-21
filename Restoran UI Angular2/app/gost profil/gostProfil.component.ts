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
    gost = {
        ime : "Stefan",
        prezime : "Boskovic",
        email : "as;ldkjfsadl;kfjsda@gmail.com",
    }

    prijatelji : any[] = [
        {
            ime : "Jan",
            prezime : "Ga",
            email : "as;ldkjfsadl;kfjsda@gmail.com",
        },
         {
            ime : "Svet",
            prezime : "Ar",
            email : "as;ldkjfsadl;kfjsda@gmail.com",
        }
    ];

    constructor() {

    }

    ngOnInit() : void{

    }

}
