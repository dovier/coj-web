package Test_Administracion.Problemas.Etiquetas;

import java.util.ArrayList;

public class Utiles_TestAdministracionEtiquetas {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//-Etiqueta-////////////////////////////////////////////////////////////


	//Lista de valores correctos para -Nombre- en -Etiqueta-
	private ArrayList<String> nombreCorrectosEtiqueta;

	//Lista de valores incorrectos para  -Nombre- en -Etiqueta-
	private ArrayList<String> nombreIncorrectosEtiqueta;

	//-Etiqueta-////////////////////////////////////////////////////////////


	public Utiles_TestAdministracionEtiquetas(){

		//-Etiqueta-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Nombre- en -Etiqueta-
		nombreCorrectosEtiqueta=new ArrayList<>();
		nombreCorrectosEtiqueta.add("Nombre");
		nombreCorrectosEtiqueta.add(" Nombre ");
		nombreCorrectosEtiqueta.add("Nombre123");
		nombreCorrectosEtiqueta.add("Nombre1234 -*/ ");

		//Listado de valores incorrectos para -Nombre- en -Etiqueta-
		nombreIncorrectosEtiqueta=new ArrayList<>();
		nombreIncorrectosEtiqueta.add("");
		nombreIncorrectosEtiqueta.add(" ");

		//-Etiqueta-////////////////////////////////////////////////////////////






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


	public ArrayList<String> getNombreCorrectosEtiqueta() {
		return nombreCorrectosEtiqueta;
	}

	public void setNombreCorrectosEtiqueta(ArrayList<String> nombreCorrectosEtiqueta) {
		this.nombreCorrectosEtiqueta = nombreCorrectosEtiqueta;
	}

	public ArrayList<String> getNombreIncorrectosEtiqueta() {
		return nombreIncorrectosEtiqueta;
	}

	public void setNombreIncorrectosEtiqueta(ArrayList<String> nombreIncorrectosEtiqueta) {
		this.nombreIncorrectosEtiqueta = nombreIncorrectosEtiqueta;
	}
}
