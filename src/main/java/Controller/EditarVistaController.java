package Controller;

import DAO.ProgramadorDAO;
import Entity.Programador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditarVistaController  {

    @FXML
    private TextField TFUsuario,TFContrasena;
    @FXML
    private ToggleGroup grupoGenero;
    @FXML
    private RadioButton RBHombre,RBMujer,RBPrefiero;
    @FXML
    private ComboBox CBLenguaje;
    @FXML
    private Button BotonGuardar, BotonCancelar;

    @FXML
    public void initialize() {

        CBLenguaje.getItems().addAll(
                "Java",
                "Python",
                "JavaScript"
        );

    }

    public void guardarCambios(ActionEvent event){
        boolean error = false;
        String labelError = "";
        String usuario = TFUsuario.getText();
        String contrasena = TFContrasena.getText();
        ToggleGroup genero = (ToggleGroup)grupoGenero.getSelectedToggle();
        String lenguaje = CBLenguaje.getItems().toString();
        char g = ' ';
        labelError = comprobarVacio(usuario,contrasena,genero,lenguaje);



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
                g = 'H';
            } else if (RBMujer.isSelected()) {
                g = 'M';
            } else if (RBPrefiero.isSelected()) {
                g = 'P';
            }

            Programador programador = new Programador(usuario,contrasena,g,lenguaje);

            ProgramadorDAO.registrarUsuario(programador);
        }
        else{
            cancelarCambios(TFUsuario, TFContrasena);
            return;
        }


    }
    //Funcion auxiliar para borrar campos en  el caso de que no pase las validaciones
    public static void cancelarCambios(TextField usuario, TextField contrasena){
        usuario.clear();
        contrasena.clear();

    }

    //funciones auxiliares
    public String comprobarVacio(String usuario, String contrasena,ToggleGroup genero, String lenguaje) {
        String labelError ="";
        if(usuario.isEmpty()) {
            labelError += "El campo del usuario esta vacio";
        }
        if(contrasena.isEmpty()){
            labelError += "El campo de la contraseña esta vacio";

        }
        if(genero.toString().isEmpty()){
            labelError += "Escoge una de las opciones de genero";
        }
        if(lenguaje.isEmpty()) {
            labelError += "Selecciona una de las opciones del lenguaje";
        }


        //Si no hay error lo devuelvo tal y como estaba
        return labelError;
    }


}
