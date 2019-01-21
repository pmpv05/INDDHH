package inddhh;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.def.EntityDef;

public class SetParamsToExpediente extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		/*Se deben cargar los siguientes atributos:
	 	- TRM_PARAMETROS_DESDE_DEFINICION_STR con el valor false.
		- DEF_TRM_EXPEDIENTE_OFICINA_ORIGEN_STR: oficina que va a recibir el expediente generado (debe existir en ApiaDocumentum)
		- DEF_TRM_EXPEDIENTE_CLASIFICACION_STR:  clasificaci�n del expediente 
		- DEF_TRM_EXPEDIENTE_DOC_FISICA_STR: si contiene documentaci�n f�sica ( 1=No, 2= Si)
		- DEF_TRM_EXPEDIENTE_CONFIDENCIALIDAD_STR:  nivel de confidencialidad del expedientes
		- DEF_TRM_EXPEDIENTE_PRIORIDAD_STR: prioridad
		- DEF_TRM_EXPEDIENTE_ASUNTO_STR: asunto del expediente
		- DEF_TRM_EXPEDIENTE_TIPO_STR: tipo de expediente (debe existir en ApiaDocumentum)
		- DEF_TRM_EXPEDIENTE_ADJUNTO_STR: si se env�a documento adjunto del tr�mite ( 1=No, 2= Si)
		- DEF_TRM_CONCATENAR_PDF_STR: si se concatena los pdf del tr�mtie y se env�an al expediente. ( 1=No, 2= Si)
		*/
		
		//Entity currEnt = this.getCurrentEntity();
		
		int idDefTramite = Integer.valueOf(getCurrentEntity().getAttribute("TRM_COD_TRAMITE_STR").getValueAsString()).intValue();
	    
	    EntityDef entDefDefTrm = getEntityDef("DEF_TRAMITE");
	    
	    Entity entDefTramite = getEntity(entDefDefTrm.getName(), entDefDefTrm.getPrefix(), Integer.valueOf(idDefTramite), entDefDefTrm.getPosfix());
	    
		String paramsDef = this.getParameter("paramsDef").getValueAsString();
		String ofiOrigen = this.getParameter("ofiOrigen").getValueAsString();
		String clasif = this.getParameter("clasif").getValueAsString();
		String docFisica = this.getParameter("docFisica").getValueAsString();
		String confidenc = this.getParameter("confidenc").getValueAsString();
		String prioridad = this.getParameter("prioridad").getValueAsString();
		String asunto = this.getParameter("asunto").getValueAsString();
		String tipo = this.getParameter("tipo").getValueAsString();
		String adjunto = this.getParameter("adjunto").getValueAsString();
		String pdf = this.getParameter("pdf").getValueAsString();
		
		entDefTramite.getAttribute("TRM_PARAMETROS_DESDE_DEFINICION_STR").setValue(paramsDef);
		entDefTramite.getAttribute("DEF_TRM_EXPEDIENTE_OFICINA_ORIGEN_STR").setValue(ofiOrigen);
		entDefTramite.getAttribute("DEF_TRM_EXPEDIENTE_CLASIFICACION_STR").setValue(clasif);
		entDefTramite.getAttribute("DEF_TRM_EXPEDIENTE_DOC_FISICA_STR").setValue(docFisica);
		entDefTramite.getAttribute("DEF_TRM_EXPEDIENTE_CONFIDENCIALIDAD_STR").setValue(confidenc);
		entDefTramite.getAttribute("DEF_TRM_EXPEDIENTE_PRIORIDAD_STR").setValue(prioridad);
		entDefTramite.getAttribute("DEF_TRM_EXPEDIENTE_ASUNTO_STR").setValue(asunto);
		entDefTramite.getAttribute("DEF_TRM_EXPEDIENTE_TIPO_STR").setValue(tipo);
		entDefTramite.getAttribute("DEF_TRM_EXPEDIENTE_ADJUNTO_STR").setValue(adjunto);
		entDefTramite.getAttribute("DEF_TRM_CONCATENAR_PDF_STR").setValue(pdf);
		
		//Archivo Estructurado: XML donde le paso los atributos Nombre, Apellido, Doc, etc...
		Entity currEnt = this.getCurrentEntity();
		
		//Datos personales
		String tipoDoc = currEnt.getAttribute("INDDHH_ATT_DATOS_PERSONALES_DOC_TIPO_STR").getValueAsString();
		String doc = currEnt.getAttribute("INDDHH_ATT_DATOS_PERSONALES_DOC_NUM_STR").getValueAsString();
		String paisEmisor = currEnt.getAttribute("INDDHH_ATT_DATOS_PERSONALES_PAIS_STR").getValueAsString();
		String nombre = currEnt.getAttribute("INDDHH_ATT_DATOS_PERSONALES_NOMBRES_STR").getValueAsString();
		String apellido = currEnt.getAttribute("INDDHH_ATT_DATOS_PERSONALES_APELLIDOS_STR").getValueAsString();
		String nacionalidad = currEnt.getAttribute("INDDHH_NACIONALIDAD_STR").getValueAsString();
		String genero = currEnt.getAttribute("INDDHH_PERSONA_GENERO_STR").getValueAsString();
		String grupoEtnico = currEnt.getAttribute("INDDHH_GRUPO_ETNICO_STR").getValueAsString();
		String fechaNac = currEnt.getAttribute("INDDHH_PERSONA_FISICA_FECHA_NACIMIENTO_DTE").getValueAsString();
		String edad = currEnt.getAttribute("INDDHH_EDAD_NUM").getValueAsString();
		
		//Domicilio
		String depto = currEnt.getAttribute("INDDHH_ATT_DOMICILIO_DEPTO_STR").getValueAsString();
		String loc = currEnt.getAttribute("INDDHH_ATT_DOMICILIO_LOCALIDAD_STR").getValueAsString();
		String calle = currEnt.getAttribute("INDDHH_ATT_DOMICILIO_CALLE_STR").getValueAsString();
		String numero = currEnt.getAttribute("INDDHH_ATT_DOMICILIO_NUMERO_STR").getValueAsString();
		String otrosDatos = currEnt.getAttribute("INDDHH_ATT_DOMICILIO_OTROS_DATOS_STR").getValuesAsString();
		
		//Contacto
		String cel = currEnt.getAttribute("INDDHH_TELEFONO_CONTACTO_STR").getValueAsString();
		String tel = currEnt.getAttribute("INDDHH_OTRO_TEL_CONTACTO_STR").getValueAsString();
		String correo = currEnt.getAttribute("INDDHH_CORREO_CONTACTO_STR").getValueAsString();
		
		//Descripci�n del problema
		String problema = currEnt.getAttribute("INDDHH_PROBLEMA_PERSONA_STR").getValuesAsString();
		String fechaInicio = currEnt.getAttribute("INDDHH_FECHA_INICIO_HECHOS_STR").getValueAsString();
		String fechaFin = currEnt.getAttribute("INDDHH_FECHA_FIN_HECHOS_STR").getValueAsString();
		String casoJusticia = currEnt.getAttribute("INDDHH_CASO_ORBITA_JUSTICIA_STR").getValueAsString();
		
		//Organismos denunciados
		String orgs = currEnt.getAttribute("INDDHH_ORG_INCISO_TABLA_STR").getValueAsString();
		String unEjs = currEnt.getAttribute("INDDHH_ORG_UNIDAD_EJECUTORA_TABLA_STR").getValueAsString();
		
		String xml = "";
		entDefTramite.getAttribute("TRM_XML_FILES_STR").setValueLargeStr(xml);
		
	}

}
