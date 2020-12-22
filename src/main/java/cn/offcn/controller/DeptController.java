package cn.offcn.controller;

import cn.offcn.entity.Dept;
import cn.offcn.service.DeptService;
import cn.offcn.utils.OAResult;
import cn.offcn.utils.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;
    @RequestMapping("/{page}")
    public String forwardPage(@PathVariable("page") String page, Integer deptno, Model model){
        model.addAttribute("deptno",deptno);
        return "dept/"+page;
    }

    @ResponseBody
    @RequestMapping("/getDepts")
    public TableVo<Dept> getDepts(int page, int limit){

        return deptService.getDepts(page,limit);
    }

    @ResponseBody
    @RequestMapping("/getDeptByDeptno")
    public Dept getDeptByDeptno(int deptno){
        return deptService.getDeptByDeptno(deptno);
    }


    @ResponseBody
    @RequestMapping("/updateDept")
    public OAResult updateDept(Dept dept){
        return deptService.updateDept(dept);
    }


    @ResponseBody
    @RequestMapping("/deleteDeptno")
    public OAResult deleteDeptno(@RequestParam("deptnos[]") int[] deptnos){

        return deptService.deleteDeptno(deptnos);
    }

    @ResponseBody
    @RequestMapping("/getAllDepts")
    public List<Dept> getAllDepts(){

       return  deptService.getAllDepts();
    }
}
