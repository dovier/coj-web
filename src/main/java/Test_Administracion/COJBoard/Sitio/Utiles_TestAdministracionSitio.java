package Test_Administracion.COJBoard.Sitio;

import java.util.ArrayList;

public class Utiles_TestAdministracionSitio {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String nombreCorrecto = "Pedro";
	private String urlCorrecta = "http://asd.asd";
	private String codigoCorrecto = "456";
	private String idHoraFechaCorrecto="99";
    private String zonaHorariaCorrecta="Pacific/Samoa";
	private String habilitadoCorrecto="true";
	private String completadoCorrecto="true";
	//Atributos correctos general//////////////////////////////////////////////


	//-Sitio-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Sitio- para prueba
	private String idSitio = "115";

	//Lista de valores correctos para -Nombre de sitio- en -Sitio-
	private ArrayList<String> nombresCorrectosSitio;

	//Lista de valores incorrectos para -Nombre de sitio- en -Sitio-
	private ArrayList<String> nombresIncorrectosSitio;

	//Lista de valores correctos para -Url- en -Sitio-
	private ArrayList<String> urlscorrectasSitio;

	//Lista de valores incorrectos para -Url- en -Sitio-
	private ArrayList<String> urlsincorrectasSitio;

	//Lista de valores correctos para -Codigo- en -Sitio-
	private ArrayList<String> codigocorrectoSitio;

	//Lista de valores incorrectos para -Codigo- en -Sitio-
	private ArrayList<String> codigoIncorrectoSitio;

	//Lista de valores correctos para -IdHoraFecha- en -Sitio-
	private ArrayList<String> idHoraFechacorrectasSitio;

	//Lista de valores incorrectos para -IdHoraFecha- en -Sitio-
	private ArrayList<String> idHoraFechaIncorrectasSitio;

	//Lista de valores correctos para -ZonaHoraria- en -Sitio-
	private ArrayList<String> zonaHorariaCorrectaCompetencia;

	//Lista de valores incorrectos para -ZonaHoraria- en -Sitio-
	private ArrayList<String> zonaHorariaIncorrectaCompetencia;
	//-Sitio-////////////////////////////////////////////////////////////


