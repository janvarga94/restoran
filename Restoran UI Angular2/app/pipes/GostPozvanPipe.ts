import { Pipe, PipeTransform } from '@angular/core';
/*
 * Raise the value exponentially
 * Takes an exponent argument that defaults to 1.
 * Usage:
 *   value | exponentialStrength:exponent
 * Example:
 *   {{ 2 |  exponentialStrength:10}}
 *   formats to: 1024
*/
@Pipe({name: 'gostPozvan'})
export class GostPozvanPipe implements PipeTransform {
  transform(value: any[]): any[] {
        return value.filter(v => v.pozvan);
  }
}

@Pipe({name: 'gostNepozvan'})
export class GostNeozvanPipe implements PipeTransform {
  transform(value: any[]): any[] {
        return value.filter(v => !v.pozvan);
  }
}