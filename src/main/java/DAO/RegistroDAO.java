package DAO;

import Entity.Programador;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import util.ConexionBD;

public class RegistroDAO {

    static ConexionBD conexionBD;

    public void registrarUsuario(Programador programador){
        //creo una conexion a la DB4O
        conexionBD = new ConexionBD();

        //Creo el objectcontainer que levanta la conexion
        ObjectContainer oc = conexionBD.abrirConexion();

        //llamo a la funcion para comprobar si existe un usuario asociado con esa contraseña
        if(comprobarUsuario(programador)){
            oc.store(programador);
        }
        /*
        Si no lo era tengo que comprobar como mandar un mensaje al controller para mandar un texto de
        "Ya hay un usuario registrado con ese usuario" y pasarlo a la ventana emergente
         */
        else{

        }
    }

    //Tengo que comprobar unicamente si existe el usuario (la contraseña no hace falta) TENGO QUE CAMBIAR LA FUNCION
    public boolean comprobarUsuario(Programador programador){

        //creo una conexion a la DB4O
        conexionBD = new ConexionBD();

        //Creo el objectcontainer que levanta la conexion
        ObjectContainer oc = conexionBD.abrirConexion();


        //Creo un objectSet con el programador que me pasan por parametro para comprobar si existe antes de logearse
        ObjectSet<Programador> resultado = oc.queryByExample(programador);

        //Si da true ya existe una persona con ese login
        if(resultado.hasNext()){
            return true;
        }
        //Si no, mando un false al controller/service para indicarle que no existen usuarios para logearse
        return false;



    }
}
