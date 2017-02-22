import { Component, OnInit } from '@angular/core';

import {RestoranService} from '../services/restorani.service';
import {IRestoran} from '../models/restoran';
import { Observable } from 'rxjs/Observable';


@Component({
    selector: 'restorani',
    templateUrl: 'app/rezervacija/rezervacija.component.html'
})
export class RezervacijaComponent implements OnInit{
    
    danas : Date = new Date();
    odabraniDatum : Date = new Date();

    stolovi : any[] = [
        {
            zauzet : false
        },
         {
            zauzet : true
        },
         {
            zauzet : true
        },
         {
            zauzet : false
        },
          {
            zauzet : false
        },
         {
            zauzet : true
        },
         {
            zauzet : true
        },
         {
            zauzet : false
        },
    ];

    prijatelji : any[] = [
        { email: "asd@fds", ime: "jfjsa", prezime: "asdf"},
        { email: "asd@fds", ime: "asd", prezime: "asdf"},
        { email: "asd@fds", ime: "sdfa", prezime: "asdf"},
        { email: "asd@fds", ime: "fdsa", prezime: "asdf"},
        { email: "asd@fds", ime: "asdf", prezime: "asdf"},
        { email: "asd@fds", ime: "asdf", prezime: "asdf"},
        
    ];

    constructor(private _restoranService : RestoranService) {
        
    }

    ngOnInit() : void{
    
    }

 }
