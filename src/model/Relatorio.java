package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

/**
 * Classe respons�vel por gerar arquivos PDF contendo relat�rios
 * parciais do conte�do do sistema ordenado e filtrado de forma 
 * espec�fica. 
 * @author Jo�o Gabriel
 */
public class Relatorio {
	/**
	 * Cria uma tabela com um t�tulo centralizado e N colunas de acordo
	 * com a quantidade passada como par�metro.
	 * 
	 * @param colunas	N�mero de colunas da tabela
	 * @param titulo	String contendo o t�tulo da tabela
	 * @return tabela	Tabela de N colunas do tipo PdfPTable
	 */
	public static PdfPTable gerarTabela(int colunas, String titulo) {
		PdfPTable tabela = new PdfPTable(colunas);
		
		// Gera e adiciona uma celula para o titulo
		Font fonte = new Font(FontFamily.COURIER, 12, Font.NORMAL);
		Paragraph titleText = new Paragraph(titulo, fonte);
		PdfPCell titleCell = new PdfPCell(titleText);
		titleCell.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
		titleCell.setColspan(colunas);
		tabela.addCell(titleCell);
				
		return tabela; 
	}
	
	/**
	 * Gera um arquivo PDF na raiz do projeto com uma formata��o espec�fica
	 * e contendo uma tabela de conteudo q � passada como par�metro.
	 * 
	 * @param conteudo		Tabela do tipo PdfPTable com o conte�do a ser inserido no PDF
	 * @param nomeArquivo	Nome que ser� dado ao PDF criado
	 * @throws DocumentException	
	 * @throws IOException
	 */
	public static void gerarPDF(PdfPTable conteudo, String nomeArquivo) throws DocumentException, IOException {
		// Cria e formata o documento
		Document documento = new Document(PageSize.A4, 10, 10, 10, 10);
		PdfWriter.getInstance(documento, new FileOutputStream(nomeArquivo));
		documento.open();
		
		// Cria padr�es de fonte
		Font fonte = new Font(FontFamily.COURIER, 16, Font.NORMAL);
		Font fonteBold = new Font(FontFamily.COURIER, 16, Font.NORMAL);
		
		// Titulo e sub titulo
		Paragraph titulo = new Paragraph("Gerenciamento Bares e Vendas", fonteBold);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		Paragraph subtitulo = new Paragraph("Relat�rio Parcial do Sistema", fonte);
		subtitulo.setAlignment(Paragraph.ALIGN_CENTER);
		Paragraph espaco = new Paragraph("-", fonte);
		espaco.setAlignment(Paragraph.ALIGN_CENTER);
		
		// Insere o conteudo no documento
		documento.add(titulo);
		documento.add(subtitulo);
		documento.add(espaco);
		documento.add(conteudo);
		
		// Fecha o documento
		documento.close();
	}
	
	/**
	 * Gera o relat�rio PDF mostrando todos as unidades de produto presentes
	 * no estoque.
	 * 
	 * @param listaprodutos	Lista com todos os produtos
	 */
	public static void stockTotal(List<ClassProduto> listaprodutos) {
		// Gera a tabela
		PdfPTable tabelaConteudo = Relatorio.gerarTabela(6, "Total de unidades do estoque");
		
		// Formatador para a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Linha de t�tulos
		tabelaConteudo.addCell("");
		tabelaConteudo.addCell("C�digo");
		tabelaConteudo.addCell("Nome");
		tabelaConteudo.addCell("Validade");
		tabelaConteudo.addCell("Fornecedor");
		tabelaConteudo.addCell("Quantidade");
		
		int indice = 0;
		
		// Adiciona o conte�do
		for (ClassProduto produto : listaprodutos) { // Percorre os produtos da lista
			for (ClassProduto unidade : produto.getStock()) { // Percorre as unidades do stock do produto
				indice += 1;
				// Adiciona o conteudo em ordem
				tabelaConteudo.addCell(Integer.toString(indice)); // Indice
				tabelaConteudo.addCell(Integer.toString(unidade.getCodigo())); // Codigo
				tabelaConteudo.addCell(unidade.getNome()); // Nome
				tabelaConteudo.addCell(sdf.format(unidade.getValidade())); // Validade
				tabelaConteudo.addCell(unidade.getFornecedor().getNome()); // Fornecedor
				tabelaConteudo.addCell(Float.toString(unidade.getQuantidade())); // Quantidade
			}
		}
		
		try {
			Relatorio.gerarPDF(tabelaConteudo, "RelatorioTotalEstoque.pdf");
		} catch (DocumentException de) {
			System.out.println("Erro ao criar arquivo: " + de.getMessage());
		} catch (IOException ioe) {
			System.out.println("Erro ao criar arquivo: " + ioe.getMessage());
		}
	}
	
