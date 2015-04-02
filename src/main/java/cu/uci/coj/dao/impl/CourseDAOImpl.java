/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.CourseDAO;
import cu.uci.coj.model.Chapter;
import cu.uci.coj.model.ChapterContent;
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
@Repository("CourseDAO")
@Transactional
public class CourseDAOImpl extends BaseDAOImpl implements CourseDAO {

    public CourseDAOImpl() {
    }

    @Override
    public void insertCourse(Course course) {
        dml("insert.course", course.getName(), course.getType(), course.getUsername());
    }

    @Override
    public int getLastCreatedCourse(String username) {
        return integer("get.last.course.byuser", username);
    }

    @Override
    public boolean createdBy(String username, int course_id) {
        return bool("course.created.by", username, course_id);
    }

    @Override
    public Course loadCourseEdit(int course_id) {
        return (Course) object("get.course", Course.class, course_id);
    }

    @Override
    public void saveCourse(Course course) {
        dml("save.course", course.getName(), course.getType(), course.isEnabled(), course.getInitdate(), course.isIspublic(), course.getChapter(), course.getProblem_points(), course.isScore_active(), course.getCourse_id());
    }

    @Override
    public List<Problem> loadAvailableProblems(int course_id) {
        return objects("select.available.problems.course", Problem.class, course_id);
    }

    @Override
    public List<Problem> loadCourseProblems(int course_id) {
        return objects("select.course.problems", Problem.class, course_id);
    }

    @Override
    public List<Problem> loadCourseProblemsChapter(int course_id, int chapter) {
        return objects("select.course.problems.chapter", Problem.class, course_id, chapter);
    }

    @Override
    public Chapter loadChapterData(int course_id, int chapter) {
        Chapter cap = object("load.chapter.info", Chapter.class, course_id, chapter);
        cap = cap == null ? new Chapter() : cap;
        cap.setProblems(loadCourseProblemsChapter(course_id, chapter));
        cap.setMaterials(objects("select.course.materials.chapter", ChapterContent.class, course_id, chapter));
        return cap;
    }

    @Override
    public boolean existsChapterInCourse(int course_id, int chapter) {
        return bool("exist.chapter.course", course_id, chapter);
    }

    @Override
    public int getChapterIdByCourseAndChapter(int course_id, int chapter) {
        return integer("select.chapterid", course_id, chapter);
    }

    @Override
    public void updateAddChapter(Course course) {
        if (existsChapterInCourse(course.getCourse_id(), course.getTotal_chapters())) {
            dml("update.chapter", course.getChapter_name(), course.getInitdate(), course.getCourse_id(), course.getTotal_chapters());
        } else {
            dml("insert.chapter", course.getTotal_chapters(), course.getCourse_id(), course.getChapter_name(), course.getInitdate());
        }
        int chapterid = getChapterIdByCourseAndChapter(course.getCourse_id(), course.getTotal_chapters());
        dml("delete.problems.chapter", chapterid);
        for (int i = 0; course.getProblemids() != null && i < course.getProblemids().length; i++) {
            Integer pid = Integer.parseInt(course.getProblemids()[i]);
            dml("insert.problem.chapter", chapterid, pid);
        }
        if (course.getUploadfile() != null && !course.getUploadfile().isEmpty()) {
            dml("insert.material.chapter", chapterid, course.getUploadfile().getOriginalFilename());
        }
    }

    @Override
    public void removeMaterial(int course_id, int chapter, int content_id) {
        dml("delete.course.material", course_id, chapter, content_id);
    }

    @Override
    public String getContentAddress(int content_id) {
        return string("get.content.address", content_id);
    }

    @Override
    public List<User> getUsersInCourse(int course_id) {
        return objects("get.users.in.course", User.class, course_id);
    }

    @Override
    public List<User> getUsersOffCourse(int course_id) {
        return objects("get.users.off.course", User.class, course_id);
    }

    @Override
    public void insertUsers(String[] usernames, int course_id) {        
        for (int i = 0; i < usernames.length; i++) {
            String username = usernames[i];
            dml("insert.course.user", username, course_id);
        }
    }

    @Override
    public int countAvailableCoursesFilter(int status, String creator, int access, String name) {
        List<Object> att = new LinkedList<Object>();
        String sql = getSql("count.available.courses.filter");
        if (status == 1 || status == 2) {
            switch (status) {
                case 1: {
                    sql += " and initdate >= now()";
                    break;
                }
                case 2: {
                    sql += " and initdate < now()";
                    break;
                }
            }
        }

        if (creator != null && !creator.isEmpty()) {
            sql += " and username ilike ?";
            att.add(creator);
        }

        if (access == 1 || access == 2) {
            sql += " and is_public = ?";
            att.add(Boolean.valueOf(access == 1 ? true : false));
        }

        if (name != null && !name.isEmpty()) {
            sql += " and course_name ilike ?";
            att.add("%" + name + "%");
        }
        if (!att.isEmpty()) {
            return integer(sql, att.toArray());
        }
        return integer(sql);
    }

