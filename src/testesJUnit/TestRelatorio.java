package testesJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ClassCliente;
import model.ClassFornecedores;
import model.ClassPrato;
import model.ClassProduto;
import model.ClassUsuario;
import model.ClassVenda;
import model.Dados;
import model.Relatorio;

public class TestRelatorio {
	List<ClassProduto> listaprodutos;
	List<ClassFornecedores> listafornecedores;
	List<ClassVenda> listavendas;
	List<ClassPrato> listapratos;
	List<Integer> listacodigos;
	
	ClassFornecedores fornecedor = new ClassFornecedores(9999, "", "Fornecedor A", "");
	ClassProduto produto = new ClassProduto(1000, "Produto A", 5);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date dataHora = new Date();
	
	@BeforeEach
	public void setUp() {
		listaprodutos = new ArrayList<ClassProduto>();
		listafornecedores = new ArrayList<ClassFornecedores>();
		listavendas = new ArrayList<ClassVenda>();
		listapratos = new ArrayList<ClassPrato>();
		listacodigos = new ArrayList<Integer>();
		
		ClassUsuario user = new ClassUsuario(1000, "gabriel", "12345", false);
		Dados.getInstance().getListausuarios().add(user);
		
		ClassFornecedores fornecedor = new ClassFornecedores(3333, "33.343.455/23", "J&G Destribuidadora", "Av. Elvira Freitas, 824");
		Dados.getInstance().getListafornecedores().add(fornecedor);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date data = sdf.parse("01/02/2022");
			ClassProduto produtounidade = new ClassProduto(2222, "Queijo", (float) 4.50, data, fornecedor, (float) 300);
			ClassProduto produto = new ClassProduto(1111, "Queijo", (float) 4.50);
			produto.getStock().add(produtounidade);
			produto.updateQuantidade();
			Dados.getInstance().getListaprodutos().add(produto);
			ClassPrato prato = new ClassPrato(5555, "PratoTeste", "Mto bom slk", (float) 13.50, "Massas", Dados.getInstance().getListaprodutos());
			ClassPrato prato2 = new ClassPrato(6666, "PratoTeste2", "Mto ruim slk", (float) 18.50, "Massas", Dados.getInstance().getListaprodutos());
			Dados.getInstance().getListapratos().add(prato);
			Dados.getInstance().getListapratos().add(prato2);
			ClassCliente cliente = new ClassCliente(8888, "Cliente", "(75)98127-2922", "85870971560", "email123@gmail.com");
			ObservableList<ClassPrato> pratos = FXCollections.observableArrayList();
			pratos.add(prato);
			pratos.add(prato2);
			ClassVenda venda = new ClassVenda(7777, data, pratos, (float) 25.50, "PIX", cliente);
			venda.somarPratos();
			Dados.getInstance().getListavendas().add(venda);
			Dados.getInstance().getListaclientes().add(cliente);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Dados.getInstance().getListacodigos().add(1000);
		Dados.getInstance().getListacodigos().add(1111);
		Dados.getInstance().getListacodigos().add(2222);
		Dados.getInstance().getListacodigos().add(3333);
		Dados.getInstance().getListacodigos().add(4444);
		Dados.getInstance().getListacodigos().add(5555);
		Dados.getInstance().getListacodigos().add(6666);
		Dados.getInstance().getListacodigos().add(7777);
		Dados.getInstance().getListacodigos().add(8888);
	}

		
	@Test
	public void testGerarTabela3Colunas() {
		PdfPTable tabela = Relatorio.gerarTabela(3, "Tabela A");
		
		assertEquals(3, tabela.getNumberOfColumns(), "A tabela gerada possui 3 colunas");
	}
	
	@Test
	public void testGerarTabela0Colunas() {	
		assertThrows(IllegalArgumentException.class, () -> Relatorio.gerarTabela(0, "Tabela A"), "A tabela não é gerada e o erro IllegalArgumentException é disparado");
	}
	
	@Test
	public void testGerarPDFValido() throws DocumentException, IOException {
		PdfPTable tabela = Relatorio.gerarTabela(3, "Tabela A");
		
		Relatorio.gerarPDF(tabela, "RelatorioA.pdf");
		
		File documento = new File("RelatorioA.pdf");
		
		assertTrue(documento.exists(), "O arquivo PDF foi criado");
		
		File arquivoLimpeza1 = new File("RelatorioA.pdf");
		arquivoLimpeza1.delete();
	}
	
	@Test
	public void testGerarPDFNomeInvalido() {
		PdfPTable tabela = Relatorio.gerarTabela(3, "Tabela A");
				
		assertThrows(FileNotFoundException.class, () -> Relatorio.gerarPDF(tabela, "Rela/torio.pdf"));
	}
	
	@Test
	public void testGerarStockTotal() {
		Relatorio.stockTotal(Dados.getInstance().getListaprodutos());
		
		File documento = new File("RelatorioTotalEstoque.pdf");
		
		assertTrue(documento.exists(), "O arquivo PDF foi criado");
		
		File arquivoLimpeza2 = new File("RelatorioTotalEstoque.pdf");
		arquivoLimpeza2.delete();
	}
	
