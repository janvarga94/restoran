import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {IRestoran} from '../models/restoran';

import {Notificator} from '../services/notification.service';
import {RestoranService} from '../services/restorani.service';
import {LoginService} from '../services/login.service';

import {Router} from '@angular/router';

@Component({
    templateUrl: 'app/register login/register.component.html'
})
export class RegisterComponent implements OnInit {

    passwordiRazlicitiText : string;
    passwordMinLength : number = 4;

    ime : string = "";
    prezime : string = "";
    email : string = "";
    password : string = "";
    password2 : string = "";

    router : Router;

    constructor(private _notificator: Notificator, private _router: Router,private _loginService: LoginService) {
        
    }

    ngOnInit(){

    }

    doRegister(){
        this._loginService.registerKorisnika(this.email,this.password, this.ime, this.prezime);
        this._notificator.notifyInfo("Molim sacekajte.....");
    }

 }