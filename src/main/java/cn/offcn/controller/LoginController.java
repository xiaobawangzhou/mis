package cn.offcn.controller;

import cn.offcn.entity.Employee;
import cn.offcn.service.EmployeeService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value={"/login","/"})
    public String forwardPage(){
        return "login/login";
    }

    @ResponseBody
    @RequestMapping("/checkLogin")
    public OAResult checkLogin(String username, String password, HttpSession session){

        System.out.println("username:"+username);
        OAResult oaResult=employeeService.checkLogin(username,password);
        if(oaResult.getStatus()==200){
            //session中存放登陆标志
            session.setAttribute("activeUser",oaResult.getData());
        }
        return oaResult;
    }

    @ResponseBody
    @RequestMapping("/checkPassword")
    public OAResult checkPassword(String password, HttpSession session){

        Employee employee=(Employee)session.getAttribute("activeUser");
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        if(password.equals(employee.getPassword())){
            return OAResult.ok(200,"原密码正确");
        }
        return OAResult.ok(400,"原密码不正确");
    }

    @ResponseBody
    @RequestMapping("/updatePassword")
    public OAResult updatePassword(String password, HttpSession session){

        Employee employee=(Employee)session.getAttribute("activeUser");
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        employee.setPassword(password);
        OAResult oaResult=employeeService.updatePassword(employee);
        if(oaResult.getStatus()==200){
            session.invalidate();
        }
        return oaResult;
    }


    @RequestMapping("/exitSystem")
    public void exitSystem(HttpServletRequest request, HttpServletResponse response)throws Exception{

        HttpSession session=request.getSession();
        if(session!=null){
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath()+"/login");
    }

}
