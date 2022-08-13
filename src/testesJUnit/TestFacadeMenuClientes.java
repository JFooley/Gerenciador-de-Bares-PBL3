package testesJUnit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ClassCliente;
import model.Dados;
import model.FacadeMenu;

public class TestFacadeMenuClientes {
	@BeforeEach
	public void setUp() {
		Dados.getInstance().getListaclientes().clear();
		Dados.getInstance().getListacodigos().clear();
	}
	
	@Test
	public void testExcluirClienteExistente() {
		ClassCliente cliente = new ClassCliente(1000, "", "", "", "");
		Dados.getInstance().getListaclientes().add(cliente);
		Dados.getInstance().getListacodigos().add(1000);
		
		FacadeMenu.excluirCliente(1000);
		
		assertFalse(Dados.getInstance().getListaclientes().contains(cliente), "O cliente não existe na lista de clientes");
	}
	
	@Test
	public void testExcluirClienteInexistente() {
		int sizePre = Dados.getInstance().getListaclientes().size();
		
		FacadeMenu.excluirCliente(1000);
		
		int sizePos = Dados.getInstance().getListaclientes().size();
		
		assertEquals(sizePre, sizePos, "A lista não foi alterada");
	}
	
	@Test
	public void testCriarCliente() {
		FacadeMenu.criarCliente("Cliente A", "1", "2", "3");
		
		ClassCliente cliente = new ClassCliente(1, "Cliente A", "1", "2", "3");
		ClassCliente cliente2 = Dados.getInstance().getListaclientes().get(0);
		
		assertTrue("O cliente foi criado conforme os parâmetros", cliente.getNome() == cliente2.getNome() && cliente.getCpf() == cliente2.getCpf() && cliente.getTelefone() == cliente2.getTelefone());
	}
	
	@Test
	public void testEditarCliente() {
		ClassCliente cliente = new ClassCliente(1000, "Cliente A", "", "", "");
		Dados.getInstance().getListaclientes().add(cliente);
		Dados.getInstance().getListacodigos().add(1000);
		
		FacadeMenu.editarCliente(1000, "Cliente B", "", "", "");
		
		assertNotEquals("O cliente foi editado", "Cliente A", Dados.getInstance().getListaclientes().get(0).getNome());
	}
}

