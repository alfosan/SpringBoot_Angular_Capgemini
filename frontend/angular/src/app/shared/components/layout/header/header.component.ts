import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  template: `
    <header>
      <h1>HarmoniCare Header</h1>
    </header>
  `,
  styles: [`
    header {
      background: #f8f9fa;
      padding: 1rem;
      text-align: center;
    }
  `]
})
export class HeaderComponent { }