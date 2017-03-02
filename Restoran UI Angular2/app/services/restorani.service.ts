import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Config } from './../app.config';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IRestoran } from '../models/restoran';
import { ISuccess} from '../models/ISuccess';

import { Notificator } from './notification.service';

import {IJelo} from "../models/jelo";
import {INamirnica} from "../models/namirnica";
import {IReon} from "../models/reon";
import {INamirnicaP} from "../models/namirnicaP";

@Injectable()
export class RestoranService {
    private _restoraniUrl = 'http://localhost:8080/resursi/restorani';
    private _restoraniSviUrl = Config.BackendUrl + '/restorani/getAll';
    private dodaj = Config.BackendUrl + '/resursi/add';
    private _managerRestoranaUrl = Config.BackendUrl + '/menadzerRestorana/getRestoranID';
    private _addPonudjac = Config.BackendUrl + '/menadzerRestorana/addPonudjac';
    private _addReon = Config.BackendUrl + '/menadzerRestorana/addReon';
    private _addStol = Config.BackendUrl + '/menadzerRestorana/addStol';
    private _addNamirnica = Config.BackendUrl + '/menadzerRestorana/addNamirnica';
    private _addJelo = Config.BackendUrl  + '/menadzerRestorana/addJelo';
    private _addPonuda = Config.BackendUrl + '/menadzerRestorana/addPonuda';
    private _getJelovnik = Config.BackendUrl + '/menadzerRestorana/getJelovnik';
    private _getOcenaRestorana = Config.BackendUrl + '/menadzerRestorana/getOcenaRestorana';
    private _getOcenaJela = Config.BackendUrl + '/menadzerRestorana/getOcenaJela';
    private _getReoni = Config.BackendUrl + '/menadzerRestorana/getReoni';
    private _getNamirnice = Config.BackendUrl + '/menadzerRestorana/getNamirnice';
    private _getNamirniceUPotraznji = Config.BackendUrl + '/menadzerRestorana/getNamirniceUPotraznji';

    constructor(private _http: Http, private _notificator: Notificator) { }

    getRestorani(): Observable<IRestoran[]> {
        return this._http.get(this._restoraniUrl)
            .map((response: Response) => {
                var restorani = <IRestoran[]> response.json();
                // for(var i = 0; i < 10; i++)
                //     restorani.push(restorani[0]);
                console.log(restorani.length);
                return restorani;
            })
            .catch(this.handleError);
    }

    /* getRestoran(id: string): Observable<IRestoran> {
     return this.getRestorani()email
     .map((restorani: IRestoran[]) => restorani.find(r => r.naziv === id))
     .catch(this.handleError);
     } */

    getManagerRestoranID(email: string) : any{
        return this._http.get(this._managerRestoranaUrl+"?email="+email)
            .map((response: Response) => {
                var id = <any> response.json();

                return id;
            })
            .catch(this.handleError);

    }



    getRestoran() : any{

    }


    /* addRestoran(restoran : IRestoran): Observable<ISuccess>{
     return this._http.get("api/successResponse.json")
     .map((response: Response) => {   return <ISuccess> response.json(); })
     .catch(this.handleError);
     } */

    /* addRestoran(restoran : any) {

     } */

    addRestoran(restoran : any )  {
        return this._http.post(this.dodaj,restoran).map((response: Response) => {
            return <any> response.json();
        }).catch(this.handleError);
    }

    public addPonudjac(ponudjac : any) {
        return this._http.post(this._addPonudjac,ponudjac).map((response: Response) => {
            return <any> response.json();
        }).catch(this.handleError);
    }

    public addReon(reon : any){
        return this._http.post(this._addReon,reon).map((response: Response) => {
            return <any> response.json();
        }).catch(this.handleError);
    }

    public addSto(sto : any){
        return this._http.post(this._addStol,sto).map((response: Response) => {
            return <any> response.json();
        }).catch(this.handleError);
    }

    public addNamirnica(namirnica : any){
        return this._http.post(this._addNamirnica,namirnica).map((response: Response) => {
            return <any> response.json();
        }).catch(this.handleError);
    }

    public addJelo(jelo : any) {
        return this._http.post(this._addJelo,jelo).map((response: Response) => {
            return <any> response.json();
        }).catch(this.handleError);
    }

    public addPonuda(ponuda : any) {
        return this._http.post(this._addPonuda,ponuda).map((response: Response) => {
            return <any> response.json();
        }).catch(this.handleError);
    }

    public getJelovnik(email: string) : Observable<IJelo[]> {
        return this._http.get(this._getJelovnik + "?email="+ encodeURIComponent(email))
            .map((response: Response) => {
                var jelovnik = <any> response.json();
                // for(var i = 0; i < 10; i++)
                //     restorani.push(restorani[0]);
                console.log(jelovnik.length);
                return jelovnik;
            })
            .catch(this.handleError);
    }

    getLongLat(adresa : string): Observable<any>{

        return this._http.get("https://maps.googleapis.com/maps/api/geocode/json?address="+encodeURIComponent(adresa)+"&key=AIzaSyAB6DgNAa-m2IHEzyFRUdV2bPTeIy0mjuc")
            .map((response: Response) => {   return <any> response.json(); })
            .catch(this.handleError);
    }


    public getReoni(email: string) : Observable<IReon[]> {
        return this._http.get(this._getReoni + "?email="+ encodeURIComponent(email))
            .map((response: Response) => {
                var reoni = <IReon[]> response.json();
                // for(var i = 0; i < 10; i++)
                //     restorani.push(restorani[0]);
                //console.log(jelovnik.length);
                return reoni;
            })
            .catch(this.handleError);
    }

    getNamirnice(): Observable<INamirnica[]>{
        return this._http.get(this._getNamirnice)
            .map((response: Response) => {
                return <INamirnica[]> response.json();
            })
            .catch(this.handleError);
    }

    public getOcenaRestorana(email: string)  {
        return this._http.get(this._getOcenaRestorana + "?email="+ encodeURIComponent(email))
            .map((response: Response) => {
                var ocena = <any> response.json();
                // for(var i = 0; i < 10; i++)
                //     restorani.push(restorani[0]);
           //     console.log(jelovnik.length);
                return ocena;
            })
            .catch(this.handleError);
    }

    public getOcenaJela(email: string,jelo : string) {
        return this._http.get(this._getOcenaJela + "?email="+ encodeURIComponent(email)+"&jelo="+encodeURIComponent(jelo))
            .map((response: Response) => {
                var ocena = <number> response.json();
                // for(var i = 0; i < 10; i++)
                //     restorani.push(restorani[0]);
                //     console.log(jelovnik.length);
                return ocena;
            })
            .catch(this.handleError);
    }


    getSviRestorani(): Observable<IRestoran[]> {
        return this._http.get(this._restoraniSviUrl)
            .map((response: Response) => {
                return <any> response.json();
            })
            .catch(this.handleError);
    }

    getNamirniceUPotraznji() : Observable<INamirnicaP[]> {
        return this._http.get(this._getNamirniceUPotraznji)
            .map((response: Response) => {
                return <INamirnicaP[]> response.json();
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
