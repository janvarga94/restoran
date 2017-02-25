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
export class ZaposleniDetailService {
    private _zaposleniUrl = 'http://localhost:8080/resursi/get_zaposlen';

    constructor(private _http: Http, private _notificator: Notificator) {

    }

    getZaposlen(email: string): Observable<any> {
        return this._http.get(this._zaposleniUrl+"?radnikEmail="+email)
            .map((response: Response) => {
                var zaposleni = <any> response.json();
                // for(var i = 0; i < 10; i++)
                //     restorani.push(restorani[0]);
                return zaposleni;
            })
            .catch(this.handleError);
    }

    getSmena(idRestorana : number, year : number, month : number, day : number): Observable<any[]> {
        let smenaUrl = "http://localhost:8080/resursi/get_smene";
        return this._http.get(smenaUrl+"?idRestorana="+idRestorana+"&year="+year+"&month="+month+"&day="+day)
            .map((response: Response) => {
                var smene = <any[]> response.json();

                return smene;
            })
            .catch(this.handleError);
    }

    getZanimanje(email: string): Observable<number> {
        let smenaUrl = "http://localhost:8080/resursi/get_zanimanje";
        return this._http.get(smenaUrl+"?radnikEmail="+email)
            .map((response: Response) => {
                var smene = <number> response.json();

                return smene;
            })
            .catch(this.handleError);
    }

    getStolovi(idRestorana: number): Observable<any[]> {
        let smenaUrl = "http://localhost:8080/resursi/get_stolovi";
        return this._http.get(smenaUrl+"?idRestorana="+idRestorana)
            .map((response: Response) => {
                var stolovi = <any[]> response.json();

                return stolovi;
            })
            .catch(this.handleError);
    }

    getParam(){
        let params = new URLSearchParams(window.location.search);
        let someParam = params.get('re');
        return someParam;
    }



    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
