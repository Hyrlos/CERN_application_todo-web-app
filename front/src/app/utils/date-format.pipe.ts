import {Pipe, PipeTransform} from '@angular/core';
import {DatePipe} from '@angular/common';

@Pipe({
    name: 'dateFormat',
})
export class DateFormatPipe implements PipeTransform {
    transform(date: number): string {
        const parsedDate = new Date(date);
        const datePipe = new DatePipe('en-US');
        return <string>datePipe.transform(parsedDate, 'MMM d, y h:mm a');
    }
}
