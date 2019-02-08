package dao;

import model.Telefone;

import java.util.List;

public abstract class TelefoneDao extends ModelDao {

    public TelefoneDao() throws Exception {
    }

    public abstract int inserirTelefone(Telefone telefone) throws Exception;

    public abstract void alterarTelefone(Telefone telefone)throws Exception;

    public abstract void excluirTelefone(int id) throws Exception;

    public abstract Telefone pesquisarPorId(int id) throws Exception;

    public abstract Telefone pesquisarPorNumero(int numero) throws Exception;

    public abstract List pesquisarTodos() throws Exception;

}
