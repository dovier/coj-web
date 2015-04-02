package cu.uci.coj.model;

import org.apache.commons.io.FilenameUtils;

public class SharedFile extends BaseBean{

	private Integer fid;
	private String name;
	private String path;
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getExtension(){
		return FilenameUtils.getExtension(name);
	}
}
