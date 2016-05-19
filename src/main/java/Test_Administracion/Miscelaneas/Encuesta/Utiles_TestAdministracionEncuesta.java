package Test_Administracion.Miscelaneas.Encuesta;

import java.util.ArrayList;

public class Utiles_TestAdministracionEncuesta {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String peguntaCorrecto = "Pregunta";
	private String Respuesta1Correcto = "Respuesta1";
	private String Respuesta2Correcto = "Respuesta2";
	private String Respuesta3Correcto = "Respuesta3";
	private String Respuesta4Correcto = "Respuesta4";
	private String Respuesta5Correcto = "Respuesta5";
	private String HabilitadoCorrecto = "true";


	//Atributos correctos general//////////////////////////////////////////////


	//-Encuesta-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Encuesta- para prueba
	private String idEncuesta = "8";

	//Lista de valores correctos para -Pregunta- en -Encuesta-
	private ArrayList<String> preguntasCorrectosEncuesta;

	//Lista de valores incorrectos para -Pregunta- en -Encuesta-
	private ArrayList<String> preguntasIncorrectosEncuesta;

	//Lista de valores correctos para -Respuesta1- en -Encuesta-
	private ArrayList<String> respuesta1CorrectosEncuesta;

	//Lista de valores incorrectos para -Respuesta1- en -Encuesta-
	private ArrayList<String> respuesta1IncorrectosEncuesta;

	//Lista de valores correctos para -Respuesta2- en -Encuesta-
	private ArrayList<String> respuesta2CorrectosEncuesta;

	//Lista de valores incorrectos para -Respuesta2- en -Encuesta-
	private ArrayList<String> respuesta2IncorrectosEncuesta;

	//Lista de valores correctos para -Respuesta3- en -Encuesta-
	private ArrayList<String> respuesta3CorrectosEncuesta;

	//Lista de valores incorrectos para -Respuesta3- en -Encuesta-
	private ArrayList<String> respuesta3IncorrectosEncuesta;

	//-Encuesta-////////////////////////////////////////////////////////////

