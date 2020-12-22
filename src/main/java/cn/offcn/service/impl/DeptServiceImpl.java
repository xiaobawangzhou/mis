package cn.offcn.service.impl;

import cn.offcn.entity.Dept;
import cn.offcn.entity.DeptExample;
import cn.offcn.mapper.DeptMapper;
import cn.offcn.service.DeptService;
import cn.offcn.utils.OAResult;
import cn.offcn.utils.TableVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public TableVo<Dept> getDepts(int page, int limit) {
        PageHelper.startPage(page,limit);
        DeptExample deptExample=new DeptExample();
        List<Dept> deptList = deptMapper.selectByExample(deptExample);
        PageInfo<Dept> pageInfo=new PageInfo<Dept>(deptList);
        TableVo<Dept> tableVo=new TableVo<Dept>();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount(pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());

        return tableVo;
    }

    @Override
    public OAResult updateDept(Dept dept) {
        int rows=deptMapper.updateByPrimaryKey(dept);
        if(rows==1){
            return OAResult.ok(200,"操作成功");
        }
        return OAResult.ok(400,"操作失败");
    }

    @Override
    public Dept getDeptByDeptno(int deptno) {


        return deptMapper.selectByPrimaryKey(deptno);
    }

    @Override
    public OAResult deleteDeptno(int[] deptnos) {
        int count=0;
        if(deptnos!=null && deptnos.length>0){
            for(int deptno : deptnos){
                deptMapper.deleteByPrimaryKey(deptno);
                count++;
            }
        }
        if(count!=0 && count==deptnos.length){
            return OAResult.ok(200,"操作成功");
        }
        return OAResult.ok(400,"操作失败");
    }

    @Override
    public List<Dept> getAllDepts() {
        return deptMapper.selectByExample(new DeptExample());
    }
}
