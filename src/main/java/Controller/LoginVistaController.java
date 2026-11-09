package Controller;

import DAO.LoginDAO;
import Entity.Programador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginVistaController {

    @FXML
    private TextField TFUsuario;
    @FXML
    private TextField TFContrasena;
    @FXML
    private Button BotonLogin;
    @FXML
    private Button BotonRegistrar;



    @FXML
    public void BotonLogin(ActionEvent event) {

        //Cojo los datos del fxml y le quito los espacios
        String usuario = TFUsuario.getText().trim();
        String contrasena =  TFContrasena.getText().trim();
        //Creo el label error vacio para rellenarlo y pasar el texto de error a la ventana emergente a la ventana emergente
        String labelError = "";
        boolean error = false;

        labelError = comprobarVacio(usuario, contrasena, labelError);

        //Si esta vacio no compruebo mas y lo mando a la ventana emergente
        if(!labelError.isEmpty()) {

            error = true;
        }
        else{

            //Compruebo que el user tenga como minimo 5 caracteres
            if(usuario.length() < 5){
                labelError += "El campo del usuario tiene que tener como minimo 5 caracteres";
                error = true;
            }

            //Compruebo que la contra tenga como minimo 8 caracteres
            if(contrasena.length() < 8){
                labelError += "El campo de la contraseña tiene que tener como minimo 8 caracteres";
                error = true;
            }

            //Mediante las siguientes expresiones regulares compruebo que la contraseña tenga en el campo al menos una MAYUS y una minus
            if(!contrasena.matches(".+[A-Z].+")){
                labelError += "El campo de la contraseña debe contener al menos una mayuscula";
                error = true;
            }
            if(!contrasena.matches(".+[a-z].+")){
                labelError += "El campo de la contraseña debe contener al menos una mayuscula";
                error = true;
            }


            /*
            Si pasa todas las comprobaciones creo un objeto programador
            le seteo los datos validados, los mando al DAO para comprobar si existen en la DB4O
            y poder logear al user en la bd4o
             */
            Programador programador = new Programador();

            programador.setNombre(usuario);
            programador.setContrasena(contrasena);

            //Si devuelve true lo mandamos a la pantalla de inicio
            if(LoginDAO.loginUsuario(programador)){

            }//Si no le agrego al mensaje de advertencia de la ventana emergente
            else{
                labelError += "El usuario con el que te intentas logear no existe en la base de datos, introduce correctamente sus datos o registrate con ese user";
            }

        }

    }
    //Funcion para mandar a la pantalla de registro
    @FXML
    public void BotonRegistrar(){


    }

    //func para comprobar campos vacios
    public String comprobarVacio(String labelError, String usuario, String contrasena) {

        int vacio = 0;

        if(usuario.isEmpty()) {
            labelError += "El campo del usuario esta vacio";
            vacio ++;
        }
        if(contrasena.isEmpty()){
            labelError += "El campo de la contraseña esta vacio";
            vacio ++;

        }
        //Si da algun error se devuelve el texto para mandarselo luego a la ventana emergente
        if(vacio > 0){
            return labelError;
        }
        //Si no hay error lo devuelvo tal y como estaba
        return labelError;
    }


    @FXML
    public void BotonRegistrar(ActionEvent event) {

    }





}