    @Override
    public IPaginatedList<Course> getAvailableCoursesFilter(int found, int status, String creator, int access, PagingOptions options, String name) {
        String sql = getSql("get.available.courses.filter");
        List<Object> att = new LinkedList<Object>();
        if (status == 1 || status == 2) {
            switch (status) {
                case 1: {
                    sql += " and initdate >= now()";
                    break;
                }
                case 2: {
                    sql += " and initdate < now()";
                    break;
                }
            }
        }

        if (creator != null && !creator.isEmpty()) {
            sql += " and username ilike ?";
            att.add(creator);
        }

        if (access == 1 || access == 2) {
            sql += " and is_public = ?";
            att.add(Boolean.valueOf(access == 1 ? true : false));
        }
        if (name != null && !name.isEmpty()) {
            sql += " and course_name ilike ?";
            att.add("%" + name + "%");
        }
        sql += " limit 30 offset ?";
        att.add(options.getPage()-1);
        RowMapper<Course> mapper = new RowMapper<Course>() {
            @Override
            public Course mapRow(ResultSet rs, int i) throws SQLException {
                //course_id, course_name as name, course_type as type, enabled, is_public as ispublic
                Course c = new Course();
                c.setCourse_id(rs.getInt("course_id"));
                c.setName(rs.getString("name"));
                c.setType(rs.getInt("type"));
                c.setEnabled(rs.getBoolean("enabled"));
                c.setIspublic(rs.getBoolean("ispublic"));
                c.setUsername(rs.getString("username"));
                c.setRunning(rs.getBoolean("running"));
                c.setTotal_users(rs.getInt("total_users"));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    c.setInitdate(df.parse(rs.getString("initdate")));
                } catch (ParseException ex) {
                    Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return c;

            }
        };
        return getPaginatedList(options, (List<Course>) jdbcTemplate.query(sql, mapper, att.toArray()), 30, found) ;
    }

    @Override
    public boolean canJoin(int course_id, String username) {
        return bool("is.course.public", course_id) && !isUserInCourse(course_id, username);
    }

    @Override
    public boolean isUserInCourse(int course_id, String username) {
        return bool("is.user.in.course", course_id, username);
    }

    @Override
    public void joinCourse(int course_id, String username) {
        dml("insert.course.user", username, course_id);
    }

    @Override
    public List<Chapter> loadAllChapters(Course course) {
        List<Chapter> chapters = new LinkedList<Chapter>();
        for (int i = 0; i <= course.getChapter(); i++) {
            Chapter chapter = loadChapterData(course.getCourse_id(), i);
            chapter.setNumber(i);
            if (chapter.getInitdate().compareTo(new Date()) <= 0) {
                chapter.setEnabled(true);
            }
            chapters.add(chapter);
        }
        return chapters;
    }

    @Override
    public int getChapterByContentId(int content_id) {
        return integer("get.chapter.by.contentid", content_id);
    }

    @Override
    public void updateCourseOverview(Course course) {
        dml("update.course.overview", course.getOverview(), course.getCourse_id());
    }

    @Override
    public boolean isProblemAvailableInCourse(int course_id, Integer pid) {
        return bool("course.problem.available", course_id, pid);
    }

    @Override
    public List<Log> lastLogs(int course_id) {
        return objects("course.last.logs", Log.class, course_id);
    }

    @Override
    public List<Log> lastLogs(int course_id, int last) {
        return objects("course.last.logs.1", Log.class, course_id, last);
    }

    @Override
    public void insertLog(String username, int course_id, String message) {
        dml("insert.course.log", username, message, course_id);
    }

    @Override
    public int totalPointsInCourse(int course_id) {
        return integer("course.total.points", course_id, course_id);
    }

    @Override
    public int userPointsInCourse(int course_id, String username) {
        return integer("course.total.points.user", course_id, username);
    }

    @Override
    public boolean isScoreActive(int course_id) {
        return bool("course.score.active", course_id);
    }

    @Override
    public List<Course> loadAdminCourses(String username) {
        return objects("load.admin.courses", Course.class, username);
    }

    @Override
    public void banUser(String username, int course_id) {
        dml("ban.user.course", username, course_id);
    }

    @Override
    public void removeCourseUser(String username, int course_id) {
        dml("delete.course.user", username, course_id);
    }
    
    
    
    
}
