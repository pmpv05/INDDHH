package inddhh;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Field;
import com.dogma.busClass.object.Form;

public class CargarClausulaConsentimiento extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		
		//Entity currEnt = this.getCurrentEntity();
		//Attribute attClausula = currEnt.getAttribute("INDDHH_CLAUSULA_TEXTO_STR");

		String clausulaTexto = "De conformidad con la Ley N� 18.331, de 11 de agosto de 2008, "
				+ "de Protecci�n de Datos Personales y Acci�n de Habeas Data (LPDP), "
				+ "los datos suministrados por usted quedar�n incorporados en la base de datos, "
				+ "la cual ser� procesada exclusivamente para la siguiente finalidad: Consulta o "
				+ "Denuncia en la Instituci�n Nacional de Derechos Humanos del Uruguay (INDDHH). "
				+ "Los datos personales ser�n tratados con el grado de protecci�n adecuado,"
				+ " tom�ndose las medidas de seguridad necesarias para evitar su alteraci�n, "
				+ "p�rdida, tratamiento o acceso no autorizado por parte de terceros. "
				+ "El responsable de la Base de datos es la INDDHH y la direcci�n donde "
				+ "podr� ejercer los derechos de acceso, rectificaci�n, actualizaci�n, "
				+ "inclusi�n o supresi�n, es Bulevar General Artigas 1532, Montevideo.";
		
		//attClausula.setValueLargeStr(clausulaTexto);
		
		Form form = getCurrentForm();
	    Field field = form.getField("CLAUSULA");
	    int propId = 6;
	    field.setProperty(propId, clausulaTexto);
	}

}
