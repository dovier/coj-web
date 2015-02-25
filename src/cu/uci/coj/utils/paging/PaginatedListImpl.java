package cu.uci.coj.utils.paging;

import java.util.List;

import org.displaytag.properties.SortOrderEnum;

/**
 * <code>com.gorti.project.web.ui.action.PaginatedListImpl</code> implemnts
 * </code>com.gorti.project.web.ui.action.IExtendedPaginatedList</code> This
 * class can be used for pagination purpose. This class depends upon
 * HttpServletRequest object. To be used by Controllers in case of Http
 * requests.
 * 
 * @author  
 */
public class PaginatedListImpl<T> implements IPaginatedList<T> {

    /** current page index, starts at 0 */
    private int index;
    /** number of results per page (number of rows per page to be displayed ) */
    private int pageSize;
    /** total number of results (the total number of rows ) */
    private int fullListSize;
    /** list of results (rows found ) in the current page */
    private List<T> list;
    /** default sorting order */
    private SortOrderEnum sortDirection = SortOrderEnum.ASCENDING;
    /** sort criteria (sorting property name) */
    private String sortCriterion;

    /** default constructor * */
    public PaginatedListImpl() {
    }

    public int getFirstRecordIndex() {
        return index * pageSize;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> results) {
        this.list = results;
    }

    public int getFullListSize() {
        return fullListSize;
    }

    public void setFullListSize(int total) {
        this.fullListSize = total;
    }

    public int getTotalPages() {
        return (int) Math.ceil(((double) fullListSize) / pageSize);
    }
    
    public int[] getNumbers(){
    	int[] pages = new int[getTotalPages()];
    	for(int i = 1;i < pages.length+1;i++)
    		pages[i-1] = i;
    	return pages;
    }

    public int getObjectsPerPage() {
        return pageSize;
    }

    public int getPageNumber() {
        return index + 1;
    }

    public String getSearchId() {
        // Not implemented for now.
        // This is required, if we want the ID to be included in the paginated
        // purpose.
        return null;

    }

    public String getSortCriterion() {
        return sortCriterion;
    }

    public SortOrderEnum getSortDirection() {
        return sortDirection;
    }

    public void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }

    public void setSortDirection(SortOrderEnum sortDirection) {
        this.sortDirection = sortDirection;
    }
}
