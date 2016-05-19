package Test_Administracion.Miscelaneas.Institucion;

import java.util.ArrayList;

public class Utiles_TestAdministracionInstitucion {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String nombreCorrecto = "Institucion";
	private String codigoCorrecta = "X7975";
	private String imagenCorrecto = "456";
	private String sitioWebCorrecto="http://directorio.uci.cu/";
    private String seleccionarPaisCorrecta="44";
	private String habilitadoCorrecto="true";

	//Atributos correctos general//////////////////////////////////////////////


	//-Institucion-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Institucion- para prueba
	private String idSitio = "7978";

	//Lista de valores correctos para -Nombre de sitio- en -Institucion-
	private ArrayList<String> nombresCorrectosInstitucion;

	//Lista de valores incorrectos para -Nombre de sitio- en -Institucion-
	private ArrayList<String> nombresIncorrectosInstitucion;

	//Lista de valores correctos para -Codigo- en -Institucion-
	private ArrayList<String> codigoCorrectasInstitucion;

	//Lista de valores incorrectos para -Codigo- en -Institucion-
	private ArrayList<String> codigosIncorrectasInstitucion;

	//Lista de valores correctos para -Imagen- en -Institucion-
	private ArrayList<String> imagencorrectoInstitucion;

	//Lista de valores incorrectos para -Imagen- en -Institucion-
	private ArrayList<String> iamgenIncorrectoInstitucion;

	//Lista de valores correctos para -SitioWeb- en -Institucion-
	private ArrayList<String> sitioWebcorrectasInstitucion;

	//Lista de valores incorrectos para -SitioWeb- en -Institucion-
	private ArrayList<String> sitioWebIncorrectasInstitucion;

	//Lista de valores correctos para -Pais- en -Institucion-
	private ArrayList<String> PaisCorrectaInstitucion;

	//Lista de valores incorrectos para -Pais- en -Institucion-
	private ArrayList<String> PaisIncorrectaInstitucion;
	//-Institucion-////////////////////////////////////////////////////////////


	public Utiles_TestAdministracionInstitucion(){

		//-Institucion-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Nombre- en -Institucion-
		nombresCorrectosInstitucion=new ArrayList<>();
		nombresCorrectosInstitucion.add("asdasd");
		nombresCorrectosInstitucion.add(" asdasd");
		nombresCorrectosInstitucion.add("asda sd");
		nombresCorrectosInstitucion.add("asdasd ");

		//Listado de valores incorrectos para -Nombre- en -Institucion-
		nombresIncorrectosInstitucion=new ArrayList<>();
		nombresIncorrectosInstitucion.add("");
		nombresIncorrectosInstitucion.add(" ");

		//Listado de valores correctos para -Codigo- en -Institucion-
		codigoCorrectasInstitucion=new ArrayList<>();
		codigoCorrectasInstitucion.add("898ASS");
		codigoCorrectasInstitucion.add("9898");
		codigoCorrectasInstitucion.add("898-ASS");

		//Listado de valores incorrectos para -Codigo- en -Institucion-
		codigosIncorrectasInstitucion=new ArrayList<>();
		codigosIncorrectasInstitucion.add("898*ASS");
		codigosIncorrectasInstitucion.add("");
		codigosIncorrectasInstitucion.add(" ");
		codigosIncorrectasInstitucion.add("aw");

		//Listado de valores correctos para -Imagen- en -Institucion-
		imagencorrectoInstitucion=new ArrayList<>();
		imagencorrectoInstitucion.add("blob:http://localhost:8084/54dc0c41-7e1f-4bb8-9680-246a76531011");


		//Listado de valores incorrectos para -Imagen- en -Institucion-
		iamgenIncorrectoInstitucion=new ArrayList<>();
		iamgenIncorrectoInstitucion.add("*-");

        //Listado de valores correctos para -sitioWeb- en -Institucion-
		sitioWebcorrectasInstitucion=new ArrayList<>();
		sitioWebcorrectasInstitucion.add("http://internos.uci.cu/");

		//Listado de valores incorrectos para -sitioWeb- en -Institucion-
		sitioWebIncorrectasInstitucion=new ArrayList<>();
		sitioWebIncorrectasInstitucion.add("");
		sitioWebIncorrectasInstitucion.add("");
		sitioWebIncorrectasInstitucion.add("coj.uci.cu/contest/contestview.xhtml?cid=1495");

		//Listado de valores correctos para -Pais- en -Institucion-
		PaisCorrectaInstitucion=new ArrayList<>();
		PaisCorrectaInstitucion.add("50");

		//Listado de valores incorrectos para -Pais- en -Institucion-
		PaisIncorrectaInstitucion=new ArrayList<>();
		PaisIncorrectaInstitucion.add("");



		//-Institucion-////////////////////////////////////////////////////////////


			}


