package testesJUnit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ClassProduto;
import model.Dados;
import model.FacadeMenu;

public class TestFacadeMenuProduto {
	
	@BeforeEach
	public void setUp() {
		Dados.getInstance().getListacodigos().clear();
		Dados.getInstance().getListaprodutos().clear();
	}
	
	@Test
	public void testExcluirProdutoExistente() {
		Dados.getInstance().getListacodigos().add(1000);
		ClassProduto produto = new ClassProduto(1000, "Produto A", (float) 3.5);
		Dados.getInstance().getListaprodutos().add(produto);
		
		FacadeMenu.excluirProduto(1000);
		
		assertFalse(Dados.getInstance().getListaprodutos().contains(produto), "O produto não existe na lista de produtos");
	}
	
	@Test
	public void testExcluirProdutoInexistente() {
		int sizePre = Dados.getInstance().getListaprodutos().size();
		
		FacadeMenu.excluirProduto(1000);
		
		int sizePos = Dados.getInstance().getListaprodutos().size();
		
		assertEquals(sizePre, sizePos, "A lista não foi alterada");
	}
	
	@Test
	public void testCriarProduto() {
		FacadeMenu.criarProduto("Produto A", (float) 3.5);
		
		ClassProduto produto = new ClassProduto(1, "Produto A", (float) 3.5);
		ClassProduto produto2 = Dados.getInstance().getListaprodutos().get(0);
		
		assertTrue("O produto foi criado conforme os parâmetros", produto.getNome() == produto2.getNome() && produto.getPreco() == produto2.getPreco());
	}
	
	@Test
	public void testEditarProduto() {
		Dados.getInstance().getListacodigos().add(1000);
		ClassProduto produto = new ClassProduto(1000, "Produto A", (float) 3.5);
		Dados.getInstance().getListaprodutos().add(produto);
		
		FacadeMenu.editarProduto(1000, "Produto B", (float) 3.5);
		
		assertNotEquals("O produto foi editado", "Produto A", Dados.getInstance().getListaprodutos().get(0).getNome());
	}
}
