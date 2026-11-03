import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-inicio',
  standalone: true,
  templateUrl: './inicio.html',
  styleUrl: './inicio.css'
})
export class Inicio {

@Input() totalAtividades = 0;

@Input() atividadesAndamento = 0;
@Input() atividadesConcluidas = 0;

@Input() atividadesAFazer = 0;

@Input() progresso = 0;

  atividadesRecentes: any[] = [];

}

