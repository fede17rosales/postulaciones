import { Component, OnInit } from "@angular/core";
import { NgFor, CommonModule } from "@angular/common";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from '@angular/common/http';

import { PostulationService, Postulation, PostulationResponse, CommentResponse, Comment } from "../../services/postulation.service";

@Component({
  selector: "app-postulations",
  imports: [HttpClientModule, NgFor, ReactiveFormsModule, CommonModule],
  templateUrl: "./postulations.component.html",
  styleUrls: ["./postulations.component.css"],
})
export class PostulationsComponent implements OnInit {
  postulations: PostulationResponse[] = [];
  comments: CommentResponse[] = [];
  showPostulation: boolean = true;
  showComment: boolean = false;
  showOpinion: boolean = false;
  showCommentForm : boolean = false;
  showForm: boolean = false;
  postulationForm: FormGroup;
  commentForm: FormGroup;
  company: string = '';

constructor(
  private postulationService: PostulationService,
  private fb: FormBuilder
) {
  this.postulationForm = this.fb.group({
      titulo: ['', Validators.required],
      empresa: ['', Validators.required],
      descripcion: ['', Validators.required],
      responsabilidades: ['', Validators.required],
      salario: [0, [Validators.required, Validators.min(0)]],
      beneficios: [''],
      tiene_ingles: [false],
      reclutador: ['', Validators.required],
      antiguedad: [0, Validators.min(0)],
      tipo: ['Indefinido'],
      cantidad_empleados: [0, Validators.min(1)],
      opiniones: [''],
      sueldo_opinion: [0, Validators.min(0)]
    });
  this.commentForm = this.fb.group({
      comentario: ['', Validators.required]
    });
}

toggleForm(): void {
  this.showForm = true;
  this.showPostulation = false;
  this.showComment = false;
}

toggleCommentForm(company: string): void {
  this.company = company;
  this.showCommentForm = true;
  this.showPostulation = false;
  this.showComment = false;
}

toggleCancel(): void {
  this.showForm = false;
  this.showCommentForm = false;
  this.showPostulation = true;
}

toggleOpinion(): void {
  this.showComment = true;
  this.showPostulation = false;
  this.showForm = false;
  this.showCommentForm = false;
}

togglePostulation(): void {
  this.showPostulation = true;
  this.showComment = false;
  this.showForm = false;
  this.showCommentForm = false;
}

ngOnInit(): void {
  this.loadPostulations();
  this.loadComments();
}

loadPostulations(): void {
  this.postulationService.getPostulations().subscribe((data) => {
  this.postulations = data;
  });
}

loadComments(): void {
    this.postulationService.listComments().subscribe((data) => {
    this.comments = data;
    });
}

createPostulation(): void {
    if (this.postulationForm.valid) {
      const formValues = this.postulationForm.value;

      const newPostulation: Postulation = {
        titulo: formValues.titulo,
        empresa: formValues.empresa,
        descripcion: formValues.descripcion,
        responsabilidades: formValues.responsabilidades.split(',').map((resp: string) => resp.trim()),
        salario: formValues.salario,
        beneficios: formValues.beneficios,
        tiene_ingles: formValues.tiene_ingles,
        fecha_publicacion: new Date().toISOString().split('T')[0],
        reclutador: formValues.reclutador,
        antiguedad: formValues.antiguedad,
        tipo: formValues.tipo,
        cantidad_empleados: formValues.cantidad_empleados,
        opiniones: formValues.opiniones ? formValues.opiniones.split(',').map((opinion: string) => opinion.trim()) : [],
        validacion: 0, // Valor predeterminado
                sueldo_opinion: formValues.sueldo_opinion
        };

        this.postulationService.savePostulation(newPostulation).subscribe({
        next: () => {
            alert('Postulación creada exitosamente.');
            this.postulationForm.reset();
        },
        error: (err) => {
          console.error('Error al crear la postulación:', err);
          alert('Ocurrió un error al intentar crear la postulación. Por favor, inténtalo de nuevo.');
        }
      });
    } else {
      alert('Por favor, completa todos los campos obligatorios.');
    }
  }

  createComment(): void {
  if (this.commentForm.valid) {
      const formValues = this.commentForm.value;

          const newComment: Comment = {
            compañia: this.company,
            comentario: formValues.comentario,
        }
      this.postulationService.saveComment(newComment).subscribe({
        next: () => {
          alert('Comentario creado exitosamente.');
          this.commentForm.reset();
          this.loadComments();
        },
        error: (err) => {
          console.error('Error al crear el comentario:', err);
          alert('Ocurrió un error al intentar crear el comentario. Por favor, inténtalo de nuevo.');
        }

      });
  }
    this.showCommentForm = false;
    this.showComment = true;
  }

  deletePostulation(id: number): void {
    this.postulationService.deletePostulation(id).subscribe(() => {
    this.loadPostulations();
      });
  }
}


