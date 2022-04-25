package br.com.nava.exemplos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nava.entities.ProfessorEntity;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.repositories.ProfessorRepository;
import br.com.nava.repositories.UsuarioRepository;

/*@SpringBootApplication
public class PrimeiroMsApplication implements CommandLineRunner {

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private UsuarioRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PrimeiroMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// seria o mesmo  que SELECT * FROM PROFESSOR;
		List<ProfessorEntity> listaProfessor = professorRepository.findAll();
		// para retornar os dados de todos os professores do banco de dados
		for (int i = 0; i < listaProfessor.size(); i++) {
			System.out.println(listaProfessor.get(i));
		}

		List<UsuarioEntity> listaUsuario = userRepository.findAll();
		// mostra os usuarios
		for (int i = 0; i < listaUsuario.size(); i++) {
			System.out.println(listaUsuario.get(i));
		}
		
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setEmail("caju@gmail.com");
		usuario.setNome("Caju");
		userRepository.save(usuario);
	}
}

 CREATE TABLE USUARIOS (ID INT PRIMARY KEY AUTO_INCREMENT, NOME VARCHAR(255), EMAIL VARCHAR(255));
 
 INSERT INTO USUARIOS (NOME, EMAIL) VALUES ('Rafa Sil', 'raf@gmail.com.br');
 INSERT INTO USUARIOS (NOME, EMAIL) VALUES ('Raquel Araujo', 'raquel@gmail.com.br');
 INSERT INTO USUARIOS (NOME, EMAIL) VALUES ('Nathany Maciel', 'natha@gmail.com.br');
INSERT INTO USUARIOS (NOME, EMAIL) VALUES ('Karine Silva', 'kari@gmail.com.br'); 

CREATE TABLE PROFESSORES (ID INT PRIMARY KEY AUTO_INCREMENT,  NOME VARCHAR(255), CPF VARCHAR (255), RUA VARCHAR(255), NUMERO INT, CEP VARCHAR(255));

 INSERT INTO PROFESSORES (NOME, CPF, RUA, NUMERO, CEP) VALUES ('Rafa Sil', '456123', 'Rua Belo', 45, '0789-895');
 INSERT INTO PROFESSORES (NOME, CPF, RUA, NUMERO, CEP) VALUES ('Raquel Araujo', '741852', 'Rua José Lara', 56, '0741-898');
INSERT INTO PROFESSORES (NOME, CPF, RUA, NUMERO, CEP) VALUES ('Nathany Maciel', '523965', 'Rua Caju', 789, '0804-125');
 INSERT INTO PROFESSORES (NOME, CPF, RUA, NUMERO, CEP) VALUES ('Karine Silva', '789123', 'Rua Alencar', 145, '0784-892');
1-) Criar tabela SQL

2-) Criar/ajustar a camada de Entidade (Model)
2.1) Verificar se a entidade tem chave primária(@Id)

3-) Criar camada de Repositório
3.1) Não esquecer da anotação @Repository

4-) Criar camada de Service*/