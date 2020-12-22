package cn.offcn.controller;

import cn.offcn.entity.Sources;
import cn.offcn.service.SourcesService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/resources")
public class ResourcesController {


    @Autowired
    private SourcesService sourcesService;

    @RequestMapping("/{page}")
    public String forwardPage(@PathVariable("page") String page, Integer id, Model model){
        //获取id并放到request范围
        model.addAttribute("id",id);
        return "sources/"+page;
    }

    @ResponseBody
    @RequestMapping("/getRootSourcesByPid")
    public List<Sources> getRootSourcesByPid(@RequestParam(defaultValue = "0") Integer pid){

        return sourcesService.getRootSourcesByPid(pid);
    }

    @ResponseBody
    @RequestMapping("/getParentNodes")
    public List<Sources> getParentNodes(@RequestParam(defaultValue = "0") Integer pid){
        List<Sources> parentList=new ArrayList<Sources>();
        return sourcesService.getParentNodes(pid,parentList);
    }

    @ResponseBody
    @RequestMapping("/addSources")
    public OAResult addSources(Sources sources){

       return  sourcesService.addSources(sources);
    }

    @ResponseBody
    @RequestMapping("/getSourcesById")
    public Sources getSourcesById(int id){

        return sourcesService.getSourcesById(id);
    }

    @ResponseBody
    @RequestMapping("/updateSourcesById")
    public OAResult updateSourcesById(Sources sources){

        return sourcesService.updateSourcesById(sources);
    }

    @ResponseBody
    @RequestMapping("/deleteResources")
    public OAResult deleteResources(Integer id){

        return sourcesService.deleteResources(id);
    }
}
