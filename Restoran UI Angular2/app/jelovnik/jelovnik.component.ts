/**
 * Created by Stefan on 2/28/2017.
 */
import {Component, OnInit} from "@angular/core";
import {IJelo} from "../models/jelo";
import {Notificator} from "../services/notification.service";
import {Router} from "@angular/router";
import {RestoranService} from "../services/restorani.service";
import {LoginService} from "../services/login.service";

@Component({

    selector : 'jelovnik',
    templateUrl: 'app/jelovnik/jelovnik.component.html',

})

export class JelovnikComponent implements OnInit{

    jelovnik : IJelo[];
    emailMenazderaRestorana : string;

    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan => {
            if(ulogovan!= null) {
                this.emailMenazderaRestorana = ulogovan.email;
                this._restoranService.getJelovnik(this.emailMenazderaRestorana).subscribe(jelovnik => {
                    if (jelovnik != null) this.jelovnik = jelovnik;
                });
            }
        });



    }

    constructor(private _notificator : Notificator,private _router: Router,private _restoranService : RestoranService,private _loginService : LoginService) {


    }

}
