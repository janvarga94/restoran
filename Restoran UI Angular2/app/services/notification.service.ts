import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {ToastsManager} from 'ng2-toastr/ng2-toastr';

import {INotificator} from './notifications';


@Injectable()
export class Notificator implements INotificator{
    private _restoraniUrl = 'api/restorani.json';

    constructor(private _toastr: ToastsManager ) { }

    notifySuccess(message: string) : void{
        this._toastr.success(message);
    }
    notifyInfo( message: string) : void{
        this._toastr.info(message);
    }
    notifyError( message: string) : void{
        this._toastr.error(message);
    }
}
