
function fnc_1001_4145(evtSource) { 
	var modal =  ModalController.openWinModal(getUrlApp() + "/expedientes/arbolDestinoMultiple/armar.jsp?" + TAB_ID_REQUEST, 710, 400, null, null, null, true, true);
	modal.onclose=function(){
		var sDestino=this.returnValue;
		if (sDestino == 'cancel' || sDestino == '' || sDestino == null){
			alert(obtenerMensajeMultilenguajeSegunCodigo('MSG_DESTINO_NO_VALIDO',currentLanguage)); //alert("No se ha elegido un destino v�lido.");
		}else{
			if (sDestino.length > 2){								
				var frmActual = ApiaFunction.getForm("UNIDAD_ORGANIZACIONAL_ALTA");
				frmActual.getField("UO_TMP_STR").setValue(sDestino);
				
				var vecAreasSeleccionadas = sDestino.split("�");		
				//document.getElementById("UO_A_AGREGAR").innerHTML = "Se agregar�n las siguientes Areas: " + vecAreasSeleccionadas[2];
				frmActual.getField("UO_A_AGREGAR").setValue(obtenerMensajeMultilenguajeSegunCodigo("MSG_UOS_A_AGREGAR_JS",currentLanguage) + " " + vecAreasSeleccionadas[2]);

				
				//document.getElementById("BTN_NODO_AREA_ALTA_DAR_ALTA_AREAS").disabled = false;
				//document.getElementById("BTN_NODO_AREA_ALTA_SELECCIONAR_AREAS").focus();		
				
				
				return true;
			}				
		}
	}
	
	return false;
	

return true; // END
} // END
