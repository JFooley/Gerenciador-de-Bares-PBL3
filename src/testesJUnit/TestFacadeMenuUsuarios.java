package testesJUnit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ClassUsuario;
import model.Dados;
import model.FacadeMenu;

public class TestFacadeMenuUsuarios {
	
	@BeforeEach
	public void setUp() {
		Dados.getInstance().getListausuarios().clear();
		Dados.getInstance().getListacodigos().clear();
	}
	
	@Test
	public void testExcluirUsuarioExistente() {
		ClassUsuario usuario = new ClassUsuario(1000, "", "", true);
		Dados.getInstance().getListausuarios().add(usuario);
		Dados.getInstance().getListacodigos().add(1000);
		
		FacadeMenu.excluirUsuario(1000);
		
		assertFalse(Dados.getInstance().getListausuarios().contains(usuario), "O usuario foi removido");
	}
	
	@Test
	public void testExcluirUsuarioInexistente() {
		int sizePre = Dados.getInstance().getListausuarios().size();
		
		FacadeMenu.excluirUsuario(1000);
		
		int sizePos = Dados.getInstance().getListausuarios().size();
		
		assertEquals(sizePre, sizePos, "A lista não foi alterada");
	}
	
	@Test
	public void testCriarUsuario() {
		FacadeMenu.criarUsuario("Usuario A", "senha", true);
		
		ClassUsuario usuario = new ClassUsuario(1, "Usuario A", "senha", true);
		ClassUsuario usuario2 = Dados.getInstance().getListausuarios().get(0);
		
		assertTrue("O ususario foi criado conforme os parâmetros", usuario.getUser() == usuario2.getUser() && usuario.getPassword() == usuario2.getPassword() && usuario.getAdm() == usuario2.getAdm());
	}
	
	@Test
	public void testEditarUsuario() {
		ClassUsuario usuario = new ClassUsuario(1000, "Usuario A", "", true);
		Dados.getInstance().getListausuarios().add(usuario);
		Dados.getInstance().getListacodigos().add(1000);
		
		FacadeMenu.editarUsuario(1000, "Usuario B", "", true);
		
		assertNotEquals("O usuario foi editado", "Usuario A" != Dados.getInstance().getListausuarios().get(0).getUser());
	}
}
