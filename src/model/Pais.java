package model;

import dao.PaisDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Pais {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nome = new SimpleStringProperty();
    private StringProperty isp = new SimpleStringProperty();
    private IntegerProperty ddi = new SimpleIntegerProperty();
    private List<Pais> paises = new ArrayList<>();
    private PaisDao paisDao;

    public Pais() throws Exception{
        paisDao = new PaisDao();
    }

    public Pais(int id, String nome, String isp, int ddi) {
        setId(id);
        setNome(nome);
        setISP(isp);
        setDDI(ddi);
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

    public String getISP() {
        return isp.get();
    }

    public void setISP(String isp) {
        this.isp.set(isp);
    }

    public StringProperty getPropriedadeISP() {
        return isp;
    }

    public void getPropiedadeISP(StringProperty isp) {
        this.isp = isp;
    }

    public int getDDI() {
        return ddi.get();
    }

    public void setDDI(int ddi) {
        this.ddi.set(ddi);
    }

    public IntegerProperty getPropriedadeDDI() {
        return ddi;
    }

    public void setPropriedadeDDI(IntegerProperty ddi) {
        this.ddi = ddi;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public List<Pais> pesquisarPaises() throws Exception{
        return paisDao.pesquisarTodos();
    }

    public void adicionarPais() throws Exception {
        paisDao.inserirPais(this);
    }

    public void atualizarPais() throws Exception {
        paisDao.alterarPais(this);
    }

    public void excluirPais() throws Exception {
        paisDao.deletarDados(this.id.get());
    }

    public Object pesquisarPorIdPais() throws Exception {
        return paisDao.pesquisarPorid(this.getId());
    }

    public Object pesquisarPorNomePais() throws Exception {
        return paisDao.pesquisarPorNome(this.getNome());
    }

    public Object pesquisarPorISP() throws Exception {
        return  paisDao.pesquisarPorISP(this.getISP());
    }

    public Object pesquisarPorDDI() throws Exception {
        return paisDao.pesquisarPorDDI(this.getDDI());
    }

    public String pesquisarNomePorPalavra(String texto) throws Exception {
        return paisDao.procurarPorIniciais(texto);
    }

    public List<String> listaNomesPaises() {
        List<String> nomesPaises = new ArrayList<>();
        nomesPaises.clear();
        for(Pais pais : paises ) {
            nomesPaises.add(pais.getNome());
        }
        return nomesPaises;
    }

    public int obterTotalPaises() throws Exception {
        return paisDao.contarPaises();
    }

}
