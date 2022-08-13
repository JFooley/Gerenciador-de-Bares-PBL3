package model;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

/**
 * Classe do padr�o de projeto Facade respons�vel por conter os m�todos que
 * realizam as opera��es CRUD do sistema al�m de m�todos auxiliares.
 * 
 * @author gabri
 */
public class FacadeMenu {
	/**
	 * Criar um usu�rio (Classusuario) com os par�metros passados e um c�digo gerado,
	 * adiciona o c�digo a lista de c�digos e a entidade criada em sua respectiva lista.
	 * 
	 * @param user		String do nome do usu�rio
	 * @param password	String da senha do usu�rio
	 * @param adm		Boleano que define se o usu�rio � gerente ou funcion�rio
	 */
	public static boolean criarUsuario(String user, String password, boolean adm) {
		int codigo = GerarCodigo(Dados.getInstance().getListacodigos()); 
		ClassUsuario usuarioProv = new ClassUsuario(codigo, user, password, adm); 
		Dados.getInstance().getListausuarios().add(usuarioProv);
		Dados.getInstance().getListacodigos().add(codigo); 
		
		return true;
	}
	
	/**
	 * Recria o usu�rio que possui o c�digo passado como par�metro utilizando
	 * as novas informa��es passadas como par�metro.
	 * 
	 * @param codigo	Inteiro �nico associado a entidade que vai ser editada.
	 * @param user		Novo nome do usu�rio que ser� editado.
	 * @param password	Nova senha do usu�rio que ser� editado.
	 * @param adm		Novo n�vel de acesso (gerente ou funcion�rio) do usu�rio editado.
	 */
	public static boolean editarUsuario(int codigo, String user, String password, boolean adm) {
		excluirUsuario(codigo);
		ClassUsuario usuarioProv = new ClassUsuario(codigo, user, password, adm); 
		Dados.getInstance().getListausuarios().add(usuarioProv);

		return true;
	}
	
	/**
	 * Remove da sua respectiva lista a entidade associada ao c�digo
	 * passado como par�metro.
	 * 
	 * @param codigo	Inteir �nico associado a entidade que vai ser removida
	 */
	public static boolean excluirUsuario(int codigo) {
		// Remove o objeto com o c�digo passado
		Dados.getInstance().getListausuarios().removeIf((ClassUsuario usuario) -> usuario.getCodigo() == codigo);
		
		// Exclui o c�digo da lista de c�digos
		ExcluirCodigo(codigo);
		
		return true;
	}
	
	/**
	 * Criar um fornecedor (ClassFornecedores) com os par�metros passados e um c�digo gerado,
	 * adiciona o c�digo a lista de c�digos e a entidade criada em sua respectiva lista.
	 * 
	 * @param cnpj		String do n�mero de CNPJ do fornecedor
	 * @param nome		String do nome do fornecedor
	 * @param endereco	String do endere�o do fornecedor
	 */
	public static boolean criarFornecedor(String cnpj, String nome, String endereco) {
		int codigo = GerarCodigo(Dados.getInstance().getListacodigos());
		ClassFornecedores fornecedorProv = new ClassFornecedores(codigo, cnpj, nome, endereco);
		Dados.getInstance().getListafornecedores().add(fornecedorProv);
		Dados.getInstance().getListacodigos().add(codigo);
		
		return true;
	}
	
	/**
	 * Recria o fornecedor que possui o c�digo passado como par�metro utilizando
	 * as novas informa��es passadas como par�metro. 
	 * 
	 * @param codigo	Inteiro �nico associado a entidade que vai ser editada.
	 * @param cnpj		Novo cnpj do fornecedor que ser� editado.
	 * @param nome		Novo nome do fornecedor que ser� editado
	 * @param endereco	Novo endere�o do fonrecedor que ser� editado.
	 */
	public static boolean editarFornecedor(int codigo, String cnpj, String nome, String endereco) {
		excluirFornecedor(codigo);
		ClassFornecedores fornecedorProv = new ClassFornecedores(codigo, cnpj, nome, endereco);
		Dados.getInstance().getListafornecedores().add(fornecedorProv);
		
		return true;
	}
	
