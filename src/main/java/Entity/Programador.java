package Entity;

public class Programador {


    //Atributos (creo el genero en String porque lo recojo así desde la base de datos )
    private String nombre;
    private String contrasena;
    private char genero;
    private String lenguaje;

    //Contructor vacio
    public Programador(){

    }

    public Programador(String nombre, String contrasena, char genero, String lenguaje) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.genero = genero;
        this.lenguaje = lenguaje;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public char getGenero() {
        return genero;
    }
    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getLenguaje() {
        return lenguaje;
    }
    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }






}
