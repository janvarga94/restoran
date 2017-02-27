import { LoginService } from './../services/login.service';
import { RezervacijaService } from './../services/rezervacija.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import {RestoranService} from '../services/restorani.service';
import {IRestoran} from '../models/restoran';
import { Observable } from 'rxjs/Observable';


@Component({
    selector: 'restorani',
    templateUrl: 'app/rezervacije/rezervacije.component.html'
})
export class RezervacijeComponent implements OnInit{
    
    odabranaRezervacija : any = {};

    rezervacije : any[] = [];

    jela: any[] = [];

    pica: any[] = [];

    randomClasses : any[] = [];
   

    constructor(private _loginService : LoginService,private _restoranService : RestoranService, private _rezervacijaService : RezervacijaService) {
        for(var i = 0; i < 20; i++){
            this.randomClasses.push(this.generateRandomClassButton());
        }
    }

    ngOnInit() : void{
        this._loginService.ulogovan.subscribe(ulogovan => {
            if(ulogovan != null){
                this._rezervacijaService.getRezervacije(ulogovan.email).subscribe(rezervacije => {
                    this.rezervacije = rezervacije;
                })
            }
        });  
            
    }

    ucitajJelaIPicaZaRestoran(restoranId : any){
        this._rezervacijaService.getJela(restoranId).subscribe(jela => {
                this.jela = jela;
            })
        this._rezervacijaService.getPica(restoranId).subscribe(pica => {
            this.pica = pica;
        })
    }

    randomClassButton(index : any){
        return this.randomClasses[index];
    }

    generateRandomClassButton(){
        switch(this.getRandomInt(0,6)){
            case 0 : return "btn-default";
            case 1 : return "btn-primary";
            case 2 : return "btn-success";
            case 3 : return "btn-info";
            case 4 : return "btn-warning";
            case 5 : return "btn-danger";
            case 6 : return "btn-link";
            default: return "btn-default";
        }
    }

    getRandomInt(min : any, max : any): any {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min)) + min;
    }

    getDate(date : any){
        return (new Date(date)).toISOString().slice(0, 16);
    }

    toFixed(num : any){
        return num.toFixed(1);
    }
 }
