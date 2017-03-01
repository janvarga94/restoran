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
import {Config} from "../app.config";

@Injectable()
export class ZaposleniDetailService {

    constructor(private _http: Http, private _notificator: Notificator) {

    }

    getZaposlen(email: string): Observable<any> {
        let zaposleniUrl = Config.BackendUrl+"/resursi/get_zaposlen";
        console.log(zaposleniUrl);
        return this._http.get(zaposleniUrl+"?radnikEmail="+encodeURIComponent(email))
            .map((response: Response) => {
                var zaposleni = <any> response.json();
                // for(var i = 0; i < 10; i++)
                //     restorani.push(restorani[0]);
                return zaposleni;
            })
            .catch(this.handleError);
    }

    getSmene(idRestorana : number, year : number, month : number, day : number): Observable<any[]> {
        let smenaUrl = Config.BackendUrl+"/resursi/get_smene";
        console.log(smenaUrl);
        return this._http.get(smenaUrl+"?idRestorana="+idRestorana+"&year="+year+"&month="+month+"&day="+day)
            .map((response: Response) => {
                var smene = <any[]> response.json();

                return smene;
            })
            .catch(this.handleError);
    }

    getZanimanje(email: string): Observable<number> {
        let smenaUrl = Config.BackendUrl+"/resursi/get_zanimanje";
        console.log(smenaUrl);
        return this._http.get(smenaUrl+"?radnikEmail="+email)
            .map((response: Response) => {
                var smene = <number> response.json();

                return smene;
            })
            .catch(this.handleError);
    }

    getStolovi(idRestorana: number): Observable<any[]> {
        let smenaUrl = Config.BackendUrl+"/resursi/get_stolovi";
        console.log(smenaUrl);
        return this._http.get(smenaUrl+"?idRestorana="+idRestorana)
            .map((response: Response) => {
                var stolovi = <any[]> response.json();

                return stolovi;
            })
            .catch(this.handleError);
    }

    getReon(idSmene : number, idRestorana : number, mail : string): Observable<any> {
        let url = Config.BackendUrl+"/resursi/get_reon";
        console.log(url);
        return this._http.get(url+"?idSmene="+idSmene+"&idRestorana="+idRestorana+"&konobarMail="+mail)
            .map((response: Response) => {
                var value = <any> response.json();

                return value;
            })
            .catch(this.handleError);
    }

    getJela(idRestorana : number, kuvarEmail : string) : Observable<any> {
        let url = Config.BackendUrl+"/resursi/jela_za_kuvara";
        console.log(url);
        return this._http.get(url+"?kuvarMail="+encodeURIComponent(kuvarEmail)+"&idRestorana="+idRestorana)
            .map((response: Response) => {
                var value = <any> response.json();

                return value;
            })
            .catch(this.handleError);
    }

    skuvanoJelo(idPorudzbine : number) : Observable<any> {
        let url = Config.BackendUrl+"/resursi/skuvano_jelo";
        console.log(url);
        return this._http.get(url+"?idPorudzbine="+idPorudzbine)
            .map((response: Response) => {
                var value = <any> response.json();

                return value;
            })
            .catch(this.handleError);
    }

    prihvacenoJelo(idPorudzbine : number) : Observable<any> {
        let url = Config.BackendUrl+"/resursi/prihvaceno_jelo";
        console.log(url);
        return this._http.get(url+"?idPorudzbine="+idPorudzbine)
            .map((response: Response) => {
                var value = <any> response.json();

                return value;
            })
            .catch(this.handleError);
    }


    getPica(idRestorana : number, sankerEmail : string) : Observable<any> {
        let url = Config.BackendUrl+"/resursi/pica_za_sankera";
        console.log(url);
        return this._http.get(url+"?sankerEmail="+encodeURIComponent(sankerEmail)+"&idRestorana="+idRestorana)
            .map((response: Response) => {
                var value = <any> response.json();

                return value;
            })
            .catch(this.handleError);
    }


    napravljenoPice(idPorudzbine : number) : Observable<any> {
        let url = Config.BackendUrl+"/resursi/spremljeno_pice";
        console.log(url);
        return this._http.get(url+"?idPorudzbine="+idPorudzbine)
            .map((response: Response) => {
                var value = <any> response.json();

                return value;
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
