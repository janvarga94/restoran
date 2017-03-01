import {Component, OnInit} from "@angular/core";
import {LoginService} from "../services/login.service";
import {Router} from "@angular/router";
import {RestoranService} from "../services/restorani.service";
import {IZaposleni} from "../models/zaposleni";
import {ZaposleniService} from "../services/zaposleni.service";

@Component({
    selector: 'restoran-detail',
    templateUrl: 'app/restoran detail/restoranDetail.component.html'
})
export class RestoranDetailComponent  implements OnInit {

    restoran : any;
    email : string;
    zaposleni : IZaposleni[];

    constructor(private _loginService : LoginService, private _router: Router,private _restoranService : RestoranService, private _zaposleniService : ZaposleniService ){

    }

    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan =>{
            this.email = ulogovan.email;
            this._zaposleniService.getZaposleni().subscribe( zaposleni =>{
                //   this.restorani = restorani;
                this.zaposleni = zaposleni;
            });

        });

        console.log(this.email);
    }


}
