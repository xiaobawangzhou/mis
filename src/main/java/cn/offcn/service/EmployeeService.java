package cn.offcn.service;

import cn.offcn.entity.Employee;
import cn.offcn.entity.EmployeeVo;
import cn.offcn.utils.OAResult;
import cn.offcn.utils.TableVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface EmployeeService {
    TableVo<EmployeeVo> getEmployees(int page, int limit);

    OAResult addEmployee(Employee employee, int roleid, MultipartFile picImage);

    Employee getEmployeeByEid(Integer eid);

    OAResult updateEmployee(Employee employee, MultipartFile picImage);

    OAResult deleteEmployee(int[] eids);

    OAResult checkLogin(String username, String password);

    OAResult changePicImage(MultipartFile myPic, HttpSession session);

    OAResult updatePassword(Employee employee);
}
