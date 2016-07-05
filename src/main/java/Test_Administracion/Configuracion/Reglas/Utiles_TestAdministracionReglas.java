package Test_Administracion.Configuracion.Reglas;

import java.util.ArrayList;

public class Utiles_TestAdministracionReglas {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String nombreCorrecto = "10.0.0.0";
	//Atributos correctos general//////////////////////////////////////////////


	//-Competencia-////////////////////////////////////////////////////////////
	//Lista de valores correctos para -NombreRegla- en -Reglas-
	private ArrayList<String> nombresCorrectosReglas;

	//Lista de valores incorrectos para -NombreRegla- en -Reglas-
	private ArrayList<String> nombresIncorrectosReglas;

	//-Competencia-////////////////////////////////////////////////////////////


	public Utiles_TestAdministracionReglas(){

		//-Reglas-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Nombre- en -Reglas-
		nombresCorrectosReglas=new ArrayList<>();
		nombresCorrectosReglas.add("10.5.5.*");


		//Listado de valores incorrectos para -Nombre- en -Reglas-
		nombresIncorrectosReglas=new ArrayList<>();
		nombresIncorrectosReglas.add("");
		nombresIncorrectosReglas.add(" ");

		//-Competencia-////////////////////////////////////////////////////////////






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


	public ArrayList<String> getNombresCorrectosReglas() {
		return nombresCorrectosReglas;
	}

	public void setNombresCorrectosReglas(ArrayList<String> nombresCorrectosReglas) {
		this.nombresCorrectosReglas = nombresCorrectosReglas;
	}

	public ArrayList<String> getNombresIncorrectosReglas() {
		return nombresIncorrectosReglas;
	}

	public void setNombresIncorrectosReglas(ArrayList<String> nombresIncorrectosReglas) {
		this.nombresIncorrectosReglas = nombresIncorrectosReglas;
	}
}
