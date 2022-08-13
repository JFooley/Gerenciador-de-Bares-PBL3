package model;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

/**
 * Classe do padrão de projeto Facade responsável por conter os métodos que
 * realizam as operações CRUD do sistema além de métodos auxiliares.
 * 
 * @author gabri
 */
public class FacadeMenu {
	/**
	 * Criar um usuário (Classusuario) com os parâmetros passados e um código gerado,
	 * adiciona o código a lista de códigos e a entidade criada em sua respectiva lista.
	 * 
	 * @param user		String do nome do usuário
	 * @param password	String da senha do usuário
	 * @param adm		Boleano que define se o usuário é gerente ou funcionário
	 */
	public static boolean criarUsuario(String user, String password, boolean adm) {
		int codigo = GerarCodigo(Dados.getInstance().getListacodigos()); 
		ClassUsuario usuarioProv = new ClassUsuario(codigo, user, password, adm); 
		Dados.getInstance().getListausuarios().add(usuarioProv);
		Dados.getInstance().getListacodigos().add(codigo); 
		
		return true;
	}
	
	/**
	 * Recria o usuário que possui o código passado como parâmetro utilizando
	 * as novas informações passadas como parâmetro.
	 * 
	 * @param codigo	Inteiro único associado a entidade que vai ser editada.
	 * @param user		Novo nome do usuário que será editado.
	 * @param password	Nova senha do usuário que será editado.
	 * @param adm		Novo nível de acesso (gerente ou funcionário) do usuário editado.
	 */
	public static boolean editarUsuario(int codigo, String user, String password, boolean adm) {
		excluirUsuario(codigo);
		ClassUsuario usuarioProv = new ClassUsuario(codigo, user, password, adm); 
		Dados.getInstance().getListausuarios().add(usuarioProv);

		return true;
	}
	
	/**
	 * Remove da sua respectiva lista a entidade associada ao código
	 * passado como parâmetro.
	 * 
	 * @param codigo	Inteir único associado a entidade que vai ser removida
	 */
	public static boolean excluirUsuario(int codigo) {
		// Remove o objeto com o código passado
		Dados.getInstance().getListausuarios().removeIf((ClassUsuario usuario) -> usuario.getCodigo() == codigo);
		
		// Exclui o código da lista de códigos
		ExcluirCodigo(codigo);
		
		return true;
	}
	
	/**
	 * Criar um fornecedor (ClassFornecedores) com os parâmetros passados e um código gerado,
	 * adiciona o código a lista de códigos e a entidade criada em sua respectiva lista.
	 * 
	 * @param cnpj		String do número de CNPJ do fornecedor
	 * @param nome		String do nome do fornecedor
	 * @param endereco	String do endereço do fornecedor
	 */
	public static boolean criarFornecedor(String cnpj, String nome, String endereco) {
		int codigo = GerarCodigo(Dados.getInstance().getListacodigos());
		ClassFornecedores fornecedorProv = new ClassFornecedores(codigo, cnpj, nome, endereco);
		Dados.getInstance().getListafornecedores().add(fornecedorProv);
		Dados.getInstance().getListacodigos().add(codigo);
		
		return true;
	}
	
	/**
	 * Recria o fornecedor que possui o código passado como parâmetro utilizando
	 * as novas informações passadas como parâmetro. 
	 * 
	 * @param codigo	Inteiro único associado a entidade que vai ser editada.
	 * @param cnpj		Novo cnpj do fornecedor que será editado.
	 * @param nome		Novo nome do fornecedor que será editado
	 * @param endereco	Novo endereço do fonrecedor que será editado.
	 */
	public static boolean editarFornecedor(int codigo, String cnpj, String nome, String endereco) {
		excluirFornecedor(codigo);
		ClassFornecedores fornecedorProv = new ClassFornecedores(codigo, cnpj, nome, endereco);
		Dados.getInstance().getListafornecedores().add(fornecedorProv);
		
		return true;
	}
	
	/**
	 * Remove da sua respectiva lista a entidade associada ao código
	 * passado como parâmetro.
	 * 
	 * @param codigo
	 */
	public static boolean excluirFornecedor(int codigo) {
		Dados.getInstance().getListafornecedores().removeIf((ClassFornecedores fornecedor) -> fornecedor.getCodigo() == codigo);
		
		ExcluirCodigo(codigo);
		
		return true;
	}
	
	/**
	 * Criar um produto (ClassProduto) com os parâmetros passados e um código gerado,
	 * adiciona o código a lista de códigos e a entidade criada em sua respectiva lista.
	 * 
	 * @param nome	String com o nome do produto
	 * @param preco Float com o preço do produto
	 */
	public static boolean criarProduto(String nome, float preco) {
		int codigo = GerarCodigo(Dados.getInstance().getListacodigos());
		ClassProduto produtoProv = new ClassProduto(codigo, nome, preco);
		Dados.getInstance().getListaprodutos().add(produtoProv);
		Dados.getInstance().getListacodigos().add(codigo);
		
		return true;
	}
	
