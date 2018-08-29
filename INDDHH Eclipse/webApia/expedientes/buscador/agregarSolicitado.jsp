<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.dogma.controller.ThreadData"%>
<%@page import="com.dogma.DogmaException"%>
<%@page import="com.dogma.UserData"%>
<%@page import="com.dogma.dataAccess.DBManagerUtil"%>
<%@page import="com.st.db.dataAccess.DBConnection"%>
<%@page import="st.access.DBConnectionLocal"%>
<%@page import="uy.com.st.adoc.expedientes.constantes.Values"%>

<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", -1);
	String nroExp = "";	
	String ubicacionAct = "";
	String usuarioAct = "";

	nroExp = request.getParameter(Values.PARAM_NRO_EXP);
	ubicacionAct = request.getParameter("ubAct");
	usuarioAct = request.getParameter("usrAct");

	UserData uData = ThreadData.getUserData();
	String usrNam = "";
	String usrLog = "";
	if (uData != null) {
		usrLog = uData.getUserId() + "";
		usrNam = uData.getUserName() + "";
	}
	
	String tokenId = "";
	if (request.getParameter("tokenId") != null) {
		tokenId = request.getParameter("tokenId").toString();
	}
	String tabId = "";
	if (request.getParameter("tabId") != null) {
		tabId = request.getParameter("tabId").toString();
	}
	String TAB_ID_REQUEST = "&tabId=" + tabId + "&tokenId=" + tokenId;
	
	PreparedStatement pst = null;
	Connection conn = null;
	DBConnection dbConn = null;

	try {
		String SQL = "INSERT INTO panel_solicitados VALUES(?,now(),?,?,?,?,'Solicitado',?)";
		dbConn = DBManagerUtil.getApiaConnection();
		conn = new DBConnectionLocal().getDBConnectionLocal(dbConn);
		pst = conn.prepareStatement(SQL);
		pst.setString(1, nroExp);
		pst.setString(2, usrNam);
		pst.setString(3, usrLog);
		pst.setString(4, ubicacionAct);
		pst.setString(5, usuarioAct);
		pst.setInt(6, uData.getEnvironmentId());
		pst.executeUpdate();
		conn.commit();
		response.getWriter().write("OK");
	} catch (DogmaException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if (dbConn != null) {
			try {
				dbConn.close();
			} catch (DogmaException e) {
				e.printStackTrace();
			}
			DBConnectionLocal.closeRecursos(pst, conn, null, null);
		}
	}
%>