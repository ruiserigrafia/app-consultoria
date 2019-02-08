package model;

import dao.NaturalidadeDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Naturalidade {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty estado = new SimpleStringProperty();
    private StringProperty descricao = new SimpleStringProperty();
    private Nacionalidade nacionalidade;
    private List<Naturalidade> naturalidades = new ArrayList<>();
    private NaturalidadeDao naturalidadeDao;

    public Naturalidade() throws Exception {
        naturalidadeDao = new NaturalidadeDao();
    }

    public Naturalidade(int id, String estado, String descricao, Nacionalidade nacionalidade) {
        setId(id);
        setEstado(estado);
        setDescricao(descricao);
        setNacionalidade(nacionalidade);
    }

    public int getId() {
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

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public Nacionalidade getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(Nacionalidade nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public List<Naturalidade> getNaturalidades() {
        return naturalidades;
    }

    public void setNaturalidades(List<Naturalidade> naturalidades) {
        this.naturalidades = naturalidades;
    }

    public void cadastrarNacionalidade() throws Exception {
        naturalidadeDao.inserirNaturalidade(this);
    }

    public void atualizarNacionalidade() throws Exception {
        naturalidadeDao.alterarNaturalidadde(this);
    }

    public Naturalidade pesquisarPorId() throws Exception {
        if(this.getId() != 0){
            return naturalidadeDao.pesquisarUm(this);
        } else {
            throw new Exception("ID não informado");
        }
    }

    public List<Naturalidade> pesquisarNaturalidades() throws Exception {
        return naturalidadeDao.pesquisarTodos();
    }

   public int obterTotalNaturalidades() throws Exception {
        return naturalidadeDao.contarTotalNaturalidade();
   }

    public List<String> listaPorNacionalidade() {
        List<String> listaNaturalidades = new ArrayList<>();
        for (Naturalidade natural : naturalidades) {
            if(natural.getNacionalidade().getId() == this.getNacionalidade().getId()) {
                listaNaturalidades.add(natural.getDescricao());
            }
        }
        return listaNaturalidades;
    }

}