	/**
	 * Remove da sua respectiva lista a entidade associada ao c�digo
	 * passado como par�metro.
	 * 
	 * @param codigo
	 */
	public static boolean excluirFornecedor(int codigo) {
		Dados.getInstance().getListafornecedores().removeIf((ClassFornecedores fornecedor) -> fornecedor.getCodigo() == codigo);
		
		ExcluirCodigo(codigo);
		
		return true;
	}
	
	/**
	 * Criar um produto (ClassProduto) com os par�metros passados e um c�digo gerado,
	 * adiciona o c�digo a lista de c�digos e a entidade criada em sua respectiva lista.
	 * 
	 * @param nome	String com o nome do produto
	 * @param preco Float com o pre�o do produto
	 */
	public static boolean criarProduto(String nome, float preco) {
		int codigo = GerarCodigo(Dados.getInstance().getListacodigos());
		ClassProduto produtoProv = new ClassProduto(codigo, nome, preco);
		Dados.getInstance().getListaprodutos().add(produtoProv);
		Dados.getInstance().getListacodigos().add(codigo);
		
		return true;
	}
	
	/**
	 * Recria o produto que possui o c�digo passado como par�metro utilizando
	 * as novas informa��es passadas como par�metro. 
	 * 
	 * @param codigo	Inteiro �nico associado a entidade que vai ser editada
	 * @param nome		Novo nome do produto que ser� editado
	 * @param preco		novo pre�o do produto que ser� editado
	 */
	public static boolean editarProduto(int codigo, String nome, float preco) {
		ClassProduto produtoProv = ClassProduto.buscaPorID(codigo);
		produtoProv.setNome(nome);
		produtoProv.setPreco(preco);
		
		return true;
	}
	
	/**
	 * Remove da sua respectiva lista a entidade associada ao c�digo
	 * passado como par�metro.
	 * 
	 * @param codigo
	 */
	public static boolean excluirProduto(int codigo) {
		Dados.getInstance().getListaprodutos().removeIf((ClassProduto produto) -> produto.getCodigo() == codigo);
		ExcluirCodigo(codigo);
		
		return true;
	}
	
	/**
	 * Criar um Cliente (ClassCliente) com os par�metros passados e um c�digo gerado,
	 * adiciona o c�digo a lista de c�digos e a entidade criada em sua respectiva lista.
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
	 * Recria o Cliente que possui o c�digo passado como par�metro utilizando
	 * as novas informa��es passadas como par�metro. 
	 *
	 * @param codigo	Inteiro �nico associado a entidade que vai ser editada
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
	 * Remove da sua respectiva lista a entidade associada ao c�digo
	 * passado como par�metro.
	 * 
	 * @param codigo
	 */
	public static boolean excluirCliente(int codigo) {
		Dados.getInstance().getListaclientes().removeIf((ClassCliente cliente) -> cliente.getCodigo() == codigo);
		
		ExcluirCodigo(codigo);
		return true;
	}
	
	/**
	 * Criar um prato (ClassPrato) com os par�metros passados e um c�digo gerado,
	 * adiciona o c�digo a lista de c�digos e a entidade criada em sua respectiva lista.
	 * 
	 * @param nome		String com o nome do prato
	 * @param descricao	String com a descri��o do prato
	 * @param preco		Float com o pre�o do prato
	 * @param categoria	String com a categoria do prato
	 * @param receita	ObservableList com produtos que comp�e o prato
	 */
	public static boolean criarPrato(String nome, String descricao, float preco, String categoria, ObservableList<ClassProduto> receita) {
		int codigo = GerarCodigo(Dados.getInstance().getListacodigos());
		ClassPrato pratoProv = new ClassPrato(codigo, nome, descricao, preco, categoria, receita);
		Dados.getInstance().getListapratos().add(pratoProv);
		Dados.getInstance().getListacodigos().add(codigo);
		
		return true;
	}
	
