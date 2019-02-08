package dao;

import model.Cliente;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao extends ModelDao{

    public ClienteDao() throws Exception {
    }

    public int inserirCliente(Cliente cliente) throws Exception {
        try {
           prepararSQL(
                   "INSERT cliente VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
           );
           getPs().setString(1, cliente.getNome());
           getPs().setString(2, cliente.getSexo());
           getPs().setDate(3, Date.valueOf(cliente.getNascimento()));
           getPs().setString(4, cliente.getEstadoCivil());
           getPs().setInt(5,cliente.getNaturalidade().getId());
           getPs().setInt(6, cliente.getNacionalidade().getId());
           if( ! executarSQL() ) {
               return mostrarIdGerado();
           } else {
               throw new Exception("Erro ao tentar inserir cliente");
           }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
    }

    public void alterarCliente() {

    }

    public void deletarCliente() {

    }

    public Cliente pesquisarUm() throws Exception {

        try{

            Cliente c = new Cliente();

            return c;

        } catch (Exception ex) {
           throw new Exception(ex);
        }

    }

    public List<Cliente> pesquisarTodos() throws Exception {
        try {
            List<Cliente> lista = new ArrayList<>();
            return lista;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }


}
