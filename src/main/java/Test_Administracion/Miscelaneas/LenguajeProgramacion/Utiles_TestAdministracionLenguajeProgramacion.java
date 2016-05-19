package Test_Administracion.Miscelaneas.LenguajeProgramacion;

import java.util.ArrayList;

public class Utiles_TestAdministracionLenguajeProgramacion {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String lenguajeCorrecto = "java";
	private String llaveCorrecta = "****233232";
	private String descripcionCorrecta = "Prueba";
	private String nombreBinCorrecto = "bin";
	private String activadoCorrecto = "true";
	//Atributos correctos general//////////////////////////////////////////////


	//-Lenguaje de programacion-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Lenguaje de programacion- para prueba
	private String idLenguajeProgramacion = "21";

	//Lista de valores correctos para -Lenguaje- en -Lenguaje de programacion-
	private ArrayList<String> lenguajesCorrectosLenguajeProgramacion;

	//Lista de valores incorrectos para -Lenguaje- en -Lenguaje de programacion-
	private ArrayList<String> lenguajesIncorrectosLenguajeProgramacion;

	//Lista de valores correctos para -LLave- en -Lenguaje de programacion-
	private ArrayList<String> llavesCorrectosLenguajeProgramacion;

	//Lista de valores incorrectos para -LLave- en -Lenguaje de programacion-
	private ArrayList<String> llavesIncorrectosLenguajeProgramacion;

	//Lista de valores correctos para -Descripcion- en -Lenguaje de programacion-
	private ArrayList<String> descripcionCorrectosLenguajeProgramacion;

	//Lista de valores incorrectos para -Descripcion- en -Lenguaje de programacion-
	private ArrayList<String> descripcionIncorrectosLenguajeProgramacion;

	//Lista de valores correctos para -Nombre bin- en -Lenguaje de programacion-
	private ArrayList<String> nombreBinCorrectosLenguajeProgramacion;

	//Lista de valores incorrectos para -Nombre bin- en -Lenguaje de programacion-
	private ArrayList<String> nombreBinIncorrectosLenguajeProgramacion;
	//-Lenguaje de programacion-////////////////////////////////////////////////////////////

