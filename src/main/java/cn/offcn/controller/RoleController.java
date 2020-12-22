package cn.offcn.controller;

import cn.offcn.entity.Role;
import cn.offcn.service.RoleService;
import cn.offcn.utils.OAResult;
import cn.offcn.utils.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private  RoleService roleService;
    @RequestMapping("/{page}")
    public String forwardPage(@PathVariable("page") String page, Integer roleid, Model model){

        model.addAttribute("roleid",roleid);
        return "role/"+page;
    }

    @ResponseBody
    @RequestMapping("/saveRole")
    public OAResult saveRole(Role role,String ids){


        return roleService.saveRole(role,ids);
    }

    @ResponseBody
    @RequestMapping("/getAllRoles")
    public TableVo<Role> getAllRoles(int page,int limit){

        return roleService.getAllRoles(page,limit);
    }

    @ResponseBody
    @RequestMapping("/deleteRoleById")
    public OAResult deleteRoleById(int roleid){

       return roleService.deleteRoleById(roleid);
    }


    @ResponseBody
    @RequestMapping("/getRoleByRoleid")
    public Map<String,Object> getRoleByRoleid(int roleid){

        return roleService.getRoleByRoleid(roleid);
    }

    @ResponseBody
    @RequestMapping("/updateRole")
    public OAResult updateRole(Role role,String ids){

        return roleService.updateRole(role,ids);
    }

    @ResponseBody
    @RequestMapping("/getCurrentAllRoles")
    public List<Role> getCurrentAllRoles(){

        return roleService.getCurrentAllRoles();
    }
}
