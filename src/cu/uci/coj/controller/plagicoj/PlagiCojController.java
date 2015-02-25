package cu.uci.coj.controller.plagicoj;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceFactory;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.PlagiCOJDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Filter;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Navigating;
import cu.uci.coj.model.PlagiCOJJudgeRevision;
import cu.uci.coj.model.Status;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.enums.PlagiCOJDetectorType;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.annValidator;
import cu.uci.coj.websocket.Category;
import cu.uci.coj.websocket.DictumDelivery;
import cu.uci.plagicoj.PlagiCOJResult;
import cu.uci.plagicoj.detectors.Detector;
import cu.uci.plagicoj.detectors.DetectorResult;
import cu.uci.plagicoj.detectors.SourceCodeDetectorResult;
import cu.uci.plagicoj.detectors.impl.StringTilingDetector;
import cu.uci.plagicoj.entities.PlagicojResultDto;
import cu.uci.plagicoj.entities.PlagicojSubmissionDto;
import cu.uci.plagicoj.entities.SourceCode;
import cu.uci.plagicoj.factories.PlagiCOJFactory;
import cu.uci.plagicoj.scpdt.PlagiCOJClient;
import cu.uci.plagicoj.utils.DetectionPriority;


@Controller
@RequestMapping(value = "/")
public class PlagiCojController extends BaseController implements PlagiCOJClient.DictumReceivedCallback {

    public final int MAX_WEBSOCKET_CNX_WAITING_TIME = 5000;
    
    @Resource
    private PlagiCOJDAO plagiCOJDAO;
    @Resource
    private SubmissionDAO submissionDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private annValidator validator;
    public final int SAMELANGUAGE = 1;
    public final int SAMEUSER = 2;
    public final int SAMESUBMISSION = 3;

    List<PlagicojSubmissionDto> sourceSubmissionsId;
    List<PlagicojSubmissionDto> destinationSubmissionsId;
    List<SubmissionJudge> sourceSubmissions;
    List<SubmissionJudge> destinationSubmissions;

    ArrayList<ArrayList<PlagiCOJResult>> results;
    private List<PlagicojResultDto> resultList;
    //NAVIGATING VARS
    private String orderby;
    int top;
    int topMatrixRows;
    int topMatrixColumn;
    private int number = 1, inv;
    private int mrNumber = 1;
    private int mcNumber = 1;

    private String submitId;
    private String submitId1;
    private String submitId2;
    private String submitIdRangeStart;
    private String submitIdRangeEnd;
    private String problemId;
    private boolean onlyAC;
    private boolean matchLanguage;
    private boolean ownSubmission;
    private boolean sameUser;
    private String listResponseSocketName;
    private Double minimumPageDictum;
    private Double maximumPageDictum;
    
    
    @RequestMapping(value = "/admin/manageplagicoj.xhtml", method = RequestMethod.GET)
    public String managePlagicoj(HttpServletRequest request, Model model, @RequestParam(required = false, value = "username") String filter_user, 
    		@RequestParam(required = false,value="pid") Integer pid, @RequestParam(required = false, value = "status") String status, 
    		@RequestParam(required = false, value = "planguage") String language, 
    		PagingOptions options, Principal principal) {
        LinkedList<Status> statuslist = new LinkedList<Status>();
        String[] ar = Config.getProperty("judge.status").split(",");
        for (int i = 0; i < ar.length; i++) {
            String string = ar[i];
            statuslist.add(new Status(string, Config.getProperty(string.replaceAll(" ", "."))));
        }
        List<Language> languagelist = submissionDAO.getEnabledLanguages();
        Filter filter = new Filter(null,null,filter_user, null,status, language, pid, languagelist,null);
        filter.fillFirstParam();
        filter.fillSecondParam();
        model.addAttribute("filter", filter);
        model.addAttribute("statuslist",statuslist);
        return "/admin/manageplagicoj";
    }
    
