import { ChangeDetectorRef, Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  
       // AVISA O (App)
  @Output() entrou = new EventEmitter<string>();

  email = '';
  senha = '';
  erro = '';

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  entrar() {
    if (this.email === '' || this.senha === '') {
      this.erro = 'Preencha o e-mail e a senha.';
      return;
    }

    const dados = { email: this.email, senha: this.senha };
    
        // POST -> AuthController (/auth + /login). subscribe espera a resposta
    this.http.post<any>('http://localhost:8080/auth/login', dados).subscribe({
        // 200: EMIT avisa o App
      next: usuario => {
        this.erro = '';
        this.entrou.emit(usuario.nome);
      },
      // 401 = senha errada;
      error: erro => {
        if (erro.status === 401) {
          this.erro = 'E-mail ou senha incorretos.';
        } else {
          this.erro = 'Não foi possível conectar ao servidor.';
        }
        this.cd.detectChanges();
      }
    });
  }
}
