package Test_Administracion.Competencias.Equipo;

import java.util.ArrayList;

public class Utiles_TestAdministracionEquipo {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////

	private String userNameEquipo="Nouserexist";
	private String apodoCorrecto = "Nonickexist";
	private String modificarApodoCorrecta = "false";
	private String contrasennaCorrecto="constrasenna*contrasenna";
	private String contrasennaCCorrecto="constrasenna*contrasenna";
	private String totalCorrecto="2";
	private String paisCorrecta = "27";
	private String institucionCorrecta = "4445";
	private String idiomaCorrecto="1";
	private String concursoCorrecto="0";

	//Atributos correctos general//////////////////////////////////////////////


	//-Equipo-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Equipo- para prueba

	//Lista de valores correctos para -userName- en -Equipo-
	private ArrayList<String> userNameCorrectosEquipo;

	//Lista de valores incorrectos para -userName- en -Equipo-
	private ArrayList<String> userNameIncorrectosEquipo;

	//Lista de valores correctos para -Nombre- en -Equipo-
	private ArrayList<String> apodoCorrectosEquipo;

	//Lista de valores incorrectos para -Nombre- en -Equipo-
	private ArrayList<String> apodoIncorrectosEquipo;

	//Duo de constrasennas correctas en -Equipo-
	private ArrayList<ArrayList<String>> contrasennasCorrectosEquipo;

	//Duo de constrasennas inccorrectas en -Equipo-
	private ArrayList<ArrayList<String>> contrasennasIncorrectosEquipo;


	//Lista de valores correctos para -totalEquipo- en -Equipo-
	private ArrayList<String> totalCorrectosEquipo;

	//Lista de valores incorrectos para -totalEquipo- en -Equipo-
	private ArrayList<String> totalIncorrectosEquipo;

	//Lista de valores correctos para -Pais- en -Equipo-
	private ArrayList<String> paisCorrectosEquipo;

	//Lista de valores correctos para -Pais- en -Equipo-
	private ArrayList<String> paisIncorrectosEquipo;

	//Lista de valores correctos para -Institucion- en -Equipo-
	private ArrayList<String> institucionsCorrectosEquipo;

	//Lista de valores correctos para -Institucion- en -Equipo-
	private ArrayList<String> institucionIncorrectosEquipo;

	//Lista de valores correctos para -Idioma- en -Equipo-
	private ArrayList<String> idiomaCorrectosEquipo;

	//Lista de valores incorrectos para -Idioma- en -Equipo-
	private ArrayList<String> idiomaIncorrectosEquipo;


	//-Equipo-////////////////////////////////////////////////////////////