	/**
	 * Recria o produto que possui o código passado como parâmetro utilizando
	 * as novas informações passadas como parâmetro. 
	 * 
	 * @param codigo	Inteiro único associado a entidade que vai ser editada
	 * @param nome		Novo nome do produto que será editado
	 * @param preco		novo preço do produto que será editado
	 */
	public static boolean editarProduto(int codigo, String nome, float preco) {
		ClassProduto produtoProv = ClassProduto.buscaPorID(codigo);
		produtoProv.setNome(nome);
		produtoProv.setPreco(preco);
		
		return true;
	}
	
	/**
	 * Remove da sua respectiva lista a entidade associada ao código
	 * passado como parâmetro.
	 * 
	 * @param codigo
	 */
	public static boolean excluirProduto(int codigo) {
		Dados.getInstance().getListaprodutos().removeIf((ClassProduto produto) -> produto.getCodigo() == codigo);
		ExcluirCodigo(codigo);
		
		return true;
	}
	
	/**
	 * Criar um Cliente (ClassCliente) com os parâmetros passados e um código gerado,
	 * adiciona o código a lista de códigos e a entidade criada em sua respectiva lista.
	 * 
	 * @param nome		String com o nome do cliente
	 * @param telefone	String com o telefone do cliente
	 * @param cpf		String com o CPF do cliente
	 * @param email		String com o email do cliente
	 */
	public static boolean criarCliente(String nome, String telefone, String cpf, String email) {
		int codigo = GerarCodigo(Dados.getInstance().getListacodigos());
		ClassCliente clienteProv = new ClassCliente(codigo, nome, telefone, cpf, email);
		Dados.getInstance().getListaclientes().add(clienteProv);
		Dados.getInstance().getListacodigos().add(codigo);
		
		return true;
	}
	
	/**
	 * Recria o Cliente que possui o código passado como parâmetro utilizando
	 * as novas informações passadas como parâmetro. 
	 *
	 * @param codigo	Inteiro único associado a entidade que vai ser editada
	 * @param nome		Novo nome do cliente que vai ser editado.
	 * @param telefone	Novo telefone do cliente que vai ser editado.
	 * @param cpf		Novo CPF do cliente que vai ser editado.
	 * @param email		Novo email do cliente que vai ser editado.
	 */
	public static boolean editarCliente(int codigo, String nome, String telefone, String cpf, String email) {
		excluirCliente(codigo);
		ClassCliente clienteProv = new ClassCliente(codigo, nome, telefone, cpf, email);
		Dados.getInstance().getListaclientes().add(clienteProv);
		
		return true;
	}
	
	/**
	 * Remove da sua respectiva lista a entidade associada ao código
	 * passado como parâmetro.
	 * 
	 * @param codigo
	 */
	public static boolean excluirCliente(int codigo) {
		Dados.getInstance().getListaclientes().removeIf((ClassCliente cliente) -> cliente.getCodigo() == codigo);
		
		ExcluirCodigo(codigo);
		return true;
	}
	
	/**
	 * Criar um prato (ClassPrato) com os parâmetros passados e um código gerado,
	 * adiciona o código a lista de códigos e a entidade criada em sua respectiva lista.
	 * 
	 * @param nome		String com o nome do prato
	 * @param descricao	String com a descrição do prato
	 * @param preco		Float com o preço do prato
	 * @param categoria	String com a categoria do prato
	 * @param receita	ObservableList com produtos que compõe o prato
	 */
	public static boolean criarPrato(String nome, String descricao, float preco, String categoria, ObservableList<ClassProduto> receita) {
		int codigo = GerarCodigo(Dados.getInstance().getListacodigos());
		ClassPrato pratoProv = new ClassPrato(codigo, nome, descricao, preco, categoria, receita);
		Dados.getInstance().getListapratos().add(pratoProv);
		Dados.getInstance().getListacodigos().add(codigo);
		
		return true;
	}
	
	/**
	 * Recria o prato que possui o código passado como parâmetro utilizando
	 * as novas informações passadas como parâmetro. 
	 * 
	 * @param codigo	Inteiro único associado a entidade que vai ser editada
	 * @param nome		Novo nome do prato que vai ser editado
	 * @param descricao	Nova descrição do prato que vai ser editado
	 * @param preco		Novo preço do prato que vai ser editado
	 * @param categoria	Nova categoria do prato que vai ser editado
	 * @param receita	Nova receita do prato que vai ser editado
	 */
	public static boolean editarPrato(int codigo, String nome, String descricao, float preco, String categoria, ObservableList<ClassProduto> receita) {
		excluirPrato(codigo);
		ClassPrato pratoProv = new ClassPrato(codigo, nome, descricao, preco, categoria, receita);
		Dados.getInstance().getListapratos().add(pratoProv);
		
		return true;
	}
	
	/**
	 * Remove da sua respectiva lista a entidade associada ao código
	 * passado como parâmetro.
	 * 
	 * @param codigo
	 */
	public static boolean excluirPrato(int codigo) {
		Dados.getInstance().getListapratos().removeIf((ClassPrato prato) -> prato.getCodigo() == codigo);
		ExcluirCodigo(codigo);
		
		return true;
	}
	
