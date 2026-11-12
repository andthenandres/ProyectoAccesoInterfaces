package Controller;

import Entity.Programador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class RegistroVistaController {


    @FXML
    private TextField TFUsuario, TFContrasena;
    @FXML
    private RadioButton RBHombre,RBMujer,RBPrefiero;
    @FXML
    private ComboBox CBLenguaje;
    @FXML
    private Button BotonRegistrar,BotonVolverAlLogin;
    @FXML
    private ToggleGroup grupoGenero;

    @FXML
    public void initialize() {

        CBLenguaje.getItems().addAll(
                "Java",
                "Python",
                "JavaScript"
        );

    }


    public void registrarse(ActionEvent event) throws IOException {
        boolean error = false;
        String labelError = "";
        String lenguaje = CBLenguaje.getValue().toString();
        //Cojo los datos del fxml y le quito los espacios
        String usuario = TFUsuario.getText().trim();
        String contrasena =  TFContrasena.getText().trim();
        char genero = ' ';


        //compruebo que no se seleccione opcion en los RB
        if(grupoGenero.getSelectedToggle() == null){
            labelError += "Tienes que seleccionar una opcion de genero";
            error = true;
        }


        //valido que no se seleccione opcion en el combobox y activo el boolean para abrir la ventana emergente
        if(lenguaje == null){
            labelError += "Selecciona una opcion de los lenguajes";
            error = true;
        }


        /*
        VALIDACIONES DEL TRABAJO
         */
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

        if(!error){

            //guardo la opcion escogida de los RButtons
            if(RBHombre.isSelected()){
                genero = 'H';
            } else if (RBMujer.isSelected()) {
                genero = 'M';
            } else if (RBPrefiero.isSelected()) {
                genero = 'P';
            }

            Programador programador = new Programador();

            programador.setNombre(usuario);
            programador.setContrasena(contrasena);
            programador.setGenero(genero);
            programador.setLenguaje(lenguaje);



        }
    }



}
