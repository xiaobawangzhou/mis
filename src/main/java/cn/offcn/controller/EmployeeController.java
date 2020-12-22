package cn.offcn.controller;

import cn.offcn.entity.Employee;
import cn.offcn.entity.EmployeeVo;
import cn.offcn.service.EmployeeService;
import cn.offcn.utils.OAResult;
import cn.offcn.utils.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/{page}")
    public String forwardPage(@PathVariable("page") String page, Integer eid, Model model){
        model.addAttribute("eid",eid);
        return "employee/"+page;
    }

    @ResponseBody
    @RequestMapping("/getEmployees")
    public TableVo<EmployeeVo> getEmployees(int page,int limit){

       return  employeeService.getEmployees(page,limit);
    }

    @ResponseBody
    @RequestMapping("/addEmployee")
    public OAResult addEmployee(Employee employee, int roleid, MultipartFile picImage){

        return employeeService.addEmployee(employee,roleid,picImage);
    }

    @ResponseBody
    @RequestMapping("/getEmployeeByEid")
    public Employee getEmployeeByEid(Integer eid){
        return employeeService.getEmployeeByEid(eid);
    }


    @ResponseBody
    @RequestMapping("/updateEmployee")
    public OAResult updateEmployee(Employee employee, MultipartFile picImage){

        return employeeService.updateEmployee(employee,picImage);
    }

    @ResponseBody
    @RequestMapping("/deleteEmployee")
    public OAResult deleteEmployee(@RequestParam("eids[]") int[] eids){

        return employeeService.deleteEmployee(eids);
    }

    @ResponseBody
    @RequestMapping("/changePicImage")
    public OAResult changePicImage(MultipartFile myPic, HttpSession session){

      return   employeeService.changePicImage(myPic,session);
    }

}
