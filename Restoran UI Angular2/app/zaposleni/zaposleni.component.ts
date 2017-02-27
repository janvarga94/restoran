import {Component, OnInit} from "@angular/core";
import {IZaposleni} from "../models/zaposleni";
import {ZaposleniService} from "../services/zaposleni.service";
import {Notificator} from "../services/notification.service";
/**
 * Created by Svetozar Stojkovic on 12/18/2016.
 */

@Component({
    selector: 'zaposleni',
    templateUrl: 'app/zaposleni/zaposleni.component.html',
    providers: [ZaposleniService]
})

export class ZaposleniComponent implements OnInit{

    pageTitle : string = "Zaposleni";

    zaposleni : IZaposleni[];

    constructor(private _notificator: Notificator, private _zaposleniService : ZaposleniService) {

    }

    ngOnInit(): void {
        this._zaposleniService.getZaposleni().subscribe( zaposleni =>{
            //   this.restorani = restorani;
            this.zaposleni = zaposleni;
        });
    }

    obrisiZaposlenog(zaposleni : IZaposleni){
        var index = this.zaposleni.indexOf(zaposleni);
        if(index > -1){
            this.zaposleni.splice(index,1);
            this._notificator.notifySuccess("Zaposleni obrisan");
        }
    }

    detaljiZaposlenog(zaposleni : IZaposleni){
        console.log(zaposleni.radnikEmail);
    }

    getBase(url : string) {
        return btoa(url);
    }
}
