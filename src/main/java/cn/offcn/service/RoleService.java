package cn.offcn.service;

import cn.offcn.entity.Role;
import cn.offcn.utils.OAResult;
import cn.offcn.utils.TableVo;

import java.util.List;
import java.util.Map;

public interface RoleService {


    public OAResult saveRole(Role role, String ids);

    public TableVo<Role> getAllRoles(int page,int limit);

    public OAResult deleteRoleById(int roleid);

    public Map<String, Object> getRoleByRoleid(int roleid);

    public  OAResult updateRole(Role role, String ids);

    List<Role> getCurrentAllRoles();
}
