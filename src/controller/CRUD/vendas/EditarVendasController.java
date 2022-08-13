package controller.CRUD.vendas;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.ClassCliente;
import model.ClassPrato;
import model.ClassVenda;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela de editar vendas
* interna a tela de vendas
* 
* @author João Gabriel
*/
public class EditarVendasController implements Initializable{
	
	ClassCliente cliente;
	ClassVenda venda;

    @FXML
    private TextField ClienteTextField;

    @FXML
    private Button ContinuarButton;

    @FXML
    private Button ManterButton;

    @FXML
    private Text Mensagem;

    @FXML
    private TextField PagamentoTextField;

    @FXML
    void ManterButtonAction(ActionEvent event) {
    	if (ClienteTextField.getText() == "" || PagamentoTextField.getText() == "" ) {
    		Mensagem.setText("Preencha os campos!");
    		Mensagem.setVisible(true);
    	} else {
    		try {
        		int codigoCliente = Integer.parseInt(ClienteTextField.getText());
        		cliente = ClassCliente.buscaPorID(codigoCliente); 
    			
        		Dados.getInstance().setReceitaTemp(venda.getPratos());
        		
        		finalizarVenda();
    		} catch (NumberFormatException nfe) {
        		Mensagem.setText("Digite um código válido!");
        		Mensagem.setVisible(true);
    		}
    	}
    }
    
    @FXML
    void ContinuarButtonAction(ActionEvent event) {
    	if (ClienteTextField.getText() == "" || PagamentoTextField.getText() == "" ) {
    		Mensagem.setText("Preencha os campos!");
    		Mensagem.setVisible(true);
    	} else {
    		try {
        		int codigoCliente = Integer.parseInt(ClienteTextField.getText());
        		cliente = ClassCliente.buscaPorID(codigoCliente); 
    			
        		if (cliente == null) {
               		Mensagem.setText("Cliente inexistente!");
            		Mensagem.setVisible(true);
            		ClienteTextField.setText("");
            		ClienteTextField.setPromptText("Digite um cliente válido");
        		} else {
            		Stage stage = new Stage();
            		AnchorPane escolherPrato = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Vendas/EscolherVendasView.fxml"));
            		Scene cena = new Scene(escolherPrato);
            		stage.setScene(cena);
            		stage.show();      	
            		
               		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent t) {
                        	try {
                            	finalizarVenda();
                        	} catch (IndexOutOfBoundsException iobe) {
                        		Mensagem.setText("Não há ingredientes suficientes!");
                        		Mensagem.setVisible(true);
                        	}
                        }
                    });
               		
               		Dados.getInstance().setStageTemp(stage);
        		}
    		} catch (IOException ioe) {
    			ioe.printStackTrace();
    		} catch (NumberFormatException nfe) {
        		Mensagem.setText("Digite um código válido!");
        		Mensagem.setVisible(true);
    		}
    	}
    }

    public void finalizarVenda() {
    	ObservableList<ClassPrato> pratos = Dados.getInstance().getReceitaTemp();
    	
    	if (pratos != null) {
    		String pagamento = PagamentoTextField.getText();
    		
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String dataHoraAtual = fmt.format(Calendar.getInstance().getTime());
			Date dataHora = new Date();
			try {
				dataHora = fmt.parse(dataHoraAtual);
			} catch (ParseException p) {
        		Mensagem.setText("Erro ao adquirir hora!");
        		Mensagem.setVisible(true);
			}		
			
			for (ClassPrato prato : pratos) {
				if (!prato.checkStock()) {
					throw new IndexOutOfBoundsException();
				}
			}
    		
            FacadeMenu.editarVenda(venda.getCodigo(), dataHora, pratos, venda.getPrecoTotal(), pagamento, cliente);
                
            ClienteTextField.setText("");
            PagamentoTextField.setText("");
    	} 
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FacadeMenu.validarTFNumerico(ClienteTextField);	
		venda = (ClassVenda) Dados.getInstance().getTemp();
		
		ClienteTextField.setText(Integer.toString(venda.getCliente().getCodigo()));
		PagamentoTextField.setText(venda.getPagamento());
	}

}
