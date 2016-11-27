import { Component, OnInit } from '@angular/core';

import {RestoranService} from '../services/restorani.service';
import {IRestoran} from '../models/restoran';
import { Observable } from 'rxjs/Observable';

@Component({
    selector: 'restorani',
    templateUrl: 'app/restorani/restorani.component.html'
})
export class RestoraniComponent implements OnInit{
    
    restorani : IRestoran[];

    constructor(private _restoranService : RestoranService) {
        
    }

    ngOnInit() : void{
        this._restoranService.getRestorani().subscribe( restorani =>{
         //   this.restorani = restorani;
            this.restorani = restorani;
        });
     //   console.log(this.restorani.length);
    }

 }
