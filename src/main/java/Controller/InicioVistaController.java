package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class InicioVistaController {
    @FXML
    private ComboBox CBLenguaje;
    @FXML
    private TableView Tabla;
    @FXML
    private Button BotonPDF,BotonExcel,BotonEditar;

    @FXML
    public void initialize() {

        CBLenguaje.getItems().addAll(
                "Java",
                "Python",
                "JavaScript"
        );

    }

    public void gestionarInicio(){
        String lenguaje = CBLenguaje.getItems().toString();
        String labelError = "";

        if(lenguaje == null){
            labelError
        }


    }

    //funcion para guardar cambios
    public void hacerPDF(){

    }
    public void hacerExcel(){

    }




    public void cancelarCambios(){




    }



}
