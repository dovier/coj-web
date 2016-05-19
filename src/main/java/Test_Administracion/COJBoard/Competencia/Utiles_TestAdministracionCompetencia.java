package Test_Administracion.COJBoard.Competencia;

import java.util.ArrayList;

public class Utiles_TestAdministracionCompetencia {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String nombreCorrecto = "Pedro";
	private String urlCorrecta = "http://asd.asd";
	private String fechaInicioCorrecta = "2016-10-15 12:30:00.0";
	private String fechaFinCorrecta = "2016-10-15 12:30:00.0";
	private String notifCreated="false";
	private String notifChanged="false";
	private String sidSitio="140";
	//Atributos correctos general//////////////////////////////////////////////


	//-Competencia-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Competencia- para prueba
	private String idCOmpetencia = "820";

	//Lista de valores correctos para -Nombre- en -Competencia-
	private ArrayList<String> nombresCorrectosCompetencia;

	//Lista de valores incorrectos para -Nombre- en -Competencia-
	private ArrayList<String> nombresIncorrectosCompetencia;

	//Lista de valores correctos para -Url- en -Competencia-
	private ArrayList<String> urlscorrectasCompetencia;

	//Lista de valores incorrectos para -Url- en -Competencia-
	private ArrayList<String> urlsincorrectasCompetencia;

	//Lista de valores correctos para -FechaInicio- en -Competencia-
	private ArrayList<String> fechaInciocorrectasCompetencia;

	//Lista de valores incorrectos para -FechaInicio- en -Competencia-
	private ArrayList<String> fechaIncioincorrectasCompetencia;

	//Lista de valores correctos para -FechaFin- en -Competencia-
	private ArrayList<String> fechaFincorrectasCompetencia;

	//Lista de valores incorrectos para -FechaFin- en -Competencia-
	private ArrayList<String> fechaFinincorrectasCompetencia;

	//Lista de valores correctos para -SeleccionarSitio- en -Competencia-
	private ArrayList<String> seleccionarSitiocorrectoCompetencia;

	//Lista de valores incorrectos para -SeleccionarSitio- en -Competencia-
	private ArrayList<String> seleccionarSitioincorrectoCompetencia;
	//-Competencia-////////////////////////////////////////////////////////////


	public Utiles_TestAdministracionCompetencia(){

		//-Competencia-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Nombre- en -Competencia-
		nombresCorrectosCompetencia=new ArrayList<>();
		nombresCorrectosCompetencia.add("asdasd");
		nombresCorrectosCompetencia.add(" asdasd");
		nombresCorrectosCompetencia.add("asda sd");
		nombresCorrectosCompetencia.add("asdasd ");

		//Listado de valores incorrectos para -Nombre- en -Competencia-
		nombresIncorrectosCompetencia=new ArrayList<>();
		nombresIncorrectosCompetencia.add("");
		nombresIncorrectosCompetencia.add(" ");

		//Listado de valores correctos para -Url- en -Competencia-
		urlscorrectasCompetencia=new ArrayList<>();
		urlscorrectasCompetencia.add("http://coj.uci.cu/contest/contestview.xhtml?cid=1495");

		//Listado de valores incorrectos para -Url- en -Competencia-
		urlsincorrectasCompetencia=new ArrayList<>();
		urlsincorrectasCompetencia.add("http:");
		urlsincorrectasCompetencia.add("");
		urlsincorrectasCompetencia.add("coj.uci.cu/contest/contestview.xhtml?cid=1495");

		//Listado de valores correctos para -fechaIncio- en -Competencia-
		fechaInciocorrectasCompetencia=new ArrayList<>();
		fechaInciocorrectasCompetencia.add("2016-5-7 03:05:00.0");

		//Listado de valores incorrectos para -fechaIncio- en -Competencia-
		fechaIncioincorrectasCompetencia=new ArrayList<>();
		fechaIncioincorrectasCompetencia.add("2016-6-11");
		fechaIncioincorrectasCompetencia.add("");
		fechaIncioincorrectasCompetencia.add("2016-6-15 00:00");

		//Listado de valores correctos para -fechaFin- en -Competencia-
		fechaFincorrectasCompetencia=new ArrayList<>();
		fechaFincorrectasCompetencia.add("2016-5-7 03:05:00.0");

		//Listado de valores incorrectos para -fechaFin- en -Competencia-
		fechaFinincorrectasCompetencia=new ArrayList<>();
		fechaFinincorrectasCompetencia.add("2016-6-11");
		fechaFinincorrectasCompetencia.add("");
		fechaFinincorrectasCompetencia.add("2016-5-7 03:05");

		//Listado de valores correctos para -seleccionarSitio- en -Competencia-
		seleccionarSitiocorrectoCompetencia=new ArrayList<>();
		seleccionarSitiocorrectoCompetencia.add("152");
		seleccionarSitiocorrectoCompetencia.add("182");

		//Listado de valores incorrectos para -seleccionarSitio- en -Competencia-
		seleccionarSitioincorrectoCompetencia=new ArrayList<>();
		seleccionarSitioincorrectoCompetencia.add("0");
		//-Competencia-////////////////////////////////////////////////////////////






	}


