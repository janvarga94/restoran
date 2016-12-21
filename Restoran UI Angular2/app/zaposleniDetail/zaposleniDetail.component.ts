import {Component, OnInit} from "@angular/core";
import {selector} from "rxjs/operator/publish";
import {ZaposleniDetailService} from "../services/zaposleniDetail.service";
import {ZaposleniService} from "../services/zaposleni.service";
import {Notificator} from "../services/notification.service";
import {IZaposleni} from "../models/zaposleni";
import {ActivatedRoute} from "@angular/router";
/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */

@Component({
    selector: 'zaposleni/:email',
    templateUrl: 'app/zaposleniDetail/zaposleniDetail.component.html',
    providers: [ZaposleniDetailService]
})

export class ZaposleniDetailComponent implements OnInit{

    pageTitle : string = "Zaposleni";

    zaposlen : IZaposleni;
    email : string;

    constructor(private _notificator: Notificator, private _zaposleniDetailService : ZaposleniDetailService, private route: ActivatedRoute) {

    }


    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.email = params['email'];
        })

        console.log(this.email);

        this._zaposleniDetailService.getZaposlen(this.email).subscribe( zaposleni =>{
            //   this.restorani = restorani;
            this.zaposlen = zaposleni;
            console.log(this.zaposlen.radnikEmail)
        });

    }

}
