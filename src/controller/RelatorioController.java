package controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Dados;
import model.FacadeMenu;
import model.Relatorio;

/**
* Classe controller responsável por definir o funcionamento da tela interna da tab de relatórios
* 
* @author João Gabriel
*/
public class RelatorioController implements Initializable{

    @FXML
    private TextField RelatorioCategoriaTextField;

    @FXML
    private TextField RelatorioEstoqueTextField;

    @FXML
    private TextField RelatorioFornecedorTextField;

    @FXML
    private Text RelatorioMensagem;

    @FXML
    private DatePicker RelatorioPeriodoDataFim;

    @FXML
    private DatePicker RelatorioPeriodoDataUm;

    @FXML
    private TextField RelatorioProdutoTextField;

    @FXML
    private Button Relatório1Button;

    @FXML
    private Button Relatório2Button;

    @FXML
    private Button Relatório3Button;

    @FXML
    private Button Relatório4Button;

    @FXML
    private Button Relatório5Button;

    @FXML
    private Button Relatório6Button;

    @FXML
    private Button Relatório7Button;

    @FXML
    private Button Relatório8Button;

    @FXML
    void RelatorioTotalEstoque(ActionEvent event) {
    	Relatorio.stockTotal(Dados.getInstance().getListaprodutos());
    	RelatorioMensagem.setText("Relatório de produtos no estoque gerado!");
    	RelatorioMensagem.setVisible(true);
    }

    @FXML
    void RelatorioProdutoEspecifico(ActionEvent event) {
    	try {
    		int codigo = Integer.parseInt(RelatorioEstoqueTextField.getText());
    		boolean check = Relatorio.stockProduto(Dados.getInstance().getListaprodutos(), codigo);
    		if (check == false) {
    	    	RelatorioMensagem.setText("Produto inexistente!");
    	    	RelatorioMensagem.setVisible(true);
    		} else {
    	    	RelatorioMensagem.setText("Relatório do produto gerado!");
    	    	RelatorioMensagem.setVisible(true);
    		}
    	} catch (NumberFormatException nfe) {
    		RelatorioMensagem.setText("Digite um código válido!");
    		RelatorioMensagem.setVisible(true);
    	}
    }

    @FXML
    void RelatorioProximoVencer(ActionEvent event) {
    	Relatorio.stockVencimento(Dados.getInstance().getListaprodutos());
    	RelatorioMensagem.setText("Relatório do produtos proximos a vencer (1 semana) gerado!");
    	RelatorioMensagem.setVisible(true);
    }
    
    @FXML
    void RelatorioFornecedor(ActionEvent event) {
    	try {
        	int codigo = Integer.parseInt(RelatorioFornecedorTextField.getText());
    		boolean check = Relatorio.fornecedorFornecedor(Dados.getInstance().getListaprodutos(), Dados.getInstance().getListafornecedores(), codigo);
    		if (check == false) {
    	    	RelatorioMensagem.setText("Fornecedor inexistente!");
    	    	RelatorioMensagem.setVisible(true);
    		} else {
    	    	RelatorioMensagem.setText("Relatório de produtos do fornecedor gerado!");
    	    	RelatorioMensagem.setVisible(true);
    		}
    	} catch (NumberFormatException nfe) {
    		RelatorioMensagem.setText("Digite um código válido!");
    		RelatorioMensagem.setVisible(true);
    	}
    }

    @FXML
    void RelatorioProduto(ActionEvent event) {
    	try {
        	int codigo = Integer.parseInt(RelatorioProdutoTextField.getText());
    		boolean check = Relatorio.fornecedorProduto(Dados.getInstance().getListaprodutos(), Dados.getInstance().getListafornecedores(), codigo);
    		if (check == false) {
    	    	RelatorioMensagem.setText("Produto inexistente!");
    	    	RelatorioMensagem.setVisible(true);
    		} else {
    	    	RelatorioMensagem.setText("Relatório dos fornecedores do produto gerado!");
    	    	RelatorioMensagem.setVisible(true);
    		}
    	} catch (NumberFormatException nfe) {
    		RelatorioMensagem.setText("Digite um código válido!");
    		RelatorioMensagem.setVisible(true);
    	}
    }
    
    @FXML
    void RelatorioVendas(ActionEvent event) {
    	Relatorio.vendasGeral(Dados.getInstance().getListavendas());
    	RelatorioMensagem.setText("Relatório das vendas realizadas gerado!");
    	RelatorioMensagem.setVisible(true);
    }

    @FXML
    void RelatorioPeriodo(ActionEvent event) {
    	try {   		
	    	String DataUm = RelatorioPeriodoDataUm.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    	String DataFim = RelatorioPeriodoDataFim.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
			Date dataInicial = sdf.parse(DataUm);
			Date dataFinal = sdf.parse(DataFim);
			
			boolean check = Relatorio.vendasPeriodo(Dados.getInstance().getListavendas(), dataInicial, dataFinal);
			
			if (check == true) {
				RelatorioMensagem.setText("Relatorio das vendas do periodo gerado!");
				RelatorioMensagem.setVisible(true);
			} else {
				RelatorioMensagem.setText("Erro ao gerar o relatório!");
				RelatorioMensagem.setVisible(true);
			}
		} catch (ParseException e) {
			RelatorioMensagem.setText("Data inválida!");
			RelatorioMensagem.setVisible(true);
		} catch (NullPointerException npe) {
			RelatorioMensagem.setText("Insira as duas datas!");
			RelatorioMensagem.setVisible(true);
		} catch (DateTimeParseException dtpe) {
			RelatorioMensagem.setText("Data inválida!");
			RelatorioMensagem.setVisible(true);
		} catch (DateTimeException dte) {
			RelatorioMensagem.setText("Data inválida!");
			RelatorioMensagem.setVisible(true);
		}
    }

    @FXML
    void RelatorioCategoriaProduto(ActionEvent event) {
    	try {
        	String categoria = RelatorioCategoriaTextField.getText();
        	if (categoria.isEmpty()) {
    	    	RelatorioMensagem.setText("Digite uma categoria!");
    	    	RelatorioMensagem.setVisible(true);
        	} else {
        		boolean check = Relatorio.vendasProduto(Dados.getInstance().getListavendas(), categoria);
        		if (check == false) {
        	    	RelatorioMensagem.setText("Erro ao gerar o relatório!");
        	    	RelatorioMensagem.setVisible(true);
        		} else {
        	    	RelatorioMensagem.setText("Relatório das vendas dos pratos da categoria gerado!");
        	    	RelatorioMensagem.setVisible(true);
        		}
        	}
    	} catch (NumberFormatException nfe) {
    		RelatorioMensagem.setText("Digite um código válido!");
    		RelatorioMensagem.setVisible(true);
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FacadeMenu.validarTFNumerico(RelatorioEstoqueTextField); 
		FacadeMenu.validarTFNumerico(RelatorioFornecedorTextField);
		FacadeMenu.validarTFNumerico(RelatorioProdutoTextField);
	}
}

