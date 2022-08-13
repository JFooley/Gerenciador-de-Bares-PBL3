package controller.CRUD.usuario;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.ClassUsuario;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela de editar usuarios
* interno a tela de usuarios
* 
* @author João Gabriel
*/
public class EditarUsuarioController implements Initializable{
	
	ClassUsuario usuario = null;

    @FXML
    private RadioButton ADMRadioButton;

    @FXML
    private Button ConfirmarButton;

    @FXML
    private Text CriarUsuarioMensagem;

    @FXML
    private TextField NomeTextField;

    @FXML
    private TextField SenhaPasswordField;

    @FXML
    void ConfirmarEditar(ActionEvent event) {
    	if (usuario != null) {
    		if (NomeTextField.getText() == "" || SenhaPasswordField.getText() == "") {
    			CriarUsuarioMensagem.setText("Preencha os campos!");
    			CriarUsuarioMensagem.setVisible(true);
    		} else {
            	String user = NomeTextField.getText();
                Boolean adm = ADMRadioButton.isSelected();
                String password = SenhaPasswordField.getText();
                	
                FacadeMenu.editarUsuario(usuario.getCodigo(), user, password, adm);
                Dados.getInstance().setTemp(null);
            
                NomeTextField.setText("");
                SenhaPasswordField.setText("");
                usuario = null;
    		}
    	} else {
    		CriarUsuarioMensagem.setText("Selecione o usuário!");
    		CriarUsuarioMensagem.setVisible(true);
    	}
    	

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Dados.getInstance().getTemp() != null) {
			usuario = (ClassUsuario) Dados.getInstance().getTemp();
			
			NomeTextField.setText(usuario.getUser());
			SenhaPasswordField.setText(usuario.getPassword());
			ADMRadioButton.setSelected(usuario.getAdm());
		}
	}

}
