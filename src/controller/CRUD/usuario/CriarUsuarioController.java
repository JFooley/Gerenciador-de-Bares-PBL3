package controller.CRUD.usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela de criar usuarios
* interna a tela de usuarios
* 
* @author João Gabriel
*/
public class CriarUsuarioController {
	
    @FXML
    private RadioButton ADMRadioButton;

    @FXML
    private Button ConfirmarButton;

    @FXML
    private Text CriarUsuarioMensagem;

    @FXML
    private TextField NomeTextField;

    @FXML
    private PasswordField SenhaPasswordField;

    @FXML
    void ConfirmarCriar(ActionEvent event) {
    	String user = NomeTextField.getText();
    	Boolean adm = ADMRadioButton.isSelected();
    	String password = SenhaPasswordField.getText();
    	
    	if (user == "" || password == "") {
    		CriarUsuarioMensagem.setText("Preencha os campos!");
    		CriarUsuarioMensagem.setVisible(true);
    	} else {
            FacadeMenu.criarUsuario(user, password, adm);    
            NomeTextField.setText("");
            SenhaPasswordField.setText("");
    	}
    }

}