	/**
	 * Recria o prato que possui o c�digo passado como par�metro utilizando
	 * as novas informa��es passadas como par�metro. 
	 * 
	 * @param codigo	Inteiro �nico associado a entidade que vai ser editada
	 * @param nome		Novo nome do prato que vai ser editado
	 * @param descricao	Nova descri��o do prato que vai ser editado
	 * @param preco		Novo pre�o do prato que vai ser editado
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
	 * Remove da sua respectiva lista a entidade associada ao c�digo
	 * passado como par�metro.
	 * 
	 * @param codigo
	 */
	public static boolean excluirPrato(int codigo) {
		Dados.getInstance().getListapratos().removeIf((ClassPrato prato) -> prato.getCodigo() == codigo);
		ExcluirCodigo(codigo);
		
		return true;
	}
	
	/**
	 * Criar um Venda (ClassVenda) com os par�metros passados e um c�digo gerado,
	 * adiciona o c�digo a lista de c�digos e a entidade criada em sua respectiva lista.
	 * 
	 * @param dataHora		Date com a data e hora que a venda foi realizada
	 * @param pratos		ObservableList com os pratos que comp�e a venda
	 * @param precoTotal	Float com o pre�o total da venda
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
	 * Recria a venda que possui o c�digo passado como par�metro utilizando
	 * as novas informa��es passadas como par�metro. 
	 * 
	 * @param codigo		Inteiro unico associado a entidade que vai ser editada
	 * @param dataHora		Nova data e hora da venda que vai ser editada
	 * @param pratos		Novos pratos da venda que vai ser editada
	 * @param precoTotal	Novo pre�o total da venda que vai ser editada
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
	 * Remove da sua respectiva lista a entidade associada ao c�digo
	 * passado como par�metro.
	 * 
	 * @param codigo
	 */
	public static boolean excluirVenda(int codigo) {
		Dados.getInstance().getListavendas().removeIf((ClassVenda venda) -> venda.getCodigo() == codigo);
		ExcluirCodigo(codigo);
		
		return true;
	}
	
	/**
	 * Recebe uma lista de c�digos e retorna um c�digo aleat�rio inteiro de 
	 * 4 d�gitos que n�o exista na lista passada ao m�todo.
	 * 
	 * @param listaCodigos	Lista contendo todos os c�digos das entidades do programa
	 * @return codigo		Inteiro de 4 digitos que n�o existe na lista de c�digos
	 */
	public static int GerarCodigo(List<Integer> listaCodigos) {
		int size = 9000;
		int codigo = 0;
		boolean codigoExiste = true;
		Random gerador = new Random(); // Cria o objeto gerador de c�digos
		
		while (listaCodigos.size() >= size) {
			size += 100;
		}
		
		while (codigoExiste) {
			codigo = gerador.nextInt(size) + 1000; // Gera um c�digo aleat�rio de 4 digitos
			codigoExiste = listaCodigos.contains(codigo); // Verifica se o c�digo j� existe
		}
		return codigo;
	}
	
	/**
	 * Verifica se um c�digo existe em uma lista de c�digos e, caso exista, 
	 * remove ele da lista.
	 * 
	 * @param codigo		Inteiro de 4 d�gitos
	 */
	public static boolean ExcluirCodigo(int codigo) {
		int indice = 0;
		
		if (Dados.getInstance().getListacodigos().contains(codigo)) { // Verifica se o c�digo existe
			indice = Dados.getInstance().getListacodigos().indexOf(codigo); // Pega o indice do c�digo passado
			Dados.getInstance().getListacodigos().remove(indice); // Remove o c�digo do �ndice passado
		}

		return true;
	}
	
	/**
	 * Faz com que o usu�rio seja impedido de digitar caracteres que n�o sejam 
	 * numerais ou . no TextField passado como par�metros
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
