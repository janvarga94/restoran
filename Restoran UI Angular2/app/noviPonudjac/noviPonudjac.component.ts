import {Component, OnInit} from "@angular/core";
import {RestoranService} from "../services/restorani.service";
import {Router} from "@angular/router";
import {Notificator} from "../services/notification.service";
/**
 * Created by Stefan on 2/25/2017.
 */

@Component({
    selector: 'noviponudjac',
    templateUrl : 'app/noviPonudjac/noviPonudjac.component.html',

})

export class NoviPonudjacComponent implements OnInit {

    naziv : string;
    email : string;
    lozinka : string;

    constructor(private _restoranService : RestoranService,private _router: Router,private _notificator : Notificator) {

    }

    ngOnInit(): void {

    }

    addMenadzera(){
        this._restoranService.addPonudjac({naziv: this.naziv, email :this.email, lozinka : this.lozinka}).subscribe(response =>{
            if(response.Succes == true)  this._notificator.notifySuccess("Usposno dodat ponudjac");
            else this._notificator.notifyError("Greska");
        })
    }

}

