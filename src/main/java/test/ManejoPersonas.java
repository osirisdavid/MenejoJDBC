package test;

import datos.Conexion;
import datos.PersonaDAO;
import datos.PersonaDaoJDBC;
import domain.PersonaDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ManejoPersonas {

    public static void main(String[] args) {

        Connection conexionTransaccional = null;
        try {
            conexionTransaccional = Conexion.getConecction();
            if (conexionTransaccional.getAutoCommit()) {
                conexionTransaccional.setAutoCommit(false);
            }

            PersonaDAO personaDao = new PersonaDaoJDBC(conexionTransaccional);

            List<PersonaDTO> personas = personaDao.select();
            for (PersonaDTO person : personas) {
                System.out.println(person);
            }

            System.out.println("Commit de la Transaccion.");
            conexionTransaccional.commit();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.err.println("Entramos al Rollback");
            try {
                conexionTransaccional.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
