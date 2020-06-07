import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'cellPhone'
})
export class CellPhone implements PipeTransform {

    transform(value: any, args?: any): any {
        return '(' + value.substr(0, 2) + ') ' + value.substr(2, 5) + '-' + value.substr(5, 4);
    }
}
