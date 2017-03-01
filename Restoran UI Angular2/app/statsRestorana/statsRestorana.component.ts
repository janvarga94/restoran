import { Router, ActivatedRoute } from '@angular/router';
import { StatService } from './../services/stat.service';
import { RestoranService } from './../services/restorani.service';
import { RezervacijaService } from './../services/rezervacija.service';
import {Component, OnInit,ViewChild} from '@angular/core';
import {IRestoran} from "../models/restoran";
import {WelcomeService} from "../services/welcome.service";
import {LoginResponse} from "../models/loginResponse";
import {LoginService} from "../services/login.service";


declare var c3 : any;
declare var d3 : any;

@Component({
    templateUrl: 'app/statsRestorana/statsRestorana.component.html'
})
export class StatsRestoranaComponent implements OnInit{
    @ViewChild('chart') userProfile: any;

    restoran = 'TODO';

    constructor(private _statsService : StatService, private _router : Router, private _activeRoute : ActivatedRoute) {
        
    }

    ngOnInit(){

        this._activeRoute.params.subscribe(params =>{
            var idRestorana = params['idRestorana'];
        
            this._statsService.getAllStats(idRestorana).subscribe(stats => {
                console.log(stats);

                var poDanuY = stats.poDanu.map(x => x.zbir);
                var poDanuX = stats.poDanu.map(x => new Date(x.vreme).toISOString());

                    c3.generate({
                        bindto: '#chart',
                    
                        data: {
                            x: 'x',
                            xFormat: '%Y-%m-%dT%H:%M:%S.%LZ',
                            columns: [
                                ['x'].concat(poDanuX),
                                ['posete po danu'].concat(poDanuY)
                            ],
                            type: 'line'
                        },
                        axis: {
                            x: {
                                type: 'timeseries',
                                tick: {
                                    format: '%Y-%m-%d'
                                }
                            }
                        }
                    });

                })
       
        });
        
    }

}