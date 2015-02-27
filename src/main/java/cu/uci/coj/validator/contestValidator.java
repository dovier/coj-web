/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.model.Contest;
import java.util.Iterator;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
@Component
public class contestValidator implements Validator {

    @Resource
    private ContestDAO contestDAO;

    public boolean supports(Class<?> clazz) {
        return Contest.class.isAssignableFrom(clazz);
    }

    public ContestDAO getContestDAO() {
        return contestDAO;
    }

    public void setContestDAO(ContestDAO contestDAO) {
        this.contestDAO = contestDAO;
    }

    public void validate(Object o, Errors errors) {
        Contest contest = (Contest) o;
        try {
            String empty = "errormsg.10";
            String invalid = "errormsg.9";
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cid", empty);
            if (!errors.hasFieldErrors("cid") && contestDAO.bool("exist.contest", contest.getCid())) {
                errors.rejectValue("cid",
                        "page.error.exist",
                        "At must 40 characters.");
            }

            if (contest.isImportData() && contest.getImportCid() <= 0) {
                errors.rejectValue("importCid",
                        invalid,
                        "At must 40 characters.");
            }
            if (contest.isImportData() && contest.getImports() == null) {
                errors.rejectValue("importCid",
                        "error.createcontest.imports.miss",
                        "At must 40 characters.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void manageContest(Object o, Errors errors) {
        Contest contest = (Contest) o;
        contest.initdates();
        String empty = "errormsg.10";
        String invalid = "errormsg.9";
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", empty);
        if (contest.getRglimit().compareTo(contest.getEnddate()) > 0) {
            errors.rejectValue("rglimit",
                    "managecontest.error.rglimit.end",
                    "At must 40 characters.");
        }

        if (contest.getInitdate().compareTo(contest.getEnddate()) >= 0) {
            errors.rejectValue("initdate",
                    "managecontest.error.initdate.end",
                    "At must 40 characters.");
        }

        if (contestDAO.bool("exist.contest.name", contest.getName(), contest.getCid())) {
            errors.rejectValue("name",
                    "managecontest.error.name",
                    "At must 40 characters.");
        }

        if (contest.isEnabled()) {
            if (contest.getStyle() == 0) {
                errors.rejectValue("style",
                        "managecontest.error.enabled.style",
                        "At must 40 characters.");
            }

        }

        if (contest.getStyle() == 1) {
            if (contest.getInitdate().compareTo(contest.getEnddate()) < 0) {
                long diff = contest.getEnddate().getTime() - contest.getInitdate().getTime();
                diff /= 1000;
                diff /= 60;
                if (diff > 60 * 8) {
                    errors.rejectValue("style",
                            "managecontest.error.acm.time",
                            "At must 40 characters.");
                }
            }
        }
    }

    public void validateGlobalSettings(Object o, Errors errors) {
        Contest contest = (Contest) o;
        String empty = "errormsg.10";
        String invalid = "errormsg.9";
        switch (contest.getStyle()) {
            case 1: {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "penalty", empty);
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "frtime", empty);
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deadtime", empty);
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unfreeze_time", empty);
                break;
            }
            case 2: {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ioimark", empty);
                break;
            }
            case 3: {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ppoints", empty);
                break;
            }
            case 4: {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "levels", empty);
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "acbylevels", empty);
                break;
            }
        }

        switch (contest.getRegistration()) {
            case 1: {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "total_users", empty);
                break;
            }
        }
        switch (contest.getStyle()) {
            case 1: {
                if (contest.getPenalty() < 0) {
                    errors.rejectValue("penalty",
                            invalid,
                            "At must 40 characters.");
                }
                if (contest.getFrtime() < 0) {
                    errors.rejectValue("frtime",
                            invalid,
                            "At must 40 characters.");
                }
                if (contest.getDeadtime() < 0) {
                    errors.rejectValue("deadtime",
                            invalid,
                            "At must 40 characters.");
                }
                if (contest.getUnfreeze_time() < 0) {
                    errors.rejectValue("unfreeze_time",
                            invalid,
                            "At must 40 characters.");
                }
                break;
            }
            case 2: {
                if (contest.getIoimark() < 0) {
                    errors.rejectValue("ioimark",
                            invalid,
                            "At must 40 characters.");
                }
                break;
            }
            case 3: {
                if (contest.getPpoints() < 0) {
                    errors.rejectValue("ppoints",
                            invalid,
                            "At must 40 characters.");
                }
                break;
            }
            case 4: {
                if (contest.getLevels() <= 0) {
                    errors.rejectValue("levels",
                            invalid,
                            "");
                }
                if (contest.getAcbylevels() <= 0) {
                    errors.rejectValue("acbylevels",
                            invalid,
                            "");
                }
                if (contest.getAclimit() <= 0) {
                    errors.rejectValue("aclimit",
                            invalid,
                            "");
                }
                break;
            }
        }
        switch (contest.getRegistration()) {
            case 1: {
                if (contest.getTotal_users() < 0) {
                    errors.rejectValue("total_users",
                            invalid,
                            "At must 40 characters.");
                }
                break;
            }
        }

        if (contest.getGold() < 0) {
            errors.rejectValue("gold",
                    invalid,
                    "At must 40 characters.");
        }

        if (contest.getSilver() < 0) {
            errors.rejectValue("silver",
                    invalid,
                    "At must 40 characters.");
        }
        if (contest.getBronze() < 0) {
            errors.rejectValue("bronze",
                    invalid,
                    "At must 40 characters.");
        }
    }

    public void contestProblems(Object o, Errors errors) {
        Contest contest = (Contest) o;

        if (contest.getProblemids().length > 12 && contest.getStyle() == 1) {
            errors.rejectValue("style",
                    "contestproblems.errors.acm",
                    "At must 40 characters.");
        }

    }
}
