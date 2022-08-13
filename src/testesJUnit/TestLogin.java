package testesJUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import model.ClassUsuario;
import model.Dados;
import model.Login;

public class TestLogin {
	Login login = Login.getInstance();
	
	@BeforeEach
	public void setUp() {
		Dados.getInstance().getListausuarios().clear();
	}
	
	@Test
	public void testAutenticacaoUsuarioValido() {
		
		ClassUsuario user1 = new ClassUsuario(1111, "JoaoGerente", "259831", true);
		Dados.getInstance().getListausuarios().add(user1);
		Dados.getInstance().getListacodigos().add(1111);
			
		boolean result1 = login.autentica("JoaoGerente", "259831");
		boolean result2 = login.getUsuarioAut().getCodigo() == user1.getCodigo();
						
		assertTrue((result1 && result2), "Usuario é autenticado e passar a ser UsusarioAut");				
	}
	
	@Test
	public void testAutenticacaoUsuarioInvalido() {
		
		ClassUsuario user1 = new ClassUsuario(1111, "JoaoGerente", "259831", true);
		ClassUsuario user2 = new ClassUsuario(2222, "PedroGerente", "259831", true);
		ClassUsuario user3 = new ClassUsuario(3333, "GabrielFuncionario", "259831", false);
		ClassUsuario user4 = new ClassUsuario(4444, "CaleoFuncionario", "259831", false);
		Dados.getInstance().getListausuarios().add(user1);
		Dados.getInstance().getListausuarios().add(user2);
		Dados.getInstance().getListausuarios().add(user3);
		Dados.getInstance().getListausuarios().add(user4);
		
		assertFalse(login.autentica("JoaoFuncionario", "12345"), "Usuario não é autenticado");				
	}
	
	@Test
	public void testAutenticacaoUsuarioUsuarioAutenticadoEhAlterado() {
		
		ClassUsuario user1 = new ClassUsuario(1111, "JoaoGerente", "259831", true);
		ClassUsuario user2 = new ClassUsuario(2222, "PedroGerente", "12345", true);
		ClassUsuario user3 = new ClassUsuario(3333, "GabrielFuncionario", "1111", false);
		ClassUsuario user4 = new ClassUsuario(4444, "CaleoFuncionario", "2222", false);
		Dados.getInstance().getListausuarios().add(user1);
		Dados.getInstance().getListausuarios().add(user2);
		Dados.getInstance().getListausuarios().add(user3);
		Dados.getInstance().getListausuarios().add(user4);
		
		login.autentica("PedroGerente", "12345");
		
		login.autentica("JoaoGerente", "259831");
		
		assertTrue(login.getUsuarioAut() == user1, "Usuario autenticado é mudadado para user1");					
	}
}
