/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nenes
 */
public class Course {

    int course_id;
    String name;
    int type;
    boolean ispublic;
    boolean enabled;
    String username;
    Date initdate;
    int iyear, imonth, iday, ihour, iminutes, iseconds;
    int chapter, total_chapters;
    List<Problem> problems;
    List<ChapterContent> files;
    String[] problemids;
    String chapter_name;
    private MultipartFile uploadfile;
    private List<User> users;
    private boolean running;
    private boolean coming;
    private int total_users;
    private String overview;
    private int problem_points;
    private boolean score_active;

    public boolean isComing() {
        return coming;
    }

    public void setComing(boolean coming) {
        this.coming = coming;
    }

    public int getProblem_points() {
        return problem_points;
    }

    public void setProblem_points(int problem_points) {
        this.problem_points = problem_points;
    }

    public boolean isScore_active() {
        return score_active;
    }

    public void setScore_active(boolean score_active) {
        this.score_active = score_active;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getTotal_users() {
        return total_users;
    }

    public void setTotal_users(int total_users) {
        this.total_users = total_users;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<ChapterContent> getFiles() {
        return files;
    }

    public void setFiles(List<ChapterContent> files) {
        this.files = files;
    }

    public MultipartFile getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(MultipartFile uploadfile) {
        this.uploadfile = uploadfile;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public int getTotal_chapters() {
        return total_chapters;
    }

    public void setTotal_chapters(int total_chapters) {
        this.total_chapters = total_chapters;
    }

    public String[] getProblemids() {
        return problemids;
    }

    public void setProblemids(String[] problemids) {
        this.problemids = problemids;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Date getInitdate() {
        return initdate;
    }

    public void setInitdate(Date initdate) {
        this.initdate = initdate;
        this.iyear = initdate.getYear() + 1900;
        this.imonth = initdate.getMonth() + 1;
        this.iday = initdate.getDate();

        this.ihour = initdate.getHours();
        this.iminutes = initdate.getMinutes();
        this.iseconds = initdate.getSeconds();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isIspublic() {
        return ispublic;
    }

    public void setIspublic(boolean ispublic) {
        this.ispublic = ispublic;
    }

    public int getIyear() {
        return iyear;
    }

    public void setIyear(int iyear) {
        this.iyear = iyear;
    }

    public int getImonth() {
        return imonth;
    }

    public void setImonth(int imonth) {
        this.imonth = imonth;
    }

    public int getIday() {
        return iday;
    }

    public void setIday(int iday) {
        this.iday = iday;
    }

    public int getIhour() {
        return ihour;
    }

    public void setIhour(int ihour) {
        this.ihour = ihour;
    }

    public int getIminutes() {
        return iminutes;
    }

    public void setIminutes(int iminutes) {
        this.iminutes = iminutes;
    }

    public int getIseconds() {
        return iseconds;
    }

    public void setIseconds(int iseconds) {
        this.iseconds = iseconds;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
