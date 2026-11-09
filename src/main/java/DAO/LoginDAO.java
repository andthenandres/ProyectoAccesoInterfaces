package DAO;

import Entity.Programador;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import util.ConexionBD;

public class LoginDAO {

    static ConexionBD conexionBD;


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
