package cu.uci.coj.controller;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.*;
import cu.uci.coj.jcompare.CeldaInfoAmpliada;
import cu.uci.coj.jcompare.Control;
import cu.uci.coj.jcompare.Resultado;
import cu.uci.coj.model.*;
import cu.uci.coj.utils.FileUtils;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.validator.DatagenValidator;
import java.io.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Scope("session")
@SessionAttributes("dataset")
@RequestMapping(value = "/datagen")
public class DatagenController extends BaseController {

    @Resource
    private BaseDAO baseDAO;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private SubmissionDAO submissionDAO;
    @Resource
    private UtilDAO utilDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private DatagenDAO datagenDAO;
    @Resource
    private DatagenValidator datagenValidator;
    private static final Integer MAX_INPUT_SIZE = 2000000;
    private static final Integer MAX_OUTPUT_SIZE = 2000000;

    @RequestMapping(value = "/datasets.xhtml", method = RequestMethod.GET)
    public String datagenDataset(Model model, Principal principal, @RequestParam(value = "mode") String mode, @RequestParam(value = "problemId", required = false) Integer problemId) {

        DatagenDataset dataset = new DatagenDataset();
        dataset.setUsername(getUsername(principal));
        Language language = userDAO.getProgrammingLanguageByUsername(getUsername(principal));
        boolean inputGenFlag = false, modelSolFlag = false;
        inputGenFlag = FileUtils.getInputGenCode(dataset, utilDAO);
        dataset.setInput(null);
        modelSolFlag = FileUtils.getModelCode(dataset, utilDAO);
        dataset.setCode(null);
        dataset.setMode(mode);
        dataset.setProblemId(problemId);
        dataset.setLid(language.getLid());
        dataset.setKey(language.getKey());
        model.addAttribute("inputGenAvail", inputGenFlag);
        model.addAttribute("modelSolAvail", modelSolFlag);
        model.addAttribute("dataset", dataset);
        boolean enabled = problemEnabled(dataset.getProblemId(), mode);

        model.addAttribute("dlAvail", enabled && datagenDAO.datasetsExist(problemId));
        model.addAttribute("enableadveditor", userDAO.bool("is.adved.enable", getUsername(principal)));
        addLanguages(model, dataset);
        return "/datagen/datasets";
    }

    private void addLanguages(Model model, DatagenDataset dataset) {
        List<Language> languages = new LinkedList<Language>();

        if (dataset.getProblemId() != null && problemDAO.exists(dataset.getProblemId())) {
            languages.addAll(utilDAO.getEnabledLanguagesByProblem(dataset.getProblemId()));
        } else {
            languages.addAll(utilDAO.getEnabledLanguagesByProblem(null));
        }
        model.addAttribute("languages", languages);
    }

