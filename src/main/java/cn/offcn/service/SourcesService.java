package cn.offcn.service;

import cn.offcn.entity.Sources;
import cn.offcn.utils.OAResult;

import java.util.List;

public interface SourcesService {

    public List<Sources> getRootSourcesByPid(int pid);

    public  List<Sources> getParentNodes(int pid, List<Sources> parentList);

    public OAResult addSources(Sources sources);

    public Sources getSourcesById(int id);

    public  OAResult updateSourcesById(Sources sources);

    public OAResult deleteResources(Integer id);


    public List<Sources> getCurrentUserSources(int eid,int pid);

    public List<Sources> getRedisCacheSources(int eid,int pid,String username);
}
