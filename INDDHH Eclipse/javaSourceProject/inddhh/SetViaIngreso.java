package inddhh;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;

public class SetViaIngreso extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		Entity currEnt = this.getCurrentEntity();

		String viaInicio = currEnt.getAttribute("INDDHH_VIA_INICIO_STR").getValueAsString();
		int viaInicioInt = Integer.valueOf(viaInicio);

//		if (viaInicio.compareTo("1") == 0) {
//			currEnt.getAttribute("INDDHH_VIA_INICIO_VALOR_STR").setValue("Telef�nica");
//		} else if (viaInicio.compareTo("2") == 0) {
//			currEnt.getAttribute("INDDHH_VIA_INICIO_VALOR_STR").setValue("Correo electr�nico");
//		} else if (viaInicio.compareTo("3") == 0) {
//			currEnt.getAttribute("INDDHH_VIA_INICIO_VALOR_STR").setValue("Presencial");
//		} else {
//			currEnt.getAttribute("INDDHH_VIA_INICIO_VALOR_STR").setValue("En L�nea");
//		}

		Entity viaInicioEnt = this.getEntity("VIA", viaInicioInt, null);
		currEnt.getAttribute("INDDHH_VIA_INICIO_VALOR_STR")
				.setValue(viaInicioEnt.getAttribute("A_CODIGUERA_VALUE").getValueAsString());

	}

}
