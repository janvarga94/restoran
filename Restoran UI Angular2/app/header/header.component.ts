import { Router } from '@angular/router';
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
    ulogovan : any = null;
    public asdf : string = "asfd";

    constructor(private _loginService : LoginService, private _router: Router,){

    }

    ngOnInit() : void{
         this._loginService.ulogovan.subscribe(ulogovan =>{
             this.ulogovan = ulogovan;
         });

         //proverimo dali je neko prethodno ulogovan na "rememberMe"
         this._loginService.loginAkoJeRememberMeBio();

    }

    doLogin() : void{
        //this._loginService.loginKorisnika(this.username,this.password);
    }

    doLogout() : void{
        this._loginService.logoutKorisnika();
         this._router.navigate(['/']);
    }


 }
