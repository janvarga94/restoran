import { Component, OnInit } from '@angular/core';

import {LoginService} from '../services/login.service';
import { IKorisnik } from '../models/korisnik';

@Component({
    selector: 'app-header',
    templateUrl: 'app/header/header.component.html'
})
export class HeaderComponent implements OnInit {
    username : string = "";
    password : string = "";
    ulogovan : IKorisnik = null;
    public asdf : string = "asfd";

    constructor(private _loginService : LoginService){

    }

    ngOnInit() : void{
        // this._loginService.ulogovan.subscribe(ulogovan =>{
        //     this.ulogovan = ulogovan;
        // });
    }

    doLogin() : void{
        //this._loginService.loginKorisnika(this.username,this.password);
    }

    doLogout() : void{
        //this._loginService.logoutKorisnika();
    }


 }