	public Utiles_TestAdministracionEquipo(){

		//-Equipo-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Apodo- en -Equipo-
		apodoCorrectosEquipo=new ArrayList<>();
		apodoCorrectosEquipo.add("Apodo");
		apodoCorrectosEquipo.add("Apodo123");
		apodoCorrectosEquipo.add("123");


		//Listado de valores incorrectos para -Apodo- en -Equipo-
		apodoIncorrectosEquipo=new ArrayList<>();
		apodoIncorrectosEquipo.add("");
		apodoIncorrectosEquipo.add(" ");
		apodoIncorrectosEquipo.add("a");
		apodoIncorrectosEquipo.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		//Listado de valores correctos para -Usuario- en -Equipo-
		userNameCorrectosEquipo=new ArrayList<>();
		userNameCorrectosEquipo.add("Usuario");
		userNameCorrectosEquipo.add("Usuario123");
		userNameCorrectosEquipo.add("123");


		//Listado de valores incorrectos para -Usuario- en -Equipo-
		userNameIncorrectosEquipo=new ArrayList<>();
		userNameIncorrectosEquipo.add("");
		userNameIncorrectosEquipo.add(" ");
		userNameIncorrectosEquipo.add("a");
		userNameIncorrectosEquipo.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		//Listado de valores correctos para duo de contrasennas- en -Equipo-
		ArrayList<String> pc1=new ArrayList<>();
		pc1.add("aaaaaaaaaaaaaaaaaaaaaa");
		pc1.add("aaaaaaaaaaaaaaaaaaaaaa");

		ArrayList<String> pc2=new ArrayList<>();
		pc2.add("45454aa6522/*-65saaaa");
		pc2.add("45454aa6522/*-65saaaa");

		contrasennasCorrectosEquipo=new ArrayList<>();
		contrasennasCorrectosEquipo.add(pc1);
		contrasennasCorrectosEquipo.add(pc2);

		//Listado de valores incorrectos para duo de contrasennas- en -Equipo-
		ArrayList<String> pi1=new ArrayList<>();
		pi1.add("");
		pi1.add("");

		ArrayList<String> pi2=new ArrayList<>();
		pi2.add("aaaaaaaaaaaaaaaaaaaa");
		pi2.add("");

		ArrayList<String> pi3=new ArrayList<>();
		pi3.add("");
		pi3.add("aaaaaaaaaaaaaaaaaaaa");

		ArrayList<String> pi4=new ArrayList<>();
		pi4.add("bbbbbbbbbbbbbbbbbbbb");
		pi4.add("aaaaaaaaaaaaaaaaaaaa");

		ArrayList<String> pi5=new ArrayList<>();
		pi5.add("a");
		pi5.add("a");

		contrasennasIncorrectosEquipo=new ArrayList<>();
		contrasennasIncorrectosEquipo.add(pi1);
		contrasennasIncorrectosEquipo.add(pi2);
		contrasennasIncorrectosEquipo.add(pi3);
		contrasennasIncorrectosEquipo.add(pi4);
		contrasennasIncorrectosEquipo.add(pi5);

		//Listado de valores correctos para -Total- en -Equipo-
		totalCorrectosEquipo=new ArrayList<>();
		totalCorrectosEquipo.add("2");
		totalCorrectosEquipo.add("100");



		//Listado de valores incorrectos para -Total- en -Equipo-
		totalIncorrectosEquipo=new ArrayList<>();
		totalIncorrectosEquipo.add("");
		totalIncorrectosEquipo.add(" ");
		totalIncorrectosEquipo.add("-3");
		totalIncorrectosEquipo.add("0");

		//Listado de valores correctos para -Pais- en -Equipo-
		paisCorrectosEquipo=new ArrayList<>();
		paisCorrectosEquipo.add("2");


		//Listado de valores incorrectos para -Pais- en -Equipo-
		paisIncorrectosEquipo=new ArrayList<>();
		paisIncorrectosEquipo.add("");
		paisIncorrectosEquipo.add(" ");
		paisIncorrectosEquipo.add("");


		//Listado de valores correctos para -Institucion- en -Equipo-
		institucionsCorrectosEquipo=new ArrayList<>();
		institucionsCorrectosEquipo.add("4445");



		//Listado de valores incorrectos para -Institucion- en -Equipo-
		institucionIncorrectosEquipo=new ArrayList<>();
		institucionIncorrectosEquipo.add("");
		institucionIncorrectosEquipo.add(" ");
		institucionIncorrectosEquipo.add("0");


		//Listado de valores correctos para -Idioma- en -Equipo-
		idiomaCorrectosEquipo=new ArrayList<>();
		idiomaCorrectosEquipo.add("1");
		idiomaCorrectosEquipo.add("2");


		//Listado de valores incorrectos para -Idioma- en -Equipo-
		idiomaIncorrectosEquipo=new ArrayList<>();
		idiomaIncorrectosEquipo.add("");
		idiomaIncorrectosEquipo.add(" ");
		idiomaIncorrectosEquipo.add("0");

		//-Equipo-////////////////////////////////////////////////////////////






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


	public String getApodoCorrecto() {
		return apodoCorrecto;
	}

	public void setApodoCorrecto(String apodoCorrecto) {
		this.apodoCorrecto = apodoCorrecto;
	}

	public String getModificarApodoCorrecta() {
		return modificarApodoCorrecta;
	}

	public void setModificarApodoCorrecta(String modificarApodoCorrecta) {
		this.modificarApodoCorrecta = modificarApodoCorrecta;
	}

	public String getPaisCorrecta() {
		return paisCorrecta;
	}

	public void setPaisCorrecta(String paisCorrecta) {
		this.paisCorrecta = paisCorrecta;
	}

	public String getInstitucionCorrecta() {
		return institucionCorrecta;
	}

	public void setInstitucionCorrecta(String institucionCorrecta) {
		this.institucionCorrecta = institucionCorrecta;
	}

	public String getIdiomaCorrecto() {
		return idiomaCorrecto;
	}

	public void setIdiomaCorrecto(String idiomaCorrecto) {
		this.idiomaCorrecto = idiomaCorrecto;
	}


	public String getContrasennaCorrecto() {
		return contrasennaCorrecto;
	}

	public void setContrasennaCorrecto(String contrasennaCorrecto) {
		this.contrasennaCorrecto = contrasennaCorrecto;
	}

	public String getContrasennaCCorrecto() {
		return contrasennaCCorrecto;
	}

	public void setContrasennaCCorrecto(String contrasennaCCorrecto) {
		this.contrasennaCCorrecto = contrasennaCCorrecto;
	}




	public String getUserNameEquipo() {
		return userNameEquipo;
	}

	public void setUserNameEquipo(String userNameEquipo) {
		this.userNameEquipo = userNameEquipo;
	}

	public ArrayList<String> getApodoCorrectosEquipo() {
		return apodoCorrectosEquipo;
	}

	public void setApodoCorrectosEquipo(ArrayList<String> apodoCorrectosEquipo) {
		this.apodoCorrectosEquipo = apodoCorrectosEquipo;
	}

	public ArrayList<String> getApodoIncorrectosEquipo() {
		return apodoIncorrectosEquipo;
	}

	public void setApodoIncorrectosEquipo(ArrayList<String> apodoIncorrectosEquipo) {
		this.apodoIncorrectosEquipo = apodoIncorrectosEquipo;
	}


	public String getTotalCorrecto() {
		return totalCorrecto;
	}

	public void setTotalCorrecto(String totalCorrecto) {
		this.totalCorrecto = totalCorrecto;
	}

	public String getConcursoCorrecto() {
		return concursoCorrecto;
	}

	public void setConcursoCorrecto(String concursoCorrecto) {
		this.concursoCorrecto = concursoCorrecto;
	}


	public ArrayList<String> getUserNameCorrectosEquipo() {
		return userNameCorrectosEquipo;
	}

	public void setUserNameCorrectosEquipo(ArrayList<String> userNameCorrectosEquipo) {
		this.userNameCorrectosEquipo = userNameCorrectosEquipo;
	}

	public ArrayList<String> getUserNameIncorrectosEquipo() {
		return userNameIncorrectosEquipo;
	}

	public void setUserNameIncorrectosEquipo(ArrayList<String> userNameIncorrectosEquipo) {
		this.userNameIncorrectosEquipo = userNameIncorrectosEquipo;
	}

	public ArrayList<ArrayList<String>> getContrasennasCorrectosEquipo() {
		return contrasennasCorrectosEquipo;
	}

	public void setContrasennasCorrectosEquipo(ArrayList<ArrayList<String>> contrasennasCorrectosEquipo) {
		this.contrasennasCorrectosEquipo = contrasennasCorrectosEquipo;
	}

	public ArrayList<ArrayList<String>> getContrasennasIncorrectosEquipo() {
		return contrasennasIncorrectosEquipo;
	}

	public void setContrasennasIncorrectosEquipo(ArrayList<ArrayList<String>> contrasennasIncorrectosEquipo) {
		this.contrasennasIncorrectosEquipo = contrasennasIncorrectosEquipo;
	}

	public ArrayList<String> getTotalCorrectosEquipo() {
		return totalCorrectosEquipo;
	}

	public void setTotalCorrectosEquipo(ArrayList<String> totalCorrectosEquipo) {
		this.totalCorrectosEquipo = totalCorrectosEquipo;
	}

	public ArrayList<String> getTotalIncorrectosEquipo() {
		return totalIncorrectosEquipo;
	}

	public void setTotalIncorrectosEquipo(ArrayList<String> totalIncorrectosEquipo) {
		this.totalIncorrectosEquipo = totalIncorrectosEquipo;
	}



	public ArrayList<String> getIdiomaCorrectosEquipo() {
		return idiomaCorrectosEquipo;
	}

	public void setIdiomaCorrectosEquipo(ArrayList<String> idiomaCorrectosEquipo) {
		this.idiomaCorrectosEquipo = idiomaCorrectosEquipo;
	}

	public ArrayList<String> getIdiomaIncorrectosEquipo() {
		return idiomaIncorrectosEquipo;
	}

	public void setIdiomaIncorrectosEquipo(ArrayList<String> idiomaIncorrectosEquipo) {
		this.idiomaIncorrectosEquipo = idiomaIncorrectosEquipo;
	}

	public ArrayList<String> getPaisCorrectosEquipo() {
		return paisCorrectosEquipo;
	}

	public void setPaisCorrectosEquipo(ArrayList<String> paisCorrectosEquipo) {
		this.paisCorrectosEquipo = paisCorrectosEquipo;
	}

	public ArrayList<String> getPaisIncorrectosEquipo() {
		return paisIncorrectosEquipo;
	}

	public void setPaisIncorrectosEquipo(ArrayList<String> paisIncorrectosEquipo) {
		this.paisIncorrectosEquipo = paisIncorrectosEquipo;
	}

	public ArrayList<String> getInstitucionsCorrectosEquipo() {
		return institucionsCorrectosEquipo;
	}

	public void setInstitucionsCorrectosEquipo(ArrayList<String> institucionsCorrectosEquipo) {
		this.institucionsCorrectosEquipo = institucionsCorrectosEquipo;
	}

	public ArrayList<String> getInstitucionIncorrectosEquipo() {
		return institucionIncorrectosEquipo;
	}

	public void setInstitucionIncorrectosEquipo(ArrayList<String> institucionIncorrectosEquipo) {
		this.institucionIncorrectosEquipo = institucionIncorrectosEquipo;
	}
}
