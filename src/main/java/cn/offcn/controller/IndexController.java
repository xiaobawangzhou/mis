package cn.offcn.controller;

import cn.offcn.entity.Employee;
import cn.offcn.entity.Sources;
import cn.offcn.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/main")
public class IndexController {

    @Autowired
    private SourcesService sourcesService;
    @RequestMapping("/{page}")
    public String forwardPage(@PathVariable("page") String page){
        return "main/"+page;
    }

    @RequestMapping("/index")
    public String forardIndex(HttpServletRequest request, @RequestParam(defaultValue = "1") int pid){
        Employee employee=(Employee)request.getSession().getAttribute("activeUser");
        List<Sources> sourceList=sourcesService.getRedisCacheSources(employee.getEid(),pid,employee.getUsername());
        request.setAttribute("sourceList",sourceList);
        return "main/index";
    }
}
