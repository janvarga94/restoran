
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IZaposleni } from '../models/zaposleni';
import { ISuccess} from '../models/ISuccess';

import { Notificator } from './notification.service';

@Injectable()
export class ZaposleniService {
    private _zaposleniUrl = 'http://localhost:8080/resursi/zaposleni';

    constructor(private _http: Http, private _notificator: Notificator) { }

    getGost(): Observable<any> {
        // return this._http.get(this._zaposleniUrl)
        //     .map((response: Response) => {
        //         var zaposleni = <any> response.json();
        //         // for(var i = 0; i < 10; i++)
        //         //     restorani.push(restorani[0]);
        //         return zaposleni;
        //     })
        //     .catch(this.handleError);

    }


    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
