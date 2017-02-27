import {Component, OnInit} from "@angular/core";
import { Router } from '@angular/router';
import {LoginService} from '../services/login.service';
import {RestoranService} from "../services/restorani.service";
import {ZaposleniService} from "../services/zaposleni.service";
import {Notificator} from "../services/notification.service";

/**
 * Created by Stefan on 2/23/2017.
 */


@Component({
    selector: 'novizaposleni',
    templateUrl: 'app/noviZaposleni/noviZaposleni.component.html',

})

export class NoviZaposleniComponent implements OnInit{

    ime : string = '';
    prezime : string = '';
    datum : Date;
    email : string = '';
    password : string = '';
    konfekcijski : any;
    obuca : any;
    jobs = [
        {id: 0, name: "Konobar"},
        {id: 1, name: "Kuvar"},
        {id: 2, name: "Sanker"}
    ];
    selectedJob : string;
    idRestorana : any;
    emailMenazderaRestorana : string;

    constructor(private _notificator : Notificator,private _loginService : LoginService, private _router: Router,private _restoranService : RestoranService, private _zaposleniService : ZaposleniService ){

    }

    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan =>{
            this.emailMenazderaRestorana = ulogovan.email;



        });


    }

    addZaposlenog() {
      /*  this._restoranService.getManagerRestoranID(this.emailMenazderaRestorana).subscribe((id:any)=>{
            this.idRestorana = id;
            console.log(this.idRestorana);

            this._zaposleniService.addZaposlen({idRestorana : this.idRestorana,ime: this.ime,prezime : this.prezime,email :this.email,pass : this.password,konfenkcijskiBroj :this.konfekcijski,velicinaObuce : this.obuca,job : this.selectedJob}).subscribe(response =>{
                this._notificator.notifySuccess("Usposno dodat zaposlen");
            });

        }); */

        this._zaposleniService.addZaposlen({emailM : this.emailMenazderaRestorana,ime: this.ime,prezime : this.prezime,email :this.email,pass : this.password,konfenkcijskiBroj :this.konfekcijski,velicinaObuce : this.obuca,selectedJob : this.selectedJob['name']}).subscribe(response =>{
           if(response.Succes == true)  this._notificator.notifySuccess("Usposno dodat zaposlen");
           else this._notificator.notifyError("Greska");
        });
        console.log(this.emailMenazderaRestorana);


    }

}
