package model;

import java.util.ArrayList;
import java.util.List;

import dao.NacionalidadeDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nacionalidade {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private List<Nacionalidade> nacionalidades = new ArrayList<>();
	NacionalidadeDao dao;
	
	public Nacionalidade() throws Exception {
		dao = new NacionalidadeDao(); 
	}
	
	public Nacionalidade(Pais pais, String nome) {
		setId(pais);
		setNome(nome);
	}
	
	public int getId() {
		return id.get();
	}
	
	public void setId(Pais pais) {
		this.id = pais.getPropriedadeId();
	}
	
	public String getNome() {
		return nome.get();
	}
	
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	public StringProperty getPropriedadeNome() {
		return nome;
	}
	
	public void setPropriedadeNome(StringProperty nome) {
		this.nome = nome;
	}
	
	public List<Nacionalidade> getNacionalidades() {
		return nacionalidades;
	}
	
	public void setNacionalidades(List<Nacionalidade> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}
	
	public void adicionarNacionaliade(Nacionalidade nacionalidade) throws Exception {
		for(Nacionalidade nac: nacionalidades) {
			if(nac.getNome() == nacionalidade.getNome()) {
				throw new Exception("A " + nacionalidade + "já está cadastrado no banco de dados!");
			} else {
				dao.inserieNacionalidade(nacionalidade);
			}
		}
	}
}
