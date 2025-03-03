import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  template: `
    <div>
      <h2>Welcome to HarmoniCare</h2>
    </div>
  `,
  styles: [`
    div {
      padding: 2rem;
      text-align: center;
    }
  `]
})
export class HomeComponent { }