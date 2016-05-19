package Test_Administracion;


import Test_Administracion.COJBoard.Competencia.*;
import Test_Administracion.COJBoard.Sitio.*;
import Test_Administracion.Competencias.ConcursosReales.AdicionarConcurso.AdicionarConcurso_Test;
import Test_Administracion.Competencias.ConcursosReales.ConfiguracionGeneral.*;
import Test_Administracion.Competencias.ConcursosReales.ConfiguracionGlobal.ConfiguracionGlobal_Test;
import Test_Administracion.Competencias.Equipo.*;
import Test_Administracion.Configuracion.Reglas.NombreReglas_Test;
import Test_Administracion.Miscelaneas.Anuncio.ContenidoAnuncio_Test;
import Test_Administracion.Miscelaneas.Encuesta.PreguntaEncuesta_Test;
import Test_Administracion.Miscelaneas.Encuesta.Respuesta1Encuesta_Test;
import Test_Administracion.Miscelaneas.Encuesta.Respuesta2Encuesta_Test;
import Test_Administracion.Miscelaneas.Encuesta.Respuesta3Encuesta_Test;
import Test_Administracion.Miscelaneas.Faq.PreguntaFaq_Test;
import Test_Administracion.Miscelaneas.Faq.RespuestaFaq_Test;
import Test_Administracion.Miscelaneas.Institucion.CodigoInstitucion_Test;
import Test_Administracion.Miscelaneas.Institucion.NombreInstitucion_Test;
import Test_Administracion.Miscelaneas.Institucion.PaisInstitucion_Test;
import Test_Administracion.Miscelaneas.Institucion.SitioWebInstitucion_Test;
import Test_Administracion.Miscelaneas.LenguajeProgramacion.DescripcionLenguajeProgramacion_Test;
import Test_Administracion.Miscelaneas.LenguajeProgramacion.LenguajeLenguajeProgramacion_Test;
import Test_Administracion.Miscelaneas.LenguajeProgramacion.LlaveLenguajeProgramacion_Test;
import Test_Administracion.Miscelaneas.LenguajeProgramacion.NombreBinLenguajeProgramacion_Test;
import Test_Administracion.Miscelaneas.NotificacionesPorCorreo.AsuntoNotifCorreo_Test;
import Test_Administracion.Miscelaneas.NotificacionesPorCorreo.CuerpoNotifCorreo_Test;
import Test_Administracion.Miscelaneas.Pais.Codigo2Pais_Test;
import Test_Administracion.Miscelaneas.Pais.Codigo3Pais_Test;
import Test_Administracion.Miscelaneas.Pais.NombrePais_Test;
import Test_Administracion.Miscelaneas.Pais.SitioWebPais_Test;
import Test_Administracion.Problemas.Etiquetas.NombreEtiqueta_Test;
import Test_Administracion.Problemas.Fuentes.AutorFuente_Test;
import Test_Administracion.Problemas.Fuentes.NombreFuente_Test;
import Test_Administracion.Problemas.Problemas.Problemas_Test;
import Test_Administracion.Usuarios.Usuarios_Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        //Miscelaneas/////////////////////////////////////////
        //Pais
        Codigo2Pais_Test.class,
        Codigo3Pais_Test.class,
        NombrePais_Test.class,
        SitioWebPais_Test.class,
        //Anuncio
        ContenidoAnuncio_Test.class,
        //Lenguaje de programacion
        DescripcionLenguajeProgramacion_Test.class,
        LenguajeLenguajeProgramacion_Test.class,
        LlaveLenguajeProgramacion_Test.class,
        NombreBinLenguajeProgramacion_Test.class,
        //Notificaciones por correo
        AsuntoNotifCorreo_Test.class,
        CuerpoNotifCorreo_Test.class,
        //Faq
        PreguntaFaq_Test.class,
        RespuestaFaq_Test.class,
        //Encuesta
        PreguntaEncuesta_Test.class,
        Respuesta1Encuesta_Test.class,
        Respuesta2Encuesta_Test.class,
        Respuesta3Encuesta_Test.class,
        //Institucion
        CodigoInstitucion_Test.class,
        NombreInstitucion_Test.class,
        PaisInstitucion_Test.class,
        SitioWebInstitucion_Test.class,
        //Miscelaneas/////////////////////////////////////////


        //Problemas/////////////////////////////////////////
        //Fuente
        NombreFuente_Test.class,
        AutorFuente_Test.class,
        //Problemas
        Problemas_Test.class,
        //Etiquetas
        NombreEtiqueta_Test.class,
        //Problemas/////////////////////////////////////////

        //Configuracion/////////////////////////////////////////
        NombreReglas_Test.class,
        //Configuracion/////////////////////////////////////////


        //COJBoard/////////////////////////////////////////////
        //Competencia
        FechaFinCompetencia_Test.class,
        FechaInicioCompetencia_Test.class,
        NombreCompetencia_Test.class,
        SeleccionarSitioCompetencia_Test.class,
        UrlCompetencia_Test.class,
        //Sitio
        NombreSitio_Test.class,
        UrlSitio_Test.class,
        CodigoSitio_Test.class,
        IDHoraFechaSitio_Test.class,
        ZonaHorariaSitio_Test.class,
        //COJBoard/////////////////////////////////////////////

        //Competencias/////////////////////////////////////////////
        //Adicionar concurso
        AdicionarConcurso_Test.class,
        //Concursos reales
        //Configuracion general
        FechaConcurso_Test.class,
        NombreConcurso_Test.class,
        RegistroConcurso_Test.class,
        TipoConcurso_Test.class,
        UsuarioConcurso_Test.class,
        //Configuracion global
        ConfiguracionGlobal_Test.class,

        //Concursos reales

        //Equipo
        ApodoCompetencia_Test.class,
        ContrasennasCompetencia_Test.class,
        IdiomaCompetencia_Test.class,
        InstitucionCompetencia_Test.class,
        PaisCompetencia_Test.class,
        TotalCompetencia_Test.class,
        UserNameCompetencia_Test.class,
        //Competencias/////////////////////////////////////////////


        //Usuarios///////////////////////////////////////////////
        //Usuario
        Usuarios_Test.class
        //Usuarios///////////////////////////////////////////////







})
public class SuitTestAdministracion {

}
