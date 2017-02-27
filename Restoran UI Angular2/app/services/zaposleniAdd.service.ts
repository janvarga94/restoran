/**
 * Created by Stefan on 2/25/2017.
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
import { ActivatedRoute, Router } from '@angular/router';

export class ZaposleniAddService{

    constructor(private _http: Http, private _notificator: Notificator, private _router: Router) { }

    addZaposlen(email: string,){

    }



}


