package cu.uci.coj.controller.statistics;

import java.security.Principal;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Stats;

@Controller
@RequestMapping(value = "/")
public class StatisticsController extends BaseController  {

    @Resource
    private BaseDAO baseDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private ContestDAO contestDAO;

    private CategoryDataset createDataset(String query) {
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        Map<String, ?> map = baseDAO.map(query);
        Set<String> set = map.keySet();
        for (String key : set) {
            if (!key.equals("total")) {
                double val = (Long) map.get(key);
                val /= (Long) map.get("total");
                val *= 100;
                val = (double) Math.round(val * 100) / 100;
                defaultcategorydataset.addValue(val, "status", key.toLowerCase());
            }
        }
        return defaultcategorydataset;
    }

    private CategoryDataset createDataset(int cid) {
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        Map<String, ?> map = baseDAO.map("statistics.contest.status", cid);
        Set<String> set = map.keySet();
        for (String key : set) {
            if (!key.equals("total")) {
                double val = (Long) map.get(key);
                val /= (Long) map.get("total");
                val *= 100;
                val = (double) Math.round(val * 100) / 100;
                defaultcategorydataset.addValue(val, "status", key.toLowerCase());
            }
        }
        return defaultcategorydataset;
    }

    private JFreeChart createChart(CategoryDataset categorydataset) {
        JFreeChart jfreechart = ChartFactory.createBarChart3D("", "judgments", "percent - %", categorydataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
        BarRenderer3D custombarrenderer3d = new BarRenderer3D();
        custombarrenderer3d.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        custombarrenderer3d.setBaseItemLabelsVisible(true);
        custombarrenderer3d.setItemLabelAnchorOffset(10D);
        custombarrenderer3d.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
        categoryplot.setRenderer(custombarrenderer3d);
        custombarrenderer3d.setBaseItemLabelsVisible(true);
        custombarrenderer3d.setMaximumBarWidth(0.050000000000000003D);
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        numberaxis.setNumberFormatOverride(NumberFormat.getIntegerInstance());
        numberaxis.setUpperMargin(0.10000000000000001D);
        ChartUtilities.applyCurrentTheme(jfreechart);
        return jfreechart;
    }

    @RequestMapping(value = "/24h/statistics.xhtml", method = RequestMethod.GET)
    public String TrainingModuleStatistics(Model model) {
        model.addAttribute("stat", baseDAO.object("statistics.total.status", Stats.class));
        model.addAttribute("statistics", baseDAO.objects("training.statistics", Language.class));
        return "/24h/statistics";
    }

    @RequestMapping(value = "/graph/24h/statistics.xhtml", method = RequestMethod.GET)
    public String TrainingModuleStatisticsGraph(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        CategoryDataset categoryDataset = createDataset("statistics.status");
        JFreeChart chart = createChart(categoryDataset);
        ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 660, 400);
        return null;
    }

    @RequestMapping(value = "/contest/cstatistics.xhtml", method = RequestMethod.GET)
    public String ContestStatistics(Principal principal,Model model, @RequestParam("cid") Integer cid) {
        Contest contest = contestDAO.loadContest(cid);
        Integer uid = getUid(principal);
        boolean uidInContest = baseDAO.bool("exist.user.in.contest",uid,cid	);
        model.addAttribute("showStats", (uidInContest && contest.isShow_stats()) || contest.isShow_stats_out());
        contestDAO.unfreezeIfNecessary(contest);
        model.addAttribute("stat", baseDAO.object("statistics.contest.total.status", Stats.class, contest.getCid()));
        model.addAttribute("statistics", baseDAO.objects("statistics.contest", Language.class, contest.getCid()));
        contest.setUsers(userDAO.loadContestUsers(contest.getCid()));
        model.addAttribute("contest", contest);
        return "/contest/cstatistics";
    }

    @Deprecated
    //sustituida o a sustituir por el Chart.js
    @RequestMapping(value = "/graph/contest/cstatistics.xhtml", method = RequestMethod.GET)
    public String ContestStatisticsGraph(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("cid") Integer cid) throws Exception {
        response.setContentType("image/png");
        CategoryDataset categoryDataset = createDataset(cid);
        JFreeChart chart = createChart(categoryDataset);
        ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 660, 400);
        return null;
    }

    @RequestMapping(value = "/contest/globalstatistics.xhtml", method = RequestMethod.GET)
    public String GlobalContestStatistics(Model model) {
        model.addAttribute("stat", baseDAO.object("statistics.global.total.status", Stats.class));
        model.addAttribute("statistics", baseDAO.objects("statistics.global", Language.class));
        return "/contest/globalstatistics";
    }

    @Deprecated
    //sustituida o a sustituir por el Chart.js
    @RequestMapping(value = "/graph/contest/globalstatistics.xhtml", method = RequestMethod.GET)
    public String GlobalContestStatisticsGraph(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        CategoryDataset categoryDataset = createDataset("statistics.global.status");
        JFreeChart chart = createChart(categoryDataset);
        ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 660, 400);
        return null;
    }

    @RequestMapping(value = "/practice/virtualstatistics.xhtml", method = RequestMethod.GET)
    public String VirtualStatistics(Model model) {
        model.addAttribute("stat", baseDAO.object("statistics.virtual.total.status", Stats.class));
        model.addAttribute("statistics", baseDAO.objects("statistics.virtual", Language.class));
        return "/practice/virtualstatistics";
    }

    @RequestMapping(value = "/graph/practice/statistics.xhtml", method = RequestMethod.GET)
    public String VirtualStatisticsGraph(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        CategoryDataset categoryDataset = createDataset("statistics.virtual.status");
        JFreeChart chart = createChart(categoryDataset);
        ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 660, 400);
        return null;
    }
}
