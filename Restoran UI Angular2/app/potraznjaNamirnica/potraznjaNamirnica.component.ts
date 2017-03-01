/**
 * Created by Stefan on 2/28/2017.
 */
import {Component, OnInit} from "@angular/core";
import {INamirnica} from "../models/namirnica";
import {Notificator} from "../services/notification.service";
import {Router} from "@angular/router";
import {RestoranService} from "../services/restorani.service";
import {LoginService} from "../services/login.service";


@Component({

    selector : 'potraznjanamirnica',
    templateUrl: 'app/potraznjaNamirnica/potraznajNamirnica.component.html',

})

export class PotraznjaNamirnicaComponent implements  OnInit{

    namirnice : INamirnica[];
    sifre : number[];
    lista : any[] = [];
    datum : Date;

    emailMenazderaRestorana : string;

    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan => {
            if(ulogovan!= null) {
                this.emailMenazderaRestorana = ulogovan.email;
                this._restoranService.getNamirnice().subscribe(namirnice => {
                    if (namirnice != null) this.namirnice = namirnice;
                });
            }
        });
    }

    constructor(private _notificator : Notificator,private _router: Router,private _restoranService : RestoranService,private _loginService : LoginService) {


    }

    add(event : any,id : number){
        this.lista.push(id);
        console.log(this.lista);
    }

    addNamirnicu(){
        this._restoranService.addNamirnica({lista:this.lista, email :this.emailMenazderaRestorana, datum : this.datum})
    }





}