/**
 * Created by Stefan on 3/1/2017.
 */
import {Component, OnInit} from "@angular/core";
import {Notificator} from "../services/notification.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../services/login.service";
import {RestoranService} from "../services/restorani.service";
import {ZaposleniService} from "../services/zaposleni.service";
import {ZaposleniDetailService} from "../services/zaposleniDetail.service";

@Component({

    selector : 'mmrp',
    templateUrl: 'app/mmrp/mmrp.component.html',

})

export class MMRPComponent implements OnInit {

    ngOnInit(): void {

    }

    constructor(private _notificator : Notificator, private route: ActivatedRoute, private _loginService : LoginService, private _router: Router,private _restoranService : RestoranService, private _zaposleniService : ZaposleniService, private _zaposleniDetailService : ZaposleniDetailService ){

    }

}