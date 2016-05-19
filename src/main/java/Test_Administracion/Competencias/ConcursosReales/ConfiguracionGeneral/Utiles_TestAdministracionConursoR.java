package Test_Administracion.Competencias.ConcursosReales.ConfiguracionGeneral;

import java.util.ArrayList;

public class Utiles_TestAdministracionConursoR {


	//Usuario y contrasenna de autenticacion////////////////////////////////////
	private String usuario="ybroche";
	private String contrasenna="adminpass123";
	//Usuario y contrasenna de autenticacion////////////////////////////////////


	//Atributos correctos general//////////////////////////////////////////////
	private String nombreCorrecto = "Nombre";
	private String registroCorrecto = "0";
	private String tipoConcursoCorrecto = "3";
	private String usuarioCorrecto = "1";
	private String activadoCorrecto = "true";
	private String estadoCorrecto = "true";
	private String agrupadosCorrecto = "true";


	private String regLinMuertaCorrecto = "2016-1-1 1:0:00.0";
	private String fechaInicialCorrecto = "2016-1-2 1:0:00.0";
	private String fechaFinalCorrecto = "2016-1-3 1:0:00.0";





	//Atributos correctos general//////////////////////////////////////////////


	//-Concurso real-////////////////////////////////////////////////////////////
	//Definicion del id de edicion de -Concurso real- para prueba
	private String idConcurso = "1371";

	//Lista de valores correctos para -Nombre- en -Concurso real-
	private ArrayList<String> NombresCorrectosConcurso;

	//Lista de valores incorrectos para -Nombre- en -Concurso real-
	private ArrayList<String> NombresIncorrectosConcurso;

	//Lista de valores correctos para -Registro- en -Concurso real-
	private ArrayList<String> RegistrosCorrectosConcurso;

	//Lista de valores incorrectos para -Registro- en -Concurso real-
	private ArrayList<String> RegistrosIncorrectosConcurso;

	//Trio de fechas correctas en -Concurso real-
	private ArrayList<ArrayList<String>> fechasCorrectas;

	//Trio de fechas incorrectas en -Concurso real-
	private ArrayList<ArrayList<String>> fechasIncorrectas;

	//Lista de valores correctos para -Tipo concurso- en -Concurso real-
	private ArrayList<String> tipoCorrectosConcurso;

	//Lista de valores incorrectos para -Tipo concurso- en -Concurso real-
	private ArrayList<String> tipoIncorrectosConcurso;

	//Lista de valores correctos para -Usuario- en -Concurso real-
	private ArrayList<String> usuarioCorrectosConcurso;

	//Lista de valores incorrectos para -Usuario- en -Concurso real-
	private ArrayList<String> usuarioIncorrectosConcurso;

    //-Concurso real-////////////////////////////////////////////////////////////

