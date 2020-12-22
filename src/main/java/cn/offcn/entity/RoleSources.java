package cn.offcn.entity;

public class RoleSources {
    private Integer rsid;

    private String rsdis;

    private Integer resourcesFk;

    private Integer roleFk;

    public Integer getRsid() {
        return rsid;
    }

    public void setRsid(Integer rsid) {
        this.rsid = rsid;
    }

    public String getRsdis() {
        return rsdis;
    }

    public void setRsdis(String rsdis) {
        this.rsdis = rsdis == null ? null : rsdis.trim();
    }

    public Integer getResourcesFk() {
        return resourcesFk;
    }

    public void setResourcesFk(Integer resourcesFk) {
        this.resourcesFk = resourcesFk;
    }

    public Integer getRoleFk() {
        return roleFk;
    }

    public void setRoleFk(Integer roleFk) {
        this.roleFk = roleFk;
    }
}