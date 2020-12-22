package cn.offcn.service.impl;

import cn.offcn.entity.*;
import cn.offcn.mapper.EmpRoleMapper;
import cn.offcn.mapper.RoleMapper;
import cn.offcn.mapper.RoleSourcesMapper;
import cn.offcn.service.RoleService;
import cn.offcn.utils.OAResult;
import cn.offcn.utils.TableVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleSourcesMapper roleSourcesMapper;
    @Autowired
    private EmpRoleMapper empRoleMapper;
    @Override
    public OAResult saveRole(Role role, String ids) {

        //保存角色
       int rows=  roleMapper.insert(role);
       String[] sourcesIds=ids.split(",");
       int count=0;
       for(String sourcesId : sourcesIds){
           RoleSources roleSources=new RoleSources();
           roleSources.setRoleFk(role.getRoleid());
           roleSources.setResourcesFk(Integer.parseInt(sourcesId));
           roleSourcesMapper.insert(roleSources);
           count++;
       }
       if(rows==1 && count==sourcesIds.length){
           return OAResult.ok(200,"操作成功");
       }

        return OAResult.ok(400,"操作失败");
    }

    public TableVo<Role> getAllRoles(int page,int limit){

        PageHelper.startPage(page,limit);
        RoleExample roleExample=new RoleExample();
        List<Role> roleList=roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo=new PageInfo<Role>(roleList);
        TableVo<Role> tableVo=new TableVo<Role>();
        tableVo.setData(pageInfo.getList());
        tableVo.setCode(0);
        tableVo.setCount(pageInfo.getTotal());
        tableVo.setMsg("");
        return tableVo;
    }

    public OAResult deleteRoleById(int roleid){

        //删除role_resours中间表中的数据
        RoleSourcesExample roleSourcesExample=new RoleSourcesExample();
        RoleSourcesExample.Criteria roleSourcesCriteria = roleSourcesExample.createCriteria();
        roleSourcesCriteria.andRoleFkEqualTo(roleid);
        roleSourcesMapper.deleteByExample(roleSourcesExample);

        //删除emp_role中间表中的数据
        EmpRoleExample empRoleExample=new EmpRoleExample();
        EmpRoleExample.Criteria empRoleExampleCriteria = empRoleExample.createCriteria();
        empRoleExampleCriteria.andRoleFkEqualTo(roleid);
        empRoleMapper.deleteByExample(empRoleExample);

        //删除role表中的数据
        int rows= roleMapper.deleteByPrimaryKey(roleid);
        if(rows==1){
            return OAResult.ok(200,"操作成功");
        }
        return OAResult.ok(400,"操作失败");
    }

    public Map<String, Object> getRoleByRoleid(int roleid){
        //查询role对象
        Role role= roleMapper.selectByPrimaryKey(roleid);
        //查询role所拥有的资源
        RoleSourcesExample roleSourcesExample=new RoleSourcesExample();
        RoleSourcesExample.Criteria criteria = roleSourcesExample.createCriteria();
        criteria.andRoleFkEqualTo(roleid);
        List<RoleSources> roleSourcesList = roleSourcesMapper.selectByExample(roleSourcesExample);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("role",role);
        map.put("roleSourcesList",roleSourcesList);
        return map;
    }

    public  OAResult updateRole(Role role, String ids){

        //更新角色Role
        int rows=roleMapper.updateByPrimaryKey(role);
        //删除当前role的role_sources中间表数据
        RoleSourcesExample roleSourcesExample=new RoleSourcesExample();
        RoleSourcesExample.Criteria roleSourcesExampleCriteria = roleSourcesExample.createCriteria();
        roleSourcesExampleCriteria.andRoleFkEqualTo(role.getRoleid());
        roleSourcesMapper.deleteByExample(roleSourcesExample);

        //重新把当前角色所拥用的资源添加到中间表中
        String[] sourcesIds=ids.split(",");
        int count=0;
        for(String sourcesId : sourcesIds){
            RoleSources roleSources=new RoleSources();
            roleSources.setRoleFk(role.getRoleid());
            roleSources.setResourcesFk(Integer.parseInt(sourcesId));
            roleSourcesMapper.insert(roleSources);
            count++;
        }
        if(rows==1 && count==sourcesIds.length){
            return  OAResult.ok(200,"操作成功");
        }

        return OAResult.ok(400,"操作失败");
    }

    @Override
    public List<Role> getCurrentAllRoles() {

        return roleMapper.selectByExample(new RoleExample());
    }


}