    private String streamToString(MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
            
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private boolean problemEnabled(Integer problemId, String mode) {
        if (("inputgen".equals(mode) || "modelsol".equals(mode))) {
            if (problemId != null) {
                return problemDAO.isEnabled(problemId) && !problemDAO.isDisable24h(problemId);
            }
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/datasets.xhtml", method = RequestMethod.POST)
    public String datagenDataset(Model model, Principal principal, @RequestParam(value = "mode") String mode, @RequestParam(value = "uploadcodefile", required = false) MultipartFile codeFile, @RequestParam(value = "uploadinputfile", required = false) MultipartFile inputFile, DatagenDataset dataset, BindingResult errors) {
    
    	//FIXME esto es un parche para que no funcione el datagen hasta que se pueda arreglar. Es sospechoso de crear el problema de que el motor se para.
    	if (true)
    	return "";
    	
    	boolean inputGenFlag = false, modelSolFlag = false;
        boolean enabled = problemEnabled(dataset.getProblemId(), mode);
        String cmpModelOutput = Config.getProperty("text.model.output.no.exist");
        String cmpUserOutput = "";
        List<CeldaInfoAmpliada> resultados = new ArrayList<CeldaInfoAmpliada>();
        boolean matchStatus = false;
        boolean modelNotFoundFlag = false;
        if (!enabled) {
            errors.rejectValue("problemId", "errormsg.8");
        } else {
            if (dataset.getProblemId() == null) {
                dataset.setProblemId(1000);
            }
            String inp = dataset.getInput();
            if (inputFile != null && inputFile.getSize() > 0) {
                if (inputFile.getSize() < MAX_INPUT_SIZE) {
                    inp = streamToString(inputFile);
                } else {
                    errors.rejectValue("input", "errormsg.56");
                }
            }

            dataset.setInput(inp);

            String code = dataset.getCode();
            if (codeFile != null && codeFile.getSize() > 0) {
                if (codeFile.getSize() < MAX_INPUT_SIZE) {
                    code = streamToString(codeFile);
                } else {
                    errors.rejectValue("code", "errormsg.56");
                }
            }

            dataset.setCode(code);

            datagenValidator.validate(dataset, errors);

            dataset.setUsername(getUsername(principal));
            List<Language> languages = new LinkedList<Language>();
            if (!errors.hasErrors()) {

                if (dataset.getProblemId() != null && problemDAO.exists(dataset.getProblemId())) {
                    languages.addAll(utilDAO.getEnabledLanguagesByProblem(dataset.getProblemId()));
                } else {
                    languages.addAll(utilDAO.getEnabledLanguagesByProblem(null));
                }

                dataset.setLanguage(Utils.getLanguageByLid(languages, dataset.getKey()));
                Problem problem = baseDAO.object("select.problem.by.id", Problem.class, dataset.getProblemId());


                if (StringUtils.isEmpty(inp)) {
                    inputGenFlag = FileUtils.getInputGenCode(dataset, utilDAO);
                    if (inputGenFlag) {
                        String output = Utils.generateOutput(dataset);
                        dataset.setOutput("");
                        dataset.setCode("");
                        dataset.setInput(output);
                    }
                } else {
                    String output = null;

                    boolean solModelo = StringUtils.isEmpty(dataset.getCode());
                    
                    //si no hay codigo se quiere generar con la solucion modelo, por lo tanto se carga ese codigo del sistema de archivos
                    if (solModelo) {
                        modelSolFlag = FileUtils.getModelCode(dataset, utilDAO);
                        //si no existe se intenta generar de las soluciones de la bd
                        if (!modelSolFlag) {
                            String bdCode = problemDAO.generateModelSolutionFrom(dataset.getProblemId());
                            if (modelSolFlag = (bdCode != null)) {
                                dataset.setCode(bdCode);
                            } else {
                                errors.rejectValue("problemId", "errormsg.37");
                                modelNotFoundFlag = true;
                            }
                        }
                    }

                    //este codigo se ejecuta si la generacion es salida por usuario o salida por modelo
                    if ((solModelo && modelSolFlag) || !solModelo) {
                        if (!modelNotFoundFlag) {
                            output = Utils.generateOutput(dataset);
                            dataset.setOutput(output);
                        }
                        //si la solucion usada para generar es una solucion modelo y no hubo error, la salida es correcta y por tanto este dataset se puede guardar.
                        if (solModelo) {
                            if (dataset.isSuccess()) {
                                datagenDAO.saveDataset(dataset);
                            }
                            dataset.setCode(null);
                        } else {
                            //si la solucion que se debe generar es por solucion de usuario, se ejecuta tambien la modelo
                            cmpUserOutput = dataset.getOutput();
                            String codeTemp = dataset.getCode();
                            modelSolFlag = FileUtils.getModelCode(dataset, utilDAO);
                            if (modelSolFlag) {
                                cmpModelOutput = Utils.generateOutput(dataset);
                            }
                            dataset.setCode(codeTemp);

                            Control c = new Control();
                            resultados = c.editDistance(cmpUserOutput, cmpModelOutput);

                            //Si se encuentra al menos una linea cuyo resultado sea diferente a Equal, significa que el resultado final es no match
                            //la unica manera de que el resultado sea un match es que TODAS las lineas sean Equal
                            int i = 0;
                            while (i < resultados.size() && !matchStatus) {
                                matchStatus = resultados.get(i).getResult() != Resultado.Equal;
                                i++;
                            }
                            model.addAttribute("output", output != null);
                        }
                    }

                }
            }
        }

        model.addAttribute("cmp_lines", resultados);
        model.addAttribute("matchStatus", matchStatus);
        model.addAttribute("mode", mode);
        model.addAttribute("inputGenAvail", inputGenFlag);
        model.addAttribute("modelSolAvail", modelSolFlag);
        model.addAttribute("dataset", dataset);
        model.addAttribute("dlAvail", enabled && datagenDAO.datasetsExist(dataset.getProblemId()));
        model.addAttribute("problemId", dataset.getProblemId());
        model.addAttribute("enableadveditor", userDAO.bool("is.adved.enable", getUsername(principal)));
        addLanguages(model, dataset);
        return "/datagen/datasets";
    }

    @RequestMapping(value = "/download.xhtml", method = RequestMethod.GET)
    public String downloadDataset(HttpServletResponse response, Principal principal, @RequestParam(value = "pid", required = false) Integer problemId, Model model) {
    	//FIXME esto es un parche para que no funcione el datagen hasta que se pueda arreglar. Es sospechoso de crear el problema de que el motor se para.
    	if (true)
    	return "";
    	
    	try {
            Integer pid = problemId;

            if (problemId == null) {
                pid = ((DatagenDataset) model.asMap().get("dataset")).getProblemId();
            }

            if (!problemEnabled(pid, ((DatagenDataset) model.asMap().get("dataset")).getMode())) {
                return null;
            }

            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "inline; filename=" + problemId + ".rar");
            List<DatagenDataset> datasets = null;

            if (problemId != null) {
                datasets = datagenDAO.getDatasets(problemId, Integer.parseInt(Config.getProperty("dl.dataset.qty")));
                response.setHeader("Content-disposition", "inline; filename=" + problemId + ".rar");
            } else {
                datasets = new ArrayList<DatagenDataset>();
                datasets.add((DatagenDataset) model.asMap().get("dataset"));
                response.setHeader("Content-disposition", "inline; filename=" + ((DatagenDataset) model.asMap().get("dataset")).getProblemId() + ".rar");
            }
            FileUtils.crearArchivoDatasets(response.getOutputStream(), datasets);
            response.getOutputStream().flush();

            return null;
        } catch (Exception ex) {
            //TODO definir los errores, cuando son de fichero y cuando son realmene debidos a la seguridad.
            return "/error/accessdenied";
        }
    }
}