	/**
	 * Criar um Venda (ClassVenda) com os parâmetros passados e um código gerado,
	 * adiciona o código a lista de códigos e a entidade criada em sua respectiva lista.
	 * 
	 * @param dataHora		Date com a data e hora que a venda foi realizada
	 * @param pratos		ObservableList com os pratos que compõe a venda
	 * @param precoTotal	Float com o preço total da venda
	 * @param pagamento		String com a forma de pagamento da venda
	 * @param cliente		Objeto ClassCliente do cliente que realizou a compra
	 */
	public static boolean criarVenda(Date dataHora, ObservableList<ClassPrato> pratos, float precoTotal, String pagamento, ClassCliente cliente) {		
		int codigo = GerarCodigo(Dados.getInstance().getListacodigos());
		ClassVenda vendaProv = new ClassVenda(codigo, dataHora, pratos, precoTotal, pagamento, cliente);
		vendaProv.somarPratos();
		
		for (ClassPrato prato : pratos) {
			for (ClassProduto ingrediente : prato.getReceita()) {
				ClassProduto produto = (ClassProduto) ClassProduto.buscaPorID(ingrediente.getCodigo());
				produto.removeStock(ingrediente.getQuantidade());	
			}
		}
		
		Dados.getInstance().getListavendas().add(vendaProv);
		Dados.getInstance().getListacodigos().add(codigo);
		
		Relatorio.notaFiscal(vendaProv);
		
		return true;
	}
	
	/**
	 * Recria a venda que possui o código passado como parâmetro utilizando
	 * as novas informações passadas como parâmetro. 
	 * 
	 * @param codigo		Inteiro unico associado a entidade que vai ser editada
	 * @param dataHora		Nova data e hora da venda que vai ser editada
	 * @param pratos		Novos pratos da venda que vai ser editada
	 * @param precoTotal	Novo preço total da venda que vai ser editada
	 * @param pagamento		Nova forma de pagamento da venda que vai ser editada
	 * @param cliente		Novo cliente que realizou a venda que vai ser editada
	 */
	public static boolean editarVenda(int codigo, Date dataHora, ObservableList<ClassPrato> pratos, float precoTotal, String pagamento, ClassCliente cliente) {
		excluirVenda(codigo);
		ClassVenda vendaProv = new ClassVenda(codigo, dataHora, pratos, precoTotal, pagamento, cliente);
		vendaProv.somarPratos();
		Dados.getInstance().getListavendas().add(vendaProv);
		
		return true;
	}
	
	/**
	 * Remove da sua respectiva lista a entidade associada ao código
	 * passado como parâmetro.
	 * 
	 * @param codigo
	 */
	public static boolean excluirVenda(int codigo) {
		Dados.getInstance().getListavendas().removeIf((ClassVenda venda) -> venda.getCodigo() == codigo);
		ExcluirCodigo(codigo);
		
		return true;
	}
	
	/**
	 * Recebe uma lista de códigos e retorna um código aleatório inteiro de 
	 * 4 dígitos que não exista na lista passada ao método.
	 * 
	 * @param listaCodigos	Lista contendo todos os códigos das entidades do programa
	 * @return codigo		Inteiro de 4 digitos que não existe na lista de códigos
	 */
	public static int GerarCodigo(List<Integer> listaCodigos) {
		int size = 9000;
		int codigo = 0;
		boolean codigoExiste = true;
		Random gerador = new Random(); // Cria o objeto gerador de códigos
		
		while (listaCodigos.size() >= size) {
			size += 100;
		}
		
		while (codigoExiste) {
			codigo = gerador.nextInt(size) + 1000; // Gera um código aleatório de 4 digitos
			codigoExiste = listaCodigos.contains(codigo); // Verifica se o código já existe
		}
		return codigo;
	}
	
	/**
	 * Verifica se um código existe em uma lista de códigos e, caso exista, 
	 * remove ele da lista.
	 * 
	 * @param codigo		Inteiro de 4 dígitos
	 */
	public static boolean ExcluirCodigo(int codigo) {
		int indice = 0;
		
		if (Dados.getInstance().getListacodigos().contains(codigo)) { // Verifica se o código existe
			indice = Dados.getInstance().getListacodigos().indexOf(codigo); // Pega o indice do código passado
			Dados.getInstance().getListacodigos().remove(indice); // Remove o código do índice passado
		}

		return true;
	}
	
	/**
	 * Faz com que o usuário seja impedido de digitar caracteres que não sejam 
	 * numerais ou . no TextField passado como parâmetros
	 * @param campo	TextField que deseja aplicar a regra
	 */
	public static void validarTFNumerico(TextField campo) {
        campo.textProperty().addListener(
                (observable, valorAntigo, novoValor) -> {
                    try {
                        if (!novoValor.matches("\\d*(\\.\\d*)?")) {
                            campo.setText(valorAntigo);
                        }
                    } catch(Exception e) {
                        campo.setText(valorAntigo);
                    }
                }
        );
    }
}
