package cu.uci.coj.dao;

import java.util.List;

import cu.uci.coj.model.Chapter;
import cu.uci.coj.model.Course;
import cu.uci.coj.model.Log;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

/**
 * @version 2.0
 * @since 2010-09-01
 */
public interface CourseDAO extends BaseDAO {
    
    public void insertCourse(Course course);
    
    public int getLastCreatedCourse(String username);
    
    public boolean createdBy(String username, int course_id);
    
    public Course loadCourseEdit(int course_id);
    
    public void saveCourse(Course course);
    
    public List<Problem> loadAvailableProblems(int course_id);
    
    public List<Problem> loadCourseProblems(int course_id);
    
    public List<Problem> loadCourseProblemsChapter(int course_id, int chapter);
    
    public Chapter loadChapterData(int course_id, int chapter);
    
    public boolean existsChapterInCourse(int course_id, int chapter);
    
    public void updateAddChapter(Course course);
    
    public int getChapterIdByCourseAndChapter(int course_id, int chapter);
    
    public void removeMaterial(int course_id, int chapter, int content_id);
    
    public String getContentAddress(int content_id);
    
    public List<User> getUsersInCourse(int course_id);
    
    public List<User> getUsersOffCourse(int course_id);
    
    public void insertUsers(String[] usernames, int course_id);
    
    public int countAvailableCoursesFilter(int status, String creator, int access, String name);
    
    public IPaginatedList<Course> getAvailableCoursesFilter(int found,int status, String creator, int access, PagingOptions options, String name);
    
    public boolean canJoin(int course_id, String username);
    
    public boolean isUserInCourse(int course_id, String username);
    
    public void joinCourse(int course_id, String username);
    
    public List<Chapter> loadAllChapters(Course course);
    
    public int getChapterByContentId(int content_id);
    
    public void updateCourseOverview(Course course);
    
    public boolean isProblemAvailableInCourse(int course_id, Integer pid);
    
    public List<Log> lastLogs(int course_id, int last);
    
    public List<Log> lastLogs(int course_id);
    
    public void insertLog(String username, int course_id, String message);
    
    public int totalPointsInCourse(int course_id);
    
    public int userPointsInCourse(int course_id, String username);
    
    public boolean isScoreActive(int course_id);
    
    public List<Course> loadAdminCourses(String username);
    
    public void banUser(String username, int course_id);
    
    public void removeCourseUser(String username, int course_id);
    
}