	@Test
	public void testGerarVendasGeral() {
		Relatorio.vendasGeral(Dados.getInstance().getListavendas());
		
		File documento = new File("RelatorioTotalVendas.pdf");
		
		assertTrue(documento.exists(), "O arquivo PDF foi criado");
		
		File arquivoLimpeza3 = new File("RelatorioTotalVendas.pdf");
		arquivoLimpeza3.delete();
	}
	
	@Test
	public void testGerarVendasDeUmaCategoria() {
		Relatorio.vendasProduto(Dados.getInstance().getListavendas(), "Massas");
		
		File documento = new File("RelatorioVendasTipo(Massas).pdf");
		
		assertTrue(documento.exists(), "O arquivo PDF foi criado");

		File arquivoLimpeza4 = new File("RelatorioVendasTipo(Massas).pdf");
		arquivoLimpeza4.delete();
	}
	
	@Test
	public void testGerarVendasPeriodoJaneiroDezembroDe2022() throws ParseException {
		Date dataA = sdf.parse("01/01/2022");
		Date dataB = sdf.parse("01/12/2022");
		
		Relatorio.vendasPeriodo(Dados.getInstance().getListavendas(), dataA, dataB);
		
		File documento = new File("RelatorioVendasPeriodo(01-01-2022 - 01-12-2022).pdf");
		
		assertTrue(documento.exists(), "O arquivo PDF foi criado");
		
		File arquivoLimpeza5 = new File("RelatorioVendasPeriodo(01-01-2022 - 01-12-2022).pdf");
		arquivoLimpeza5.delete();
	}
	
	@Test
	public void testGerarStockProdutosAVencer() {
		Relatorio.stockVencimento(Dados.getInstance().getListaprodutos());
		
		File documento = new File("RelatorioProdutosAVencerEstoque.pdf");
		
		assertTrue(documento.exists(), "O arquivo PDF foi criado");
		
		File arquivoLimpeza6 = new File("RelatorioProdutosAVencerEstoque.pdf");
		arquivoLimpeza6.delete();
	}
	
	@Test
	public void testGerarStockDeUmProduto() throws ParseException {
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
		
		listaprodutos.add(produto);
		Dados.getInstance().getListaprodutos().add(produto);
		
		Relatorio.stockProduto(listaprodutos, 1000);
		
		File documento = new File("RelatorioEstoqueProduto(Produto A).pdf");
		
		assertTrue(documento.exists(), "O arquivo PDF foi criado");
		
		File arquivoLimpeza7 = new File("RelatorioEstoqueProduto(Produto A).pdf");
		arquivoLimpeza7.delete();
	}
	
	@Test
	public void testGerarProdutosDeUmFornecedor() throws ParseException {
		dataHora = sdf.parse("01/01/2000");
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora, fornecedor, 500);
		ClassProduto unidade2 = new ClassProduto(1002, "Produto A", 5, dataHora, fornecedor, 500);
		produto.getStock().add(unidade1);
		produto.getStock().add(unidade2);

		listaprodutos.add(produto);
		listafornecedores.add(fornecedor);
		
		Dados.getInstance().getListafornecedores().add(fornecedor);
		Dados.getInstance().getListaprodutos().add(produto);
		
		Relatorio.fornecedorFornecedor(listaprodutos, listafornecedores, 9999);
		
		File documento = new File("RelatorioProdutosFornecedor(Fornecedor A).pdf");
		
		assertTrue(documento.exists(), "O arquivo PDF foi criado");
		
		File arquivoLimpeza8 = new File("RelatorioProdutosFornecedor(Fornecedor A).pdf");
		arquivoLimpeza8.delete();
	}
	
	@Test
	public void testGerarFornecedorDeUmitem() throws ParseException {
		dataHora = sdf.parse("01/01/2000");
		ClassProduto unidade1 = new ClassProduto(1001, "Produto A", 5, dataHora, fornecedor, 500);
		ClassProduto unidade2 = new ClassProduto(1002, "Produto A", 5, dataHora, fornecedor, 500);
		produto.getStock().add(unidade1);
		produto.getStock().add(unidade2);

		listaprodutos.add(produto);
		listafornecedores.add(fornecedor);
		
		Dados.getInstance().getListaprodutos().add(produto);
		Dados.getInstance().getListafornecedores().add(fornecedor);
		
		Relatorio.fornecedorProduto(listaprodutos, listafornecedores, 1000);
		
		File documento = new File("RelatorioFornecedoresDoProduto(Produto A).pdf");
		
		assertTrue(documento.exists(), "O arquivo PDF foi criado");
		
		File arquivoLimpeza9 = new File("RelatorioFornecedoresDoProduto(Produto A).pdf");
		arquivoLimpeza9.delete();
		
	}
}
