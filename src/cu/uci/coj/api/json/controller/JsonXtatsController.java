/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cu.uci.coj.api.json.dao.JsonContestDAO;
import cu.uci.coj.api.json.dao.JsonProblemDAO;
import cu.uci.coj.api.json.dao.JsonSubmissionDAO;
import cu.uci.coj.api.json.dao.JsonUserDAO;
import cu.uci.coj.api.json.model.JsonContest;
import cu.uci.coj.api.json.model.JsonProblem;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.model.Country;
import cu.uci.coj.model.Institution;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.User;

@Controller
@RequestMapping(value = "/json/xtats")
@Scope("session")
public class JsonXtatsController extends BaseController {

	@Resource
	private SubmissionDAO submissionDAO;
	@Resource
	private JsonUserDAO jsonUserDAO;
	@Resource
	private JsonSubmissionDAO jsonSubmissionDAO;
	@Resource
	private JsonProblemDAO jsonProblemDAO;
	@Resource
	private CountryDAO countryDAO;
	@Resource
	private InstitutionDAO institutionDAO;
	@Resource
	private JsonContestDAO jsonContestDAO;

	// Distribución de nuevos registros
	@RequestMapping(produces = "application/json", value = "/recordsoverview.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String newRecordsOverview(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam(required = false,value="textbox") String textbox,
			@RequestParam(required = false,value="states") String states, @RequestParam String prefix) {
		if ((!StringUtils.hasText(textbox) || textbox.length() > 30) || (!StringUtils.hasText(states) || states.length() > 30) || (!StringUtils.hasText(prefix) || prefix.length() > 30))
			return null;

		List<User> mapa = jsonUserDAO.newusersgraphic(startDate, endDate, textbox, states, prefix);
		List<String[]> other = new ArrayList<String[]>();
		for (User u : mapa) {
			String[] aux = new String[2];
			aux[0] = String.valueOf(u.getY());
			aux[1] = u.getRgdate();

			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Distribución de registros que permanecen inactivos
	@RequestMapping(produces = "application/json", value = "/offrecords.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String offRecords(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam(required = false,value="textbox") String textbox, @RequestParam(required = false,value="states") String states) {

		List<User> mapa = jsonUserDAO.offusers(startDate, endDate, textbox, states);
		List<String[]> other = new ArrayList<String[]>();
		for (User u : mapa) {
			String[] aux = new String[4];
			aux[0] = u.getUsername();
			aux[1] = u.getRgdate();
			aux[3] = u.getInstitution();
			aux[2] = u.getCountry();
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Linea de tiempo por veredicto del COJ y distribucion de envios
	@RequestMapping(produces = "application/json", value = "/veredictstl.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String veredictstl(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam(required = false,value="textbox") String textbox, @RequestParam(required = false,value="states") String states) {

		List<SubmissionJudge> mapa = jsonSubmissionDAO.veredictstl(startDate, endDate, textbox, states);
		List<String[]> other = new ArrayList<String[]>();
		for (SubmissionJudge u : mapa) {
			String[] aux = new String[3];
			aux[0] = u.getStatus();
			aux[1] = String.valueOf(u.getY());
			aux[2] = u.getDate().toString();

			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Para filtro por paises
	@RequestMapping(produces = "application/json", value = "/getcountries.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String getcountries() {

		List<Country> mapa = countryDAO.objects("enabled.countries", Country.class);
		List<String[]> other = new ArrayList<String[]>();
		for (Country u : mapa) {
			String[] aux = new String[2];
			aux[1] = u.getName();
			aux[0] = u.getName();
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Para filtro por instituciones
	@RequestMapping(produces = "application/json", value = "/getinstitution.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String getinstitution() {

		List<Institution> mapa = institutionDAO.getEnabledInstitutions();
		List<String[]> other = new ArrayList<String[]>();
		for (Institution u : mapa) {
			String[] aux = new String[2];
			aux[1] = u.getName();
			aux[0] = u.getName();
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// filtro para veredictos
	@RequestMapping(produces = "application/json", value = "/getveredict.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String getveredict() {
		List<JsonContest> mapa = jsonContestDAO.statusContest();
		List<String[]> other = new ArrayList<String[]>();
		for (JsonContest u : mapa) {
			String[] aux = new String[2];
			aux[0] = u.getState();
			aux[1] = u.getState();
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Para filtro para clasificaciones de problemas
	@RequestMapping(produces = "application/json", value = "/getproblemclasif.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String getProblemClass() {
		List<JsonProblem> mapa = jsonProblemDAO.problemsClassifications();
		List<String[]> other = new ArrayList<String[]>();
		for (JsonProblem u : mapa) {
			String[] aux = new String[2];
			aux[0] = u.getClassification();
			aux[1] = u.getClassification();
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Distribución de submisiones en el COJ
	@RequestMapping(produces = "application/json", value = "/distsubmissions.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String distsubmissions(@RequestParam(required = false,value="textbox") String textbox, @RequestParam(required = false,value="states") String states) {
		List<User> mapa = jsonUserDAO.distsubmissions(textbox, states);
		List<String[]> other = new ArrayList<String[]>();
		for (User p : mapa) {
			String[] aux = new String[10];
			aux[0] = String.valueOf(p.getAcc());
			aux[1] = String.valueOf(p.getCe());
			aux[2] = String.valueOf(p.getFle());
			aux[3] = String.valueOf(p.getIvf());
			aux[4] = String.valueOf(p.getMle());
			aux[5] = String.valueOf(p.getOle());
			aux[6] = String.valueOf(p.getPe());
			aux[7] = String.valueOf(p.getRte());
			aux[8] = String.valueOf(p.getTle());
			aux[9] = String.valueOf(p.getWa());
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// devuelve un arreglo con los datos de dos problemas seleccionados, los
	// datos estan separados por _
	@RequestMapping(produces = "application/json", value = "/problemcompare.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String problemcompare(@RequestParam int textbox1, @RequestParam int textbox2, @RequestParam Date startDate, @RequestParam Date endDate,
			@RequestParam(required = false,value="textbox") String textbox, @RequestParam(required = false,value="states") String states) {

		List<SubmissionJudge> mapa = jsonSubmissionDAO.problemcompare1(textbox1, startDate, endDate, textbox, states);
		List<String[]> other = new ArrayList<String[]>();
		for (SubmissionJudge u : mapa) {
			String[] aux = new String[2];
			aux[0] = u.getStatus();
			aux[1] = String.valueOf(u.getY());
			other.add(aux);
		}
		String[] aux1 = new String[2];
		aux1[0] = "_";
		aux1[1] = "_";
		other.add(aux1);
		mapa.clear();
		mapa.addAll(jsonSubmissionDAO.problemcompare2(textbox2, startDate, endDate, textbox, states));
		for (SubmissionJudge u : mapa) {
			String[] aux2 = new String[2];
			aux2[0] = u.getStatus();
			aux2[1] = String.valueOf(u.getY());
			other.add(aux2);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Distribución de nuevos problemas
	@RequestMapping(produces = "application/json", value = "/newproblems.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String newproblems(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam(required = false,value="textbox") String textbox, @RequestParam(required = false,value="states") String states) {
		List<JsonProblem> mapa = jsonProblemDAO.newproblems(startDate, endDate, textbox, states);
		List<String[]> other = new ArrayList<String[]>();
		for (JsonProblem u : mapa) {
			String[] aux = new String[2];
			aux[0] = String.valueOf(u.getY());
			aux[1] = u.getDate();

			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// devuelve los datos relacionados con los envios de un problema
	// seleccionado
	@RequestMapping(produces = "application/json", value = "/problemsubmit.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String problemSubmit(@RequestParam int textbox1, @RequestParam Date startDate, @RequestParam Date endDate, 
			@RequestParam(required = false,value="textbox") String textbox,
			@RequestParam(required = false,value="states") String states) {

		List<SubmissionJudge> mapa = jsonSubmissionDAO.problemsubmissions(textbox1, startDate, endDate, textbox, states);
		List<String[]> other = new ArrayList<String[]>();
		for (SubmissionJudge u : mapa) {
			String[] aux = new String[2];
			aux[0] = u.getStatus();
			aux[1] = String.valueOf(u.getY());
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// devuelve los datos relacionados con los envios de un usuario seleccionado
	@RequestMapping(produces = "application/json", value = "/distsubuser.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String distSubUser(@RequestParam String textbox1, @RequestParam() Date startDate, @RequestParam Date endDate) {

		List<SubmissionJudge> mapa = jsonSubmissionDAO.usersubmissions(textbox1, startDate, endDate);
		List<String[]> other = new ArrayList<String[]>();
		for (SubmissionJudge u : mapa) {
			String[] aux = new String[2];
			aux[0] = u.getStatus();
			aux[1] = String.valueOf(u.getY());
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Devuelve los datos de los envios relacionados a problemas
	@RequestMapping(produces = "application/json", value = "/veredictsproblemstl.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String veredictsproblemstl(@RequestParam int textbox1, @RequestParam Date startDate, @RequestParam Date endDate, @RequestParam String prefix) {

		List<SubmissionJudge> mapa = jsonSubmissionDAO.veredictsproblemstl(textbox1, startDate, endDate, prefix);
		List<String[]> other = new ArrayList<String[]>();
		for (SubmissionJudge u : mapa) {
			String[] aux = new String[3];
			aux[0] = u.getStatus();
			aux[1] = String.valueOf(u.getY());
			aux[2] = u.getSdate();

			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// devuelve los datos relacionados con los envios en un concurso
	@Deprecated
	// Ya esta integrado en otra version
	@RequestMapping(produces = "application/json", value = "/contestsubmissions.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String contestSubmissions(@RequestParam int textbox1, @RequestParam String textbox, @RequestParam String states) {
		List<JsonContest> mapa = jsonContestDAO.contestSubmissions(textbox1, textbox, states);
		List<String[]> other = new ArrayList<String[]>();
		for (JsonContest u : mapa) {
			String[] aux = new String[2];
			aux[0] = u.getState();
			aux[1] = String.valueOf(u.getCstats());
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Devuelve los datos de los envios relacionados a veredictos en concurso en
	// un tiempo determinado
	@RequestMapping(produces = "application/json", value = "/veredictscontest.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String veredictsContest(@RequestParam int textbox1, @RequestParam String veredict, @RequestParam String textbox, @RequestParam String states) {

		List<JsonContest> mapa = jsonContestDAO.contestVeredicts(textbox1, veredict, textbox, states);
		List<String[]> other = new ArrayList<String[]>();
		for (JsonContest u : mapa) {
			String[] aux = new String[3];
			aux[0] = u.getState();
			aux[1] = String.valueOf(u.getCstats());
			aux[2] = u.getDate();

			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Devuelve los datos de los datos relacionados al estado de problemas en
	// concurso en un tiempo determinado
	@RequestMapping(produces = "application/json", value = "/problemscontest.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String problemContest(@RequestParam int textbox1, @RequestParam int textbox2, @RequestParam String veredict, @RequestParam String textbox, @RequestParam String states) {

		List<JsonContest> mapa = jsonContestDAO.problemsContest(textbox1, textbox2, veredict, textbox, states);
		List<String[]> other = new ArrayList<String[]>();
		for (JsonContest u : mapa) {
			String[] aux = new String[3];
			aux[0] = u.getState();
			aux[1] = String.valueOf(u.getCstats());
			aux[2] = u.getDate();

			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// devuelve los problemas relacionados a un concurso
	@RequestMapping(produces = "application/json", value = "/problemscontestfilt.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String problemContestFilt(@RequestParam int textbox1) {

		List<JsonContest> mapa = jsonContestDAO.problemsContestFilter(textbox1);
		List<String[]> other = new ArrayList<String[]>();
		for (JsonContest u : mapa) {
			String[] aux = new String[2];
			aux[0] = String.valueOf(u.getPid());
			aux[1] = String.valueOf(u.getPid());
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// Devuelve los datos de los datos relacionados al estado de problemas en
	// concurso en un tiempo determinado
	@RequestMapping(produces = "application/json", value = "/contestuserdevelop.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String contestUserDevelop(@RequestParam int textbox1, @RequestParam String textbox2, @RequestParam String textbox, @RequestParam String veredict) {

		List<JsonContest> mapa = jsonContestDAO.contestUserDevelopment(textbox1, textbox2, textbox, veredict);
		List<String[]> other = new ArrayList<String[]>();
		for (JsonContest u : mapa) {
			String[] aux = new String[3];
			aux[0] = u.getState();
			aux[1] = String.valueOf(u.getCstats());
			aux[2] = u.getDate();

			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// devuelve los usuarios en un concurso dado
	@RequestMapping(produces = "application/json", value = "/usersincontest.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String userInContestFilt(@RequestParam int textbox1) {

		List<JsonContest> mapa = jsonContestDAO.filterUsersInContest(textbox1);
		List<String[]> other = new ArrayList<String[]>();
		for (JsonContest u : mapa) {
			String[] aux = new String[2];
			aux[0] = u.getUser();
			aux[1] = u.getUser();
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// devuelve un arreglo con los datos de dos usuarios seleccionados, los
	// datos estan separados por _
	@RequestMapping(produces = "application/json", value = "/userscompare.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String usersCompare(@RequestParam String textbox1, @RequestParam String textbox2, @RequestParam(required = false,value="textbox") int textbox,
			@RequestParam(required = false,value="states") String states, @RequestParam Date startDate, @RequestParam Date endDate) {

		List<SubmissionJudge> mapa = jsonSubmissionDAO.userscompare1(textbox1, textbox, states, startDate, endDate);
		List<String[]> other = new ArrayList<String[]>();
		for (SubmissionJudge u : mapa) {
			String[] aux = new String[2];
			aux[0] = u.getStatus();
			aux[1] = String.valueOf(u.getY());
			other.add(aux);
		}
		String[] aux1 = new String[2];
		aux1[0] = "_";
		aux1[1] = "_";
		other.add(aux1);
		mapa.clear();
		mapa.addAll(jsonSubmissionDAO.userscompare2(textbox2, textbox, states, startDate, endDate));
		for (SubmissionJudge u : mapa) {
			String[] aux2 = new String[2];
			aux2[0] = u.getStatus();
			aux2[1] = String.valueOf(u.getY());
			other.add(aux2);
		}
		return JSONArray.fromObject(other).toString();
	}

	// ///////// Integrados

	@RequestMapping(produces = "application/json", value = "/distuser.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String distSubUser(@RequestParam String user) {

		List<SubmissionJudge> mapa = jsonSubmissionDAO.usersubmissions(user);
		List<String[]> other = new ArrayList<String[]>();
		for (SubmissionJudge u : mapa) {
			String[] aux = new String[2];
			aux[0] = u.getStatus();
			aux[1] = String.valueOf(u.getY());
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

	// devuelve los datos relacionados con los envios en un concurso
	@RequestMapping(produces = "application/json", value = "/contestsubmits.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() String contestSubmits(@RequestParam int cid, @RequestParam String username) {
		List<JsonContest> mapa = jsonContestDAO.contestSubmissions(cid, username);
		List<String[]> other = new ArrayList<String[]>();
		for (JsonContest u : mapa) {
			String[] aux = new String[2];
			aux[0] = u.getState();
			aux[1] = String.valueOf(u.getCstats());
			other.add(aux);
		}
		return JSONArray.fromObject(other).toString();
	}

}
