package model;
import java.util.Date;

import javafx.collections.ObservableList;

/**
 * Classe que define os atributos e métodos dos objetos responsáveis
 * por representar as Vendas no sistema. Diferente das outras classes
 * modelo, possui dois métodos implementados.
 * 
 * @author João Gabriel
 * @see ClassEntidade
 */
public class ClassVenda extends ClassEntidade {
	// Atributos
	private Date dataHora;
	private String pagamento;
	private float precoTotal;
	private ObservableList<ClassPrato> pratos;
	private ClassCliente cliente;
	
	// Construtor
	/**
	 * Inicializa a instancia da classe associando os valores passado 
	 * como parametro aos atributos internos do objeto.
	 * 
	 * @param codigo		Inteiro de 4 digitos
	 * @param dataHora		String contendo a data e a hora da venda
	 * @param pratos		Lista de objetos ClassPrato contendo os pratos que compõe a venda
	 * @param precoTotal	Float com a soma total do valor dos pratos que compõe a venda
	 * @param pagamento		String com a forma de pagamento da venda
	 * @param cliente		Objeto ClassCliente que representa o cliente que fez a venda
	 */
	public ClassVenda(int codigo, Date dataHora, ObservableList<ClassPrato> pratos, float precoTotal, String pagamento, ClassCliente cliente) {
		super(codigo);
		this.setDataHora(dataHora);
		this.setPratos(pratos);
		this.setPrecoTotal(precoTotal);
		this.setPagamento(pagamento);
		this.setCliente(cliente);
	}
	
	// Metodo retorna o valor total dos pratos do array
	/**
	 * Soma o valor retornado pelo método getPreço dos objetos ClassPrato contidos
	 * na lista "pratos", atributo do objeto ao qual o método foi chamado.
	 */
	public void somarPratos() {
		float total = 0;
		for (ClassPrato prato : pratos) {
			total += prato.getPreco();
		}
		this.setPrecoTotal(total);
	}
	
	
	// Getters e Setters
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora2) {
		this.dataHora = dataHora2;
	}
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public float getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(float precoTotal) {
		this.precoTotal = precoTotal;
	}
	public ClassCliente getCliente() {
		return cliente;
	}
	public void setCliente(ClassCliente cliente) {
		this.cliente = cliente;
	}
	public ObservableList<ClassPrato> getPratos() {
		return pratos;
	}
	public void setPratos(ObservableList<ClassPrato> pratos) {
		this.pratos = pratos;
	}
}
