package testesJUnit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ClassFornecedores;
import model.Dados;
import model.FacadeMenu;

public class TestFacadeMenuFornecedores {
	@BeforeEach
	public void setUp() {
		Dados.getInstance().getListafornecedores().clear();
		Dados.getInstance().getListacodigos().clear();
	}
	
	@Test
	public void testExcluirFornecedorExistente() {
		ClassFornecedores fornecedor = new ClassFornecedores(1000, "Fornecedor A", "", "");
		Dados.getInstance().getListafornecedores().add(fornecedor);
		Dados.getInstance().getListacodigos().add(1000);
		
		FacadeMenu.excluirFornecedor(1000);
		
		assertFalse(Dados.getInstance().getListafornecedores().contains(fornecedor), "O fornecedor não existe na lista de fornecedores");
	}
	
	@Test
	public void testExcluirFornecedorInexistente() {
		int sizePre = Dados.getInstance().getListaclientes().size();
		
		FacadeMenu.excluirCliente(1000);
		
		int sizePos = Dados.getInstance().getListaclientes().size();
		
		assertEquals(sizePre, sizePos, "A lista não foi alterada");
	}
	
	@Test
	public void testCriarFornecedor() {
		FacadeMenu.criarFornecedor("Fornecedor A", "1", "2");
		
		ClassFornecedores fornecedor = new ClassFornecedores(1, "Fornecedor A", "1", "2");
		ClassFornecedores fornecedor2 = Dados.getInstance().getListafornecedores().get(0);
		
		assertTrue("O fornecedor foi criado conforme os parâmetros", fornecedor.getNome() == fornecedor2.getNome() && fornecedor.getCnpj() == fornecedor2.getCnpj() && fornecedor.getEndereco() == fornecedor2.getEndereco());
	}
	
	@Test
	public void testEditarFornecedor() {
		ClassFornecedores fornecedor = new ClassFornecedores(1000, "Fornecedor A", "", "");
		Dados.getInstance().getListafornecedores().add(fornecedor);
		Dados.getInstance().getListacodigos().add(1000);
		
		FacadeMenu.editarFornecedor(1000, "Cliente B", "", "");
		
		assertNotEquals("O fornecedor foi editado", "Fornecedor A", Dados.getInstance().getListafornecedores().get(0).getNome());
	}
}
