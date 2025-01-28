import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Comment {
  comentario: string;
}

export interface CommentResponse {
  empresa: string;
  valoracion: number;
  opiniones_glassdoor: string[];
  opinion_personal: string;
}

export interface Postulation {
  titulo: string
  empresa: string
  descripcion: string
  responsabilidades: string[]
  salario: number
  beneficios: string
  tiene_ingles: boolean
  fecha_publicacion: string
  reclutador: string
  antiguedad: number
  tipo: string
  cantidad_empleados: number
  opiniones: string[]
  validacion: number
  sueldo_opinion: number
}

export interface PostulationResponse {
  id: number;
  empresa: string;
  titulo: string;
  descripcion: string;
  responsabilidades: string[];
  reclutador: string;
  salario: number;
  fecha_de_alta: string;
}

@Injectable({
providedIn: 'root', // Asegura que Angular lo registre en el inyector raíz.
})

export class PostulationService {
  private postulationUrl = 'http://localhost:8080/postulation';
  private commentUrl = 'http://localhost:8080/comment';

  constructor(private http: HttpClient) {}

  getPostulations(): Observable<PostulationResponse[]> {
    return this.http.get<PostulationResponse[]>(this.postulationUrl);
  }

  savePostulation(postulation: Postulation): Observable<void> {
    return this.http.post<void>(this.postulationUrl, postulation);
  }

  deletePostulation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.postulationUrl}/${id}`);
  }

// Obtener la lista de comentarios
  listComments(): Observable<CommentResponse[]> {
    return this.http.get<CommentResponse[]>(`${this.commentUrl}`);
  }

// Guardar un comentario
  saveComment(id: number, comment: Comment): Observable<void> {
      const url = `${this.commentUrl}/${id}`;
      return this.http.post<void>(url, comment);
  }
}
