package cn.offcn.service.impl;

import cn.offcn.entity.Sources;
import cn.offcn.entity.SourcesExample;
import cn.offcn.mapper.SourcesMapper;
import cn.offcn.service.SourcesService;
import cn.offcn.utils.JsonUtils;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService {

    @Autowired
    private SourcesMapper sourcesMapper;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public List<Sources> getRootSourcesByPid(int pid) {

        SourcesExample sourcesExample=new SourcesExample();
        SourcesExample.Criteria criteria = sourcesExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<Sources> sourcesList= sourcesMapper.selectByExample(sourcesExample);
        //求子项
        for(Sources sources : sourcesList){
            List<Sources> subList=getRootSourcesByPid(sources.getId());
            sources.setChildren(subList);
        }
        return sourcesList;
    }

    public  List<Sources> getParentNodes(int pid, List<Sources> parentList){
        SourcesExample sourcesExample=new SourcesExample();
        SourcesExample.Criteria criteria = sourcesExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<Sources> sourcesList= sourcesMapper.selectByExample(sourcesExample);
        for(Sources sources : sourcesList){
            if(isExistsChildrenNodes(sources.getId())){
                parentList.add(sources);
            }
            sourcesList=getParentNodes(sources.getId(),parentList);
        }
        return parentList;
    }

    /**
     * 判断id有没有子节点，如果有返回true,没有返回false
     * @param id
     * @return
     */
    public boolean isExistsChildrenNodes(int id){
        SourcesExample sourcesExample=new SourcesExample();
        SourcesExample.Criteria criteria = sourcesExample.createCriteria();
        criteria.andPidEqualTo(id);
        List<Sources> sourcesList= sourcesMapper.selectByExample(sourcesExample);
        if(sourcesList!=null && sourcesList.size()>0){
            return true;
        }
        return false;
    }

    public OAResult addSources(Sources sources){
        int rows=sourcesMapper.insert(sources);
        if(rows==1){
            return OAResult.ok(200,"操作成功");
        }

        return OAResult.ok(400,"操作失败");
    }

    public Sources getSourcesById(int id){
        return sourcesMapper.selectByPrimaryKey(id);
    }

    public  OAResult updateSourcesById(Sources sources){

       int rows= sourcesMapper.updateByPrimaryKey(sources);
       if(rows==1){
            return OAResult.ok(200,"操作成功");
        }

        return OAResult.ok(400,"操作失败");
    }

    public OAResult deleteResources(Integer id){
        int rows= sourcesMapper.deleteByPrimaryKey(id);
        if(rows==1){
            return OAResult.ok(200,"操作成功");
        }

        return OAResult.ok(400,"操作失败");
    }

    @Override
    public List<Sources> getCurrentUserSources(int eid, int pid) {
        List<Sources> sourcesList= sourcesMapper.getCrrentEmployeeSources(eid,pid);
        for(Sources sources : sourcesList){
            List<Sources> subSourcesList= getCurrentUserSources(eid,sources.getId());
            sources.setChildren(subSourcesList);
        }
        return sourcesList;
    }

    public List<Sources> getRedisCacheSources(int eid,int pid,String username){

        Jedis jedis=null;
        //从redis中取数据
        try{
            jedis=  jedisPool.getResource();
            String jsonData=jedis.hget("MenuSources",username);
            if(jsonData!=null){
               return  JsonUtils.jsonToList(jsonData,Sources.class);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null) jedis.close();
        }

        //查询数据库
        List sourceList=getCurrentUserSources(eid,pid);

        //往redis中放数据
        try{
            jedis=  jedisPool.getResource();
            jedis.hset("MenuSources",username, JsonUtils.objectToJson(sourceList));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null) jedis.close();
        }

        return sourceList;
    }
}
