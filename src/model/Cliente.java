package model;

import dao.ClienteDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nome = new SimpleStringProperty();
    private StringProperty sexo = new SimpleStringProperty();
    private LocalDate nascimento;
    private StringProperty estadoCivil = new SimpleStringProperty();
    private Naturalidade naturalidade;
    private Nacionalidade nacionalidade;
    private Endereco endereco;
    private Etnia etnia;
    private Contato contato;
    private Religiao religiao;
    private List<Cliente> clientes = new ArrayList<>();
    private ClienteDao clienteDao;

    public Cliente() throws  Exception{
       clienteDao = new ClienteDao();
    }

    public Cliente(int id,
                String nome,
                SexoEnum sexoEnum,
                LocalDate nascimento,
                EstadoCivilEnum estadoCivilEnum,
                Naturalidade naturalidade,
                Nacionalidade nacionalidade,
                Endereco endereco,
                Etnia etnia,
                Contato contato,
                Religiao religiao){
        setId(id);
        setNome(nome);
        setSexo(sexoEnum);
        setNascimento(nascimento);
        setEstadoCivil(estadoCivilEnum);
        setNaturalidade(naturalidade);
        setNacionalidade(nacionalidade);
        setEndereco(endereco);
        setEtnia(etnia);
        setContato(contato);
        setReligiao(religiao);
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

    public void setSexo(SexoEnum sexoEnum) {
        this.sexo.set(String.valueOf(sexoEnum.getCodigo()));
    }

    public StringProperty getPropriedadeSexo() {
        return sexo;
    }

    public void setPropriedadeSexo(StringProperty sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil.get();
    }

    public void setEstadoCivil(EstadoCivilEnum estadoCivilEnum) {
        this.estadoCivil.set(String.valueOf(estadoCivilEnum.getCodigo()));
    }

    public StringProperty getPropriedadeEstadoCivil(){
        return estadoCivil;
    }

    public void setPropriedadeEstadoCivil(StringProperty estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Naturalidade getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(Naturalidade naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Nacionalidade getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(Nacionalidade nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Etnia getEtnia() {
        return etnia;
    }

    public void setEtnia(Etnia etnia) {
        this.etnia = etnia;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Religiao getReligiao() {
        return religiao;
    }

    public void setReligiao(Religiao religiao) {
        this.religiao = religiao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void cadastrarCliente() throws Exception{

    }
}
