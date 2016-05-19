package Test_Administracion.Miscelaneas.NotificacionesPorCorreo;

import java.util.ArrayList;

public class Utiles_TestAdministracionNotificacionesCorreo {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String asuntoCorrecto = "asunto";
	private String cuerpoCorrecta = "cuerpo";
	//Atributos correctos general//////////////////////////////////////////////


	//-Notificaciones por correo-////////////////////////////////////////////////////////////

	//Lista de valores correctos para -Asunto- en -Notificaciones por correo-
	private ArrayList<String> asuntosCorrectosLenguajeProgramacion;

	//Lista de valores incorrectos para -Asunto- en -Notificaciones por correo-
	private ArrayList<String> asuntosIncorrectosLenguajeProgramacion;

	//Lista de valores correctos para -Cuerpo- en -Notificaciones por correo-
	private ArrayList<String> cuerposCorrectosLenguajeProgramacion;

	//Lista de valores incorrectos para -Cuerpo- en -Notificaciones por correo-
	private ArrayList<String> cuerposIncorrectosLenguajeProgramacion;
	//-Notificaciones por correo-////////////////////////////////////////////////////////////

	public Utiles_TestAdministracionNotificacionesCorreo(){

		//-Notificaciones por correo-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Asunto- en -Notificaciones por correo-
		asuntosCorrectosLenguajeProgramacion=new ArrayList<>();
		asuntosCorrectosLenguajeProgramacion.add("Asunto123/*-");


		//Listado de valores incorrectos para -Asunto- en -Notificaciones por correo-
		asuntosIncorrectosLenguajeProgramacion=new ArrayList<>();
		asuntosIncorrectosLenguajeProgramacion.add("");


		//Listado de valores correctos para -Cuerpo- en -Notificaciones por correo-
		cuerposCorrectosLenguajeProgramacion=new ArrayList<>();
		cuerposCorrectosLenguajeProgramacion.add("Cuerpo123/*-");


		//Listado de valores incorrectos para -Cuerpo- en -Notificaciones por correo-
		cuerposIncorrectosLenguajeProgramacion=new ArrayList<>();
		cuerposIncorrectosLenguajeProgramacion.add("");

		//-Notificaciones por correo-////////////////////////////////////////////////////////////


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

	public String getAsuntoCorrecto() {
		return asuntoCorrecto;
	}

	public void setAsuntoCorrecto(String asuntoCorrecto) {
		this.asuntoCorrecto = asuntoCorrecto;
	}

	public String getCuerpoCorrecta() {
		return cuerpoCorrecta;
	}

	public void setCuerpoCorrecta(String cuerpoCorrecta) {
		this.cuerpoCorrecta = cuerpoCorrecta;
	}

	public ArrayList<String> getAsuntosCorrectosLenguajeProgramacion() {
		return asuntosCorrectosLenguajeProgramacion;
	}

	public void setAsuntosCorrectosLenguajeProgramacion(ArrayList<String> asuntosCorrectosLenguajeProgramacion) {
		this.asuntosCorrectosLenguajeProgramacion = asuntosCorrectosLenguajeProgramacion;
	}

	public ArrayList<String> getAsuntosIncorrectosLenguajeProgramacion() {
		return asuntosIncorrectosLenguajeProgramacion;
	}

	public void setAsuntosIncorrectosLenguajeProgramacion(ArrayList<String> asuntosIncorrectosLenguajeProgramacion) {
		this.asuntosIncorrectosLenguajeProgramacion = asuntosIncorrectosLenguajeProgramacion;
	}

	public ArrayList<String> getCuerposCorrectosLenguajeProgramacion() {
		return cuerposCorrectosLenguajeProgramacion;
	}

	public void setCuerposCorrectosLenguajeProgramacion(ArrayList<String> cuerposCorrectosLenguajeProgramacion) {
		this.cuerposCorrectosLenguajeProgramacion = cuerposCorrectosLenguajeProgramacion;
	}

	public ArrayList<String> getCuerposIncorrectosLenguajeProgramacion() {
		return cuerposIncorrectosLenguajeProgramacion;
	}

	public void setCuerposIncorrectosLenguajeProgramacion(ArrayList<String> cuerposIncorrectosLenguajeProgramacion) {
		this.cuerposIncorrectosLenguajeProgramacion = cuerposIncorrectosLenguajeProgramacion;
	}
}
