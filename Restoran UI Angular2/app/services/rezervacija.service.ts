import { Config } from './../app.config';
/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
/**
 * Created by Svetozar Stojkovic on 12/19/2016.
 */
import { Injectable } from '@angular/core';
import {Http, Response, URLSearchParams} from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IZaposleni } from '../models/zaposleni';
import { ISuccess} from '../models/ISuccess';

import { Notificator } from './notification.service';
import {getRelativePath} from "tslint/lib/configuration";
import {ActivatedRoute} from "@angular/router";

@Injectable()
export class RezervacijaService {
    private _stoloviUrl = Config.BackendUrl +  '/rezervacija/stolovi';
    private _rezervisiUrl = Config.BackendUrl +  '/rezervacija/rezervisi';
    private _jelaUrl = Config.BackendUrl + '/rezervacija/jela';
    private _picaUrl = Config.BackendUrl + '/rezervacija/pica';
    private _rezervaicjeUrl = Config.BackendUrl + '/rezervacija/rezervacije'

    constructor(private _http: Http, private _notificator: Notificator) {

    }

    getStolovi(restoran: string): Observable<any[]> {
        return this._http.get(this._stoloviUrl+"?restoran="+restoran)
            .map((response: Response) => {
                return response.json();   
            })
            .catch(this.handleError);
    }

    rezervisi(rezervisiReq : any): Observable<any[]> {
        return this._http.post(this._rezervisiUrl,rezervisiReq)
            .map((response: Response) => {
                return response.json();   
            })
            .catch(this.handleError);
    }

    getJela(restoran: any): Observable<any[]> {
        return this._http.get(this._jelaUrl+"?restoran="+restoran)
            .map((response: Response) => {
                return response.json();   
            })
            .catch(this.handleError);
    }

     getPica(restoran: any): Observable<any[]> {
        return this._http.get(this._picaUrl+"?restoran="+restoran)
            .map((response: Response) => {
                return response.json();   
            })
            .catch(this.handleError);
    }

    getRezervacije(email: any): Observable<any[]> {
        return this._http.get(this._rezervaicjeUrl+"?email="+encodeURIComponent(email))
            .map((response: Response) => {
                return response.json();   
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
