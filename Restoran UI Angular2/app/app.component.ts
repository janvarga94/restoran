import { Component } from '@angular/core';


@Component({
    selector: 'pm-app',
    template: `
        <app-header></app-header>      
        <div class="container">
            <router-outlet></router-outlet>
        </div>
    `
})
export class AppComponent { }
