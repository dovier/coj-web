package Test_Administracion.Miscelaneas.Faq;

import java.util.ArrayList;

public class Utiles_TestAdministracionFaq {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String peguntaCorrecto = "Pregunta";
	private String respuestaCorrecta = "Respuesta";

	//Atributos correctos general//////////////////////////////////////////////


	//-Faq-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Lenguaje de programacion- para prueba
	private String idFaq = "22";

	//Lista de valores correctos para -Pregunta- en -Faq-
	private ArrayList<String> preguntasCorrectosFaq;

	//Lista de valores incorrectos para -Pregunta- en -Faq-
	private ArrayList<String> preguntasIncorrectosFaq;

	//Lista de valores correctos para -Respuesta- en -Faq-
	private ArrayList<String> respuestasCorrectosFaq;

	//Lista de valores incorrectos para -Respuesta- en -Faq-
	private ArrayList<String> respuestasIncorrectosFaq;

	//-Faq-////////////////////////////////////////////////////////////

	public Utiles_TestAdministracionFaq(){

		//-Faq-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Pregunta- en -Faq-
		preguntasCorrectosFaq=new ArrayList<>();
		preguntasCorrectosFaq.add("¿Pregunta-7?");
		preguntasCorrectosFaq.add("/*-.");


		//Listado de valores incorrectos para -Pregunta- en -Faq-
		preguntasIncorrectosFaq=new ArrayList<>();
		preguntasIncorrectosFaq.add("");
		preguntasIncorrectosFaq.add(" ");

		//Listado de valores correctos para -Respuesta- en -Faq-
		respuestasCorrectosFaq=new ArrayList<>();
		respuestasCorrectosFaq.add("¿Respuesta-7?");
		respuestasCorrectosFaq.add("/*-.");

		//Listado de valores incorrectos para -Respuesta- en -Faq-
		respuestasIncorrectosFaq=new ArrayList<>();
		respuestasIncorrectosFaq.add("");
		respuestasIncorrectosFaq.add(" ");


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

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public String getIdFaq() {
		return idFaq;
	}

	public void setIdFaq(String idFaq) {
		this.idFaq = idFaq;
	}

	public ArrayList<String> getPreguntasCorrectosFaq() {
		return preguntasCorrectosFaq;
	}

	public void setPreguntasCorrectosFaq(ArrayList<String> preguntasCorrectosFaq) {
		this.preguntasCorrectosFaq = preguntasCorrectosFaq;
	}

	public ArrayList<String> getPreguntasIncorrectosFaq() {
		return preguntasIncorrectosFaq;
	}

	public void setPreguntasIncorrectosFaq(ArrayList<String> preguntasIncorrectosFaq) {
		this.preguntasIncorrectosFaq = preguntasIncorrectosFaq;
	}

	public ArrayList<String> getRespuestasCorrectosFaq() {
		return respuestasCorrectosFaq;
	}

	public void setRespuestasCorrectosFaq(ArrayList<String> respuestasCorrectosFaq) {
		this.respuestasCorrectosFaq = respuestasCorrectosFaq;
	}

	public ArrayList<String> getRespuestasIncorrectosFaq() {
		return respuestasIncorrectosFaq;
	}

	public void setRespuestasIncorrectosFaq(ArrayList<String> respuestasIncorrectosFaq) {
		this.respuestasIncorrectosFaq = respuestasIncorrectosFaq;
	}
}
