package test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.Userposjava;
import org.junit.Test;

import java.util.List;

public class TesteBancoJdbc{

    @Test
    public void initBanco(){
        SingleConnection.getConnection();
    }

    @Test
    public void testDao(){
        UserPosDAO userPosDAO = new UserPosDAO();
        Userposjava userposjava = new Userposjava();

        userposjava.setNome("Rogerio");
        userposjava.setEmail("testando@test.com");
        userPosDAO.salvar(userposjava);
    }

    @Test
    public void initListar(){
        UserPosDAO userPosDAO = new UserPosDAO();
        try {
            List<Userposjava> list = userPosDAO.listar();
            for (Userposjava userposjava : list){
                System.out.println(userposjava);
                System.out.println("-------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void initBuscar(){
        UserPosDAO userPosDAO = new UserPosDAO();
        try {
            Userposjava userposjava  = userPosDAO.buscar(5l);

            System.out.println(userposjava);
            System.out.println("-------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initAtualizar(){
        UserPosDAO userPosDAO = new UserPosDAO();
        try {
            Userposjava userposjava  = new Userposjava();
            userposjava = userPosDAO.buscar(5l);
            userposjava.setNome("teste teste teste");
            userPosDAO.atualizar(userposjava);

            System.out.println(userposjava);
            System.out.println("-------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initDeletar(){
        try {
            UserPosDAO userPosDAO = new UserPosDAO();
            userPosDAO.deletar(4l);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