	public Utiles_TestAdministracionLenguajeProgramacion(){

		//-Lenguaje de programacion-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Lenguaje- en -Lenguaje de programacion-
		lenguajesCorrectosLenguajeProgramacion=new ArrayList<>();
		lenguajesCorrectosLenguajeProgramacion.add("java-7");
		lenguajesCorrectosLenguajeProgramacion.add("/*java");


		//Listado de valores incorrectos para -Lenguaje- en -Lenguaje de programacion-
		lenguajesIncorrectosLenguajeProgramacion=new ArrayList<>();
		lenguajesIncorrectosLenguajeProgramacion.add("");
		lenguajesIncorrectosLenguajeProgramacion.add(" ");

		//Listado de valores correctos para -Llave- en -Lenguaje de programacion-
		llavesCorrectosLenguajeProgramacion=new ArrayList<>();
		llavesCorrectosLenguajeProgramacion.add("llave-7");
		llavesCorrectosLenguajeProgramacion.add("/*llave");

		//Listado de valores incorrectos para -Llave- en -Lenguaje de programacion-
		llavesIncorrectosLenguajeProgramacion=new ArrayList<>();
		llavesIncorrectosLenguajeProgramacion.add("");
		llavesIncorrectosLenguajeProgramacion.add(" ");

		//Listado de valores correctos para -Descripcion- en -Lenguaje de programacion-
		descripcionCorrectosLenguajeProgramacion=new ArrayList<>();
		descripcionCorrectosLenguajeProgramacion.add("descripcion-7");
		descripcionCorrectosLenguajeProgramacion.add("/*descripcion");

		//Listado de valores incorrectos para -Descripcion- en -Lenguaje de programacion-
		descripcionIncorrectosLenguajeProgramacion=new ArrayList<>();
		descripcionIncorrectosLenguajeProgramacion.add("");
		descripcionIncorrectosLenguajeProgramacion.add(" ");

		//Listado de valores correctos para -Nombre bin- en -Lenguaje de programacion-
		nombreBinCorrectosLenguajeProgramacion=new ArrayList<>();
		nombreBinCorrectosLenguajeProgramacion.add("bin-7");
		nombreBinCorrectosLenguajeProgramacion.add("/*bin");

		//Listado de valores incorrectos para -Nombre bin- en -Lenguaje de programacion-
		nombreBinIncorrectosLenguajeProgramacion=new ArrayList<>();
		nombreBinIncorrectosLenguajeProgramacion.add("");
		nombreBinIncorrectosLenguajeProgramacion.add(" ");

		//-Lenguaje de programacion-////////////////////////////////////////////////////////////


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


	public String getLenguajeCorrecto() {
		return lenguajeCorrecto;
	}

	public void setLenguajeCorrecto(String lenguajeCorrecto) {
		this.lenguajeCorrecto = lenguajeCorrecto;
	}

	public String getLlaveCorrecta() {
		return llaveCorrecta;
	}

	public void setLlaveCorrecta(String llaveCorrecta) {
		this.llaveCorrecta = llaveCorrecta;
	}

	public String getDescripcionCorrecta() {
		return descripcionCorrecta;
	}

	public void setDescripcionCorrecta(String descripcionCorrecta) {
		this.descripcionCorrecta = descripcionCorrecta;
	}

	public String getNombreBinCorrecto() {
		return nombreBinCorrecto;
	}

	public void setNombreBinCorrecto(String nombreBinCorrecto) {
		this.nombreBinCorrecto = nombreBinCorrecto;
	}

	public String getActivadoCorrecto() {
		return activadoCorrecto;
	}

	public void setActivadoCorrecto(String activadoCorrecto) {
		this.activadoCorrecto = activadoCorrecto;
	}

	public String getIdLenguajeProgramacion() {
		return idLenguajeProgramacion;
	}

	public void setIdLenguajeProgramacion(String idLenguajeProgramacion) {
		this.idLenguajeProgramacion = idLenguajeProgramacion;
	}

	public ArrayList<String> getLenguajesCorrectosLenguajeProgramacion() {
		return lenguajesCorrectosLenguajeProgramacion;
	}

	public void setLenguajesCorrectosLenguajeProgramacion(ArrayList<String> lenguajesCorrectosLenguajeProgramacion) {
		this.lenguajesCorrectosLenguajeProgramacion = lenguajesCorrectosLenguajeProgramacion;
	}

	public ArrayList<String> getLenguajesIncorrectosLenguajeProgramacion() {
		return lenguajesIncorrectosLenguajeProgramacion;
	}

	public void setLenguajesIncorrectosLenguajeProgramacion(ArrayList<String> lenguajesIncorrectosLenguajeProgramacion) {
		this.lenguajesIncorrectosLenguajeProgramacion = lenguajesIncorrectosLenguajeProgramacion;
	}

	public ArrayList<String> getLlavesCorrectosLenguajeProgramacion() {
		return llavesCorrectosLenguajeProgramacion;
	}

	public void setLlavesCorrectosLenguajeProgramacion(ArrayList<String> llavesCorrectosLenguajeProgramacion) {
		this.llavesCorrectosLenguajeProgramacion = llavesCorrectosLenguajeProgramacion;
	}

	public ArrayList<String> getLlavesIncorrectosLenguajeProgramacion() {
		return llavesIncorrectosLenguajeProgramacion;
	}

	public void setLlavesIncorrectosLenguajeProgramacion(ArrayList<String> llavesIncorrectosLenguajeProgramacion) {
		this.llavesIncorrectosLenguajeProgramacion = llavesIncorrectosLenguajeProgramacion;
	}

	public ArrayList<String> getDescripcionCorrectosLenguajeProgramacion() {
		return descripcionCorrectosLenguajeProgramacion;
	}

	public void setDescripcionCorrectosLenguajeProgramacion(ArrayList<String> descripcionCorrectosLenguajeProgramacion) {
		this.descripcionCorrectosLenguajeProgramacion = descripcionCorrectosLenguajeProgramacion;
	}

	public ArrayList<String> getDescripcionIncorrectosLenguajeProgramacion() {
		return descripcionIncorrectosLenguajeProgramacion;
	}

	public void setDescripcionIncorrectosLenguajeProgramacion(ArrayList<String> descripcionIncorrectosLenguajeProgramacion) {
		this.descripcionIncorrectosLenguajeProgramacion = descripcionIncorrectosLenguajeProgramacion;
	}

	public ArrayList<String> getNombreBinCorrectosLenguajeProgramacion() {
		return nombreBinCorrectosLenguajeProgramacion;
	}

	public void setNombreBinCorrectosLenguajeProgramacion(ArrayList<String> nombreBinCorrectosLenguajeProgramacion) {
		this.nombreBinCorrectosLenguajeProgramacion = nombreBinCorrectosLenguajeProgramacion;
	}

	public ArrayList<String> getNombreBinIncorrectosLenguajeProgramacion() {
		return nombreBinIncorrectosLenguajeProgramacion;
	}

	public void setNombreBinIncorrectosLenguajeProgramacion(ArrayList<String> nombreBinIncorrectosLenguajeProgramacion) {
		this.nombreBinIncorrectosLenguajeProgramacion = nombreBinIncorrectosLenguajeProgramacion;
	}
}
