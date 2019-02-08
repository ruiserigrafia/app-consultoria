package dao;

import bd.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class ModelDao {

    private Connection conecta;
    private PreparedStatement ps;
    private ResultSet rs;

    public ModelDao() throws Exception {
        conecta = Conexao.getConnection();
        ps = null;
        rs = null;
    }

    protected void prepararSQL(String sql) throws Exception {
        ps = conecta.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    protected boolean executarSQL() throws Exception {
        return ps.execute();
    }

    protected int mostrarIdGerado() throws Exception {
        rs = ps.getGeneratedKeys();
        return (rs.next())? Integer.parseInt(rs.getString(1)):0;
    }

    protected ResultSet executarQuerySQL() throws Exception {
        rs = ps.executeQuery();
        return rs;
    }

    protected PreparedStatement getPs() {
        return ps;
    }

    protected void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    protected ResultSet getRs() {
        return rs;
    }

    protected void setRs(ResultSet rs) {
        this.rs = rs;
    }
}
