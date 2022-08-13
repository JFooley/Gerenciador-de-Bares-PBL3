package controller.CRUD.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela de criar clientes
* interna na tela de clientes
* 
* @author João Gabriel
*/
public class CriarClienteController {

    @FXML
    private TextField CPFTextField;

    @FXML
    private Button ConfirmarButton;

    @FXML
    private TextField EmailTextField;

    @FXML
    private Text Mensagem;

    @FXML
    private TextField NomeTextField;

    @FXML
    private TextField TelefoneTextField;

    @FXML
    void ConfirmarCriar(ActionEvent event) {
    	String nome = NomeTextField.getText();
    	String telefone = TelefoneTextField.getText();
    	String cpf = CPFTextField.getText();
    	String email = EmailTextField.getText();
    	
    	if (nome == "" || telefone == "" || cpf == "" || email == "") {
    		Mensagem.setText("Preencha os campos!");
    		Mensagem.setVisible(true);
    	} else {
    		FacadeMenu.criarCliente(nome, telefone, cpf, email);
    		
    		NomeTextField.setText("");
    		TelefoneTextField.setText("");
    		CPFTextField.setText("");
    		EmailTextField.setText("");
    	}
    }

}
