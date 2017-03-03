import {Component, OnInit} from "@angular/core";
import {Notificator} from "../services/notification.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../services/login.service";
import {RestoranService} from "../services/restorani.service";
import {ZaposleniDetailService} from "../services/zaposleniDetail.service";
import {ZaposleniService} from "../services/zaposleni.service";
import {Idponude} from "../models/dponude";
/**
 * Created by Stefan on 3/2/2017.
 */

@Component({

    selector : 'primljeneponude',
    templateUrl: 'app/primljenePonude/primljenePonude.component.html',

})

export class PrimljenePonudeComponent implements OnInit{

    email : string;
    ponude : Idponude[];

    ngOnInit(): void {
        this._loginService.ulogovan.subscribe(ulogovan =>{
            if (ulogovan) {
                this.email = ulogovan.email;
                this._restoranService.getDobivenePonude(this.email).subscribe(ponude =>{
                    this.ponude = ponude;
                });
            }

        });


    }

    constructor(private _notificator : Notificator, private route: ActivatedRoute, private _loginService : LoginService, private _router: Router,private _restoranService : RestoranService, private _zaposleniService : ZaposleniService, private _zaposleniDetailService : ZaposleniDetailService ){

    }

    privatiPonudu(idPonuda : number){
        this._restoranService.prihvacena(idPonuda).subscribe();
    }

}