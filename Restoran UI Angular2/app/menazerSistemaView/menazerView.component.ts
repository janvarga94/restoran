import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {IRestoran} from '../models/restoran';

import {Notificator} from '../services/notification.service';
import {RestoranService} from '../services/restorani.service';

@Component({
    templateUrl: 'app/menazerSistemaView/menazerView.component.html'
})
export class MenazerSistemaViewComponent implements OnInit {
    
    restorani : IRestoran[]

    constructor(private _notificator: Notificator,private _restoraniService: RestoranService) {
        
    }

    ngOnInit(){
        this._restoraniService.getRestorani().subscribe( restorani =>{
            this.restorani = restorani;
        });
    }


    dodaj(){
        this._notificator.notifySuccess("Kao dodat");
    }

 }
