package dao;

import model.Nacionalidade;
import model.Naturalidade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NaturalidadeDao extends ModelDao {
    public NaturalidadeDao() throws Exception {
    }

    public void inserirNaturalidade(Naturalidade naturalidade) throws Exception {
        try {
            if(existeNaturalidade(naturalidade.getDescricao())) {
               alterarNaturalidadde(naturalidade);
            } else{
                throw new Exception("O registro de naturalidade já existe!");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
    }

    public void alterarNaturalidadde(Naturalidade naturalidade) throws Exception {
        try {
            prepararSQL(
                    "UPDATE estado SET naturaldade = ? WHERE id = ?"
            );
            getPs().setString(1, naturalidade.getDescricao());
            getPs().setInt(2, naturalidade.getId());
            executarSQL();
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
    }

    public Naturalidade pesquisarUm(Naturalidade naturalidade) throws Exception {
        try {
            prepararSQL(
                    "SELECT nome, naturalidade, pais_id FROM estado WHERE naturalidade = ?"
            );
            getPs().setString(1, naturalidade.getDescricao());
            executarQuerySQL();
            if(getRs().next()) {
                naturalidade.setEstado(getRs().getString(1));
                naturalidade.setDescricao(getRs().getString(2));
                naturalidade.getNacionalidade().setId(3);
            } else {
                throw new Exception("Erro tentar encontrar o registro de nacionalidade");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
        return naturalidade;
    }

    public List<Naturalidade> pesquisarTodos() throws Exception {
        try {
            prepararSQL(
                    "SELECT id, nome, naturalidade, pais_id FROM estado WHERE naturalidade IS NOT NULL"
            );
            executarQuerySQL();
            List<Naturalidade> naturalidades = new ArrayList<>();
            while (getRs().next()) {
                naturalidades.add(
                        new Naturalidade(
                                getRs().getInt(1),
                                getRs().getString(2),
                                getRs().getString(3),
                                new Nacionalidade(getRs().getInt(4), null, null)
                        )
                );
            }
            return naturalidades;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
    }

    public int contarTotalNaturalidade() throws Exception {
        try {
            prepararSQL(
                    "SELECT COUNT(naturalidade) FROM estado"
            );
            executarQuerySQL();
            if(getRs().next()) {
                return getRs().getInt(1);
            } else {
                throw new Exception("Erro tentar obter total de nacionalidades");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
    }

    public boolean existeNaturalidade(String naturalidade) throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM estado WHERE naturalidade = ?"
            );
            getPs().setString(1, naturalidade);
            executarQuerySQL();
            if(getRs().next()) {
                return true;
            } else {
                throw new Exception("Erro ao pesquisar por " + naturalidade);
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
    }

}
