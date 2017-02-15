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
export class PrijateljstvoService {

    public emailUlogovanog : string = 'email0';

    private prijateljsvoUrl = Config.BackendUrl + '/prijateljstvo';

    private posaljiZahtevUrl = this.prijateljsvoUrl + '/posaljiZahtevZaPrijateljstvo';
    private prihvatiZahtevUrl = this.prijateljsvoUrl + '/prihvatiZahtevZaPrijateljstvo';
    private prekiniPrijateljstvoUrl = this.prijateljsvoUrl + '/prekiniPrijateljstvo';
  
    private _prijateljiUrl = this.prijateljsvoUrl + '/getPrijatelje';
    private _nepozvaniUPrijateljstvo = this.prijateljsvoUrl + '/getNepozvaneUPrijateljstvo';
    private _pozvaniUPrijateljstvo = this.prijateljsvoUrl + '/getPozvaneUPrijateljstvo';
    private _gostPozvanUPrijateljstvoOd = this.prijateljsvoUrl + '/gostPozvanUPrijateljstvoOd';

    constructor(private _http: Http, private _notificator: Notificator, private _router: Router) { }

     PosaljiZahtev(emailPrvog : String, emailDrugog : String) : Observable<any>{
         return this._http.get(this.posaljiZahtevUrl + "?emailPrvog=" + emailPrvog + "&emailDrugog=" + emailDrugog )
            .map((response: Response) =>{
                return <any> response.json()})
            .catch(this.handleError)
    }

    
     PrihvatiZahtev(emailPrvog : String, emailDrugog : String) : Observable<any>{
         return this._http.get(this.prihvatiZahtevUrl + "?emailPrvog=" + emailPrvog + "&emailDrugog=" + emailDrugog )
            .map((response: Response) =>{
                return <any> response.json()})
            .catch(this.handleError)
    }

    
     PrekiniZahtev(emailPrvog : String, emailDrugog : String) : Observable<any>{
        return this.PrekiniPrijateljstvo(emailPrvog,emailDrugog);
    }

    
     PrekiniPrijateljstvo(emailPrvog : String, emailDrugog : String) : Observable<any>{
         return this._http.get(this.prekiniPrijateljstvoUrl + "?emailPrvog=" + emailPrvog + "&emailDrugog=" + emailDrugog )
            .map((response: Response) =>{
                return <any> response.json()})
            .catch(this.handleError)
    }

     GetPrijateljeOf(email : String) : Observable<any[]>{
         return this._http.get(this._prijateljiUrl + "?email=" + email)
            .map((response: Response) =>{
                return <any[]> response.json()})
            .catch(this.handleError)
    }

   GetNepozvaneUPrijateljstvo(email : String) : Observable<any[]>{
         return this._http.get(this._nepozvaniUPrijateljstvo + "?email=" + email)
            .map((response: Response) =>{
                return <any[]> response.json()})
            .catch(this.handleError)
    }

    
   GetPozvaneUPrijateljstvo(email : String) : Observable<any[]>{
         return this._http.get(this._pozvaniUPrijateljstvo + "?email=" + email)
            .map((response: Response) =>{
                return <any[]> response.json()})
            .catch(this.handleError)
    }

    GetGostPozvanUPrijateljstvoOd(email : String): Observable<any[]>{
         return this._http.get(this._gostPozvanUPrijateljstvoOd + "?email=" + email)
            .map((response: Response) =>{
                return <any[]> response.json()})
            .catch(this.handleError)
    }


    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
