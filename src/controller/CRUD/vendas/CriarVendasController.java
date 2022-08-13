package controller.CRUD.vendas;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
import model.ClassProduto;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller respons�vel por definir o funcionamento da tela de criar venda
* interna a tela de vendas
* 
* @author Jo�o Gabriel
*/
public class CriarVendasController implements Initializable{
	
	ClassCliente cliente;

    @FXML
    private TextField ClienteTextField;

    @FXML
    private Button ContinuarButton;

    @FXML
    private Text Mensagem;

    @FXML
    private TextField PagamentoTextField;

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
            		ClienteTextField.setPromptText("Digite um cliente v�lido");
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
                        	} catch (NullPointerException npe) {
                        		Mensagem.setText("N�o h� ingredientes suficientes!");
                        		Mensagem.setVisible(true);
                        	}
                        }
                    });
               		
               		Dados.getInstance().setStageTemp(stage);
        		}
    		} catch (IOException ioe) {
    			ioe.printStackTrace();
    		} catch (NumberFormatException nfe) {
        		Mensagem.setText("Digite um c�digo v�lido!");
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
			
			// Cria um Hashmap associando o ID do ingrediente com sua quantidade total na venda
			Map<Integer,Float> receitaTotal = new HashMap<Integer,Float>();
			for (ClassPrato prato : pratos) {
				for (ClassProduto ingrediente : prato.getReceita()) {
					if (receitaTotal.containsKey(ingrediente.getCodigo())) {
						receitaTotal.put(ingrediente.getCodigo(), ingrediente.getQuantidade() + receitaTotal.get(ingrediente.getCodigo()));
					} else {
						receitaTotal.put(ingrediente.getCodigo(), ingrediente.getQuantidade());
					}
				}
			}
			
			// Cria um prato �nico para testar se h� quantidade de todos os ingredientes � suficiente
			ObservableList<ClassProduto> receita = FXCollections.observableArrayList();
			receitaTotal.forEach((key, value) -> {
				ClassProduto produto = new ClassProduto(key, "", (float) 0);
				produto.setQuantidade(value);
				receita.add(produto);
			});
			
			ClassPrato pratao = new ClassPrato(0, null, null, 0, null, receita);
			if (!pratao.checkStock()) {
				throw new NullPointerException();
			}
			
			FacadeMenu.criarVenda(dataHora, pratos, 0, pagamento, cliente);

            ClienteTextField.setText("");
            PagamentoTextField.setText("");
            Mensagem.setText("Venda registrada.");
    	} 
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FacadeMenu.validarTFNumerico(ClienteTextField);	
	}

}
