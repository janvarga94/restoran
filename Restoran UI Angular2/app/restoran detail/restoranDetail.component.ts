import {Component, OnInit} from "@angular/core";
import {LoginService} from "../services/login.service";
import {Router} from "@angular/router";
import {RestoranService} from "../services/restorani.service";

@Component({
    selector: 'restoran-detail',
    templateUrl: 'app/restoran detail/restoranDetail.component.html'
})
export class RestoranDetailComponent  implements OnInit {

    restoran : any;
    email : string;

    constructor(private _loginService : LoginService, private _router: Router,private _restoranService : RestoranService ){

    }

    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan =>{
            this.email = ulogovan.email;


        });

        console.log(this.email);
    }


}
