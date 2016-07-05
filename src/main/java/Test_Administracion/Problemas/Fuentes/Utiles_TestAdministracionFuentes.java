package Test_Administracion.Problemas.Fuentes;

import java.util.ArrayList;

public class Utiles_TestAdministracionFuentes {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String fuenteCorrecto = "Fuente";
	private String fuenteInCorrecto = "";
	private String autorCorrecta = "Autor Autor Autor";
	private String autorIncorrecta = "";


	//Atributos correctos general//////////////////////////////////////////////


	//-Fuente-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Fuente- para prueba
	private String idSource="616";

	//Lista de valores correctos para -Fuente- en -Fuente-
	private ArrayList<String> fuenteCorrectosFuente;

	//Lista de valores incorrectos para  -Fuente- en -Fuente-
	private ArrayList<String> fuenteIncorrectosFuente;


	//Lista de valores correctos para -Autor- en -Fuente-
	private ArrayList<String> autorCorrectosFuente;

	//Lista de valores incorrectos para  -Autor- en -Fuente-
	private ArrayList<String> autorIncorrectosFuente;

	//-Fuente-////////////////////////////////////////////////////////////


	public Utiles_TestAdministracionFuentes(){

		//-Fuentes-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Fuente- en -Fuente-
		fuenteCorrectosFuente=new ArrayList<>();
		fuenteCorrectosFuente.add("Fuente");
		fuenteCorrectosFuente.add(" Fuente");
		fuenteCorrectosFuente.add("Fuente Fuente");
		fuenteCorrectosFuente.add("Fuente ");

		//Listado de valores incorrectos para -Fuente- en -Fuente-
		fuenteIncorrectosFuente=new ArrayList<>();
		fuenteIncorrectosFuente.add("");
		fuenteIncorrectosFuente.add(" ");

		//Listado de valores correctos para -Autor- en -Fuente-
		autorCorrectosFuente=new ArrayList<>();
		autorCorrectosFuente.add("Autor");
		autorCorrectosFuente.add(" Autor");
		autorCorrectosFuente.add("Autor Autor");
		autorCorrectosFuente.add("Autor ");

		//Listado de valores incorrectos para -Autor- en -Fuente-
		autorIncorrectosFuente=new ArrayList<>();
		autorIncorrectosFuente.add("");
		autorIncorrectosFuente.add(" ");

		//-Fuentes-////////////////////////////////////////////////////////////






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


	public String getFuenteCorrecto() {
		return fuenteCorrecto;
	}

	public void setFuenteCorrecto(String fuenteCorrecto) {
		this.fuenteCorrecto = fuenteCorrecto;
	}

	public String getAutorCorrecta() {
		return autorCorrecta;
	}

	public void setAutorCorrecta(String autorCorrecta) {
		this.autorCorrecta = autorCorrecta;
	}

	public String getIdSource() {
		return idSource;
	}

	public void setIdSource(String idSource) {
		this.idSource = idSource;
	}

	public ArrayList<String> getFuenteCorrectosFuente() {
		return fuenteCorrectosFuente;
	}

	public void setFuenteCorrectosFuente(ArrayList<String> fuenteCorrectosFuente) {
		this.fuenteCorrectosFuente = fuenteCorrectosFuente;
	}

	public ArrayList<String> getFuenteIncorrectosFuente() {
		return fuenteIncorrectosFuente;
	}

	public void setFuenteIncorrectosFuente(ArrayList<String> fuenteIncorrectosFuente) {
		this.fuenteIncorrectosFuente = fuenteIncorrectosFuente;
	}

	public String getFuenteInCorrecto() {
		return fuenteInCorrecto;
	}

	public void setFuenteInCorrecto(String fuenteInCorrecto) {
		this.fuenteInCorrecto = fuenteInCorrecto;
	}

	public String getAutorIncorrecta() {
		return autorIncorrecta;
	}

	public void setAutorIncorrecta(String autorIncorrecta) {
		this.autorIncorrecta = autorIncorrecta;
	}

	public ArrayList<String> getAutorCorrectosFuente() {
		return autorCorrectosFuente;
	}

	public void setAutorCorrectosFuente(ArrayList<String> autorCorrectosFuente) {
		this.autorCorrectosFuente = autorCorrectosFuente;
	}

	public ArrayList<String> getAutorIncorrectosFuente() {
		return autorIncorrectosFuente;
	}

	public void setAutorIncorrectosFuente(ArrayList<String> autorIncorrectosFuente) {
		this.autorIncorrectosFuente = autorIncorrectosFuente;
	}
}
