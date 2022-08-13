package testesJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import model.ClassFornecedores;
import model.ClassProduto;
import model.Dados;

public class TestClassProduto {
	ClassFornecedores fornecedor = new ClassFornecedores(0, "", "", "");
	ClassProduto produto = new ClassProduto(1000, "Produto A", 5);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date dataHora;
	
	@Test
	public void testUpdateQuantidadeComUmProdutoDe1000() throws ParseException {
		dataHora = sdf.parse("01/01/2000");
		
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora, fornecedor, 1000);
		produto.getStock().add(unidade1);
		
		produto.updateQuantidade();
		
		assertEquals(1000, produto.getQuantidade(), "A quantidade do produto é 1000");
	}
	
	@Test
	public void testUpdateQuantidadeComMaisDeUmProdutosDe500() throws ParseException {
		dataHora = sdf.parse("01/01/2000");
		
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora, fornecedor, 500);
		ClassProduto unidade2 = new ClassProduto(1002, "Produto A", 5, dataHora, fornecedor, 500);
		ClassProduto unidade3 = new ClassProduto(1003, "Produto A", 5, dataHora, fornecedor, 500);
		ClassProduto unidade4 = new ClassProduto(1004, "Produto A", 5, dataHora, fornecedor, 500);
		produto.getStock().add(unidade1);
		produto.getStock().add(unidade2);
		produto.getStock().add(unidade3);
		produto.getStock().add(unidade4);
		
		produto.updateQuantidade();
		
		assertEquals(2000, produto.getQuantidade(), "A quantidade do produto é 2000");
	}
	
	@Test
	public void testUpdateQuantidadeSemProdutos() {
		produto.updateQuantidade();
	
		assertEquals(0, produto.getQuantidade(), "A quantidade do produto é 0");
	}
	
	@Test
	public void testSortStockComQuatroUnidades() throws ParseException {
		Date dataHora1 = sdf.parse("01/01/2000");
		Date dataHora2 = sdf.parse("10/01/2000");
		Date dataHora3 = sdf.parse("01/02/2000");
		Date dataHora4 = sdf.parse("10/02/2000");
		
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora1, fornecedor, 500);
		ClassProduto unidade2 = new ClassProduto(1002, "Produto A", 5, dataHora2, fornecedor, 500);
		ClassProduto unidade3 = new ClassProduto(1003, "Produto A", 5, dataHora3, fornecedor, 500);
		ClassProduto unidade4 = new ClassProduto(1004, "Produto A", 5, dataHora4, fornecedor, 500);

		produto.getStock().add(unidade2);
		produto.getStock().add(unidade1);
		produto.getStock().add(unidade3);
		produto.getStock().add(unidade4);

		produto.sortStock();
		
		assertEquals(unidade1, produto.getStock().get(0), "Primeiro item da lista é a unidade mais antiga (unidade1)");
	}
	
	@Test
	public void testSortStockComDuasUnidadesDeDataIgual() throws ParseException {
		Date dataHora1 = sdf.parse("01/01/2000");
		Date dataHora2 = sdf.parse("10/01/2000");
		Date dataHora3 = sdf.parse("01/01/2000");
		Date dataHora4 = sdf.parse("10/02/2000");
		
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora1, fornecedor, 500);
		ClassProduto unidade2 = new ClassProduto(1002, "Produto A", 5, dataHora2, fornecedor, 500);
		ClassProduto unidade3 = new ClassProduto(1003, "Produto A", 5, dataHora3, fornecedor, 500);
		ClassProduto unidade4 = new ClassProduto(1004, "Produto A", 5, dataHora4, fornecedor, 500);

		produto.getStock().add(unidade2);
		produto.getStock().add(unidade1);
		produto.getStock().add(unidade3);
		produto.getStock().add(unidade4);

		produto.sortStock();
		
		assertTrue((unidade1 == produto.getStock().get(0) && unidade3 == produto.getStock().get(1)), "Primeiro da lista é o adicionado primeiro (unidade1)");
	}
	
	@Test
	public void testAdicionaUmItemNoStockVazio() throws ParseException {
		Date dataHora1 = sdf.parse("01/01/2000");
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora1, fornecedor, 500);
		
		produto.adicionaStock(1001, dataHora1, fornecedor, 500);
		
		ClassProduto unidadeAdicionada = produto.getStock().get(0);
		
		assertEquals(unidade1.getCodigo(), unidadeAdicionada.getCodigo(), "Uma unidade é criada e adicionada no stock");
	}
	
	@Test
	public void testAdicionaUmItemMaisNovoQueTodos() throws ParseException {
		Date dataHora1 = sdf.parse("01/01/2000");
		Date dataHora2 = sdf.parse("02/02/2000");
		Date dataHora3 = sdf.parse("03/03/2000");
		Date dataHora4 = sdf.parse("04/04/2000");
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora1, fornecedor, 500);
		ClassProduto unidade2 = new ClassProduto(1002, "Produto A", 5, dataHora2, fornecedor, 500);
		ClassProduto unidade3 = new ClassProduto(1003, "Produto A", 5, dataHora3, fornecedor, 500);
		ClassProduto unidade4 = new ClassProduto(1004, "Produto A", 5, dataHora4, fornecedor, 500);
		produto.getStock().add(unidade1);
		produto.getStock().add(unidade2);
		produto.getStock().add(unidade3);
		produto.getStock().add(unidade4);
		
		Date dataHora = sdf.parse("01/01/2022");
		ClassProduto unidade = new ClassProduto(1005, "Produto A", 5, dataHora, fornecedor, 500);
		
		produto.adicionaStock(1005, dataHora, fornecedor, 500);
		
		ClassProduto unidadeAdicionada = produto.getStock().get( produto.getStock().size()-1);
		
		assertEquals(unidade.getValidade(), unidadeAdicionada.getValidade(), "A unidade criada é a mais nova (ultima) do estoque");
	}
	
	@Test
	public void testRemoverEstoqueComUmaUnidadeEMesmaQuantidade() throws ParseException {
		Date dataHora1 = sdf.parse("01/01/2000");
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora1, fornecedor, 500);
		produto.getStock().add(unidade1);
		
		produto.removeStock(500);
				
		assertEquals(0, produto.getStock().size(), "Não sobram unidades no estoque após remoção");
	}
	
	@Test
	public void testRemoverEstoqueComUmaUnidadeQuantidadeInferior() throws ParseException {
		Date dataHora1 = sdf.parse("01/01/2000");
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora1, fornecedor, 500);
		produto.getStock().add(unidade1);
		
		produto.removeStock(300);
				
		assertEquals(200, produto.getStock().get(0).getQuantidade(), "Não sobram unidades no estoque após remoção");
	}
	
	@Test
	public void testRemoverEstoqueVariasUnidades() throws ParseException {
		Date dataHora1 = sdf.parse("01/01/2000");
		Date dataHora2 = sdf.parse("02/02/2000");
		Date dataHora3 = sdf.parse("03/03/2000");
		Date dataHora4 = sdf.parse("04/04/2000");
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora1, fornecedor, 500);
		ClassProduto unidade2 = new ClassProduto(1002, "Produto A", 5, dataHora2, fornecedor, 500);
		ClassProduto unidade3 = new ClassProduto(1003, "Produto A", 5, dataHora3, fornecedor, 500);
		ClassProduto unidade4 = new ClassProduto(1004, "Produto A", 5, dataHora4, fornecedor, 500);
		produto.getStock().add(unidade1);
		produto.getStock().add(unidade2);
		produto.getStock().add(unidade3);
		produto.getStock().add(unidade4);
		
		produto.removeStock(1200);
		
		assertTrue((produto.getStock().size() == 2 && produto.getStock().get(0).getQuantidade() == 300), "Duas unidades são removidas e a ultima parcialmente");
	}
	
	@Test
	public void testRemoveUnidadeExistenteEmUmStockComQuatroUnidades() throws ParseException {
		Date dataHora1 = sdf.parse("01/01/2000");
		Date dataHora2 = sdf.parse("02/02/2000");
		Date dataHora3 = sdf.parse("03/03/2000");
		Date dataHora4 = sdf.parse("04/04/2000");
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora1, fornecedor, 500);
		ClassProduto unidade2 = new ClassProduto(1002, "Produto A", 5, dataHora2, fornecedor, 500);
		ClassProduto unidade3 = new ClassProduto(1003, "Produto A", 5, dataHora3, fornecedor, 500);
		ClassProduto unidade4 = new ClassProduto(1004, "Produto A", 5, dataHora4, fornecedor, 500);
		produto.getStock().add(unidade1);
		produto.getStock().add(unidade2);
		produto.getStock().add(unidade3);
		produto.getStock().add(unidade4);
		
		produto.removeUnidade(1001);
	
		assertEquals(3, produto.getStock().size(), "Unidade do código 1001 foi removida sobrando as 3 outras");
	}
	
	@Test
	public void testRemoveUnidadeInexistente() throws ParseException {
		Date dataHora1 = sdf.parse("01/01/2000");
		Date dataHora2 = sdf.parse("02/02/2000");
		Date dataHora3 = sdf.parse("03/03/2000");
		Date dataHora4 = sdf.parse("04/04/2000");
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora1, fornecedor, 500);
		ClassProduto unidade2 = new ClassProduto(1002, "Produto A", 5, dataHora2, fornecedor, 500);
		ClassProduto unidade3 = new ClassProduto(1003, "Produto A", 5, dataHora3, fornecedor, 500);
		ClassProduto unidade4 = new ClassProduto(1004, "Produto A", 5, dataHora4, fornecedor, 500);
		produto.getStock().add(unidade1);
		produto.getStock().add(unidade2);
		produto.getStock().add(unidade3);
		produto.getStock().add(unidade4);
		
		produto.removeUnidade(9999);
	
		assertEquals(4, produto.getStock().size(), "Nenhuma unidade é removida");
	}
	
	@Test
	public void testRemoveUnidadeComOMesmoCodigo() throws ParseException {
		Date dataHora1 = sdf.parse("01/01/2000");
		Date dataHora2 = sdf.parse("02/02/2000");
		Date dataHora3 = sdf.parse("03/03/2000");
		Date dataHora4 = sdf.parse("04/04/2000");
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora1, fornecedor, 500);
		ClassProduto unidade2 = new ClassProduto(1001, "Produto A", 5, dataHora2, fornecedor, 500);
		ClassProduto unidade3 = new ClassProduto(1002, "Produto A", 5, dataHora3, fornecedor, 500);
		ClassProduto unidade4 = new ClassProduto(1003, "Produto A", 5, dataHora4, fornecedor, 500);
		produto.getStock().add(unidade1);
		produto.getStock().add(unidade2);
		produto.getStock().add(unidade3);
		produto.getStock().add(unidade4);
		
		produto.removeUnidade(1001);
	
		assertEquals(3, produto.getStock().size(), "Apenas a primeira unidade com o código é removida");
	}
	
	@Test
	public void testBuscaPorIDProdutoExistente() {
		Dados.getInstance().getListaprodutos().clear();
		Dados.getInstance().getListaprodutos().add(produto);
		
		assertSame(produto, ClassProduto.buscaPorID(1000));
	}
	
	@Test
	public void testBuscaPorIDProdutoInexistente() {
		Dados.getInstance().getListaprodutos().clear();
		
		assertNull(ClassProduto.buscaPorID(1000));
	}
}
