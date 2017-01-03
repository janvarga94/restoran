import { ActivatedRoute } from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {IRestoran} from "../models/restoran";
import {WelcomeService} from "../services/welcome.service";
import {RestoranService} from "../services/restorani.service";
import {LoginResponse} from "../models/loginResponse";
import {LoginService} from "../services/login.service";


@Component({
    templateUrl: 'app/accountActivation/activation.component.html'
})
export class ActivationComponent implements OnInit{

    token : string;

    constructor(public _loginService : LoginService, private route: ActivatedRoute) {

    }

    ngOnInit() : void{
        this.route.params.subscribe(params => {
            var param = params["token"];
            this.token = <string> param;
            this._loginService.activateKorisnika(param);
        });
    }
}
