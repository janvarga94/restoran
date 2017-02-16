import { ToasterConfig } from 'angular2-toaster';
import { Component } from '@angular/core';


@Component({
    selector: 'pm-app',
    template: `
     <toaster-container [toasterconfig]="toasterconfig"></toaster-container>
        <app-header></app-header>      
        <div class="container">
            <router-outlet></router-outlet>
        </div>
    `
})
export class AppComponent { 
      public toasterconfig : ToasterConfig = 
        new ToasterConfig({            
            tapToDismiss: true, 
            timeout: 2000,
            mouseoverTimerStop: true,            
            positionClass : "toast-bottom-full-width",

        });
}
