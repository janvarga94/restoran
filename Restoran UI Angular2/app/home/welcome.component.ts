import { Component } from '@angular/core';
import {IRestoran} from "../models/restoran";
import {WelcomeService} from "../services/welcome.service";
import {RestoranService} from "../services/restorani.service";


@Component({
    templateUrl: 'app/home/welcome.component.html'
})
export class WelcomeComponent {
    public pageTitle: string = 'Welcome people';

    restorani : IRestoran[];


    constructor(private _welcomeService : WelcomeService) {

    }

    ngOnInit() : void{
        this._welcomeService.getRestoraniForUser("email0").subscribe( restorani =>{
            //   this.restorani = restorani;
            this.restorani = restorani;
        });
    }
}