	/**
	 * Gera o relat�rio PDF mostrando apenas as unidades do estoque de um produto
	 * especificado pelo seu c�digo (ID).
	 * 
	 * @param listaprodutos	Lista com todos os produtos
	 * @param codigo		C�digo (ID) do objeto espec�fico a ser listado
	 */
	public static boolean stockProduto(List<ClassProduto> listaprodutos, int codigo) {
		// Pega o produto especifico
		ClassProduto produto = ClassProduto.buscaPorID(codigo);
		
		if (produto == null) {
			return false;
		}
		
		// Gera a tabela
		PdfPTable tabelaConteudo = Relatorio.gerarTabela(5, "Total de unidades do estoque do produto " + produto.getNome());
		
		// Formatador para a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Linha de t�tulos
		tabelaConteudo.addCell("");
		tabelaConteudo.addCell("C�digo");
		tabelaConteudo.addCell("Validade");
		tabelaConteudo.addCell("Fornecedor");
		tabelaConteudo.addCell("Quantidade");
		
		int indice = 0;
		
		for (ClassProduto unidade : produto.getStock()) { // Percorre as unidades do stock do produto
			indice += 1;
			// Adiciona o conteudo em ordem
			tabelaConteudo.addCell(Integer.toString(indice)); // Indice
			tabelaConteudo.addCell(Integer.toString(unidade.getCodigo())); // Codigo
			tabelaConteudo.addCell(sdf.format(unidade.getValidade())); // Validade
			tabelaConteudo.addCell(unidade.getFornecedor().getNome()); // Fornecedor
			tabelaConteudo.addCell(Float.toString(unidade.getQuantidade())); // Quantidade
		}
		
		try {
			Relatorio.gerarPDF(tabelaConteudo, String.format("RelatorioEstoqueProduto(%s).pdf", produto.getNome()));
		} catch (DocumentException de) {
			return false;
		} catch (IOException ioe) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Gera o relat�rio PDF mostrando apenas os produtos do estoque que estejam 
	 * vencidas ou a 7 dias de vencer.
	 * 
	 * @param listaprodutos	Lista com todos os produtos
	 */
	public static void stockVencimento(List<ClassProduto> listaprodutos) {
		// Gera a tabela
		PdfPTable tabelaConteudo = Relatorio.gerarTabela(6, "Unidades proximas de vencer ou vencidas (nos proximos 7 dias)");
		
		// Formatador para a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Linha de t�tulos
		tabelaConteudo.addCell("");
		tabelaConteudo.addCell("C�digo");
		tabelaConteudo.addCell("Nome");
		tabelaConteudo.addCell("Validade");
		tabelaConteudo.addCell("Fornecedor");
		tabelaConteudo.addCell("Quantidade");
		
		// Pega a data atual do sistema somando 7 dias
		Calendar dataNow = Calendar.getInstance();
		dataNow.add(Calendar.DATE, 7);
		Calendar dataUnidade;
		
		int indice = 0;
		
		for (ClassProduto produto : listaprodutos) { // Percorre os produtos da lista
			for (ClassProduto unidade : produto.getStock()) { // Percorre as unidades do stock do produto
				// Transforma a data da unidade em um calendar
				dataUnidade = Calendar.getInstance();
				dataUnidade.setTime(unidade.getValidade());
				
				if (dataUnidade.before(dataNow)) {
					indice += 1;
					// Adiciona o conteudo em ordem
					tabelaConteudo.addCell(Integer.toString(indice)); // Indice
					tabelaConteudo.addCell(Integer.toString(unidade.getCodigo())); // Codigo
					tabelaConteudo.addCell(unidade.getNome()); // Nome
					tabelaConteudo.addCell(sdf.format(unidade.getValidade())); // Validade
					tabelaConteudo.addCell(unidade.getFornecedor().getNome()); // Fornecedor
					tabelaConteudo.addCell(Float.toString(unidade.getQuantidade())); // Quantidade
				}
			}
		}
		
		try {
			Relatorio.gerarPDF(tabelaConteudo, "RelatorioProdutosAVencerEstoque.pdf");
		} catch (DocumentException de) {
			System.out.println("Erro ao criar arquivo: " + de.getMessage());
		} catch (IOException ioe) {
			System.out.println("Erro ao criar arquivo: " + ioe.getMessage());
		}
		
	}
	
	/**
	 * Gera o relat�rio PDF mostrando apenas os itens de um fornecedor espec�fico
	 * 
	 * @param listaprodutos		Lista com todos os produtos
	 * @param listafornecedores	Lista com todos os fornecedores
	 * @param codigo			Codigo (ID) do fornecedor
	 */
	public static boolean fornecedorFornecedor(List<ClassProduto> listaprodutos, List<ClassFornecedores> listafornecedores, int codigo) {
		// Pega o fornecedor espec�fico
		ClassFornecedores fornecedor = ClassFornecedores.buscaPorID(codigo);
		
		if (fornecedor == null) {
			return false;
		}
		
		// Gera a tabela
		PdfPTable tabelaConteudo = Relatorio.gerarTabela(5, "Produtos fornecidos pelo fornecedor " + fornecedor.getNome());
		
		// Formatador para a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Linha de t�tulos
		tabelaConteudo.addCell("");
		tabelaConteudo.addCell("C�digo");
		tabelaConteudo.addCell("Nome");
		tabelaConteudo.addCell("Validade");
		tabelaConteudo.addCell("Quantidade");
		
		int indice = 0;
		
		for (ClassProduto produto : listaprodutos) { // Percorre os produtos da lista
			for (ClassProduto unidade : produto.getStock()) { // Percorre as unidades do stock do produto
				if (unidade.getFornecedor().getCodigo() == fornecedor.getCodigo()) { // Verifica se a unidade � do fornecedor espec�fico
					indice += 1;
					// Adiciona o conteudo em ordem
					tabelaConteudo.addCell(Integer.toString(indice)); // Indice
					tabelaConteudo.addCell(Integer.toString(unidade.getCodigo())); // Codigo
					tabelaConteudo.addCell(unidade.getNome()); // Nome
					tabelaConteudo.addCell(sdf.format(unidade.getValidade())); // Validade
					tabelaConteudo.addCell(Float.toString(unidade.getQuantidade())); // Quantidade
				}
			}
		}
		
		try {
			Relatorio.gerarPDF(tabelaConteudo, String.format("RelatorioProdutosFornecedor(%s).pdf", fornecedor.getNome()));
		} catch (DocumentException de) {
			de.printStackTrace();
			return false;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Gera o relat�rio PDF mostrando todos os fornecedores de um item espec�ficado
	 * pelo c�digo (ID)
	 *  
	 * @param listaprodutos		Lista com todos os produtos
	 * @param listafornecedores	Lista com todos os fornecedores
	 * @param codigo			C�digo (ID) do produto espec�fico
	 */
	public static boolean fornecedorProduto(List<ClassProduto> listaprodutos, List<ClassFornecedores> listafornecedores, int codigo) {
		// Pega o produto espec�fico
		ClassProduto produto = ClassProduto.buscaPorID(codigo);
		
		if (produto == null) {
			return false;
		}
		
		// Gera a tabela
		PdfPTable tabelaConteudo = Relatorio.gerarTabela(5, "Fornecedores do produto " + produto.getNome());
		
		// Linha de t�tulos
		tabelaConteudo.addCell("");
		tabelaConteudo.addCell("C�digo");
		tabelaConteudo.addCell("Nome");
		tabelaConteudo.addCell("CNPJ");
		tabelaConteudo.addCell("Endere�o");
		
		int indice = 0;
		List<Integer> fornecedores = new ArrayList<Integer>();
		
		for (ClassProduto unidade : produto.getStock()) {
			if (!fornecedores.contains(unidade.getFornecedor().getCodigo())) {
				indice += 1;
				fornecedores.add(unidade.getFornecedor().getCodigo()); // Garante que o fornecedor n�o vai ser adicionado mais de uma vez
				tabelaConteudo.addCell(Integer.toString(indice)); // Indice
				tabelaConteudo.addCell(Integer.toString(unidade.getFornecedor().getCodigo())); // C�digo
				tabelaConteudo.addCell(unidade.getFornecedor().getNome()); // Nome
				tabelaConteudo.addCell(unidade.getFornecedor().getCnpj()); // CNPJ
				tabelaConteudo.addCell(unidade.getFornecedor().getEndereco()); // Endere�o
			}
		}
		
		try {
			Relatorio.gerarPDF(tabelaConteudo, String.format("RelatorioFornecedoresDoProduto(%s).pdf", produto.getNome()));
		} catch (DocumentException de) {
			de.printStackTrace();
			return false;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Gera o relat�rio PDF mostrando todas as vendas realizadas
	 * 
	 * @param listavendas	Lista com todas as vendas
	 */
	public static void vendasGeral(List<ClassVenda> listavendas) {
		// Gera a tabela
		PdfPTable tabelaConteudo = Relatorio.gerarTabela(5, "Total de vendas realizadas");
		
		// Formatador para a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Linha de t�tulos
		tabelaConteudo.addCell("");
		tabelaConteudo.addCell("C�digo");
		tabelaConteudo.addCell("Forma de pagamento");
		tabelaConteudo.addCell("Data e Hora");
		tabelaConteudo.addCell("Valor total");
		
		int indice = 0;
		
		// Adiciona o conte�do
		for (ClassVenda venda : listavendas) {
			indice += 1;
			tabelaConteudo.addCell(Integer.toString(indice)); // �ndice
			tabelaConteudo.addCell(Integer.toString(venda.getCodigo())); // C�digo
			tabelaConteudo.addCell(venda.getPagamento()); // Forma de pagamento
			tabelaConteudo.addCell(sdf.format(venda.getDataHora())); // Data e Hora
			tabelaConteudo.addCell("R$ " + Float.toString(venda.getPrecoTotal())); // Valor
			
			// Adiciona os pratos logo abaixo
			for (ClassPrato prato : venda.getPratos()) {
				tabelaConteudo.addCell(" "); // Coluna 1 
				tabelaConteudo.addCell(prato.getNome()); // Coluna 2 - Nome do prato
				tabelaConteudo.addCell("R$ " + Float.toString(prato.getPreco())); // Coluna 3 - Pre�o do prato
				tabelaConteudo.addCell(" "); // Coluna 4 
				tabelaConteudo.addCell(" "); // Coluna 5 
			}
		}
		
		try {
			Relatorio.gerarPDF(tabelaConteudo, "RelatorioTotalVendas.pdf");
		} catch (DocumentException de) {
			System.out.println("Erro ao criar arquivo: " + de.getMessage());
		} catch (IOException ioe) {
			System.out.println("Erro ao criar arquivo: " + ioe.getMessage());
		}
	}
	
	/**
	 * Gera o relat�rio PDF monstrando apenas as vendas realizadas dentro de um periodo
	 * A-B espec�fico.
	 * 
	 * @param listavendas 	Lista com todas as vendas
	 * @param dataA			Data A do intervalo de tempo A-B
	 * @param dataB			Data B do intervalo de tempo A-B
	 */
	public static boolean vendasPeriodo(List<ClassVenda> listavendas, Date dataA, Date dataB) {
		// Gera a tabela
		PdfPTable tabelaConteudo = Relatorio.gerarTabela(5, "Total de vendas realizadas");
		
		// Formatador para a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Linha de t�tulos
		tabelaConteudo.addCell("");
		tabelaConteudo.addCell("C�digo");
		tabelaConteudo.addCell("Forma de pagamento");
		tabelaConteudo.addCell("Data e Hora");
		tabelaConteudo.addCell("Valor total");
		
		int indice = 0;
		
		// Adiciona o conte�do
		for (ClassVenda venda : listavendas) {
			if (venda.getDataHora().after(dataA) && venda.getDataHora().before(dataB)) { // Verifica se a data passada est� entre A e B
				indice += 1;
				tabelaConteudo.addCell(Integer.toString(indice)); // �ndice
				tabelaConteudo.addCell(Integer.toString(venda.getCodigo())); // C�digo
				tabelaConteudo.addCell(venda.getPagamento()); // Forma de pagamento
				tabelaConteudo.addCell(sdf.format(venda.getDataHora())); // Data e Hora
				tabelaConteudo.addCell("R$ " + Float.toString(venda.getPrecoTotal())); // Valor
				
				// Adiciona os pratos logo abaixo
				for (ClassPrato prato : venda.getPratos()) {
					tabelaConteudo.addCell(" "); // Coluna 1 
					tabelaConteudo.addCell(prato.getNome()); // Coluna 2 - Nome do prato
					tabelaConteudo.addCell("R$ " + Float.toString(prato.getPreco())); // Coluna 3 - Pre�o do prato
					tabelaConteudo.addCell(" "); // Coluna 4 
					tabelaConteudo.addCell(" "); // Coluna 5 
				}
			}
		}
		
		SimpleDateFormat PDFfmt = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Relatorio.gerarPDF(tabelaConteudo, String.format("RelatorioVendasPeriodo(%s - %s).pdf", PDFfmt.format(dataA), PDFfmt.format(dataB)));
		} catch (DocumentException de) {
			de.printStackTrace();
			return false;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * Gera o relat�rio PDF mostrando todas as vendas de um produto espec�ficado
	 * pelo c�digo (ID) passado como par�metro.
	 * 
	 * @param listavendas	Lista com todas as vendas
	 * @param tipoprato		String ques especifica a categoria do prato
	 */
	public static boolean vendasProduto(List<ClassVenda> listavendas, String tipoprato) {
		// Gera a tabela
		PdfPTable tabelaConteudo = Relatorio.gerarTabela(5, "Total de vendas realizadas");
		
		// Formatador para a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Linha de t�tulos
		tabelaConteudo.addCell("");
		tabelaConteudo.addCell("C�digo");
		tabelaConteudo.addCell("Forma de pagamento");
		tabelaConteudo.addCell("Data e Hora");
		tabelaConteudo.addCell("Valor total");
		
		int indice = 0;
		List<ClassVenda> vendas = new ArrayList<ClassVenda>();
		
		// Adiciona as vendas que possuem pratos da categoria espec�fica em uma lista auxiliar
		for (ClassVenda venda : listavendas) {
			for (ClassPrato prato : venda.getPratos()) {
				if (prato.getCategoria().equals(tipoprato)) {
					vendas.add(venda);
				}
			}
		}
		
		// Adiciona o conte�do
		for (ClassVenda venda : vendas) {
			indice += 1;
			tabelaConteudo.addCell(Integer.toString(indice)); // �ndice
			tabelaConteudo.addCell(Integer.toString(venda.getCodigo())); // C�digo
			tabelaConteudo.addCell(venda.getPagamento()); // Forma de pagamento
			tabelaConteudo.addCell(sdf.format(venda.getDataHora())); // Data e Hora
			tabelaConteudo.addCell("R$ " + Float.toString(venda.getPrecoTotal())); // Valor
			
			// Adiciona os pratos logo abaixo
			for (ClassPrato prato : venda.getPratos()) {
				tabelaConteudo.addCell(" "); // Coluna 1 
				tabelaConteudo.addCell(prato.getNome()); // Coluna 2 - Nome do prato
				tabelaConteudo.addCell("R$ " + Float.toString(prato.getPreco())); // Coluna 3 - Pre�o do prato
				tabelaConteudo.addCell(" "); // Coluna 4 
				tabelaConteudo.addCell(" "); // Coluna 5
			}
		}
		
		try {
			Relatorio.gerarPDF(tabelaConteudo, String.format("RelatorioVendasTipo(%s).pdf", tipoprato));
		} catch (DocumentException de) {
			de.printStackTrace();
			return false;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Gera um PDF da nota fiscal da venda realizada
	 * 
	 * @param venda	Objeto ClassVenda da venda que ser� gerada a nota fiscal
	 */
	public static boolean notaFiscal(ClassVenda venda) {
		// Gera a tabela
		PdfPTable tabelaConteudo = Relatorio.gerarTabela(4, String.format("Nota Fiscal do cliente %s", venda.getCliente().getNome()));
		
		// Formatador para a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Linha de t�tulos
		tabelaConteudo.addCell("C�digo");
		tabelaConteudo.addCell("Forma de pagamento");
		tabelaConteudo.addCell("Data e Hora");
		tabelaConteudo.addCell("Valor total");
		
		tabelaConteudo.addCell(Integer.toString(venda.getCodigo())); // C�digo
		tabelaConteudo.addCell(venda.getPagamento()); // Forma de pagamento
		tabelaConteudo.addCell(sdf.format(venda.getDataHora())); // Data e Hora
		tabelaConteudo.addCell("R$ " + Float.toString(venda.getPrecoTotal())); // Valor
		
		tabelaConteudo.addCell(" "); // Coluna 1 
		tabelaConteudo.addCell("Prato:"); // Coluna 2 - Nome do prato
		tabelaConteudo.addCell("Pre�o"); // Coluna 3 - Pre�o do prato
		tabelaConteudo.addCell(" "); // Coluna 4 
		
		// Adiciona os pratos logo abaixo
		for (ClassPrato prato : venda.getPratos()) {
			tabelaConteudo.addCell(" "); // Coluna 1 
			tabelaConteudo.addCell(prato.getNome()); // Coluna 2 - Nome do prato
			tabelaConteudo.addCell("R$ " + Float.toString(prato.getPreco())); // Coluna 3 - Pre�o do prato
			tabelaConteudo.addCell(" "); // Coluna 4 
		}
		
		SimpleDateFormat PDFfmt = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		
		try {
			Relatorio.gerarPDF(tabelaConteudo, String.format("NotaFiscal - %s (%s).pdf", venda.getCliente().getNome(), PDFfmt.format(venda.getDataHora())));
		} catch (DocumentException de) {
			System.out.println("Erro ao criar arquivo: " + de.getMessage());
		} catch (IOException ioe) {
			System.out.println("Erro ao criar arquivo: " + ioe.getMessage());
		}
		
		return true;
	}
}
