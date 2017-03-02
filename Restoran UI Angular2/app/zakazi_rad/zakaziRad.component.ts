import {OnInit, Component} from "@angular/core";
import {ZakaziRadService} from "../services/zakaziRad.service";
import {Notificator} from "../services/notification.service";
import {ActivatedRoute} from "@angular/router";
import {ZaposleniDetailService} from "../services/zaposleniDetail.service";
import {RestoranService} from "../services/restorani.service";
import {IReon} from "../models/reon";
/**
 * Created by Svetozar Stojkovic on 3/2/2017.
 */

@Component({
    selector: 'zakazi_rad',
    templateUrl: 'app/zakazi_rad/zakaziRad.component.html',
    providers: [ZakaziRadService]
})

export class ZakaziRadComponent implements OnInit{

    email : string;
    zaposlen : any;

    datumPocetka : Date;
    brojDana : number;

    zanimanjeInt : number;

    smena : number;
    reon : number;

    reoni : IReon[];

    constructor(private _zakaziRad : ZakaziRadService, private _restoraniService : RestoranService, private _zaposleniDetailService : ZaposleniDetailService, private route: ActivatedRoute, private _notification : Notificator) {}

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.email = atob(params['email']);
            console.log(this.email);
            this._zaposleniDetailService.getZaposlen(this.email).subscribe(zaposlen => {
                this.zaposlen = zaposlen;
                this._zaposleniDetailService.getZanimanje(this.email).subscribe(zanimanje => {
                    this.zanimanjeInt = zanimanje; // 0 - konobar, 1 - kuvar, 2 - sanker
                    this._restoraniService.getReoniForRestoran(zaposlen[5]).subscribe(reoni => {
                        this.reoni = reoni;
                        console.log("Reoni:" + reoni);
                    });
                });
            });

        });


    }

    zakaziDane(){
        if (this.datumPocetka == null) {
            this._notification.notifyError("Ne valjda datum");
            return;
        } else if (this.smena == null) {
            this._notification.notifyError("Ne valja smena");
            return;
        } else if (this.reon == null && this.zanimanjeInt==0) {
            this._notification.notifyError("Ne valja reon");
            return;
        }
        console.log({email : this.zaposlen[0], idRestorana : this.zaposlen[5], datumPocetka : this.datumPocetka, brojDana : this.brojDana, smena : Number(this.smena), reon : this.reon});
        this._zakaziRad.zakaziDane({email : this.zaposlen[0], idRestorana : this.zaposlen[5], datumPocetka : this.datumPocetka, brojDana : this.brojDana, smena : Number(this.smena), reon : this.reon}).subscribe( response => {
            if (response == true){
                this._notification.notifySuccess("Dodavanje uspelo");
            } else {
                this._notification.notifyError("Dodavanje nije uspelo");
            }
        });
    }

}
