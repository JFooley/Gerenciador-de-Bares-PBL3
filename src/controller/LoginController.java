package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Login;

/**
 * Classe controller responsável por definir o funcionamento da tela de Login
 * 
 * @author João Gabriel
 */
public class LoginController implements Initializable{

    @FXML
    private Button BotaoLogar;
    
    @FXML
    private Text ErrorText;

    @FXML
    private PasswordField LoginSenha;

    @FXML
    private TextField LoginUser;

    @FXML
    void EntrarLogar(ActionEvent event) {
    	ErrorText.setVisible(false);
    	
    	// Recebe um usuario
    	String usuario = LoginUser.getText();
    	String password = LoginSenha.getText();
    	
    	Login login = Login.getInstance();
		login.autentica(usuario, password);  	
    	
    	if (login.getUsuarioAut() != null) { // Testa se um usuário foi autenticado e chama o menu principal
    		try {
    			Node node = (Node) event.getSource();
    			AnchorPane MainMenu = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/MenuPrincipalView.fxml"));
    			node.getScene().setRoot(MainMenu);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else { // Exibe mensagem de erro caso usuário e senha sejam inválidos
    		ErrorText.setText("Usuario ou senha inválidos!");
    		ErrorText.setVisible(true);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		BotaoLogar.setDefaultButton(true);
	}

    	
}
