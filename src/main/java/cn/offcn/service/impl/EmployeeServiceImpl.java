package cn.offcn.service.impl;

import cn.offcn.entity.*;
import cn.offcn.mapper.DeptMapper;
import cn.offcn.mapper.EmpRoleMapper;
import cn.offcn.mapper.EmployeeMapper;
import cn.offcn.mapper.RoleMapper;
import cn.offcn.service.EmployeeService;
import cn.offcn.utils.OAResult;
import cn.offcn.utils.TableVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Value("${uploadFileDir}")
    private String uploadFileDir;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpRoleMapper empRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private JedisPool jedisPool;
    @Override
    public TableVo<EmployeeVo> getEmployees(int page, int limit) {
        PageHelper.startPage(page,limit);
        EmployeeExample employeeExample=new EmployeeExample();
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        PageInfo<Employee> pageInfo=new PageInfo<Employee>(employeeList);
        TableVo<EmployeeVo> tableVo=new TableVo<EmployeeVo>();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount(pageInfo.getTotal());
        List<EmployeeVo> employeeVoList=new ArrayList<EmployeeVo>();
        List<Employee> pageListEmployee= pageInfo.getList();
        for(Employee employee : pageListEmployee){
            EmployeeVo employeeVo=new EmployeeVo(employee);
            employeeVo.setDname(deptMapper.selectByPrimaryKey(employee.getDfk()).getDname());
            employeeVoList.add(employeeVo);
        }
        tableVo.setData(employeeVoList);
        return tableVo;
    }

    @Override
    public OAResult addEmployee(Employee employee, int roleid, MultipartFile picImage) {

        //用户上传了头像
        try {
              if(picImage!=null && picImage.getSize()>0){
                //获取文件名
                String originalFilename=picImage.getOriginalFilename();
                int hashCode= originalFilename.hashCode();
                int d1= hashCode&0xf;
                int d2=(hashCode>>2)&0xf;
                String filePath=uploadFileDir+"\\"+d1+"\\"+d2;
                String extName=originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFileName= UUID.randomUUID().toString()+extName;
                File saveDir=new File(filePath);
                if(!saveDir.exists()) saveDir.mkdirs();
                File file=new File(saveDir,newFileName);
                picImage.transferTo(file);
                employee.setPic("/pic/images/"+d1+"/"+d2+"/"+newFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return OAResult.ok(401,"头像上传失败");
        }
        //保存员工信息
        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        int rows=employeeMapper.insert(employee);
        //保存员工所对应的角色信息
        EmpRole empRole=new EmpRole();
        empRole.setRoleFk(roleid);
        empRole.setEmpFk(employee.getEid());
        empRoleMapper.insert(empRole);
        if(rows==1){
            return OAResult.ok(200,"操作成功");
        }
        return OAResult.ok(400,"操作失败");
    }

    @Override
    public Employee getEmployeeByEid(Integer eid) {

        Employee employee=employeeMapper.selectByPrimaryKey(eid);

        EmpRoleExample empRoleExample=new EmpRoleExample();
        EmpRoleExample.Criteria criteria = empRoleExample.createCriteria();
        criteria.andEmpFkEqualTo(eid);
        List<EmpRole> empRoleList = empRoleMapper.selectByExample(empRoleExample);
        if(empRoleList!=null && empRoleList.size()>0){
            employee.setRoleid(empRoleList.get(0).getRoleFk());
        }
        return employee;
    }

    @Override
    public OAResult updateEmployee(Employee employee, MultipartFile picImage) {

        Employee oldEmployee=employeeMapper.selectByPrimaryKey(employee.getEid());
        //用户上传了头像
        try {
            if(picImage!=null && picImage.getSize()>0){
                //获取文件名
                String originalFilename=picImage.getOriginalFilename();
                int hashCode= originalFilename.hashCode();
                int d1= hashCode&0xf;
                int d2=(hashCode>>2)&0xf;
                String filePath=uploadFileDir+"\\"+d1+"\\"+d2;
                String extName=originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFileName= UUID.randomUUID().toString()+extName;
                File saveDir=new File(filePath);
                if(!saveDir.exists()) saveDir.mkdirs();
                File file=new File(saveDir,newFileName);
                picImage.transferTo(file);
                employee.setPic("/pic/images/"+d1+"/"+d2+"/"+newFileName);
            }else{
                employee.setPic(oldEmployee.getPic());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return OAResult.ok(401,"头像上传失败");
        }
        //保存员工信息
        System.out.println("password==>"+employee.getPassword());
        if(employee.getPassword()==null || "".equals(employee.getPassword())){
             employee.setPassword(oldEmployee.getPassword());
        }else{
            employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        }

        //修改员工
        int rows=employeeMapper.updateByPrimaryKey(employee);

        //该删该员工的角色
        EmpRoleExample empRoleExample=new EmpRoleExample();
        EmpRoleExample.Criteria criteria = empRoleExample.createCriteria();
        criteria.andEmpFkEqualTo(employee.getEid());
        empRoleMapper.deleteByExample(empRoleExample);

        //修改员工所对应的角色信息
        EmpRole empRole=new EmpRole();
        empRole.setRoleFk(employee.getRoleid());
        empRole.setEmpFk(employee.getEid());
        empRoleMapper.insert(empRole);
        if(rows==1){
            return OAResult.ok(200,"操作成功");
        }
        return OAResult.ok(400,"操作失败");

    }

    @Override
    public OAResult deleteEmployee(int[] eids) {

        //删除员工所对应的角色的中间表
        int count=0;
        for(int eid : eids){
            Employee employee=employeeMapper.selectByPrimaryKey(eid);
            Jedis jedis=null;
            try {

                jedis=jedisPool.getResource();
                boolean isExists=jedis.hexists("MenuSources",employee.getUsername());
                if(isExists){
                    jedis.hdel("MenuSources",employee.getUsername());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(jedis!=null) jedis.close();
            }
            EmpRoleExample empRoleExample=new EmpRoleExample();
            EmpRoleExample.Criteria criteria = empRoleExample.createCriteria();
            criteria.andEmpFkEqualTo(eid);
            empRoleMapper.deleteByExample(empRoleExample);
            //删除员工
            employeeMapper.deleteByPrimaryKey(eid);
            count++;
        }
        if(count==eids.length){
            return OAResult.ok(200,"操作成功");
        }
        return OAResult.ok(400,"操作失败");
    }

    @Override
    public OAResult checkLogin(String username, String password) {

        EmployeeExample employeeExample=new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        System.out.println(employeeList==null);
        System.out.println(employeeList.size());
        if(employeeList!=null && employeeList.size()>0){
            Employee employee= employeeList.get(0);
            EmpRoleExample empRoleExample =new EmpRoleExample();
            EmpRoleExample.Criteria empRolecriteria = empRoleExample.createCriteria();
            empRolecriteria.andEmpFkEqualTo(employee.getEid());
            List<EmpRole> empRoleList= empRoleMapper.selectByExample(empRoleExample);
            Role role=roleMapper.selectByPrimaryKey(empRoleList.get(0).getRoleFk());
            employee.setRolename(role.getRolename());

            System.out.println("登陆成功");
            return OAResult.build(200,"登陆成功",employee);
        }
        return OAResult.ok(400,"登陆失败");
    }

    @Override
    public OAResult changePicImage(MultipartFile picImage, HttpSession session) {
        Employee employee =(Employee)session.getAttribute("activeUser");
        try {
            if(picImage!=null && picImage.getSize()>0){
                //获取文件名
                String originalFilename=picImage.getOriginalFilename();
                int hashCode= originalFilename.hashCode();
                int d1= hashCode&0xf;
                int d2=(hashCode>>2)&0xf;
                String filePath=uploadFileDir+"\\"+d1+"\\"+d2;
                String extName=originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFileName= UUID.randomUUID().toString()+extName;
                File saveDir=new File(filePath);
                if(!saveDir.exists()) saveDir.mkdirs();
                File file=new File(saveDir,newFileName);
                picImage.transferTo(file);
                employee.setPic("/pic/images/"+d1+"/"+d2+"/"+newFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return OAResult.ok(401,"头像上传失败");
        }
        int rows=employeeMapper.updateByPrimaryKey(employee);
        if(rows==1){
            session.setAttribute("activeUser",employee);
            return OAResult.build(200,"头像更新成功",employee);
        }

        return OAResult.ok(400,"头像更新失败");
    }

    @Override
    public OAResult updatePassword(Employee employee) {

       int rows=employeeMapper.updateByPrimaryKey(employee);

        if(rows==1){
            return OAResult.ok(200,"");
        }else{
            return OAResult.ok(400,"");
        }
    }
}
