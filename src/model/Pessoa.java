package model;

import ajudantes.SexoEnum;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

public abstract class Pessoa {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nome = new SimpleStringProperty();
    private StringProperty sexo = new SimpleStringProperty();
    private DatePicker nascimneto = new DatePicker();
    private StringProperty estadoCivil = new SimpleStringProperty();
    private StringProperty naturalidade = new SimpleStringProperty();
    private StringProperty nacionalidade = new SimpleStringProperty();
    private StringProperty religiao = new SimpleStringProperty();

    Pessoa() {

    }

    Pessoa(int id, String nome, String sexo, DatePicker nascimneto, String estadoCivil){
        setId(id);
        setNome(nome);
        setSexo(sexo);
        setNascimneto(nascimneto);

    }

    public int getId(){
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty getPropriedadeId() {
        return id;
    }

    public void setPropriedadeId(IntegerProperty id) {
        this.id = id;
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

    public String getSexo() {
        return sexo.get();
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public StringProperty getPropriedadeSexo() {
        return sexo;
    }

    public void setPropriedadeSexo(StringProperty sexo) {
        this.sexo = sexo;
    }

    public DatePicker getNascimneto() {
        return nascimneto;
    }

    public void setNascimneto(DatePicker nascimneto) {
        this.nascimneto = nascimneto;
    }

}
