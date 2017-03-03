import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Notificator} from "./notification.service";
import {Http, Response} from "@angular/http";
import {Config} from "../app.config";
/**
 * Created by Svetozar Stojkovic on 3/2/2017.
 */

@Injectable()
export class ZakaziRadService {

    constructor(private _http: Http, private _notificator: Notificator) { }

    zakaziDane(postObj : any) : Observable<any>{
        let zakazi_rad = Config.BackendUrl+'/resursi/zakazi_dane';
        return this._http.post(zakazi_rad,postObj).map((response: Response) => {
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
