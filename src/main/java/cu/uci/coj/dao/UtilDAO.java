package cu.uci.coj.dao;

import cu.uci.coj.model.Authority;
import cu.uci.coj.model.GlobalFlags;
import cu.uci.coj.model.Group;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Locale;
import cu.uci.coj.model.Rule;

import java.util.List;

/**
 * @version 2.0
 * @since 2010-09-01
 */
public interface UtilDAO extends BaseDAO {

    public List<Language> getEnabledProgramingLanguages();
    public List<Language> getEnabledLanguagesByProblem(Integer pid);
    public Language getLanguageByExtension(String ext);
    public String getClassificationNameById(Integer idClassification);   
}
