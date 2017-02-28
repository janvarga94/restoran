/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IZaposleni } from '../models/zaposleni';
import { ISuccess} from '../models/ISuccess';

import { Notificator } from './notification.service';
import {Config} from "../app.config";

@Injectable()
export class ZaposleniService {
    private _zaposleniUrl = 'http://localhost:8080/resursi/zaposleni';
    private _registerUrl = Config.BackendUrl + '/menadzerRestorana/addZaposlenog';
    private _managerRestoranaURL = Config.BackendUrl + '/menadzerRestorana/addmenadzeraRestorana';

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


    addZaposleni(zaposleni : IZaposleni): Observable<ISuccess>{
        return this._http.get("api/successResponse.json")
            .map((response: Response) => {   return <ISuccess> response.json(); })
            .catch(this.handleError);
    }

    addZaposlen(zaposlen : any )  {
        return this._http.post(this._registerUrl,zaposlen).map((response: Response) => {
            return <any> response.json();
        }).catch(this.handleError);
    }

    addMenadzera(menadzer : any){
        return this._http.post(this._managerRestoranaURL,menadzer).map((response: Response) => {
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
