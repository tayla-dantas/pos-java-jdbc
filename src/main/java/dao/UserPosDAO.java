package dao;

import conexaojdbc.SingleConnection;
import model.Userposjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPosDAO {

    private Connection connection;

    public UserPosDAO() {
        connection = SingleConnection.getConnection();
    }
    public void salvar(Userposjava userposjava){
        String sql = "insert into userposjava (nome, email) values(?,?)";
        try {
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, userposjava.getNome());
            insert.setString(2, userposjava.getEmail());
            insert.execute();
            connection.commit(); //salva no banco
        } catch (SQLException throwables) {
            try {
                connection.rollback(); //reverte a operação
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }
    public List<Userposjava>  listar() throws SQLException {
        List<Userposjava> list = new ArrayList<Userposjava>();
        String sql = "select * from userposjava";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();
        while (resultado.next()){
            Userposjava userposjava = new Userposjava();
            userposjava.setId(resultado.getLong("id"));
            userposjava.setNome(resultado.getString("nome"));
            userposjava.setEmail(resultado.getString("email"));

            list.add(userposjava);
        }
        return list;
    }

    public Userposjava buscar(Long id) throws SQLException {
        Userposjava retorno = new Userposjava();
        String sql = "select * from userposjava where id = " + id;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();
        while (resultado.next()){
            Userposjava userposjava = new Userposjava();
            userposjava.setId(resultado.getLong("id"));
            userposjava.setNome(resultado.getString("nome"));
            userposjava.setEmail(resultado.getString("email"));
            retorno = userposjava;
        }
        return retorno;
    }

    public void atualizar(Userposjava userposjava) throws SQLException {
        String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userposjava.getNome());
        statement.execute();
        connection.commit();
    }
    public void deletar(Long id){
        try {
            String sql = "delete from userposjava where id = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.commit();
        }catch (Exception e){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
