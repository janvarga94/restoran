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

    googleMap : any;

    lat: number = 45.229264;
    lng: number = 19.8516435;

    lokacije : any[] = [];

    constructor(private _restoranService : RestoranService) {
        
    }

    ngOnInit() : void{
        this._restoranService.getRestorani().subscribe( restorani =>{
         //   this.restorani = restorani;
            this.restorani = restorani;
        });
     //   console.log(this.restorani.length);

        this.getLongLat("janka cmelika 23 novi sad");
    }

    getLongLat(adresa : string){
        this._restoranService.getLongLat(adresa).subscribe( mapsResp =>{
            //   this.restorani = restorani;
            if (mapsResp['status'] == 'OK') {
                let lat = mapsResp['results'][0]['geometry']['location']['lat'];
                let lng = mapsResp['results'][0]['geometry']['location']['lng'];
                this.lokacije.push({lat : lat, lng : lng});

                console.log(mapsResp['results'][0]['geometry']['location']);
            }
        });
    }




    prikaziMapu() {

    }

 }
