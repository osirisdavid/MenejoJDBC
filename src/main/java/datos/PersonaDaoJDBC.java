package datos;

import domain.PersonaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoJDBC implements PersonaDAO{

    private Connection conexionTransaccional;
    private static final String SQL_SELECT = 
            "SELECT p.id, p.nombre, p.apellido, p.email FROM persona p";
    private static final String SQL_INSERT = 
            "INSERT INTO persona(nombre, apellido, email) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = 
            "UPDATE persona set nombre = ?, apellido = ?, email = ? "
          + "WHERE persona.ID = ?";
    private static final String SQL_ELIMINAR = 
            "DELETE FROM persona WHERE id = ?";

    public PersonaDaoJDBC() {
    }

    public PersonaDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<PersonaDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> personas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? 
                    this.conexionTransaccional : Conexion.getConecction();
            
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String mail = rs.getString("email");
                persona = new PersonaDTO(idPersona, nombre, apellido, mail);
                personas.add(persona);
            }
        }finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return personas;
    }
    
    @Override
    public int delete(PersonaDTO persona) {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? 
                    this.conexionTransaccional : Conexion.getConecction();
            
            stmt = conn.prepareStatement(SQL_ELIMINAR);
            stmt.setInt(1, persona.getIdPersona());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    @Override
    public int insert(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? 
                    this.conexionTransaccional : Conexion.getConecction();
            
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            registros = stmt.executeUpdate();
        } finally {
            try {
                Conexion.close(stmt);
                if(this.conexionTransaccional == null){
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int update(PersonaDTO persona) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? 
                    this.conexionTransaccional : Conexion.getConecction();
            
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setInt(4, persona.getIdPersona());
            registros = stmt.executeUpdate();
        }
        
        finally {
            try {
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
