package Test_Administracion.Miscelaneas.Pais;

import org.springframework.validation.ValidationUtils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utiles_TestAdministracionPais {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String nombreCorrecto = "Noexistepais";
	private String codigo3Correcta = "NOE";
	private String codigo2Correcta = "NO";
	private String sitioWebCorrecto = "http://en.wikipedia.org/wiki/Brunei";
	private String activadoCorrecto = "true";
	//Atributos correctos general//////////////////////////////////////////////


	//-Pais-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Pais- para prueba
	private String idPais = "32";

	//Lista de valores correctos para -Nombre- en -Pais-
	private ArrayList<String> nombresCorrectosPais;

	//Lista de valores incorrectos para -Nombre- en -Pais-
	private ArrayList<String> nombresIncorrectosPais;

	//Lista de valores correctos para -Codigo3- en -Pais-
	private ArrayList<String> codigos3CorrectosPais;

	//Lista de valores incorrectos para -Codigo3- en -Pais-
	private ArrayList<String> codigos3IncorrectosPais;

	//Lista de valores correctos para -Codigo2- en -Pais-
	private ArrayList<String> codigo2CorrectosPais;

	//Lista de valores incorrectos para -Codigo2- en -Pais-
	private ArrayList<String> codigo2IncorrectosPais;

	//Lista de valores correctos para -Sitio web- en -Pais-
	private ArrayList<String> sitioCorrectosPais;

	//Lista de valores incorrectos para -Sitio web- en -Pais-
	private ArrayList<String> sitioIncorrectosPais;
	//-Pais-////////////////////////////////////////////////////////////

	private String STRING_PATTERN = "[a-zA-Záéíóú ]+";
	private Pattern pattern;
	private Matcher matcher;

	public Utiles_TestAdministracionPais(){

		//-Pais-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Nombre- en -Pais-
		nombresCorrectosPais=new ArrayList<>();
		//nombresCorrectosPais.add("Pais Pais");
		//nombresCorrectosPais.add(" Pais ");
		nombresCorrectosPais.add("Noexistepaiss");


		//Listado de valores incorrectos para -Nombre- en -Pais-
		nombresIncorrectosPais=new ArrayList<>();
	    nombresIncorrectosPais.add("");
		nombresIncorrectosPais.add("-Pais");
		nombresIncorrectosPais.add(" ");


		//Listado de valores correctos para -Codigo3- en -Pais-
		codigos3CorrectosPais=new ArrayList<>();
		codigos3CorrectosPais.add("PAI");


		//Listado de valores incorrectos para -Codigo3- en -Pais-
		codigos3IncorrectosPais=new ArrayList<>();
		codigos3IncorrectosPais.add("");
		codigos3IncorrectosPais.add(" ");
		codigos3IncorrectosPais.add("pai");
		codigos3IncorrectosPais.add("pa3");
		codigos3IncorrectosPais.add("AP2");
		codigos3IncorrectosPais.add("A-2");
		codigos3IncorrectosPais.add("PAIS");

		//Listado de valores correctos para -Codigo2- en -Pais-
		codigo2CorrectosPais=new ArrayList<>();
		codigo2CorrectosPais.add("PA");


		//Listado de valores incorrectos para -Codigo2- en -Pais-
		codigo2IncorrectosPais=new ArrayList<>();
		codigo2IncorrectosPais.add("");
		codigo2IncorrectosPais.add(" ");
		codigo2IncorrectosPais.add("pa");
		codigo2IncorrectosPais.add("p3");
		codigo2IncorrectosPais.add("A2");
		codigo2IncorrectosPais.add("A-");
		codigo2IncorrectosPais.add("PAI");

		//Listado de valores correctos para -Sitio web- en -Pais-
		sitioCorrectosPais=new ArrayList<>();
		sitioCorrectosPais.add("http://en.wikipedia.org/wiki/Azerbaijan");

		//Listado de valores incorrectos para -Sitio web- en -Pais-
		sitioIncorrectosPais=new ArrayList<>();
		sitioIncorrectosPais.add("");
		sitioIncorrectosPais.add(" ");
		sitioIncorrectosPais.add("en.wikipedia.org/wiki/Azerbaijan");

		//-Pais-////////////////////////////////////////////////////////////


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


	public String getNombreCorrecto() {
		return nombreCorrecto;
	}

	public void setNombreCorrecto(String nombreCorrecto) {
		this.nombreCorrecto = nombreCorrecto;
	}

	public String getCodigo3Correcta() {
		return codigo3Correcta;
	}

	public void setCodigo3Correcta(String codigo3Correcta) {
		this.codigo3Correcta = codigo3Correcta;
	}

	public String getCodigo2Correcta() {
		return codigo2Correcta;
	}

	public void setCodigo2Correcta(String codigo2Correcta) {
		this.codigo2Correcta = codigo2Correcta;
	}

	public String getSitioWebCorrecto() {
		return sitioWebCorrecto;
	}

	public void setSitioWebCorrecto(String sitioWebCorrecto) {
		this.sitioWebCorrecto = sitioWebCorrecto;
	}

	public String getActivadoCorrecto() {
		return activadoCorrecto;
	}

	public void setActivadoCorrecto(String activadoCorrecto) {
		this.activadoCorrecto = activadoCorrecto;
	}

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	public ArrayList<String> getNombresCorrectosPais() {
		return nombresCorrectosPais;
	}

	public void setNombresCorrectosPais(ArrayList<String> nombresCorrectosPais) {
		this.nombresCorrectosPais = nombresCorrectosPais;
	}

	public ArrayList<String> getNombresIncorrectosPais() {
		return nombresIncorrectosPais;
	}

	public void setNombresIncorrectosPais(ArrayList<String> nombresIncorrectosPais) {
		this.nombresIncorrectosPais = nombresIncorrectosPais;
	}

	public ArrayList<String> getCodigos3CorrectosPais() {
		return codigos3CorrectosPais;
	}

	public void setCodigos3CorrectosPais(ArrayList<String> codigos3CorrectosPais) {
		this.codigos3CorrectosPais = codigos3CorrectosPais;
	}

	public ArrayList<String> getCodigos3IncorrectosPais() {
		return codigos3IncorrectosPais;
	}

	public void setCodigos3IncorrectosPais(ArrayList<String> codigos3IncorrectosPais) {
		this.codigos3IncorrectosPais = codigos3IncorrectosPais;
	}





	public ArrayList<String> getSitioCorrectosPais() {
		return sitioCorrectosPais;
	}

	public void setSitioCorrectosPais(ArrayList<String> sitioCorrectosPais) {
		this.sitioCorrectosPais = sitioCorrectosPais;
	}

	public ArrayList<String> getSitioIncorrectosPais() {
		return sitioIncorrectosPais;
	}

	public void setSitioIncorrectosPais(ArrayList<String> sitioIncorrectosPais) {
		this.sitioIncorrectosPais = sitioIncorrectosPais;
	}

	public ArrayList<String> getCodigo2CorrectosPais() {
		return codigo2CorrectosPais;
	}

	public void setCodigo2CorrectosPais(ArrayList<String> codigo2CorrectosPais) {
		this.codigo2CorrectosPais = codigo2CorrectosPais;
	}

	public ArrayList<String> getCodigo2IncorrectosPais() {
		return codigo2IncorrectosPais;
	}

	public void setCodigo2IncorrectosPais(ArrayList<String> codigo2IncorrectosPais) {
		this.codigo2IncorrectosPais = codigo2IncorrectosPais;
	}

	public boolean validateName(String name)
	{
		pattern = Pattern.compile(STRING_PATTERN);
		matcher = pattern.matcher(name);
		return matcher.matches();
	}
}