    @RequestMapping(value = "/admin/tables/manageplagicoj.xhtml", method = RequestMethod.GET)
    public String tableManagePlagicoj(HttpServletRequest request, Model model, @RequestParam(required = false, value = "username") String filter_user, 
    		@RequestParam(required = false,value="pid") Integer pid, @RequestParam(required = false, value = "status") String status, 
    		@RequestParam(required = false, value = "planguage") String language, 
    		PagingOptions options, Principal principal) {
    	String lang = submissionDAO.string("select language from language where key=?", language);
        int found = submissionDAO.countSubmissions(filter_user, lang, pid, Config.getProperty("judge.status." + status));
        if (found != 0) {
            IPaginatedList<SubmissionJudge> submissions = submissionDAO.getSubmissions(filter_user, found,lang, pid, Config.getProperty("judge.status." + status), options, request.getUserPrincipal() != null, request.isUserInRole("ROLE_ADMIN"), getUsername(principal));
            model.addAttribute("submissions", submissions);
        }
        return "/admin/tables/manageplagicoj";
    }

    @RequestMapping(value = "/plagicoj/plagicojinspectionresult.xhtml")
    public String plagicojInspecctionResult(SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, Principal principal,
            @RequestParam(required = false, value = "submitid") String submitId,
            @RequestParam(required = false, value = "submitid1") String submitId1,
            @RequestParam(required = false, value = "submitid2") String submitId2,
            @RequestParam(required = false, value = "sid1") String submitIdRangeStart,
            @RequestParam(required = false, value = "sid2") String submitIdRangeEnd,
            @RequestParam(required = false, value = "pid") String problemId,
            @RequestParam(required = false, value = "onlyac") String onlyac,
            @RequestParam(required = false, value = "matchlanguage") String matchl,
            @RequestParam(required = false, value = "ownsubmission") String owns,
            @RequestParam(required = false, value = "sameuser") String sameu) {

       //  SCPDT.init(10000);
        
        this.submitId = submitId != null && !"".equals(submitId) ? submitId : null;
        this.submitId1 = submitId1 != null && !"".equals(submitId1) ? submitId1 : null;
        this.submitId2 = submitId2 != null && !"".equals(submitId2) ? submitId2 : null;
        this.submitIdRangeStart = submitIdRangeStart != null && !"".equals(submitIdRangeStart) ? submitIdRangeStart : null;
        this.submitIdRangeEnd = submitIdRangeEnd != null && !"".equals(submitIdRangeEnd) ? submitIdRangeEnd : null;

        if (onlyac != null) {
            onlyAC = onlyac.compareTo("checked") == 0 || onlyac.compareTo("on") == 0;
        } else {
            onlyAC = false;
        }
        if (matchl != null) {
            matchLanguage = matchl.compareTo("checked") == 0 || matchl.compareTo("on") == 0;
        } else {
            matchLanguage = false;
        }
        if (owns != null) {
            ownSubmission = owns.compareTo("checked") == 0 || owns.compareTo("on") == 0;
        } else {
            ownSubmission = false;
        }
        if (sameu != null) {
            sameUser = sameu.compareTo("checked") == 0 || sameu.compareTo("on") == 0;
        } else {
            sameUser = false;
        }

        boolean withPivot = !(submitId == null || submitId.compareTo("") == 0);

        destinationSubmissionsId = plagiCOJDAO.getSubmissions(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, onlyAC);

        if (!withPivot) {
            sourceSubmissionsId = new ArrayList<>(destinationSubmissionsId);
        } else {
            sourceSubmissionsId = plagiCOJDAO.getSubmissions(submitId, null, null, null, null, onlyAC);

        }

        setNumber(1);
        setMrNumber(1);
        setMcNumber(1);

        final PlagiCOJClient client = new PlagiCOJClient();
        
        try {
            
            client.init(this).setupConsumer();
        } catch (java.net.ConnectException e) {
            Logger.getLogger(PlagiCojController.class.getName()).log(Level.SEVERE, null, e);
            client.closeConnection();
            return "/error/serviceunavaliable";
        } catch (Exception ex) {
            Logger.getLogger(PlagiCojController.class.getName()).log(Level.SEVERE, null, ex);
            client.closeConnection();
            return "/error/plagicojerror";
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Iterator<PlagicojSubmissionDto> ssIdIt = sourceSubmissionsId.iterator(); ssIdIt.hasNext();) {
                        PlagicojSubmissionDto sourceSubmissionId = ssIdIt.next();
                        for (Iterator<PlagicojSubmissionDto> dsIdIt = destinationSubmissionsId.iterator(); dsIdIt.hasNext();) {
                            PlagicojSubmissionDto destinationSubmissionId = dsIdIt.next();

                            if (!((ownSubmission && sourceSubmissionId.getSubmitId() == destinationSubmissionId.getSubmitId()) || (sameUser && sourceSubmissionId.getUserId() == destinationSubmissionId.getUserId()) || (matchLanguage && sourceSubmissionId.getLanguage().compareTo(destinationSubmissionId.getLanguage()) != 0))) {
                                {
                                    PlagiCOJResult plagiCOJResult;
                                    plagiCOJResult = plagiCOJDAO.getPlagiCOJResult(sourceSubmissionId.getSubmitId(), destinationSubmissionId.getSubmitId());
                                    if (plagiCOJResult == null) {
                                        client.sendOrder(String.format("{\"ssid\": %d,\"dsid\": %d,\"sslang\": \"%s\",\"dslang\": \"%s\"}",
                                                sourceSubmissionId.getSubmitId(),
                                                destinationSubmissionId.getSubmitId(),
                                                sourceSubmissionId.getLanguage().replace("C++", "Cpp").replace("C#", "CSharp"),
                                                destinationSubmissionId.getLanguage().replace("C++", "Cpp").replace("C#", "CSharp")));

                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        new Thread(client).start();

        minimumPageDictum = 0.0;
        maximumPageDictum = 1.0;
        listResponseSocketName = client.getReplyQueueName();
        model.addAttribute("listResponseSocketName", listResponseSocketName);
        model.addAttribute("ownSubmission", ownSubmission);
        model.addAttribute("sameUser", sameUser);
        return "/plagicoj/plagicojinspectionresult";
    }

    @RequestMapping(value = "/plagicoj/plagicojinspectionresultmatrix.xhtml")
    public String plagicojInspecctionResultMatrix(SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, Principal principal,
            @RequestParam(required = false, value = "vid") String vid) {

        String responseSocketName = "";

        loadParameters(requestWrapper);

        if (buildNavigating(sourceSubmissionsId.size(), 10, "mr", getMrNumber(), model) != 0) {
            topMatrixRows = getMrNumber() - 1;
            topMatrixRows *= 10;
        }

        if (buildNavigating(destinationSubmissionsId.size(), 10, "mc", getMcNumber(), model) != 0) {
            topMatrixColumn = getMcNumber() - 1;
            topMatrixColumn *= 10;
        }

        results = new ArrayList<>();

        PlagiCOJClient client = null;

        try {
            client = new PlagiCOJClient();
            client.init(this, DetectionPriority.Medium).setupConsumer();

            destinationSubmissions = plagiCOJDAO.getSubmissions(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, onlyAC, topMatrixColumn, 10, principal != null, requestWrapper.isUserInRole("ROLE_ADMIN"), getUsername(principal), true);

            if (submitId != null) {
                sourceSubmissions = new ArrayList<>();
                sourceSubmissions = plagiCOJDAO.getSubmissions(submitId, null, null, null, null, onlyAC, null, null, principal != null, requestWrapper.isUserInRole("ROLE_ADMIN"), getUsername(principal), true);
            } else {
                sourceSubmissions = plagiCOJDAO.getSubmissions(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, onlyAC, topMatrixRows, 10, principal != null, requestWrapper.isUserInRole("ROLE_ADMIN"), getUsername(principal), true);
            }
            
            new Thread(client).start();
            
            int i = 0;
            for (Iterator<SubmissionJudge> ssIt = sourceSubmissions.iterator(); ssIt.hasNext(); ++i) {
                SubmissionJudge sourceSubmission = ssIt.next();
                results.add(new ArrayList<PlagiCOJResult>());
                for (Iterator<SubmissionJudge> dsIt = destinationSubmissions.iterator(); dsIt.hasNext();) {
                    SubmissionJudge destinationSubmission = dsIt.next();

                    if (ownSubmission && sourceSubmission.getSid() == destinationSubmission.getSid()) {
                        results.get(i).add(PlagiCOJFactory.createPlagiCOJResult(PlagiCOJResult.SpecialResult.SAME_SUBMISSION));
                    } else if (sameUser && sourceSubmission.getUid() == destinationSubmission.getUid()) {
                        results.get(i).add(PlagiCOJFactory.createPlagiCOJResult(PlagiCOJResult.SpecialResult.SAME_USER));
                    } else if (matchLanguage && sourceSubmission.getLang().compareTo(destinationSubmission.getLang()) != 0) {
                        results.get(i).add(PlagiCOJFactory.createPlagiCOJResult(PlagiCOJResult.SpecialResult.SAME_LANGUAGE));
                    } else {
                        PlagiCOJResult plagiCOJResult;
                        
                            plagiCOJResult = plagiCOJDAO.getPlagiCOJResult(sourceSubmission.getSid(), destinationSubmission.getSid());
                            if (plagiCOJResult != null){
                            }
                            else {
                                
                            plagiCOJResult = PlagiCOJFactory.createPlagiCOJResult(new ArrayList<DetectorResult>(), -1.0);

                            sourceSubmission.getSid();

                            client.sendOrder(String.format("{\"ssid\": %d,\"dsid\": %d,\"sslang\": \"%s\",\"dslang\": \"%s\"}",
                                    sourceSubmission.getSid(),
                                    destinationSubmission.getSid(),
                                    sourceSubmission.getLang().replace("C++", "Cpp").replace("C#", "CSharp"),
                                    destinationSubmission.getLang().replace("C++", "Cpp").replace("C#", "CSharp")));
                        }
                        results.get(i).add(plagiCOJResult);
                    }
                }
            }
        } catch (Exception e) {
        }

        responseSocketName = client.getReplyQueueName();
        
        model.addAttribute("responseSocketName", responseSocketName);
        model.addAttribute(
                "resultsMatrix", results);
        model.addAttribute(
                "sourceSubmissions", sourceSubmissions);
        model.addAttribute(
                "destinationSubmissions", destinationSubmissions);
        model.addAttribute(
                "colorPerDictum", Utils.colorPerDictum);
        return "/plagicoj/plagicojinspectionresultmatrix";
    }
    
    @RequestMapping(value = "/plagicoj/plagicojinspectionresultlist.xhtml")
    public String plagicojInspecctionResultList(SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, Principal principal,
            @RequestParam(required = false, value = "vid") String vid) {

        int countDictums = plagiCOJDAO.countDictums(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId,onlyAC,matchLanguage,ownSubmission,sameUser);        
        
        loadParameters(requestWrapper);

        if (buildNavigating(countDictums, 50, "", getNumber(), model) != 0) {
            top = getNumber() - 1;            
            top*=50;            
        }
        
        resultList = plagiCOJDAO.getDictums(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, onlyAC,matchLanguage,ownSubmission,sameUser,top,50);
        
        if (!resultList.isEmpty()){            
            maximumPageDictum = getNumber() == 1?1.0:resultList.get(0).getDictum();
            minimumPageDictum = resultList.size() < 50?0.0:resultList.get(resultList.size() - 1).getDictum();
        }
        
        model.addAttribute(
                "resultsList", resultList);
        return "/plagicoj/plagicojinspectionresultlist";
    }
    
    @RequestMapping(value = "/plagicoj/plagicojresult.xhtml", method = RequestMethod.GET)
    public String plagicojResult(SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, 
            @RequestParam(required = false, value = "username") String username, 
            @RequestParam(required = false, value = "value") String value, 
            @RequestParam(required = false, value = "revid") String revid, 
            @RequestParam(required = false, value = "ssid") Integer sourceSubmissionx, 
            @RequestParam(required = false, value = "didd") Integer destinationSubmissiony,
            @RequestParam(required = false, defaultValue = "0", value = "eval") Integer evaluation,
            @RequestParam(required = false, value = "comment") String comment, 
            @RequestParam(required = false, value = "uid") String user, Principal principal) throws Exception {
        
        SubmissionJudge sourceSubmission = plagiCOJDAO.getSubmissions(sourceSubmissionx.toString(), null, null, null, null, false, null, null, principal != null, requestWrapper.isUserInRole("ROLE_ADMIN"), getUsername(principal), false).get(0);
        SubmissionJudge destinationSubmission = plagiCOJDAO.getSubmissions(destinationSubmissiony.toString(), null, null, null, null, false, null, null, principal != null, requestWrapper.isUserInRole("ROLE_ADMIN"), getUsername(principal), false).get(0);
        
        SourceCode source = new SourceCode(sourceSubmission.getCode(),cu.uci.plagicoj.utils.Language.valueOf(formatLanguageCode(sourceSubmission.getLang())));
        SourceCode destination = new SourceCode(destinationSubmission.getCode(),cu.uci.plagicoj.utils.Language.valueOf(formatLanguageCode(destinationSubmission.getLang())));
        
        StringTilingDetector stringTilingDetector = new StringTilingDetector(source,destination);
        List<Detector> detectors = new ArrayList<>();   
        detectors.add(stringTilingDetector);
        
        cu.uci.plagicoj.PlagiCOJ plagiCOJ = cu.uci.plagicoj.PlagiCOJ.create(detectors);
        
        cu.uci.plagicoj.PlagiCOJResult plagiCOJResult = plagiCOJ.detectPlagiarism();
//      
          model.addAttribute("plagiCOJDetectorResults", plagiCOJResult.getDetectorResults());
          model.addAttribute("sourceSubmission", sourceSubmission);
          model.addAttribute("sourceUser", userDAO.loadAllUserData(sourceSubmission.getUsername()));
          model.addAttribute("destinationSubmission", destinationSubmission);
          model.addAttribute("destinationUser", userDAO.loadAllUserData(destinationSubmission.getUsername()));
          model.addAttribute("judgesRevisions",new ArrayList<PlagiCOJJudgeRevision>());
        return "/plagicoj/plagicojresult";
    }

        @RequestMapping(value = "/plagicoj/sourcecodedetectorresult.xhtml", method = RequestMethod.GET)
    public String sourceCodeDetectorResult(SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, Principal principal,
            @RequestParam(required = true, value = "ssid") Integer sourceSubmissionx,
            @RequestParam(required = true, value = "dsid") Integer destinationSubmissiony) {
                
            SubmissionJudge sourceSubmission = plagiCOJDAO.getSubmissions(sourceSubmissionx.toString(), null, null, null, null, false, null, null, principal != null, requestWrapper.isUserInRole("ROLE_ADMIN"), getUsername(principal), false).get(0);
            SubmissionJudge destinationSubmission = plagiCOJDAO.getSubmissions(destinationSubmissiony.toString(), null, null, null, null, false, null, null, principal != null, requestWrapper.isUserInRole("ROLE_ADMIN"), getUsername(principal), false).get(0);
        
            SourceCode source = new SourceCode(sourceSubmission.getCode(),cu.uci.plagicoj.utils.Language.valueOf(formatLanguageCode(sourceSubmission.getLang())));
            SourceCode destination = new SourceCode(destinationSubmission.getCode(),cu.uci.plagicoj.utils.Language.valueOf(formatLanguageCode(destinationSubmission.getLang())));
        
            Detector stringTilingDetector = new StringTilingDetector(source,destination);        
            
            cu.uci.plagicoj.PlagiCOJ plagiCOJ = cu.uci.plagicoj.PlagiCOJ.create(stringTilingDetector);        
            cu.uci.plagicoj.PlagiCOJResult plagiCOJResult = plagiCOJ.detectPlagiarism();
            
            SourceCodeDetectorResult detectorResult = (SourceCodeDetectorResult) plagiCOJResult.getDetectorResults().get(0);
            
            model.addAttribute("matches", detectorResult.getMatches());
            model.addAttribute("sourceTokens", detectorResult.getSourceTokens());
            model.addAttribute("destinationTokens", detectorResult.getDestinationTokens());

            model.addAttribute("judgesRevisions", plagiCOJDAO.getPlagiCOJDetectorResultJudgeRevisions(sourceSubmissionx, destinationSubmissiony, PlagiCOJDetectorType.PlagiCOJStringTilingDetector));
            model.addAttribute("sourceSubmission", sourceSubmission);
            model.addAttribute("destinationSubmission", destinationSubmission);
            model.addAttribute("plagiCOJDetectorResult", detectorResult);      
        return "/plagicoj/sourcecodedetectorresult";
    }
    
    @RequestMapping(produces = "application/json",value = "/plagicoj/saverevision.xhtml", method = RequestMethod.POST,headers = {"Accept=application/json"})
    public  @ResponseBody() String saveRevision(SecurityContextHolderAwareRequestWrapper requestWrapper, Model model,@RequestParam(required = false, value = "ssid") Integer sourceSubmission, @RequestParam(required = false, value = "didd") Integer destinationSubmission, @RequestParam(required = false, defaultValue = "0", value = "eval") Integer evaluation, @RequestParam(required = false, value = "comment") String comment, Principal principal) throws Exception {        
       
        return "{\"state\":\"success\"}";
    }
    
    public void insertPlagiCOJResult(int sourceSubmissionId, int destinationSubmissionId, double dictum) {
        plagiCOJDAO.dml(
                "insert.plagicoj.result ",
                sourceSubmissionId, destinationSubmissionId, dictum);
    }
    
//        @RequestMapping(produces = "application/json",value = "/plagicoj/plagicojinspectionresult/profile.json", method = RequestMethod.POST,headers = {"Accept=application/json"})
//    public @ResponseBody() String plagicojInspecctionResultProfile(SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, Principal principal,
//            @RequestParam(required = false, value = "vid") String vid,
//            @RequestParam(required = false, value = "submitid") String submitId,
//            @RequestParam(required = false, value = "submitid1") String submitId1,
//            @RequestParam(required = false, value = "submitid2") String submitId2,
//            @RequestParam(required = false, value = "sid1") String submitIdRangeStart,
//            @RequestParam(required = false, value = "sid2") String submitIdRangeEnd,
//            @RequestParam(required = false, value = "pid") String problemId,
//            @RequestParam(required = false, value = "onlyac") String onlyac,
//            @RequestParam(required = false, value = "matchlanguage") String matchl,
//            @RequestParam(required = false, value = "ownsubmission") String owns,
//            @RequestParam(required = false, value = "sameuser") String sameu) {
//    
//        JsonDetectionProfile detectionProfile = new JsonDetectionProfile();
//       
//            if (onlyac != null) {
//                onlyAC = onlyac.compareTo("checked") == 0 || onlyac.compareTo("on") == 0;
//            } else {
//                onlyAC = false;
//            }
//            if (matchl != null) {
//                matchLanguage = matchl.compareTo("checked") == 0 || matchl.compareTo("on") == 0;
//            } else {
//                matchLanguage = false;
//            }
//            if (owns != null) {
//                ownSubmission = owns.compareTo("checked") == 0 || owns.compareTo("on") == 0;
//            } else {
//                ownSubmission = false;
//            }
//            if (sameu != null) {
//                sameUser = sameu.compareTo("checked") == 0 || sameu.compareTo("on") == 0;
//            } else {
//                sameUser = false;
//            }
//
//            boolean withPivot = !(submitId == null || submitId.compareTo("") == 0);
//
//             try {
//                 long cantDestinationSubmissions = plagiCOJDAO.countSubmissions(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, onlyAC, principal != null, requestWrapper.isUserInRole("ROLE_ADMIN"), getUsername(principal));
//                 long cantsourceSubmissions = withPivot ? 1 : cantDestinationSubmissions;
//                 
//                 detectionProfile.setCantsub(cantsourceSubmissions * cantDestinationSubmissions);
//                 detectionProfile.setStatus("success");                 
//             } catch (Exception e) {
//                 detectionProfile.setStatus("error");                              
//             }
//        return JSONObject.fromObject(detectionProfile).toString();
//    }    
    

    private void setResultList(List<PlagicojResultDto> resultList) {
        this.resultList = resultList;
    }

    private List<PlagicojResultDto> getResultList() {
        return resultList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMrNumber() {
        return mrNumber;
    }

    public void setMrNumber(int mrNumber) {
        this.mrNumber = mrNumber;
    }

    public int getMcNumber() {
        return mcNumber;
    }

    public void setMcNumber(int mcNumber) {
        this.mcNumber = mcNumber;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    private void loadParameters(SecurityContextHolderAwareRequestWrapper requestWrapper) {
        orderby = requestWrapper.getParameter("orderby");
        setInv(0);
        try {
            setInv((int) new Integer(requestWrapper.getParameter("inv")));
        } catch (Exception e) {
        }
        int pos = 1;
        try {
            pos = new Integer(requestWrapper.getParameter("vid"));
            if (pos <= 0) {
                pos = 1;
            }
        } catch (Exception ex) {
        }

        if (requestWrapper.getParameter("mrnav") != null) {
            setMrNumber(pos);
        } else if (requestWrapper.getParameter("mcnav") != null) {
            setMcNumber(pos);
        } else {
            setNumber(pos);
        }
    }

    private int buildNavigating(int tot, int mod, String pref, int pos, Model model) {

        int totVolumes = tot / mod;
        if (tot % mod != 0 || totVolumes == 0) {
            totVolumes++;            
        }

        int start = pos;
        for (int i = 5; i > 0 && start > 1; i--) {
            start--;
        }

        if (pos == 1) {
            model.addAttribute(pref + "nfirst", new Navigating(pos, false));
        } else {
            model.addAttribute(pref + "nfirst", new Navigating(pos - 1, true));
        }
        LinkedList<Navigating> navi = new LinkedList<Navigating>();
        int h = 0;
        for (; start <= totVolumes && h < 10; start++) {
            if (start == pos) {
                navi.add(new Navigating(start, false));
            } else {
                navi.add(new Navigating(start, true));
            }
            h++;
        }

        if (pos == totVolumes) {
            model.addAttribute(pref + "nend", new Navigating(pos, false));
        } else {
            model.addAttribute(pref + "nend", new Navigating(pos + 1, true));
        }
        model.addAttribute(pref + "end", new Navigating(totVolumes, true));

        model.addAttribute(pref + "links", navi);

        return tot;
    }

    private int buildNavigating(Model model) {
        int tot = resultList.size();

        int totVolumes = tot / 50;
        if (tot % 50 != 0||totVolumes == 0) {
            totVolumes++;
        }
        int start = getNumber();
        for (int i = 5; i > 0 && start > 1; i--) {
            start--;
        }

        if (getNumber() == 1) {
            model.addAttribute("nfirst", new Navigating(getNumber(), false));
        } else {
            model.addAttribute("nfirst", new Navigating(getNumber() - 1, true));
        }
        LinkedList<Navigating> navi = new LinkedList<Navigating>();
        int h = 0;
        for (; start <= totVolumes && h < 10; start++) {
            if (start == getNumber()) {
                navi.add(new Navigating(start, false));
            } else {
                navi.add(new Navigating(start, true));
            }
            h++;
        }

        if (getNumber() == totVolumes) {
            model.addAttribute("nend", new Navigating(getNumber(), false));
        } else {
            model.addAttribute("nend", new Navigating(getNumber() + 1, true));
        }
        model.addAttribute("end", new Navigating(totVolumes, true));

        model.addAttribute("links", navi);

        return tot;
    }

    @Override
    public void dictumReceived(String answer, String fromQueueName) {
        JSONObject jsonAnswer = JSONObject.fromObject(answer);
        
        if (listResponseSocketName.equals(fromQueueName)){
            Double dictum = jsonAnswer.getDouble("dictum");
            
            if (dictum < minimumPageDictum || dictum > maximumPageDictum){
                return;
            }
        }
        
        String resource = fromQueueName;
        AtmosphereResource r = AtmosphereResourceFactory.getDefault().find(DictumDelivery.resources.get(resource));
        int waitingTime = 100;
        while (r == null && waitingTime < MAX_WEBSOCKET_CNX_WAITING_TIME) {
            try {
                Thread.sleep(waitingTime);
                waitingTime*=2;
            } catch (InterruptedException ex) {
                Logger.getLogger(PlagiCojController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            r = AtmosphereResourceFactory.getDefault().find(DictumDelivery.resources.get(resource));
        }
        
        if (r != null) {
            Category m = new Category(resource, jsonAnswer.toString(), new ArrayList<String>(), new ArrayList<String>());

            DictumDelivery.factory.lookup("/websocket/plagicoj").broadcast(m, r);
        }
        
    }
    
    private String formatLanguageCode(String language){
        return language.replace("C++", "Cpp").replace("C#", "CSharp");
    }
}
