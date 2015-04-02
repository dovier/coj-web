package cu.uci.coj.model;

public class Clarification {

    private int id;
    private int cid;
    private int idteam;
    private int idteamfor;
    private String title;
    private String description;
    private String date;
    private boolean publics;
    private boolean isread;
    private boolean forall;
    private boolean reply;
    private String general;
    private String cclass;
    private String username;
    private String ptitle;
    private String usernamefrom;
    private String usernameto;
    private int size;
    private String originalMSG;
    private boolean byjudge;
    private Integer pid;
    private boolean request;

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }
    
    public boolean isReply() {
        return reply;
    }

    public void setReply(boolean reply) {
        this.reply = reply;
    }

    public Clarification(int id, int cid, int idteam, int idteamfor, String title, String text, String date, boolean publics, boolean isread, boolean forall) {
        this.id = id;
        this.cid = cid;
        this.idteam = idteam;
        this.idteamfor = idteamfor;
        this.title = title;
        this.description = text;
        this.date = date;
        this.publics = publics;
        this.isread = isread;
        this.forall = forall;
        this.date = date.substring(0, 19);
    }

    public Clarification() {
    }

    public Clarification(int idteam, int pid, String title, String text, String date, boolean publics, boolean isread, int id) {
        this.idteam = idteam;
        this.pid = pid;
        this.title = title;
        this.description = text;
        this.date = date;
        this.publics = publics;
        this.isread = isread;
        this.id = id;
        if (!isread) {
            this.cclass = "unread";
        } else {
            this.cclass = "read";
        }
        this.date = date.substring(0, 19);
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isForall() {
        return forall;
    }

    public void setForall(boolean forall) {
        this.forall = forall;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdteam() {
        return idteam;
    }

    public void setIdteam(int idteam) {
        this.idteam = idteam;
    }

    public int getIdteamfor() {
        return idteamfor;
    }

    public void setIdteamfor(int idteamfor) {
        this.idteamfor = idteamfor;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public boolean isPublics() {
        return publics;
    }

    public void setPublics(boolean publics) {
        this.publics = publics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getCclass() {
        return cclass;
    }

    public void setCclass(String cclass) {
        this.cclass = cclass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getUsernamefrom() {
        return usernamefrom;
    }

    public void setUsernamefrom(String usernamefrom) {
        this.usernamefrom = usernamefrom;
    }

    public String getUsernameto() {
        return usernameto;
    }

    public void setUsernameto(String usernameto) {
        this.usernameto = usernameto;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOriginalMSG() {
        return originalMSG;
    }

    public void setOriginalMSG(String originalMSG) {
        this.originalMSG = originalMSG;
    }

    public boolean isByjudge() {
        return byjudge;
    }

    public void setByjudge(boolean byjudge) {
        this.byjudge = byjudge;
    }

    public Object[] toArray() {
        return new Object[]{cid, idteam, pid, title, description, publics, forall};
    }

    public Object[] toArrayRequest() {
        return new Object[]{cid, idteam, pid, title, description};
    }
}

