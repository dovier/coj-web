package cu.uci.coj.utils.paging;


public class PagingOptions {
	/*
	 * @RequestParam(required = false, defaultValue = "1", value =
	 * IPaginatedList.IRequestParameters.PAGE) Integer pageEnabled,
	 * 
	 * @RequestParam(required = false, value =
	 * IPaginatedList.IRequestParameters.DIRECTION) String directionEnabled,
	 * 
	 * @RequestParam(required = false, value =
	 * IPaginatedList.IRequestParameters.SORT) String sortEnabled,
	 */
	private Integer page;
	private String direction;
	private String sort;
	
	
	
	public PagingOptions() {
		super();
		page = 1;
		direction = null;
		sort = null;
	}

	public PagingOptions(int page) {
		super();
		this.page = page;
		direction = null;
		sort = null;
	}

	public PagingOptions(Integer page, String direction, String sort) {
		super();
		this.page = page;
		this.direction = direction;
		this.sort = sort;
	}
	
	
	public Integer getOffset(int pageSize){
		return (page-1)*pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}


}
