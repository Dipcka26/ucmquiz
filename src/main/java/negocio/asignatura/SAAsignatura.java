package negocio.asignatura;

import java.util.List;
import presentacion.Contexto;

public interface SAAsignatura {
	
	public Contexto readAll();
	
	public Contexto delete(int id);

	Contexto create(Asignatura asignatura);
	
}
