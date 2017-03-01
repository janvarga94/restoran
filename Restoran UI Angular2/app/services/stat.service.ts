import { Config } from './../app.config';
import {Injectable} from "@angular/core";
import {Notificator} from "./notification.service";
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {IRestoran} from "../models/restoran";
import {IKorisnik} from "../models/korisnik";
/**
 * Created by Svetozar Stojkovic on 12/20/2016.
 */

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {IOcenaRestorana} from "../models/ocenaRestorana";


@Injectable()
export class StatService {

    private statsUrl = Config.BackendUrl + '/stat/allStats';

    constructor(private _http: Http, private _notificator: Notificator) { }

    getAllStats(idRestorana : any): Observable<any> {
        return this._http.get(this.statsUrl+"?id="+idRestorana)
            .map((response: Response) => {
                var restoraniOcena = <any> response.json();

                return restoraniOcena;
            })
            .catch(this.handleError);
    }

  private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }

}