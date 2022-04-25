package br.com.nava;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.nava.repositories.ProfessorRepository;
import br.com.nava.repositories.UsuarioRepository;
import br.com.nava.services.BDService;

@SpringBootApplication
public class PrimeiroMsApplication {
	
//	@Autowired
//	private ProfessorRepository professorRepository;
//	
//	@Autowired
//	private UsuarioRepository userRepository;
//	
//	@Autowired
//	private BDService bdService;

	public static void main(String[] args) {
		SpringApplication.run(PrimeiroMsApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		bdService.inserirVendas();
		// seria o mesmo  que SELECT * FROM PROFESSOR;
//		List<ProfessorEntity> listaProfessor = professorRepository.findAll();
//		// para retornar os dados de todos os professores do banco de dados
//		for (int i = 0; i < listaProfessor.size(); i++) {
//			System.out.println(listaProfessor.get(i));
//		}
//
//		List<UsuarioEntity> listaUsuario = userRepository.findAll();
//		// mostra os usuarios
//		for (int i = 0; i < listaUsuario.size(); i++) {
//			System.out.println(listaUsuario.get(i));
//		}
//		
//		UsuarioEntity usuario = new UsuarioEntity();
//		usuario.setEmail("caju@gmail.com");
//		usuario.setNome("Caju");
//		userRepository.save(usuario);
//	}
}