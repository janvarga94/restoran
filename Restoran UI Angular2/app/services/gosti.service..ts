import { IUlogovan } from './../models/ulogovan';

import { Injectable, OnInit } from '@angular/core';
import { Http, Response, URLSearchParams } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';

import { Observable, BehaviorSubject } from 'rxjs/Rx';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IKorisnik } from '../models/korisnik';
import { Uloga } from '../models/uloga';
import { LoginResponse } from '../models/loginResponse';

import {Notificator} from './notification.service';

import { Config } from '../app.config';

@Injectable()
export class GostiService {

    public emailUlogovanog : string = 'email0';

    private ulogovanSubject: BehaviorSubject<any> = new BehaviorSubject<IUlogovan>(null);
    private _gostiUrl = Config.BackendUrl + '/gosti/getAll';
    private _modifyGostaUrl = Config.BackendUrl + '/gosti/modifyGosta';

    constructor(private _http: Http, private _notificator: Notificator, private _router: Router) { }

    GetAll() : Observable<any[]> {
       return this._http.get(this._gostiUrl)
            .map((response: Response) =>{
                return <any[]> response.json()})
            .catch(this.handleError)
    }


    ModifyGosta(ime : String, prezime : String, email : String) : Observable<any>{
         return this._http.post(this._modifyGostaUrl, {ime : ime, prezime : prezime, email : email})
            .map((response: Response) =>{
                return <any> response.json()})
            .catch(this.handleError)
    }

    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