	public String getNombreCorrecto() {
		return nombreCorrecto;
	}

	public void setNombreCorrecto(String nombreCorrecto) {
		this.nombreCorrecto = nombreCorrecto;
	}

	public String getUrlCorrecta() {
		return urlCorrecta;
	}

	public void setUrlCorrecta(String urlCorrecta) {
		this.urlCorrecta = urlCorrecta;
	}

	public String getFechaInicioCorrecta() {
		return fechaInicioCorrecta;
	}

	public void setFechaInicioCorrecta(String fechaInicioCorrecta) {
		this.fechaInicioCorrecta = fechaInicioCorrecta;
	}

	public String getFechaFinCorrecta() {
		return fechaFinCorrecta;
	}

	public void setFechaFinCorrecta(String fechaFinCorrecta) {
		this.fechaFinCorrecta = fechaFinCorrecta;
	}

	public String getIdCOmpetencia() {
		return idCOmpetencia;
	}

	public void setIdCOmpetencia(String idCOmpetencia) {
		this.idCOmpetencia = idCOmpetencia;
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenna() {
		return contrasenna;
	}

	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}

	public ArrayList<String> getNombresCorrectosCompetencia() {
		return nombresCorrectosCompetencia;
	}

	public void setNombresCorrectosCompetencia(ArrayList<String> nombresCorrectosCompetencia) {
		this.nombresCorrectosCompetencia = nombresCorrectosCompetencia;
	}

	public ArrayList<String> getNombresIncorrectosCompetencia() {
		return nombresIncorrectosCompetencia;
	}

	public void setNombresIncorrectosCompetencia(ArrayList<String> nombresIncorrectosCompetencia) {
		this.nombresIncorrectosCompetencia = nombresIncorrectosCompetencia;
	}

	public String getNotifCreated() {
		return notifCreated;
	}

	public void setNotifCreated(String notifCreated) {
		this.notifCreated = notifCreated;
	}

	public String getNotifChanged() {
		return notifChanged;
	}

	public void setNotifChanged(String notifChanged) {
		this.notifChanged = notifChanged;
	}

	public String getSidSitio() {
		return sidSitio;
	}

	public void setSidSitio(String sidSitio) {
		this.sidSitio = sidSitio;
	}

	public ArrayList<String> getUrlscorrectasCompetencia() {
		return urlscorrectasCompetencia;
	}

	public void setUrlscorrectasCompetencia(ArrayList<String> urlscorrectasCompetencia) {
		this.urlscorrectasCompetencia = urlscorrectasCompetencia;
	}

	public ArrayList<String> getUrlsincorrectasCompetencia() {
		return urlsincorrectasCompetencia;
	}

	public void setUrlsincorrectasCompetencia(ArrayList<String> urlsincorrectasCompetencia) {
		this.urlsincorrectasCompetencia = urlsincorrectasCompetencia;
	}

	public ArrayList<String> getFechaInciocorrectasCompetencia() {
		return fechaInciocorrectasCompetencia;
	}

	public void setFechaInciocorrectasCompetencia(ArrayList<String> fechaInciocorrectasCompetencia) {
		this.fechaInciocorrectasCompetencia = fechaInciocorrectasCompetencia;
	}

	public ArrayList<String> getFechaIncioincorrectasCompetencia() {
		return fechaIncioincorrectasCompetencia;
	}

	public void setFechaIncioincorrectasCompetencia(ArrayList<String> fechaIncioincorrectasCompetencia) {
		this.fechaIncioincorrectasCompetencia = fechaIncioincorrectasCompetencia;
	}

	public ArrayList<String> getFechaFincorrectasCompetencia() {
		return fechaFincorrectasCompetencia;
	}

	public void setFechaFincorrectasCompetencia(ArrayList<String> fechaFincorrectasCompetencia) {
		this.fechaFincorrectasCompetencia = fechaFincorrectasCompetencia;
	}

	public ArrayList<String> getFechaFinincorrectasCompetencia() {
		return fechaFinincorrectasCompetencia;
	}

	public void setFechaFinincorrectasCompetencia(ArrayList<String> fechaFinincorrectasCompetencia) {
		this.fechaFinincorrectasCompetencia = fechaFinincorrectasCompetencia;
	}

	public ArrayList<String> getSeleccionarSitiocorrectoCompetencia() {
		return seleccionarSitiocorrectoCompetencia;
	}

	public void setSeleccionarSitiocorrectoCompetencia(ArrayList<String> seleccionarSitiocorrectoCompetencia) {
		this.seleccionarSitiocorrectoCompetencia = seleccionarSitiocorrectoCompetencia;
	}

	public ArrayList<String> getSeleccionarSitioincorrectoCompetencia() {
		return seleccionarSitioincorrectoCompetencia;
	}

	public void setSeleccionarSitioincorrectoCompetencia(ArrayList<String> seleccionarSitioincorrectoCompetencia) {
		this.seleccionarSitioincorrectoCompetencia = seleccionarSitioincorrectoCompetencia;
	}
}
