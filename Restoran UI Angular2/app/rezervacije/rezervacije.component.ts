import { BehaviorSubject } from 'rxjs/Rx';
import { Notificator } from './../services/notification.service';
import { LoginService } from './../services/login.service';
import { RezervacijaService } from './../services/rezervacija.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import {RestoranService} from '../services/restorani.service';
import {IRestoran} from '../models/restoran';
import { Observable } from 'rxjs/Observable';


@Component({
    selector: 'restorani',
    templateUrl: 'app/rezervacije/rezervacije.component.html'
})
export class RezervacijeComponent implements OnInit{
    
    private _search1 : String = "";
    private _search2 : String = "";

    odabranaRezervacija : any;
    odabranRestoran : any;

    rezervacije : any[] = [];

    private _jela : any[] = [];
            jela: any[] = [];

    private _pica : any[] = [];
            pica: any[] = [];

    porucenaJela: any[] = [];
    porucenaPica: any[] = [];

    randomClasses : any[] = [];

    ulogovan : any;

    ukupnaCena : number = 0;
   
    private gostSaKojimRadimoSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
    gostSaKojimRadimo : Observable<any> = this.gostSaKojimRadimoSubject.asObservable();

    constructor(private route: ActivatedRoute,private _notificator : Notificator,private _loginService : LoginService,private _restoranService : RestoranService, private _rezervacijaService : RezervacijaService) {
        for(var i = 0; i < 20; i++){
            this.randomClasses.push(this.generateRandomClassButton());
        }

        
      
    }

    ngOnInit() : void{

        this.route.params.subscribe(params => {
            var gost = params['gost'];
            if(gost != undefined && gost != null){
                console.log("Gost : "+atob(gost));
                this.gostSaKojimRadimoSubject.next({email : atob(gost)});
            }else{
                this._loginService.ulogovan.subscribe(ulogovan => {
                    this.gostSaKojimRadimoSubject.next(ulogovan);
                });
            }
        });



        this.gostSaKojimRadimo.subscribe(ulogovan => {
            if(ulogovan != null){
                this._rezervacijaService.getRezervacije(ulogovan.email).subscribe(rezervacije => {
                    this.rezervacije = rezervacije;
                })
            }
        });  
            
    }

    get search1(){
        return this._search1;
    }

    set search1(e){
        this._search1 = e;
        this.jela = this._jela.filter(j => j.nazivJela.toLowerCase().indexOf(e) > -1);
    }
      get search2(){
        return this._search2;
    }

    set search2(e){
        this._search2 = e;
        this.pica = this._pica.filter(j => j.nazivPica.toLowerCase().indexOf(e.toLowerCase()) > -1);
    }

    ucitajJelaIPicaZaRestoran(restoranId : any){
        this.odabranRestoran = restoranId;
        this._rezervacijaService.getJela(restoranId).subscribe(jela => {
            this._jela = jela;
            this.search1 = "";
        })
        this._rezervacijaService.getPica(restoranId).subscribe(pica => {
            this._pica = pica;
            this.search2 = "";
        })

         this.gostSaKojimRadimo.subscribe(ulogovan => {
            if(ulogovan){
                this.ukupnaCena = 0;
                this._rezervacijaService.porucenaJela(this.odabranaRezervacija['idRezervacije'],encodeURIComponent(ulogovan.email)).subscribe(porucena => {
                    this.porucenaJela = porucena;
                    console.log(porucena)
                    this.izracunajJela();

                });         
                  this._rezervacijaService.porucenaPica(this.odabranaRezervacija['idRezervacije'],encodeURIComponent(ulogovan.email)).subscribe(porucena => {
                    this.porucenaPica = porucena;
                    this.izracunajPica();
                });


            }
        });
    }

    izracunajCenu() {
        this.ukupnaCena = 0;
        this.izracunajJela();
        this.izracunajPica();
    }

    izracunajJela(){
        for (let por of this.porucenaJela) {
            this.ukupnaCena += por.cena;
        }
    }

    izracunajPica(){
        for (let por of this.porucenaPica) {
            this.ukupnaCena += por.cena;
        }
    }

    plati() {
        this.gostSaKojimRadimo.subscribe(ulogovan => {
            if(ulogovan != null){
                this._rezervacijaService.plati(this.odabranaRezervacija['idRezervacije'],encodeURIComponent(ulogovan.email), this.ukupnaCena).subscribe(porucena => {


                });
            }
        });

    }

    randomClassButton(index : any){
        return this.randomClasses[index];
    }

    generateRandomClassButton(){
        switch(this.getRandomInt(0,6)){
            case 0 : return "btn-default";
            case 1 : return "btn-primary";
            case 2 : return "btn-success";
            case 3 : return "btn-info";
            case 4 : return "btn-warning";
            case 5 : return "btn-danger";
            case 6 : return "btn-link";
            default: return "btn-default";
        }
    }

    getRandomInt(min : any, max : any): any {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min)) + min;
    }

    getDate(date : any){
        return (new Date(date)).toLocaleString().slice(0, 16);
    }

    toFixed(num : any){
        return num.toFixed(1);
    }

    dodajUPorucenaJela(nazivJela : any){
        this.porucenaJela.push(nazivJela);
    }

    dodajUPorucenaPica(nazivPica : any){
        this.porucenaPica.push(nazivPica);
    }

    poruciIzmeni(){
        this.gostSaKojimRadimo.subscribe(ulogovan => {
            if(ulogovan){
                var request = { 
                    email: ulogovan.email,
                    rezervacijaId: this.odabranaRezervacija['idRezervacije'],
                    spremnoKadaSeDodje : false,
                    naziviJela : this.porucenaJela.map(j => j.nazivJela),
                    restoranId : this.odabranRestoran
                };


                this._rezervacijaService.poruciJela(request).subscribe(resp => {
                    if(resp['Success'] == true){
                        this._notificator.notifySuccess("Uspesna porudzbina jela!");
                    }else{
                        this._notificator.notifyError(resp['Message']);
                    }
                });

                var request2 = { 
                    email: ulogovan.email,
                    rezervacijaId: this.odabranaRezervacija['idRezervacije'],
                    spremnoKadaSeDodje : false,
                    naziviPica : this.porucenaPica.map(j => j.nazivPica),
                    restoranId : this.odabranRestoran
                };


                this._rezervacijaService.poruciPica(request2).subscribe(resp => {
                    if(resp['Success'] == true){
                        this._notificator.notifySuccess("Uspesna porudzbina pica!");
                    }else{
                        this._notificator.notifyError(resp['Message']);
                    }
                });

                this.izracunajCenu();
            }
        });
    }

    izbaciJelo(indx : any){
        this.porucenaJela.splice(indx,1);
    }
    izbaciPice(indx : any){
        this.porucenaPica.splice(indx,1);
    }
 }
