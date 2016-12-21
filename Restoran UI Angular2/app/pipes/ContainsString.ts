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
@Pipe({name: 'containsString'})
export class ContainsString implements PipeTransform {
  transform(value: string, contained: string): boolean {
    return value.indexOf(contained) > - 1;
  }
}