	public Utiles_TestAdministracionSitio(){

		//-Sitio-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Nombre- en -Sitio-
		nombresCorrectosSitio=new ArrayList<>();
		nombresCorrectosSitio.add("asdasd");
		nombresCorrectosSitio.add(" asdasd");
		nombresCorrectosSitio.add("asda sd");
		nombresCorrectosSitio.add("asdasd ");

		//Listado de valores incorrectos para -Nombre- en -Sitio-
		nombresIncorrectosSitio=new ArrayList<>();
		nombresIncorrectosSitio.add("");
		nombresIncorrectosSitio.add(" ");

		//Listado de valores correctos para -Url- en -Sitio-
		urlscorrectasSitio=new ArrayList<>();
		urlscorrectasSitio.add("http://internos.uci.cu/");

		//Listado de valores incorrectos para -Url- en -Sitio-
		urlsincorrectasSitio=new ArrayList<>();
		urlsincorrectasSitio.add("http:");
		urlsincorrectasSitio.add("");
		urlsincorrectasSitio.add("coj.uci.cu/contest/contestview.xhtml?cid=1495");

		//Listado de valores correctos para -codigo- en -Sitio-
		codigocorrectoSitio=new ArrayList<>();
		codigocorrectoSitio.add("123");
		codigocorrectoSitio.add("123abc");
		codigocorrectoSitio.add("abc");
		codigocorrectoSitio.add("89-a");
		codigocorrectoSitio.add("ab-2");

		//Listado de valores incorrectos para -codigo- en -Sitio-
		codigoIncorrectoSitio=new ArrayList<>();
		codigoIncorrectoSitio.add("");
		codigoIncorrectoSitio.add("89*");
		codigoIncorrectoSitio.add("abc*");
		codigoIncorrectoSitio.add("/*");

		//Listado de valores correctos para -idHoraFecha- en -Sitio-
		idHoraFechacorrectasSitio=new ArrayList<>();
		idHoraFechacorrectasSitio.add("23");

		//Listado de valores incorrectos para -idHoraFecha- en -Sitio-
		idHoraFechaIncorrectasSitio=new ArrayList<>();
		idHoraFechaIncorrectasSitio.add("56666666666666");
		idHoraFechaIncorrectasSitio.add("");
		idHoraFechaIncorrectasSitio.add("0");
		idHoraFechaIncorrectasSitio.add("-9");
		idHoraFechaIncorrectasSitio.add("asd");

		//Listado de valores correctos para -zona Horaria- en -Sitio-
		zonaHorariaCorrectaCompetencia=new ArrayList<>();
		zonaHorariaCorrectaCompetencia.add("Etc/GMT+12");

		//Listado de valores incorrectos para -seleccionarSitio- en -Competencia-
		zonaHorariaIncorrectaCompetencia=new ArrayList<>();
		zonaHorariaIncorrectaCompetencia.add("0");
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

	public String getCodigoCorrecto() {
		return codigoCorrecto;
	}

	public void setCodigoCorrecto(String codigoCorrecto) {
		this.codigoCorrecto = codigoCorrecto;
	}

	public String getIdHoraFechaCorrecto() {
		return idHoraFechaCorrecto;
	}

	public void setIdHoraFechaCorrecto(String idHoraFechaCorrecto) {
		this.idHoraFechaCorrecto = idHoraFechaCorrecto;
	}

	public String getZonaHorariaCorrecta() {
		return zonaHorariaCorrecta;
	}

	public void setZonaHorariaCorrecta(String zonaHorariaCorrecta) {
		this.zonaHorariaCorrecta = zonaHorariaCorrecta;
	}


	public String getHabilitadoCorrecto() {
		return habilitadoCorrecto;
	}

	public void setHabilitadoCorrecto(String habilitadoCorrecto) {
		this.habilitadoCorrecto = habilitadoCorrecto;
	}

	public String getCompletadoCorrecto() {
		return completadoCorrecto;
	}

	public void setCompletadoCorrecto(String completadoCorrecto) {
		this.completadoCorrecto = completadoCorrecto;
	}

	public String getIdSitio() {
		return idSitio;
	}

	public void setIdSitio(String idSitio) {
		this.idSitio = idSitio;
	}

	public ArrayList<String> getNombresCorrectosSitio() {
		return nombresCorrectosSitio;
	}

	public void setNombresCorrectosSitio(ArrayList<String> nombresCorrectosSitio) {
		this.nombresCorrectosSitio = nombresCorrectosSitio;
	}

	public ArrayList<String> getNombresIncorrectosSitio() {
		return nombresIncorrectosSitio;
	}

	public void setNombresIncorrectosSitio(ArrayList<String> nombresIncorrectosSitio) {
		this.nombresIncorrectosSitio = nombresIncorrectosSitio;
	}

	public ArrayList<String> getUrlscorrectasSitio() {
		return urlscorrectasSitio;
	}

	public void setUrlscorrectasSitio(ArrayList<String> urlscorrectasSitio) {
		this.urlscorrectasSitio = urlscorrectasSitio;
	}

	public ArrayList<String> getUrlsincorrectasSitio() {
		return urlsincorrectasSitio;
	}

	public void setUrlsincorrectasSitio(ArrayList<String> urlsincorrectasSitio) {
		this.urlsincorrectasSitio = urlsincorrectasSitio;
	}

	public ArrayList<String> getCodigocorrectoSitio() {
		return codigocorrectoSitio;
	}

	public void setCodigocorrectoSitio(ArrayList<String> codigocorrectoSitio) {
		this.codigocorrectoSitio = codigocorrectoSitio;
	}

	public ArrayList<String> getCodigoIncorrectoSitio() {
		return codigoIncorrectoSitio;
	}

	public void setCodigoIncorrectoSitio(ArrayList<String> codigoIncorrectoSitio) {
		this.codigoIncorrectoSitio = codigoIncorrectoSitio;
	}

	public ArrayList<String> getIdHoraFechacorrectasSitio() {
		return idHoraFechacorrectasSitio;
	}

	public void setIdHoraFechacorrectasSitio(ArrayList<String> idHoraFechacorrectasSitio) {
		this.idHoraFechacorrectasSitio = idHoraFechacorrectasSitio;
	}

	public ArrayList<String> getIdHoraFechaIncorrectasSitio() {
		return idHoraFechaIncorrectasSitio;
	}

	public void setIdHoraFechaIncorrectasSitio(ArrayList<String> idHoraFechaIncorrectasSitio) {
		this.idHoraFechaIncorrectasSitio = idHoraFechaIncorrectasSitio;
	}

	public ArrayList<String> getZonaHorariaCorrectaCompetencia() {
		return zonaHorariaCorrectaCompetencia;
	}

	public void setZonaHorariaCorrectaCompetencia(ArrayList<String> zonaHorariaCorrectaCompetencia) {
		this.zonaHorariaCorrectaCompetencia = zonaHorariaCorrectaCompetencia;
	}

	public ArrayList<String> getZonaHorariaIncorrectaCompetencia() {
		return zonaHorariaIncorrectaCompetencia;
	}

	public void setZonaHorariaIncorrectaCompetencia(ArrayList<String> zonaHorariaIncorrectaCompetencia) {
		this.zonaHorariaIncorrectaCompetencia = zonaHorariaIncorrectaCompetencia;
	}

}
