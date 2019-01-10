package dao;

import bd.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class ModelDao {

    private Connection conecta;
    private PreparedStatement prepararInstrucao;
    private ResultSet resultados;

    public ModelDao() throws Exception {
        conecta = Conexao.getConnection();
        prepararInstrucao = null;
        resultados = null;
    }

    protected void prepararSQL(String sql) throws Exception {
        prepararInstrucao = conecta.prepareStatement(sql);
    }

    protected void executarInstrucao() throws Exception {
        prepararInstrucao.execute();
    }

    protected ResultSet consultarBanco() throws Exception {
        resultados = prepararInstrucao.executeQuery();
        return resultados;
    }


    protected PreparedStatement getPrepararInstrucao() {
        return prepararInstrucao;
    }

    public void setPrepararInstrucao(PreparedStatement prepararInstrucao) {
        this.prepararInstrucao = prepararInstrucao;
    }

    protected ResultSet getResultados() {
        return resultados;
    }

    protected void setResultados(ResultSet resultados) {
        this.resultados = resultados;
    }


    
}
