package testesJUnit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ClassCliente;
import model.ClassFornecedores;
import model.ClassPrato;
import model.ClassProduto;
import model.ClassVenda;
import model.Dados;
import model.FacadeMenu;

public class TestFacadeMenuVenda {
	
	ClassCliente cliente = new ClassCliente(0, "", "", "", "");
	Date dataHora;
	ObservableList<ClassProduto> receita = FXCollections.observableArrayList();
	ObservableList<ClassPrato> pratos = FXCollections.observableArrayList();
	ObservableList<ClassPrato> pratos2 = FXCollections.observableArrayList();
	
	@BeforeEach
	public void setUp() throws ParseException {
		Dados.getInstance().getListavendas().clear();
		Dados.getInstance().getListacodigos().clear();
		Dados.getInstance().getListafornecedores().clear();
		Dados.getInstance().getListapratos().clear();
		Dados.getInstance().getListaprodutos().clear();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataHora = sdf.parse("01/01/2000");
		

		ClassProduto produto = new ClassProduto(1, "Produto A", (float) 3.5);
		ClassProduto produto2 = new ClassProduto(1, "Produto A", (float) 3.5);
		
		ClassFornecedores fornecedor = new ClassFornecedores(2, "", "", "");
		Dados.getInstance().getListafornecedores().add(fornecedor);
		
		ClassProduto unidade = new ClassProduto(3, "Unidade A", (float) 3.5, dataHora, fornecedor, 1000);
		produto.getStock().add(unidade);
		Dados.getInstance().getListaprodutos().add(produto);
		
		produto2.setQuantidade(100);
		receita.add(produto2);
		
		ClassPrato prato1 = new ClassPrato(4, "", "", (float) 5, "", receita);
		
		pratos.add(prato1);
		
		Dados.getInstance().getListacodigos().add(1);
		Dados.getInstance().getListacodigos().add(2);
		Dados.getInstance().getListacodigos().add(3);
		Dados.getInstance().getListacodigos().add(4);
		Dados.getInstance().getListacodigos().add(5);

	}
	
	@Test
	public void testExcluirVendaExistente() {
		ClassVenda venda = new ClassVenda(1000, dataHora, pratos, 0, null, null);
		Dados.getInstance().getListavendas().add(venda);
		Dados.getInstance().getListacodigos().add(1000);
		
		FacadeMenu.excluirVenda(1000);
		
		assertFalse(Dados.getInstance().getListavendas().contains(venda), "A venda não existe na lista de vendas");
	}
	
	@Test
	public void testExcluirVendaInexistente() {
		int sizePre = Dados.getInstance().getListavendas().size();
		
		FacadeMenu.excluirVenda(1000);
		
		int sizePos = Dados.getInstance().getListavendas().size();
		
		assertEquals(sizePre, sizePos, "A lista não foi alterada");
	}
	
	@Test
	public void testCriarVenda() {
		FacadeMenu.criarVenda(dataHora, pratos, 2, "3", cliente);
		
		ClassVenda venda = new ClassVenda(1000 , dataHora, pratos, 2, "3", cliente);
		ClassVenda venda2 = Dados.getInstance().getListavendas().get(0);
		Dados.getInstance().getListacodigos().add(1000);
		
		assertTrue("A venda foi criada conforme os parâmetros", venda.getDataHora() == venda2.getDataHora() && venda.getCliente() == venda2.getCliente() && venda.getPagamento() == venda2.getPagamento() && venda.getPratos() == venda2.getPratos());
	}
	
	@Test
	public void testEditarVenda() {
		ClassVenda venda = new ClassVenda(1000, dataHora, pratos, 0, null, null);
		Dados.getInstance().getListavendas().add(venda);
		Dados.getInstance().getListacodigos().add(1000);
		
		FacadeMenu.editarVenda(1000, dataHora, pratos2, 0, null, null);
		
		assertNotEquals("A venda foi editada", pratos, Dados.getInstance().getListavendas().get(0).getPratos());	
	}
}
