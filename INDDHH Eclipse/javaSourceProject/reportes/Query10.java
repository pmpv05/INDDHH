package reportes;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.dogma.busClass.BusClassException;
import com.dogma.busClass.QueryAbstractClass;
import com.dogma.busClass.object.Query;
import com.dogma.busClass.object.Filter;

public class Query10 extends QueryAbstractClass {

	@Override
	protected void executeQuery(Query qry) throws BusClassException {

		Filter filtroFecha = qry.getFilter("filtroPeriodo");

		Date fechaInicio = (Date) filtroFecha.getValue(0);
		Date fechaFin = (Date) filtroFecha.getValue(1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaInicioStr = sdf.format(fechaInicio);
		String fechaFinStr = sdf.format(fechaFin);

		try {
			Connection conn = this.getCurrentConnection();
			Statement stmt = conn.createStatement();

			String query =	
					"(SELECT 'Acceso a informaci�n p�blica' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 1  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Alimentaci�n' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 2  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Ambiente sano' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 3  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Debido proceso administrativo' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 4  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Educaci�n' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 5  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Identidad' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 6  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Igualdad y no discriminaci�n por edad' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 7  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Igualdad y no discriminaci�n de g�nero' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 8  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Igualdad y no discriminaci�n por discapacidad' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 9  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Igualdad y no discriminaci�n por identidad sexual y orientaci�n sexual' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 10  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Igualdad y no discriminaci�n por migrante' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 11  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Igualdad y no discriminaci�n por motivos religiosos' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 12  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Igualdad y no discriminaci�n por pobreza' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 14  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Igualdad y no discriminaci�n �tnico-racial' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 15  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Integridad personal (f�sica, ps�quica y moral)' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 16  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Intimidad' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 17  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Libertad de circulaci�n y residencia' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 18  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Libertad de conciencia y de religi�n' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 19  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Libertad de expresi�n' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 20  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Libertad de reuni�n y asociaci�n' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 21  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Libertad personal' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 22  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Participaci�n pol�tica' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 23  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Prestaci�n eficiente de servicios p�blicos' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 24  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Propiedad' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 25  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Proteccion familiar' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 26  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Protecci�n judicial' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 27  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " +  
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Reparaci�n integral por violaciones de los DDHH' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 28  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Salud' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 29  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					"UNION ALL " + 
					"  (SELECT 'Seguridad social' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 30  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Trabajo' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 31  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Vida' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 32  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"UNION ALL " + 
					"  (SELECT 'Vivienda' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and bei.bus_ent_inst_id_auto in (select bei.bus_ent_inst_id_auto " + 
					" " + 
					"      from bus_ent_inst_attribute ent_att " + 
					"        join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"        join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"      where att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"        and bei.bus_ent_id = 1379 and bei.att_value_10 = '1' " + 
					"        and ent_att.ent_inst_att_str_value = '2' " + 
					"        and bei.env_id = 1 and ent_att.env_id = 1) " + 
					" " + 
					"      and att.att_name = 'INDDHH_SELECCIONAR_DERECHO_STR' " + 
					"      and ent_att.att_index_id = 33  " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = 'true' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					") " + 
					" " + 
					"union all " + 
					"  (SELECT 'Total' as derecho, " + 
					"    (select count(*) " + 
					" " + 
					"    from bus_ent_inst_attribute ent_att " + 
					"      join attribute att on ent_att.att_id = att.att_id_auto and ent_att.env_id = att.env_id " + 
					"      join bus_ent_instance bei on bei.env_id = ent_att.env_id and bei.bus_ent_inst_id_auto = ent_att.bus_ent_inst_id " + 
					" " + 
					"    where bei.att_value_10 = '1' " + 
					"      and att.att_name = 'INDDHH_OPCIONES_ADMISIBLE_STR' " + 
					"      and bei.bus_ent_id = 1379 " + 
					"      and ent_att.ent_inst_att_str_value = '2' " + 
					"      and bei.env_id = 1 and ent_att.env_id = 1 "
					+ "and bei.bus_ent_inst_create_data >= timestamp '" + fechaInicioStr + "' "
					+ "and bei.bus_ent_inst_create_data <= timestamp '" + fechaFinStr + "' " + ") as ctd" + 
					")";
			
			ResultSet rs = stmt.executeQuery(query);

			qry.getData().clear();
			
			while (rs.next()) {
				ArrayList row = new ArrayList();
				
				row.add(rs.getString("derecho"));
				row.add(rs.getString("ctd"));
				
				qry.getData().addRow(row);
			}			

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

}
