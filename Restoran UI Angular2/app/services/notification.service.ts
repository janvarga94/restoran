import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {ToasterModule, ToasterService, ToasterConfig} from 'angular2-toaster';

import {INotificator} from './notifications';


@Injectable()
export class Notificator implements INotificator{
    private _restoraniUrl = 'api/restorani.json';

    constructor(private _toastr: ToasterService ) {
        let toasterconfig : ToasterConfig = 
            new ToasterConfig({
                showCloseButton: true, 
                tapToDismiss: false, 
                timeout: 7000
            });
        
        
     }

    notifySuccess(message: string) : void{
        this._toastr.pop('success', message);
    }
    notifyInfo( message: string) : void{
        this._toastr.pop('info', message);
    }
    notifyError( message: string) : void{
        this._toastr.pop('error', message);
    }
}
