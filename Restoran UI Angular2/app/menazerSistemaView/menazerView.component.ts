import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {IRestoran} from '../models/restoran';

import {Notificator} from '../services/notification.service';
import {RestoranService} from '../services/restorani.service';

@Component({
    templateUrl: 'app/menazerSistemaView/menazerView.component.html'
})
export class MenazerSistemaViewComponent implements OnInit {
    
    noviNaziv : string;
    novaVrsta : string;
    noviVlasnik : string;

    restorani : IRestoran[]

    constructor(private _notificator: Notificator,private _restoraniService: RestoranService) {
        
    }

    ngOnInit(){
        this._restoraniService.getRestorani().subscribe( restorani =>{
            this.restorani = restorani;
        });
    }


    dodaj(){
        if(this.noviNaziv && this.novaVrsta && this.noviVlasnik){
            var noviRestoran : IRestoran = {
                naziv : this.noviNaziv,
                vrsta : this.novaVrsta,
                idRestorana : 10,
                opis : ""
            };
            this._restoraniService
            .addRestoran(noviRestoran)
            .subscribe( response => {
                if(response.success){
                    this._notificator.notifySuccess('Uspesno dodat restoran');
                    this.restorani.push(noviRestoran);
                }
            });
        } 
    }

    obrisiRestoran(restoran : IRestoran){
        var index = this.restorani.indexOf(restoran);
        if(index > -1){
            this.restorani.splice(index,1);
            this._notificator.notifySuccess(" obrisan");
        }
    }

 }
