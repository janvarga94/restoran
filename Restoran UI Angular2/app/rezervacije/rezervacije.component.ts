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

    rezervacije : any[] = [
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

    jela: any[] = [
        {naziv: "asdf"},{naziv: "asdf"},{naziv: "asdf"},{naziv: "asdf"},
    ];

    pica: any[] = [
        {naziv: "asdf"},{naziv: "asdf"},{naziv: "asdf"},{naziv: "asdf"},
    ];
   

    constructor(private _restoranService : RestoranService) {
        
    }

    ngOnInit() : void{
    
    }

 }
