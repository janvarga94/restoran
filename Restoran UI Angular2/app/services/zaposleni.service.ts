/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import {Config} from "../app.config";

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IZaposleni } from '../models/zaposleni';
import { ISuccess} from '../models/ISuccess';

import { Notificator } from './notification.service';

@Injectable()
export class ZaposleniService {
    private _zaposleniUrl = Config.BackendUrl+'/resursi/zaposleni';

    constructor(private _http: Http, private _notificator: Notificator) { }

    getZaposleni(): Observable<IZaposleni[]> {
        return this._http.get(this._zaposleniUrl)
            .map((response: Response) => {
                var zaposleni = <IZaposleni[]> response.json();
                // for(var i = 0; i < 10; i++)
                //     restorani.push(restorani[0]);
                return zaposleni;
            })
            .catch(this.handleError);
    }

    getZaposlen(radnikEmail: string): Observable<IZaposleni> {
        return this.getZaposleni()
            .map((zaposleni: IZaposleni[]) => zaposleni.find(z => z.radnikEmail === radnikEmail))
            .catch(this.handleError);
    }

    getZaposlenAllSpecs(email : string) : Observable<any> {
        let zaposlen = Config.BackendUrl+'/resursi/get_zaposlen?radnikEmail='+email;
        return this._http.get(zaposlen)
            .map((response: Response) => {
                return <any> response.json();
            })
            .catch(this.handleError);
    }


    addZaposlen(zaposlen : any )  {
        let _registerUrl = Config.BackendUrl + '/menadzerRestorana/addZaposlenog';
        return this._http.post(_registerUrl,zaposlen).map((response: Response) => {
            return <any> response.json();
        }).catch(this.handleError);
    }

    updateZaposlen(zaposlen : any )  {
        let _registerUrl = Config.BackendUrl + '/menadzerRestorana/updateZaposlenog';
        return this._http.post(_registerUrl,zaposlen).map((response: Response) => {
            return <any> response.json();
        }).catch(this.handleError);
    }

    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