	public Utiles_TestAdministracionEncuesta(){

		//-Encuesta-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Pregunta- en -Encuesta-
		preguntasCorrectosEncuesta=new ArrayList<>();
		preguntasCorrectosEncuesta.add("¿Pregunta-7?");
		preguntasCorrectosEncuesta.add("/*-.");


		//Listado de valores incorrectos para -Pregunta- en -Encuesta-
		preguntasIncorrectosEncuesta=new ArrayList<>();
		preguntasIncorrectosEncuesta.add("");
		preguntasIncorrectosEncuesta.add(" ");


		//Listado de valores correctos para -Respuesta1- en -Encuesta-
		respuesta1CorrectosEncuesta=new ArrayList<>();
		respuesta1CorrectosEncuesta.add("¿Respuesta-7?");
		respuesta1CorrectosEncuesta.add("/*-.");


		//Listado de valores incorrectos para -Respuesta1- en -Encuesta-
		respuesta1IncorrectosEncuesta=new ArrayList<>();
		respuesta1IncorrectosEncuesta.add("");
		respuesta1IncorrectosEncuesta.add(" ");

		//Listado de valores correctos para -Respuesta2- en -Encuesta-
		respuesta2CorrectosEncuesta=new ArrayList<>();
		respuesta2CorrectosEncuesta.add("¿Respuesta-7?");
		respuesta2CorrectosEncuesta.add("/*-.");


		//Listado de valores incorrectos para -Respuesta2- en -Encuesta-
		respuesta2IncorrectosEncuesta=new ArrayList<>();
		respuesta2IncorrectosEncuesta.add("");
		respuesta2IncorrectosEncuesta.add(" ");


		//Listado de valores correctos para -Respuesta3- en -Encuesta-
		respuesta3CorrectosEncuesta=new ArrayList<>();
		respuesta3CorrectosEncuesta.add("¿Respuesta-7?");
		respuesta3CorrectosEncuesta.add("/*-.");


		//Listado de valores incorrectos para -Respuesta3- en -Encuesta-
		respuesta3IncorrectosEncuesta=new ArrayList<>();
		respuesta3IncorrectosEncuesta.add("");
		respuesta3IncorrectosEncuesta.add(" ");
		//-Faq-////////////////////////////////////////////////////////////



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

	public String getPeguntaCorrecto() {
		return peguntaCorrecto;
	}

	public void setPeguntaCorrecto(String peguntaCorrecto) {
		this.peguntaCorrecto = peguntaCorrecto;
	}




	public String getRespuesta1Correcto() {
		return Respuesta1Correcto;
	}

	public void setRespuesta1Correcto(String respuesta1Correcto) {
		Respuesta1Correcto = respuesta1Correcto;
	}

	public String getRespuesta2Correcto() {
		return Respuesta2Correcto;
	}

	public void setRespuesta2Correcto(String respuesta2Correcto) {
		Respuesta2Correcto = respuesta2Correcto;
	}

	public String getRespuesta3Correcto() {
		return Respuesta3Correcto;
	}

	public void setRespuesta3Correcto(String respuesta3Correcto) {
		Respuesta3Correcto = respuesta3Correcto;
	}

	public String getRespuesta4Correcto() {
		return Respuesta4Correcto;
	}

	public void setRespuesta4Correcto(String respuesta4Correcto) {
		Respuesta4Correcto = respuesta4Correcto;
	}

	public String getRespuesta5Correcto() {
		return Respuesta5Correcto;
	}

	public void setRespuesta5Correcto(String respuesta5Correcto) {
		Respuesta5Correcto = respuesta5Correcto;
	}

	public String getHabilitadoCorrecto() {
		return HabilitadoCorrecto;
	}

	public void setHabilitadoCorrecto(String habilitadoCorrecto) {
		HabilitadoCorrecto = habilitadoCorrecto;
	}

	public ArrayList<String> getPreguntasCorrectosEncuesta() {
		return preguntasCorrectosEncuesta;
	}

	public void setPreguntasCorrectosEncuesta(ArrayList<String> preguntasCorrectosEncuesta) {
		this.preguntasCorrectosEncuesta = preguntasCorrectosEncuesta;
	}

	public ArrayList<String> getPreguntasIncorrectosEncuesta() {
		return preguntasIncorrectosEncuesta;
	}

	public void setPreguntasIncorrectosEncuesta(ArrayList<String> preguntasIncorrectosEncuesta) {
		this.preguntasIncorrectosEncuesta = preguntasIncorrectosEncuesta;
	}

	public String getIdEncuesta() {
		return idEncuesta;
	}

	public void setIdEncuesta(String idEncuesta) {
		this.idEncuesta = idEncuesta;
	}


	public ArrayList<String> getRespuesta1CorrectosEncuesta() {
		return respuesta1CorrectosEncuesta;
	}

	public void setRespuesta1CorrectosEncuesta(ArrayList<String> respuesta1CorrectosEncuesta) {
		this.respuesta1CorrectosEncuesta = respuesta1CorrectosEncuesta;
	}

	public ArrayList<String> getRespuesta1IncorrectosEncuesta() {
		return respuesta1IncorrectosEncuesta;
	}

	public void setRespuesta1IncorrectosEncuesta(ArrayList<String> respuesta1IncorrectosEncuesta) {
		this.respuesta1IncorrectosEncuesta = respuesta1IncorrectosEncuesta;
	}

	public ArrayList<String> getRespuesta2CorrectosEncuesta() {
		return respuesta2CorrectosEncuesta;
	}

	public void setRespuesta2CorrectosEncuesta(ArrayList<String> respuesta2CorrectosEncuesta) {
		this.respuesta2CorrectosEncuesta = respuesta2CorrectosEncuesta;
	}

	public ArrayList<String> getRespuesta2IncorrectosEncuesta() {
		return respuesta2IncorrectosEncuesta;
	}

	public void setRespuesta2IncorrectosEncuesta(ArrayList<String> respuesta2IncorrectosEncuesta) {
		this.respuesta2IncorrectosEncuesta = respuesta2IncorrectosEncuesta;
	}

	public ArrayList<String> getRespuesta3CorrectosEncuesta() {
		return respuesta3CorrectosEncuesta;
	}

	public void setRespuesta3CorrectosEncuesta(ArrayList<String> respuesta3CorrectosEncuesta) {
		this.respuesta3CorrectosEncuesta = respuesta3CorrectosEncuesta;
	}

	public ArrayList<String> getRespuesta3IncorrectosEncuesta() {
		return respuesta3IncorrectosEncuesta;
	}

	public void setRespuesta3IncorrectosEncuesta(ArrayList<String> respuesta3IncorrectosEncuesta) {
		this.respuesta3IncorrectosEncuesta = respuesta3IncorrectosEncuesta;
	}
}
