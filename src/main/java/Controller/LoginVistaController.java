package Controller;

import DAO.ProgramadorDAO;
import Entity.Programador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginVistaController {

    @FXML
    private TextField TFUsuario,TFContrasena;
    @FXML
    private Button BotonLogin,BotonRegistrar;



    @FXML
    public void iniciarSesion(ActionEvent event) throws IOException {

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
            if(ProgramadorDAO.loginUsuario(programador)){
                cambiarBotonLogin(event);
            }//Si no le agrego al mensaje de advertencia de la ventana emergente
            else{
                labelError += "El usuario con el que te intentas logear no existe en la base de datos, introduce correctamente sus datos o registrate con ese user";
            }

        }

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
    public void registrarse(ActionEvent event) throws IOException {
        cambiarVentana(event, "Vistas/RegistroVista.fxml");
    }
    @FXML
    public void cambiarBotonLogin(ActionEvent event) throws IOException {
        cambiarVentana(event, "Vistas/InicioVista.fxml");
    }


    private void abrirVentanaEmergente(String labelError){


        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Titulo de prueba");
        alert.setContentText(labelError);

        alert.showAndWait();
    }

    private void cambiarVentana(ActionEvent event, String ruta) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ruta));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);

        Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();


        stage.setScene(scene);
        stage.show();
    }




}
