package testesJUnit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ClassPrato;
import model.ClassProduto;
import model.Dados;
import model.FacadeMenu;

public class TestFacadeMenuCardapio {
	ObservableList<ClassProduto> receita = FXCollections.observableArrayList();;
	
	@BeforeEach
	public void setUp() {
		Dados.getInstance().getListapratos().clear();
		Dados.getInstance().getListacodigos().clear();
		
		ClassProduto produto = new ClassProduto(1, "Produto A", (float) 3.5);
		ClassProduto produto2 = new ClassProduto(1, "Produto A", (float) 3.5);
		Dados.getInstance().getListacodigos().add(1);
		Dados.getInstance().getListaprodutos().add(produto);
		produto2.setQuantidade(100);
		receita.add(produto2);
	}
	
	@Test
	public void testExcluirPratoExistente() {
		ClassPrato prato = new ClassPrato(1000, null, null, 0, null, receita);
		Dados.getInstance().getListapratos().add(prato);
		Dados.getInstance().getListacodigos().add(1000);
		
		FacadeMenu.excluirPrato(1000);
		
		assertFalse(Dados.getInstance().getListapratos().contains(prato), "O prato não existe na lista de pratos");
	}
	
	@Test
	public void testExcluirPratoInexistente() {
		int sizePre = Dados.getInstance().getListapratos().size();
		
		FacadeMenu.excluirPrato(1000);
		
		int sizePos = Dados.getInstance().getListapratos().size();
		
		assertEquals(sizePre, sizePos, "A lista não foi alterada");
	}
	
	@Test
	public void testCriarPrato() {
		FacadeMenu.criarPrato("Prato A", "1", 2, "3", receita);
		
		ClassPrato prato = new ClassPrato(1000 , "Prato A", "1", 2, "3", receita);
		ClassPrato prato2 = Dados.getInstance().getListapratos().get(0);
		Dados.getInstance().getListacodigos().add(1000);
		
		assertTrue("O prato foi criado conforme os parâmetros", prato.getNome() == prato2.getNome() && prato.getPreco() == prato2.getPreco() && prato.getCategoria() == prato2.getCategoria() && prato.getReceita() == prato2.getReceita());
	}
	
	@Test
	public void testEditarPrato() {
		ClassPrato prato = new ClassPrato(1000, "Prato A", null, 0, null, receita);
		Dados.getInstance().getListapratos().add(prato);
		Dados.getInstance().getListacodigos().add(1000);
		
		FacadeMenu.editarPrato(1000, "Prato B", null, 0, null, receita);
		
		assertNotEquals("O prato foi editado", "Prato A", Dados.getInstance().getListapratos().get(0).getNome());
		
	}
}
