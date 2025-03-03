import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  template: `
    <footer>
      <p>HarmoniCare Footer</p>
    </footer>
  `,
  styles: [`
    footer {
      background: #f8f9fa;
      padding: 1rem;
      text-align: center;
    }
  `]
})
export class FooterComponent { }