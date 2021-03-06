package presentacion;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 * Clase que representa todos los eventos internos de la aplicacion.
 */
public enum Events {
	
	/**
	 * Los eventos CRUD_ estan relacionados con el resultado de  operaciones
	 * basicas Create, Read / ReadAll, Delete y Update.
	 * 
	 * Los eventos SHOW_ son eventos creados para mostrar una ventana.
	 * 
	 * Los eventos COMMAND_ se utilizan para invocar operaciones de negocio,
	 * su resultado es mostrado al usuario mostrandole a este un mensaje en
	 * la interfaz correspondiente.
	 */
	
	CRUD_CREATE_USUARIO_OK,
	CRUD_CREATE_PREGUNTA_OK,
	CRUD_CREATE_RESPUESTA_OK,
	CRUD_CREATE_ASIGNATURA_OK,
	
	CRUD_CREATE_USUARIO_KO,
	CRUD_CREATE_PREGUNTA_KO,
	CRUD_CREATE_RESPUESTA_KO,
	CRUD_CREATE_ASIGNATURA_KO,
	
	CRUD_READ_USUARIO_OK,
	CRUD_READ_PREGUNTA_OK,
	CRUD_READ_RESPUESTA_OK,
	CRUD_READ_ASIGNATURA_OK,
	
	CRUD_READ_USUARIO_KO,
	CRUD_READ_PREGUNTA_KO,
	CRUD_READ_RESPUESTA_KO,
	CRUD_READ_ASIGNATURA_KO,
	
	CRUD_READ_ALL_USUARIO_OK,
	CRUD_READ_ALL_PREGUNTA_OK,
	CRUD_READ_ALL_RESPUESTA_OK,
	CRUD_READ_ALL_ASIGNATURA_OK,
	
	CRUD_READ_ALL_USUARIO_KO,
	CRUD_READ_ALL_PREGUNTA_KO,
	CRUD_READ_ALL_RESPUESTA_KO,
	CRUD_READ_ALL_ASIGNATURA_KO,
	
	CRUD_UPDATE_USUARIO_OK,
	CRUD_UPDATE_PREGUNTA_OK,
	CRUD_UPDATE_RESPUESTA_OK,
	CRUD_UPDATE_ASIGNATURA_OK,
	
	CRUD_UPDATE_USUARIO_KO,
	CRUD_UPDATE_PREGUNTA_KO,
	CRUD_UPDATE_RESPUESTA_KO,
	CRUD_UPDATE_ASIGNATURA_KO,
	
	CRUD_DELETE_USUARIO_OK,
	CRUD_DELETE_PREGUNTA_OK,
	CRUD_DELETE_RESPUESTA_OK,
	CRUD_DELETE_ASIGNATURA_OK,
	
	CRUD_DELETE_USUARIO_KO,
	CRUD_DELETE_PREGUNTA_KO,
	CRUD_DELETE_RESPUESTA_KO,
	CRUD_DELETE_ASIGNATURA_KO,
	
	SHOW_LOGIN,
	SHOW_ROL_MENU,

	SHOW_ASIGNATURA_ACTIVATE_DESACTIVATE,
	SHOW_MENU_PROFESORES_PREGUNTAS,
	SHOW_ASIGNATURA_DELETE,
	SHOW_ASIGNATURA_CREATE,
	SHOW_PREGUNTA_CREATE,

	SHOW_SELECCION_USUARIO,
	
	SHOW_MENU_PROFESORES_ASIGNATURAS,

	SHOW_PREGUNTA_DELETE,

	SHOW_ASIGNATURA_READ_ALL,
	
	COMMAND_USER_LOGIN,

	COMMAND_ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE,
	
	COMMAND_ASIGNATURA_LISTAR,
	
	ASIGNATURA_ACTIVATE_OK,
	ASIGNATURA_ACTIVATE_KO,
	ASIGNATURA_DESACTIVATE_OK,
	ASIGNATURA_DESACTIVATE_KO,

	ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE_OK,
	ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE_KO,
	
	WRONG_TYPE_PARAMETER,
	NO_ENTITY,
	ENTITY_NOT_ACTIVE,
	ENTITY_WITH_DEPENDENCIES, 
	COMMAND_ASIGNATURA_DELETE, 
	COMMAND_CREATE_SUBJECT, 
	COMMAND_PREGUNTA_CREATE, 
	COMMAND_ASIGNATURA_ACTIVATE, 
	COMMAND_ASIGNATURA_DESACTIVATE,
	
	COMMAND_PREGUNTA_DELETE,

	;

	private Filter filter;
	private InputSource inputSource;
	
	private Events() {
		this.filter = new Filter();
		this.inputSource = new InputSource("Events.xml");
	}
	
	/**
	 * @param filter Filtro que se aplica al mensaje del evento actual.
	 * @return Devuelve el evento actual con el filtro asignado.
	 */
	public Events setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}
	
	/**
	 * @return Devuelve el mensaje asociado a un evento concreto.
	 */
	public String getMessage() {

		String message = null;
		
		try {
		
			XPathFactory xPathFactory = XPathFactory.newInstance();
			
			XPath xpath = xPathFactory.newXPath();
	        
			Node element = (Node) xpath.evaluate("//*[@id='"+ this.name() +"'][1]",inputSource,XPathConstants.NODE);
			
			message = element.getTextContent().trim();
			
			message = filter.filter(message); 
			
		} catch (XPathExpressionException e) {
			
			e.printStackTrace();
		
		}
		
		return message;
		
	}
	
}
