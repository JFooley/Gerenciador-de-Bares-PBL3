package controller.CRUD.produto;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.ClassProduto;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela de editar produtos
* interna a tela de produtos
* 
* @author João Gabriel
*/
public class EditarProdutoController implements Initializable{
	
	ClassProduto produto = null;

    @FXML
    private Button ConfirmarButton;

    @FXML
    private Text Mensagem;

    @FXML
    private TextField NomeTextField;

    @FXML
    private TextField PrecoTextField;

    @FXML
    void ConfirmarEditar(ActionEvent event) {
    	String nome = NomeTextField.getText();
    	float preco = 0;
    	
    	if (produto != null) {
    	   	try {
            	if (nome == "" || PrecoTextField.getText() == "") {
            		Mensagem.setText("Preencha os campos!");
            		Mensagem.setVisible(true);
            	} else {
            		preco = Float.parseFloat(PrecoTextField.getText());
            		
                    FacadeMenu.editarProduto(produto.getCodigo(), nome, preco);
                    
                    produto = null;
                    NomeTextField.setText("");
                    PrecoTextField.setText("");
                }
        	} catch (NumberFormatException nfe) {
        		Mensagem.setText("Preço inválido! utilize . invés de ,");
        		PrecoTextField.setPromptText("Ex: 3.50 ");
        		PrecoTextField.setText("");
        		Mensagem.setVisible(true);
        	}
    	} else {
    		Mensagem.setText("Selecione um produto!");
    		Mensagem.setVisible(true);
    	}
 
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Dados.getInstance().getTemp() != null) {
			produto = (ClassProduto) Dados.getInstance().getTemp();
			
			NomeTextField.setText(produto.getNome());
			PrecoTextField.setText(Float.toString(produto.getPreco()));
		} 
		FacadeMenu.validarTFNumerico(PrecoTextField);
	}

}
