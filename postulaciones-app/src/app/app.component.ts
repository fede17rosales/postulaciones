import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PostulationsComponent } from './components/postulations/postulations.component';

@Component({
  selector: 'app-root',
  imports: [PostulationsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'postulaciones-app';
}