	public Utiles_TestAdministracionConursoR(){

		//-Concurso real-////////////////////////////////////////////////////////////
		//Listado de valores correctos para -Nombre- en -Concurso real-
		NombresCorrectosConcurso=new ArrayList<>();
		NombresCorrectosConcurso.add("Nombre");
		NombresCorrectosConcurso.add("Nombre Nombre");


		//Listado de valores incorrectos para -Nombre- en -Concurso real-
		NombresIncorrectosConcurso=new ArrayList<>();
		NombresIncorrectosConcurso.add("");
		NombresIncorrectosConcurso.add(" ");

		//Listado de valores correctos para -Registro- en -Concurso real-
		RegistrosCorrectosConcurso=new ArrayList<>();
		RegistrosCorrectosConcurso.add("2");

		//Listado de valores incorrectos para -Registro- en -Concurso real-
		RegistrosIncorrectosConcurso=new ArrayList<>();
		RegistrosIncorrectosConcurso.add("");

		//Listado de trio de fechas correctas en -Concurso real-
		ArrayList<String> primerTrioCorrecto=new ArrayList<>();
		primerTrioCorrecto.add(regLinMuertaCorrecto);
		primerTrioCorrecto.add(fechaInicialCorrecto);
		primerTrioCorrecto.add(fechaFinalCorrecto);

		fechasCorrectas=new ArrayList<>();
		fechasCorrectas.add(primerTrioCorrecto);

		//Listado de trio de fechas incorrectas en -Concurso real-
		ArrayList<String> primerTrioIncorrecto=new ArrayList<>();
		primerTrioIncorrecto.add(fechaInicialCorrecto);
		primerTrioIncorrecto.add(fechaFinalCorrecto);
		primerTrioIncorrecto.add(regLinMuertaCorrecto);

		ArrayList<String> segundoTrioIncorrecto=new ArrayList<>();
		segundoTrioIncorrecto.add(fechaFinalCorrecto);
		segundoTrioIncorrecto.add(regLinMuertaCorrecto);
		segundoTrioIncorrecto.add(fechaInicialCorrecto);

		ArrayList<String> tercerTrioIncorrecto=new ArrayList<>();
		tercerTrioIncorrecto.add(fechaInicialCorrecto);
		tercerTrioIncorrecto.add(fechaInicialCorrecto);
		tercerTrioIncorrecto.add(fechaInicialCorrecto);

		fechasIncorrectas=new ArrayList<>();
		fechasIncorrectas.add(primerTrioIncorrecto);
		fechasIncorrectas.add(segundoTrioIncorrecto);
		fechasIncorrectas.add(tercerTrioIncorrecto);

		//Listado de valores correctos para -Tipo- en -Concurso real-
		tipoCorrectosConcurso=new ArrayList<>();
		tipoCorrectosConcurso.add("3");

		//Listado de valores incorrectos para -Tipo- en -Concurso real-
		tipoIncorrectosConcurso=new ArrayList<>();
		tipoIncorrectosConcurso.add("");

		//Listado de valores correctos para -Usuario- en -Concurso real-
		usuarioCorrectosConcurso=new ArrayList<>();
		usuarioCorrectosConcurso.add("1");

		//Listado de valores incorrectos para -Usuario- en -Concurso real-
		usuarioIncorrectosConcurso=new ArrayList<>();
		usuarioIncorrectosConcurso.add("");

		//-Concurso real-////////////////////////////////////////////////////////////


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

	public String getRegistroCorrecto() {
		return registroCorrecto;
	}

	public void setRegistroCorrecto(String registroCorrecto) {
		this.registroCorrecto = registroCorrecto;
	}

	public String getTipoConcursoCorrecto() {
		return tipoConcursoCorrecto;
	}

	public void setTipoConcursoCorrecto(String tipoConcursoCorrecto) {
		this.tipoConcursoCorrecto = tipoConcursoCorrecto;
	}

	public String getUsuarioCorrecto() {
		return usuarioCorrecto;
	}

	public void setUsuarioCorrecto(String usuarioCorrecto) {
		this.usuarioCorrecto = usuarioCorrecto;
	}

	public String getActivadoCorrecto() {
		return activadoCorrecto;
	}

	public void setActivadoCorrecto(String activadoCorrecto) {
		this.activadoCorrecto = activadoCorrecto;
	}

	public String getEstadoCorrecto() {
		return estadoCorrecto;
	}

	public void setEstadoCorrecto(String estadoCorrecto) {
		this.estadoCorrecto = estadoCorrecto;
	}

	public String getAgrupadosCorrecto() {
		return agrupadosCorrecto;
	}

	public void setAgrupadosCorrecto(String agrupadosCorrecto) {
		this.agrupadosCorrecto = agrupadosCorrecto;
	}

	public String getRegLinMuertaCorrecto() {
		return regLinMuertaCorrecto;
	}

	public void setRegLinMuertaCorrecto(String regLinMuertaCorrecto) {
		this.regLinMuertaCorrecto = regLinMuertaCorrecto;
	}

	public String getFechaInicialCorrecto() {
		return fechaInicialCorrecto;
	}

	public void setFechaInicialCorrecto(String fechaInicialCorrecto) {
		this.fechaInicialCorrecto = fechaInicialCorrecto;
	}

	public String getFechaFinalCorrecto() {
		return fechaFinalCorrecto;
	}

	public void setFechaFinalCorrecto(String fechaFinalCorrecto) {
		this.fechaFinalCorrecto = fechaFinalCorrecto;
	}

	public String getIdConcurso() {
		return idConcurso;
	}

	public void setIdConcurso(String idConcurso) {
		this.idConcurso = idConcurso;
	}

	public ArrayList<String> getNombresCorrectosConcurso() {
		return NombresCorrectosConcurso;
	}

	public void setNombresCorrectosConcurso(ArrayList<String> nombresCorrectosConcurso) {
		NombresCorrectosConcurso = nombresCorrectosConcurso;
	}

	public ArrayList<String> getNombresIncorrectosConcurso() {
		return NombresIncorrectosConcurso;
	}

	public void setNombresIncorrectosConcurso(ArrayList<String> nombresIncorrectosConcurso) {
		NombresIncorrectosConcurso = nombresIncorrectosConcurso;
	}

	public String ExtraerDeFecha (String fechaCompleta, String valor)
	{
		String[] partes= fechaCompleta.split(" ");
		String fecha=partes[0];
		String horaCompleta=partes[1];

		String[]partesFecha=fecha.split("-");
		String anno=partesFecha[0];
		String mes=partesFecha[1];
		String dia=partesFecha[2];

		String[]partesHora=horaCompleta.split(":");
		String hora=partesHora[0];
		String minuto=partesHora[1];
		String segundo=partesHora[2];

		switch (valor) {
			case "anno":
				return anno;

			case "mes":
				return mes;

			case "dia":
				return dia;

			case "hora":
				return hora;

			case "minuto":
				return minuto;

			case "segundo": {
				if(segundo.equals("00.0"))
					segundo="0";
				return segundo;
			}



		}

		return "fallo";



	}


	public ArrayList<String> getRegistrosCorrectosConcurso() {
		return RegistrosCorrectosConcurso;
	}

	public void setRegistrosCorrectosConcurso(ArrayList<String> registrosCorrectosConcurso) {
		RegistrosCorrectosConcurso = registrosCorrectosConcurso;
	}

	public ArrayList<String> getRegistrosIncorrectosConcurso() {
		return RegistrosIncorrectosConcurso;
	}

	public void setRegistrosIncorrectosConcurso(ArrayList<String> registrosIncorrectosConcurso) {
		RegistrosIncorrectosConcurso = registrosIncorrectosConcurso;
	}



	public ArrayList<String> getTipoCorrectosConcurso() {
		return tipoCorrectosConcurso;
	}

	public void setTipoCorrectosConcurso(ArrayList<String> tipoCorrectosConcurso) {
		this.tipoCorrectosConcurso = tipoCorrectosConcurso;
	}

	public ArrayList<String> getTipoIncorrectosConcurso() {
		return tipoIncorrectosConcurso;
	}

	public void setTipoIncorrectosConcurso(ArrayList<String> tipoIncorrectosConcurso) {
		this.tipoIncorrectosConcurso = tipoIncorrectosConcurso;
	}


	public ArrayList<ArrayList<String>> getFechasCorrectas() {
		return fechasCorrectas;
	}

	public void setFechasCorrectas(ArrayList<ArrayList<String>> fechasCorrectas) {
		this.fechasCorrectas = fechasCorrectas;
	}

	public ArrayList<ArrayList<String>> getFechasIncorrectas() {
		return fechasIncorrectas;
	}

	public void setFechasIncorrectas(ArrayList<ArrayList<String>> fechasIncorrectas) {
		this.fechasIncorrectas = fechasIncorrectas;
	}


	public ArrayList<String> getUsuarioCorrectosConcurso() {
		return usuarioCorrectosConcurso;
	}

	public void setUsuarioCorrectosConcurso(ArrayList<String> usuarioCorrectosConcurso) {
		this.usuarioCorrectosConcurso = usuarioCorrectosConcurso;
	}

	public ArrayList<String> getUsuarioIncorrectosConcurso() {
		return usuarioIncorrectosConcurso;
	}

	public void setUsuarioIncorrectosConcurso(ArrayList<String> usuarioIncorrectosConcurso) {
		this.usuarioIncorrectosConcurso = usuarioIncorrectosConcurso;
	}
}
