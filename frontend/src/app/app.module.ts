import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { InventoryComponent } from './pages/inventory/inventory.component';
import { CommonModule } from "@angular/common";

import { AppComponent } from './app.component';
import { PostulationsComponent } from './components/postulations/postulations.component';
import { PostulationService } from './services/postulation.service';

@NgModule({
  declarations: [AppComponent, PostulationsComponent, InventoryComponent],
  imports: [BrowserModule,
            FormsModule,
            ReactiveFormsModule,
            HttpClientModule,
            CommonModule],
  providers: [PostulationService], // Añade el servicio aquí.
  bootstrap: [AppComponent],
})
export class AppModule {}
