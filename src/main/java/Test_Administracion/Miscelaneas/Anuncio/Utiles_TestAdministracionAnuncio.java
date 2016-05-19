package Test_Administracion.Miscelaneas.Anuncio;

import java.util.ArrayList;

public class Utiles_TestAdministracionAnuncio {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String habilitadoCorrecto = "true";
	private String seleccionarConcursoCorrecto = "0";
	//Atributos correctos general//////////////////////////////////////////////


	//-Anuncio-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Anuncio- para prueba
	private String idAnuncio = "77";

	//Lista de valores correctos para -Nombre de sitio- en -Sitio-
	private ArrayList<String> contenidosCorrectosSitio;

	//Lista de valores incorrectos para -Nombre de sitio- en -Sitio-
	private ArrayList<String> contenidosIncorrectosSitio;

	//-Anuncio-////////////////////////////////////////////////////////////


	public Utiles_TestAdministracionAnuncio(){

		//-Anuncio-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Contenido- en -Anuncio-
		contenidosCorrectosSitio=new ArrayList<>();
		contenidosCorrectosSitio.add("Welcome to the COJ Round Contest #2: send us any picture of you and/or your friends/teammates during the contest or near to it. Best pictures will be uploaded to public gallery of the competition. Thank you and have fun! ");
		contenidosCorrectosSitio.add("89*/*/as");


		//Listado de valores incorrectos para -Contenido- en -Anuncio-
		contenidosIncorrectosSitio=new ArrayList<>();
		contenidosIncorrectosSitio.add("");
		//-Anuncio-////////////////////////////////////////////////////////////


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



	public String getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(String idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public ArrayList<String> getContenidosCorrectosSitio() {
		return contenidosCorrectosSitio;
	}

	public void setContenidosCorrectosSitio(ArrayList<String> contenidosCorrectosSitio) {
		this.contenidosCorrectosSitio = contenidosCorrectosSitio;
	}

	public ArrayList<String> getContenidosIncorrectosSitio() {
		return contenidosIncorrectosSitio;
	}

	public void setContenidosIncorrectosSitio(ArrayList<String> contenidosIncorrectosSitio) {
		this.contenidosIncorrectosSitio = contenidosIncorrectosSitio;
	}

	public String getHabilitadoCorrecto() {
		return habilitadoCorrecto;
	}

	public void setHabilitadoCorrecto(String habilitadoCorrecto) {
		this.habilitadoCorrecto = habilitadoCorrecto;
	}

	public String getSeleccionarConcursoCorrecto() {
		return seleccionarConcursoCorrecto;
	}

	public void setSeleccionarConcursoCorrecto(String seleccionarConcursoCorrecto) {
		this.seleccionarConcursoCorrecto = seleccionarConcursoCorrecto;
	}
}
