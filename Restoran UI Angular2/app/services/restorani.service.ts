import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IRestoran } from '../models/restoran';
import { ISuccess} from '../models/ISuccess';

import { Notificator } from './notification.service';

@Injectable()
export class RestoranService {
    private _restoraniUrl = 'http://localhost:8080/resursi/restorani';

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

    getRestoran(id: string): Observable<IRestoran> {
        return this.getRestorani()
            .map((restorani: IRestoran[]) => restorani.find(r => r.naziv === id))
            .catch(this.handleError);
    }


    addRestoran(restoran : IRestoran): Observable<ISuccess>{
          return this._http.get("api/successResponse.json")
            .map((response: Response) => {   return <ISuccess> response.json(); })
            .catch(this.handleError);        
    }



    private handleError(error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
