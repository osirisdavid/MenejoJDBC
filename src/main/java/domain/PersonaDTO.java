package domain;

public class PersonaDTO {
    private int idPersona;
    private String nombre, apellido, email;
    
    public PersonaDTO(){
    }
    
    public PersonaDTO(int idPersona){
        this.idPersona = idPersona;
    }
    public PersonaDTO(String nombre, String apellido, String mail){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = mail;
    }
    public PersonaDTO(int id, String nombre, String apellido, String mail){
        this.idPersona = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = mail;
    }
    public String getNombre() {return this.nombre;}
    
    public void setNombre(String nombre) {this.nombre = nombre;}
    
    public String getApellido() {return this.apellido;}
    
    public void setApellido(String apellido) {this.apellido = apellido;}
    
    public int getIdPersona() {return this.idPersona;}
    
    public void setIdPersona(int idPersona) {this.idPersona = idPersona;}
    
    public String getEmail() {return this.email;}
   
    public void setMail(String mail) {this.email = mail;}

    @Override
    public String toString() {
        return "Persona{" + "IdPersona=" + idPersona + ", nombre=" + 
                nombre + ", apellido=" + apellido + ", mail=" + email + '}';}
}
