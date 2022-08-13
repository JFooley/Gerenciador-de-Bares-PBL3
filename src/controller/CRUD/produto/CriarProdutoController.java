package controller.CRUD.produto;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela de criar produtos
* interna a tela de produtos
* 
* @author João Gabriel
*/
public class CriarProdutoController implements Initializable{

    @FXML
    private Button ConfirmarButton;

    @FXML
    private Text Mensagem;

    @FXML
    private TextField NomeTextField;

    @FXML
    private TextField PrecoTextField;

    @FXML
    void ConfirmarCriar(ActionEvent event) {
    	String nome = NomeTextField.getText();
    	float preco = 0;
    	
    	try {
        	if (nome == "" || PrecoTextField.getText() == "") {
        		Mensagem.setText("Preencha os campos!");
        		Mensagem.setVisible(true);
        	} else {
        		preco = Float.parseFloat(PrecoTextField.getText());
        		
                FacadeMenu.criarProduto(nome, preco);
                
                NomeTextField.setText("");
                PrecoTextField.setText("");
        	}
    	} catch (NumberFormatException nfe) {
    		Mensagem.setText("Preço inválido! utilize . invés de ,");
    		Mensagem.setVisible(true);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FacadeMenu.validarTFNumerico(PrecoTextField);
	}

}
