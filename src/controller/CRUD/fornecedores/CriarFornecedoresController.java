package controller.CRUD.fornecedores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela de criar fornecedores
* interna na tela de fornecedores
* 
* @author João Gabriel
*/
public class CriarFornecedoresController {

    @FXML
    private TextField CNPJTextField;

    @FXML
    private Button ConfirmarButton;

    @FXML
    private Text CriarUsuarioMensagem;

    @FXML
    private TextField EnderecoTextField;

    @FXML
    private TextField NomeTextField;

    @FXML
    void ConfirmarCriar(ActionEvent event) {
    	String cnpj = CNPJTextField.getText();
    	String nome = NomeTextField.getText();
    	String endereco = EnderecoTextField.getText();
    	
    	if (nome == "" || cnpj == "" || endereco == "") {
    		CriarUsuarioMensagem.setText("Preencha os campos!");
    		CriarUsuarioMensagem.setVisible(true);
    	} else {
            FacadeMenu.criarFornecedor(cnpj, nome, endereco);
            
            NomeTextField.setText("");
            CNPJTextField.setText("");
            EnderecoTextField.setText("");
    	}
    }

}
