import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IRestoran } from '../models/restoran';
import { ISuccess} from '../models/ISuccess';

import { Notificator } from './notification.service';
import {Config} from "../app.config";

@Injectable()
export class RestoranService {
    private _restoraniUrl = 'http://localhost:8080/resursi/restorani';
    private dodaj = Config.BackendUrl + '/resursi/add';
    private _managerRestoranaUrl = Config.BackendUrl + '/menadzerRestorana/getRestoranID';
    private _addPonudjac = Config.BackendUrl + '/menadzerRestorana/addPonudjac'

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



    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
