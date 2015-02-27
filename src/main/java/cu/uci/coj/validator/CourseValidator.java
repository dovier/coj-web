/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.Course;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CourseValidator implements Validator {

    @Resource
    private ProblemDAO problemDAO;

    public ProblemDAO getProblemDAO() {
        return problemDAO;
    }

    public void setProblemDAO(ProblemDAO problemDAO) {
        this.problemDAO = problemDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Course.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Course course = (Course) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "asasd", "Field Required");
    }

    public void validateEditCourse(Object o, Errors errors) {
        Course course = (Course) o;
        course.setInitdate(new Date(course.getIyear() - 1900, course.getImonth() - 1, course.getIday(), course.getIhour(), course.getIminutes(), course.getIseconds()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "asasd", "Field Required");
        if (!course.getName().isEmpty() && course.getName().length() < 5) {
            errors.rejectValue("name", "At least 5 characters long", "At least 5 characters long");
        }
    }

    public void validateUpdateAddChapter(Object o, Errors errors) {
        Course course = (Course) o;
        course.setInitdate(new Date(course.getIyear() - 1900, course.getImonth() - 1, course.getIday(), course.getIhour(), course.getIminutes(), course.getIseconds()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chapter_name", "asasd", "Field Required");
        if (course.getProblemids().length > 10) {
            errors.rejectValue("problemids", "", "Each chapter chan have at most 10 problems");
        }
        if (course.getUploadfile().getSize() > 1024 * 1000) {
            errors.rejectValue("uploadfile", "", "Each file must not exceed the size of 1MB");
        }
        if (course.getUploadfile()!= null && !course.getUploadfile().isEmpty() && !supportExtension(course.getUploadfile().getOriginalFilename().toLowerCase())) {
            errors.rejectValue("uploadfile", "", "extension not allowed");
        }
    }
    
    public void validateUpdateGeneralInformation(Object o, Errors errors) {
        Course course = (Course) o;        
        if (course.getUploadfile().getSize() > 1024 * 1000) {
            errors.rejectValue("uploadfile", "", "Each file must not exceed the size of 1MB");
        }
        if (course.getUploadfile()!= null && !course.getUploadfile().isEmpty() && !supportExtension(course.getUploadfile().getOriginalFilename().toLowerCase())) {
            errors.rejectValue("uploadfile", "", "extension not allowed");
        }
    }

    private boolean supportExtension(String filename) {
        String[] extensions = ".doc,.docx,.odt,.ppt,.pptx,.ppts,.odp,.pdf,.rar,.tar.gz,.zip,jpg,png,txt".split(",");
        for (int i = 0; i < extensions.length; i++) {
            String string = extensions[i];
            if (filename.endsWith(string)) {
                return true;
            }
        }
        return false;
    }
}
