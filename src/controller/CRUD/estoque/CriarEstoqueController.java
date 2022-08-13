package controller.CRUD.estoque;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.ClassFornecedores;
import model.ClassProduto;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela de criar unidades do estoque
* interna a tela de estoque
* 
* @author João Gabriel
*/
public class CriarEstoqueController implements Initializable{

    @FXML
    private Button ConfirmarButton;

    @FXML
    private DatePicker DataDatePicker;

    @FXML
    private TextField FornecedorTextField;

    @FXML
    private Text Mensagem;

    @FXML
    private TextField QuantidadeTextField;

    @FXML
    void ConfirmarCriar(ActionEvent event) {
		Mensagem.setText("");
		Mensagem.setVisible(false);
		
    	if (QuantidadeTextField.getText() == "" || FornecedorTextField.getText() == "" || Dados.getInstance().getEstoqueTemp() == null) {
    		Mensagem.setText("Preencha os campos!");
    		Mensagem.setVisible(true);
    		if (Dados.getInstance().getEstoqueTemp() == null) {
        		Mensagem.setText("Digite o ID do produto!");
        		Mensagem.setVisible(true);
    		}
    	} else {
    		try {
    	    	String datastr = DataDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			Date data = sdf.parse(datastr);
    			
    			float quantidade = Float.parseFloat(QuantidadeTextField.getText());
    			
    			int idFornecedor = Integer.parseInt(FornecedorTextField.getText());
    			ClassFornecedores fornecedor = ClassFornecedores.buscaPorID(idFornecedor);
    			if (fornecedor == null) {
    				throw new NullPointerException();
    			}
    			
    			int codigo = FacadeMenu.GerarCodigo(Dados.getInstance().getListacodigos());
    			
    			ClassProduto produto = ClassProduto.buscaPorID(Dados.getInstance().getEstoqueTemp().getCodigo());
    			produto.adicionaStock(codigo, data, fornecedor, quantidade);
    			produto.sortStock();
    			produto.updateQuantidade();
    			
    		} catch (ParseException e) {
        		Mensagem.setText("Data inválida!");
        		Mensagem.setVisible(true);
    		} catch (DateTimeParseException dtpe) {
        		Mensagem.setText("Data inválida!");
        		Mensagem.setVisible(true);
    		} catch (NumberFormatException nfe) {
        		Mensagem.setText("Número inválido!");
        		Mensagem.setVisible(true);
    		} catch (NullPointerException npe) {
        		Mensagem.setText("Fornecedor inexistente!");
        		Mensagem.setVisible(true);
    		}
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FacadeMenu.validarTFNumerico(QuantidadeTextField);		
	}

}
