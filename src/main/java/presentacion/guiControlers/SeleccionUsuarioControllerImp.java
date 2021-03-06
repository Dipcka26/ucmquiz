package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;


public class SeleccionUsuarioControllerImp extends SeleccionUsuarioController {

	@FXML
    private StackPane root;
	
	@FXML
    private JFXButton btnprofesor;
	
/*    @FXML
    private JFXButton btnAdministrador;

    @FXML
    private JFXButton btnAlumno;
*/
    @FXML
    void btnAdministradorListener(ActionEvent event) {

    }

    @FXML
    void btnAlumnoListener(ActionEvent event) {

    }

    @FXML
    void btnprofesorListener(ActionEvent event) {
    	
    	Stage stage = (Stage) root.getScene().getWindow();
    	
    	stage.close();
    	
    	Contexto contexto = new Contexto(Events.SHOW_MENU_PROFESORES_PREGUNTAS,null);
		Controlador.getInstance().accion(contexto);
		
    	
    }

	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub
		
	}

}
