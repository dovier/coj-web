package cu.uci.coj.dao;

import cu.uci.coj.model.Country;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

public interface CountryDAO extends BaseDAO {

    public int countEnabledCountries(String pattern);

    public IPaginatedList<Country> getEnabledCountries(String pattern, int found,PagingOptions options);

    public IPaginatedList<Country> getCountry(String pattern, PagingOptions options);


}
