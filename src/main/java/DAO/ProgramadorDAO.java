package DAO;

import Entity.Programador;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import util.ConexionBD;

public class ProgramadorDAO {

    static ConexionBD conexionBD;

    //funcion para controlador de la vista
    public static boolean loginUsuario(Programador programador){



        //creo una conexion a la DB4O
        conexionBD = new ConexionBD();

        //Creo el objectcontainer que levanta la conexion
        ObjectContainer oc = conexionBD.abrirConexion();

        //Si existe un usuario en la bd4o con esa contra devolvemos true
        if(comprobarUser(programador)){
            return true;
        }
        else{
            return false;
        }


    }
    //funcion para registrarse controller
    public static void registrarUsuario(Programador programador){
        //creo una conexion a la DB4O
        conexionBD = new ConexionBD();

        //Creo el objectcontainer que levanta la conexion
        ObjectContainer oc = conexionBD.abrirConexion();

        //llamo a la funcion para comprobar si existe un usuario asociado con esa contraseña
        if(!comprobarUser(programador)){
            oc.store(programador);
        }
        /*
        Si no lo era tengo que comprobar como mandar un mensaje al controller para mandar un texto de
        "Ya hay un usuario registrado con ese usuario" y pasarlo a la ventana emergente
         */
        else{

        }
    }


    public static boolean comprobarUser(Programador programador){
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
