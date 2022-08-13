package testesJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ClassFornecedores;
import model.ClassPrato;
import model.ClassProduto;
import model.Dados;
import model.FacadeMenu;

public class TestClassPrato {
	ObservableList<ClassProduto> ingredientes = FXCollections.observableArrayList();;
	Date dataHora;
	ClassProduto produto;
	ClassProduto unidade;
	ClassFornecedores fornecedor = new ClassFornecedores(0, "", "", "");
	
	@BeforeEach
	public void setUp() {
		Dados.getInstance().getListaprodutos().clear();
		
		produto = new ClassProduto(1000, "Produto1", (float) 1.50);
		unidade = new ClassProduto(1001, "Produto1", (float) 1.50, dataHora, fornecedor, 1000);
		produto.getStock().add(unidade);
		produto.updateQuantidade();
		Dados.getInstance().getListaprodutos().add(produto);
	}
	
	@Test
	public void testaPossuiIngredientesParaFazer() {			
		ClassProduto ingrediente = new ClassProduto(1000, "Produto1", (float) 1.50);
		ingrediente.setQuantidade(500);
		ingredientes.add(ingrediente);
		
		ClassPrato prato1 = new ClassPrato(1004, "Macarronada", "-", (float) 20.00, "Massas", ingredientes);
		
		Dados.getInstance().getListacodigos().add(1000);
		Dados.getInstance().getListacodigos().add(1001);
		Dados.getInstance().getListacodigos().add(1004);
		
		assertTrue(prato1.checkStock());
	}
	
	
	@Test
	public void testaIngredienteNaoExisteNosProdutos() {
		ClassProduto ingrediente = new ClassProduto(1000, "Produto1", (float) 1.50);
		ingrediente.setQuantidade(500);
		ingredientes.add(ingrediente);
		
		ClassPrato prato1 = new ClassPrato(1004, "Macarronada", "-", (float) 20.00, "Massas", ingredientes);
		
		Dados.getInstance().getListacodigos().add(1000);
		Dados.getInstance().getListacodigos().add(1001);
		Dados.getInstance().getListacodigos().add(1004);
		
		FacadeMenu.excluirProduto(1000);
		
		assertFalse(prato1.checkStock());
	}
	
	
	@Test
	public void testaNaoPossuiIngredientesSuficientes() {
		ClassProduto ingrediente = new ClassProduto(1000, "Produto1", (float) 1.50);
		ingrediente.setQuantidade(5000);
		ingredientes.add(ingrediente);
		
		ClassPrato prato1 = new ClassPrato(1004, "Macarronada", "-", (float) 20.00, "Massas", ingredientes);
		
		Dados.getInstance().getListacodigos().add(1000);
		Dados.getInstance().getListacodigos().add(1001);
		Dados.getInstance().getListacodigos().add(1004);
		
		assertFalse(prato1.checkStock());
	} 
}
