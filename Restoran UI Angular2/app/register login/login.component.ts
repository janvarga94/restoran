import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {IRestoran} from '../models/restoran';

import {Notificator} from '../services/notification.service';
import {RestoranService} from '../services/restorani.service';

@Component({
    templateUrl: 'app/register login/login.component.html'
})
export class LoginComponent implements OnInit {

    constructor(private _notificator: Notificator) {
        
    }

    ngOnInit(){

    }

 }