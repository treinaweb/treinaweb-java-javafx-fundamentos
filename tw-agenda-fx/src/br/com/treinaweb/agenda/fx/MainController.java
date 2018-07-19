package br.com.treinaweb.agenda.fx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.treinaweb.agenda.entidades.Contato;
import br.com.treinaweb.agenda.repositorios.impl.ContatoRepositorio;
import br.com.treinaweb.agenda.repositorios.interfaces.AgendaRepositorio;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController implements Initializable {
	
	@FXML
	private TableView<Contato> tabelaContatos;
	@FXML
	private Button botaoInserir;
	@FXML
	private Button botaoAlterar;
	@FXML
	private Button botaoExcluir;
	@FXML
	private TextField txfNome;
	@FXML
	private TextField txfIdade;
	@FXML
	private TextField txfTelefone;
	@FXML
	private Button botaoSalvar;
	@FXML
	private Button botaoCancelar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tabelaContatos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		habilitarEdicaoAgenda(false);
//		this.tabelaContatos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contato>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Contato> observable, Contato oldValue, Contato newValue) {
//				// TODO Auto-generated method stub
//				if (newValue != null) {
//					txfNome.setText(newValue.getNome());
//					txfIdade.setText(String.valueOf(newValue.getIdade()));
//					txfTelefone.setText(newValue.getTelefone());
//				}
//			}
//		});
		this.tabelaContatos.getSelectionModel().selectedItemProperty().addListener((observador, contatoAntigo, contatoNovo) -> {
			if (contatoNovo != null) {
				txfNome.setText(contatoNovo.getNome());
				txfIdade.setText(String.valueOf(contatoNovo.getIdade()));
				txfTelefone.setText(contatoNovo.getTelefone());
			}
		});
		carregarTabelaContatos();		
	}
	
	private void carregarTabelaContatos() {
		AgendaRepositorio<Contato> repositorioContato = new ContatoRepositorio();
		List<Contato> contatos = repositorioContato.selecionar();
		if (contatos.isEmpty()) {
			Contato contato = new Contato();
			contato.setNome("TreinaWeb");
			contato.setIdade(12);
			contato.setTelefone("123456");
			contatos.add(contato);
		}
		ObservableList<Contato> contatosObservableList = FXCollections.observableArrayList(contatos);
		this.tabelaContatos.getItems().setAll(contatosObservableList);
	}
	
	private void habilitarEdicaoAgenda(Boolean edicaoEstaHabilitada) {
		this.txfNome.setDisable(!edicaoEstaHabilitada);
		this.txfIdade.setDisable(!edicaoEstaHabilitada);
		this.txfTelefone.setDisable(!edicaoEstaHabilitada);
		this.botaoSalvar.setDisable(!edicaoEstaHabilitada);
		this.botaoCancelar.setDisable(!edicaoEstaHabilitada);
		this.botaoInserir.setDisable(edicaoEstaHabilitada);
		this.botaoAlterar.setDisable(edicaoEstaHabilitada);
		this.botaoExcluir.setDisable(edicaoEstaHabilitada);
		this.tabelaContatos.setDisable(edicaoEstaHabilitada);
	}
	
}