	public String getNombreCorrecto() {
		return nombreCorrecto;
	}

	public void setNombreCorrecto(String nombreCorrecto) {
		this.nombreCorrecto = nombreCorrecto;
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


	public String getCodigoCorrecta() {
		return codigoCorrecta;
	}

	public void setCodigoCorrecta(String codigoCorrecta) {
		this.codigoCorrecta = codigoCorrecta;
	}

	public String getImagenCorrecto() {
		return imagenCorrecto;
	}

	public void setImagenCorrecto(String imagenCorrecto) {
		this.imagenCorrecto = imagenCorrecto;
	}

	public String getSitioWebCorrecto() {
		return sitioWebCorrecto;
	}

	public void setSitioWebCorrecto(String sitioWebCorrecto) {
		this.sitioWebCorrecto = sitioWebCorrecto;
	}

	public String getSeleccionarPaisCorrecta() {
		return seleccionarPaisCorrecta;
	}

	public void setSeleccionarPaisCorrecta(String seleccionarPaisCorrecta) {
		this.seleccionarPaisCorrecta = seleccionarPaisCorrecta;
	}

	public String getHabilitadoCorrecto() {
		return habilitadoCorrecto;
	}

	public void setHabilitadoCorrecto(String habilitadoCorrecto) {
		this.habilitadoCorrecto = habilitadoCorrecto;
	}

	public String getIdSitio() {
		return idSitio;
	}

	public void setIdSitio(String idSitio) {
		this.idSitio = idSitio;
	}

	public ArrayList<String> getNombresCorrectosInstitucion() {
		return nombresCorrectosInstitucion;
	}

	public void setNombresCorrectosInstitucion(ArrayList<String> nombresCorrectosInstitucion) {
		this.nombresCorrectosInstitucion = nombresCorrectosInstitucion;
	}

	public ArrayList<String> getNombresIncorrectosInstitucion() {
		return nombresIncorrectosInstitucion;
	}

	public void setNombresIncorrectosInstitucion(ArrayList<String> nombresIncorrectosInstitucion) {
		this.nombresIncorrectosInstitucion = nombresIncorrectosInstitucion;
	}

	public ArrayList<String> getCodigoCorrectasInstitucion() {
		return codigoCorrectasInstitucion;
	}

	public void setCodigoCorrectasInstitucion(ArrayList<String> codigoCorrectasInstitucion) {
		this.codigoCorrectasInstitucion = codigoCorrectasInstitucion;
	}

	public ArrayList<String> getCodigosIncorrectasInstitucion() {
		return codigosIncorrectasInstitucion;
	}

	public void setCodigosIncorrectasInstitucion(ArrayList<String> codigosIncorrectasInstitucion) {
		this.codigosIncorrectasInstitucion = codigosIncorrectasInstitucion;
	}

	public ArrayList<String> getImagencorrectoInstitucion() {
		return imagencorrectoInstitucion;
	}

	public void setImagencorrectoInstitucion(ArrayList<String> imagencorrectoInstitucion) {
		this.imagencorrectoInstitucion = imagencorrectoInstitucion;
	}

	public ArrayList<String> getIamgenIncorrectoInstitucion() {
		return iamgenIncorrectoInstitucion;
	}

	public void setIamgenIncorrectoInstitucion(ArrayList<String> iamgenIncorrectoInstitucion) {
		this.iamgenIncorrectoInstitucion = iamgenIncorrectoInstitucion;
	}

	public ArrayList<String> getSitioWebcorrectasInstitucion() {
		return sitioWebcorrectasInstitucion;
	}

	public void setSitioWebcorrectasInstitucion(ArrayList<String> sitioWebcorrectasInstitucion) {
		this.sitioWebcorrectasInstitucion = sitioWebcorrectasInstitucion;
	}

	public ArrayList<String> getSitioWebIncorrectasInstitucion() {
		return sitioWebIncorrectasInstitucion;
	}

	public void setSitioWebIncorrectasInstitucion(ArrayList<String> sitioWebIncorrectasInstitucion) {
		this.sitioWebIncorrectasInstitucion = sitioWebIncorrectasInstitucion;
	}

	public ArrayList<String> getPaisCorrectaInstitucion() {
		return PaisCorrectaInstitucion;
	}

	public void setPaisCorrectaInstitucion(ArrayList<String> paisCorrectaInstitucion) {
		PaisCorrectaInstitucion = paisCorrectaInstitucion;
	}

	public ArrayList<String> getPaisIncorrectaInstitucion() {
		return PaisIncorrectaInstitucion;
	}

	public void setPaisIncorrectaInstitucion(ArrayList<String> paisIncorrectaInstitucion) {
		PaisIncorrectaInstitucion = paisIncorrectaInstitucion;
	}
}
