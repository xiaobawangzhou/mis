package cn.offcn.entity;

public class EmployeeVo extends Employee {


    private String dname;

    public EmployeeVo(){}
    public EmployeeVo(Employee employee){
        this.setEid(employee.getEid());
        this.setEname(employee.getEname());
        this.setEsex(employee.getEsex());
        this.setEage(employee.getEage());
        this.setTelephone(employee.getTelephone());
        this.setHiredate(employee.getHiredate());
        this.setPnum(employee.getPnum());
        this.setUsername(employee.getUsername());
        this.setPassword(employee.getPassword());
        this.setPic(employee.getPic());
        this.setRemark(employee.getRemark());
        this.setDfk(employee.getDfk());
    }
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
