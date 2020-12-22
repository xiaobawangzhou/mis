package cn.offcn.service;

import cn.offcn.entity.Dept;
import cn.offcn.utils.OAResult;
import cn.offcn.utils.TableVo;

import java.util.List;

public interface DeptService {
    TableVo<Dept> getDepts(int page, int limit);

    Dept getDeptByDeptno(int deptno);

    OAResult updateDept(Dept dept);

    OAResult deleteDeptno(int[] deptnos);

    List<Dept> getAllDepts();
}
