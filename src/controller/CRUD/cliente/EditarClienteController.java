package controller.CRUD.cliente;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.ClassCliente;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela de editar clientes
* interna na tela de clientes 
* 
* @author João Gabriel
*/
public class EditarClienteController implements Initializable{

	private ClassCliente cliente = null;
	
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
    void ConfirmarEditar(ActionEvent event) {
    	if (cliente != null) {
    		if (NomeTextField.getText() == "" || TelefoneTextField.getText() == "" || CPFTextField.getText() == "" || EmailTextField.getText() == "") {
        		Mensagem.setText("Preencha os campos!");
        		Mensagem.setVisible(true);
    		} else {
	            FacadeMenu.editarCliente(cliente.getCodigo(), NomeTextField.getText(), TelefoneTextField.getText(), CPFTextField.getText(), EmailTextField.getText());
	            Dados.getInstance().setTemp(null);
	        
	            NomeTextField.setText("");
	            TelefoneTextField.setText("");
	            CPFTextField.setText("");
	            EmailTextField.setText("");
	            cliente = null;
    		}
    	} else {
    		Mensagem.setText("Selecione o cliente!");
    		Mensagem.setVisible(true);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Dados.getInstance().getTemp() != null) {
			cliente = (ClassCliente) Dados.getInstance().getTemp();
			
			NomeTextField.setText(cliente.getNome());
			TelefoneTextField.setText(cliente.getTelefone());
			CPFTextField.setText(cliente.getCpf());
			EmailTextField.setText(cliente.getEmail());
		} 
		
	}

}
