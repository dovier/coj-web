package cu.uci.coj.dao;

import java.util.List;

import cu.uci.coj.model.Institution;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

public interface InstitutionDAO extends BaseDAO {
    
    public String getInstitutionName(int inst_id);


    public int countEnabledInstitutions(String pattern);

    public int countEnabledInstitutionsByCountry(String pattern, int country_id);

    public IPaginatedList<Institution> getEnabledInstitutions(String pattern, int found, PagingOptions options);

    public IPaginatedList<Institution> getEnabledInstitutionsByCountry(String pattern, int found,PagingOptions options, int country_id);

    public List<Institution> getEnabledInstitutions();

    public List<Institution> getEnabledInstitutionsByCountry_id(int country_id);

    public IPaginatedList<Institution> getAllInstitutions(String pattern, PagingOptions options);

    public void updateInstitution(Institution institution);

    public boolean existInstitutionZipBut(String zip, int inst_id);

    public void insertInstitution(Institution institution);

    public boolean existInstitutionNameByCountry(String name, int country_id);

    public boolean existInstitutionZip(String zip);

    public double getScore(String zip);    
    
}
