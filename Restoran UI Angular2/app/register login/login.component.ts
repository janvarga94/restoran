import { LoginService } from './../services/login.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {IRestoran} from '../models/restoran';

import {Notificator} from '../services/notification.service';
import {RestoranService} from '../services/restorani.service';

@Component({
    templateUrl: 'app/register login/login.component.html'
})
export class LoginComponent implements OnInit {

    email : string = '';
    password : string = '';

    constructor(private _notificator: Notificator, private _loginService : LoginService) {
        
    }

    ngOnInit(){

    }

    doLogin(){
        this._loginService.loginKorisnika(this.email,this.password);
    }

 }