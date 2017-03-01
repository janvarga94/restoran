import {OnInit, Component} from "@angular/core";
import {Notificator} from "../services/notification.service";
import {Router} from "@angular/router";
import {RestoranService} from "../services/restorani.service";
import {LoginService} from "../services/login.service";
import {IRestoran} from "../models/restoran";
/**
 * Created by Stefan on 3/1/2017.
 */


@Component({

    selector : 'potraznje',
    templateUrl: 'app/potraznje/potraznje.component.html',

})

export class PotraznjeComponent implements OnInit {

    potraznje : any;
    restorani : IRestoran[];
    constructor(private _notificator : Notificator,private _router: Router,private _restoranService : RestoranService,private _loginService : LoginService) {

    }

    ngOnInit(): void {
        this._restoranService.getSviRestorani().subscribe( restorani =>{
            //   this.restorani = restorani;
            this.restorani = restorani;
           this._restoranService.getNamirniceUPotraznji().subscribe();

        });
    }

}