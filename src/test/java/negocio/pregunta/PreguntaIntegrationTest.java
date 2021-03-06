package negocio.pregunta;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import negocio.FactoriaNegocio;
import presentacion.Contexto;
import presentacion.Events;

public class PreguntaIntegrationTest {

	@Test
	public void integrationTest() {
		
		// DATA ****************************************************************************************************
		
		Integer mmiId = null;
		
		Integer mdlId = null;
		
		Integer inventedId = 999;
		
		Contexto contexto = null;
		
		Pregunta preguntaMMI = null; 
		
		Pregunta preguntaMDL = null; 
		
		FactoriaNegocio factoriaNegocio = FactoriaNegocio.getInstance();
		
		SAPregunta saPregunta = factoriaNegocio.generateSAPregunta();

		// CREATE ****************************************************************************************************
		
		assertThat("No se puede crear una pregunta nula.", saPregunta.create(preguntaMDL) , nullValue() );
		
		preguntaMDL = new Pregunta("¿ Es ( ^ , v , ¬ ) un conjunto universal ?", true);
		
		contexto = saPregunta.create(preguntaMDL);
		
		mdlId = (Integer) contexto.getDato();
		
		assertThat("Deberiamos obtener un contexto no nulo.", contexto , notNullValue() );
		
		assertThat("La pregunta se ha de registrar con un id positivo.", (int) contexto.getDato() >= 0 , is(equalTo(true)) );
		
		assertThat("La pregunta ha de devolver un evento de creacion correcto.", contexto.getEvent() , is(equalTo(Events.CRUD_CREATE_PREGUNTA_OK)) );
		
		contexto = saPregunta.create(preguntaMDL);
		
		assertThat("Deberiamos obtener un contexto no nulo.", contexto , notNullValue() );
		
		assertThat("La pregunta ya existia en la BBDD, ha de retornar un identificador nulo.", contexto.getDato() , nullValue() );
		
		assertThat("La pregunta ha de devolver un evento de creacion incorrecto.", contexto.getEvent() , is(equalTo(Events.CRUD_CREATE_PREGUNTA_KO)) );
		
		preguntaMMI = new Pregunta("¿ Sum(0 ... n) = ((n * (n+1)) / 2)  ?", false);
		
		contexto = saPregunta.create(preguntaMMI);
		
		mmiId = (Integer) contexto.getDato();
		
		assertThat("Deberiamos obtener un contexto no nulo.", contexto , notNullValue() );
		
		assertThat("La pregunta se ha de registrar con un id positivo.", (int) contexto.getDato() >= 0 , is(equalTo(true)) );
		
		assertThat("La pregunta ha de devolver un evento de creacion correcto.", contexto.getEvent() , is(equalTo(Events.CRUD_CREATE_PREGUNTA_OK)) );
		
		contexto = saPregunta.create(preguntaMMI);
		
		assertThat("Deberiamos obtener un contexto no nulo.", contexto , notNullValue() );
		
		assertThat("La pregunta se ha de reactivar y ofrecer su id.", (int) contexto.getDato() >= 0 , is(equalTo(true)) );
		
		assertThat("La pregunta ha de devolver un evento de creacion correcto.", contexto.getEvent() , is(equalTo(Events.CRUD_CREATE_PREGUNTA_OK)) );
		
		// DELETE ****************************************************************************************************
		
		contexto = saPregunta.borrarPregunta(inventedId);
		
		assertThat("No esta en la BBDD, aun asi el contexto no deberia se nulo.", contexto.getEvent() , notNullValue() );
		
		assertThat("No esta en la BBDD, el identificador deberia ser nulo.", contexto.getDato() , nullValue() );
		
		assertThat("No esta en la BBDD, el evento de borrado deberia ser negativo.", contexto.getEvent() , is(equalTo(Events.CRUD_DELETE_PREGUNTA_KO)) );
	
		contexto = saPregunta.borrarPregunta(mmiId);
		
		assertThat("El contexto no deberia se nulo.", contexto.getEvent() , notNullValue() );
		
		assertThat("El identificador deberia ser nulo.", contexto.getDato() , nullValue() );
		
		assertThat("El evento de borrado deberia ser positivo.", contexto.getEvent() , is(equalTo(Events.CRUD_DELETE_PREGUNTA_OK)) );
		
		contexto = saPregunta.borrarPregunta(mdlId);
		
		assertThat("El contexto no deberia se nulo.", contexto.getEvent() , notNullValue() );
		
		assertThat("El identificador deberia ser nulo.", contexto.getDato() , nullValue() );
		
		assertThat("El evento de borrado deberia ser positivo.", contexto.getEvent() , is(equalTo(Events.CRUD_DELETE_PREGUNTA_OK)) );
		
	}

}
