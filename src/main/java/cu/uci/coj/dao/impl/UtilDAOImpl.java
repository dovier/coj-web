package cu.uci.coj.dao.impl;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Language;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository("utilDAO")
@Transactional
public class UtilDAOImpl extends BaseDAOImpl implements UtilDAO {

    @Transactional(readOnly = true)
    public List<Language> getEnabledProgramingLanguages() {
        List<Language> languages = objects("enabled.programming.language", Language.class);
        for (Language language: languages)
            language.setDescripcion(language.getLanguage() + " (" + language.getDescripcion()+ ")");
        return languages;
    }

    public Language getLanguageByExtension(String ext) {

        List<Language> languages = getEnabledProgramingLanguages();

        Language val = null;
        if (!CollectionUtils.isEmpty(languages)) {
            Iterator<Language> iterator = languages.iterator();

            do {
                val = iterator.next();
            } while (!ext.equals(val.getExt()) && iterator.hasNext());
        }
        return val;
    }

    @Transactional(readOnly = true)
    public List<Language> getEnabledLanguagesByProblem(Integer pid) {
        List<Language> languages = null;
        if (pid != null && !pid.equals("")) {
            languages = objects("enabled.language.by.problem.1", Language.class, pid);
        } else {
            languages = objects("enabled.language.by.problem", Language.class);
        }
        for (Language language: languages)
            language.setDescripcion(language.getLanguage() + " (" + language.getDescripcion()+ ")");
        return languages;
    }

    @PostConstruct
    public void startUpConfigurations() {
        dml("update.users.startup");
    }
    
    //frankr addition start
    @Override
    @Transactional(readOnly = true)    
    public String getClassificationNameById(Integer idClassification){
    	String sqlKey = Config.getProperty("classification.name.by.id");
    	String result = string(sqlKey, idClassification);
    	return result;
    }
    //frankr addition end
    
}
