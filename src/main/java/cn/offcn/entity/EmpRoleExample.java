package cn.offcn.entity;

import java.util.ArrayList;
import java.util.List;

public class EmpRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmpRoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andEridIsNull() {
            addCriterion("erid is null");
            return (Criteria) this;
        }

        public Criteria andEridIsNotNull() {
            addCriterion("erid is not null");
            return (Criteria) this;
        }

        public Criteria andEridEqualTo(Integer value) {
            addCriterion("erid =", value, "erid");
            return (Criteria) this;
        }

        public Criteria andEridNotEqualTo(Integer value) {
            addCriterion("erid <>", value, "erid");
            return (Criteria) this;
        }

        public Criteria andEridGreaterThan(Integer value) {
            addCriterion("erid >", value, "erid");
            return (Criteria) this;
        }

        public Criteria andEridGreaterThanOrEqualTo(Integer value) {
            addCriterion("erid >=", value, "erid");
            return (Criteria) this;
        }

        public Criteria andEridLessThan(Integer value) {
            addCriterion("erid <", value, "erid");
            return (Criteria) this;
        }

        public Criteria andEridLessThanOrEqualTo(Integer value) {
            addCriterion("erid <=", value, "erid");
            return (Criteria) this;
        }

        public Criteria andEridIn(List<Integer> values) {
            addCriterion("erid in", values, "erid");
            return (Criteria) this;
        }

        public Criteria andEridNotIn(List<Integer> values) {
            addCriterion("erid not in", values, "erid");
            return (Criteria) this;
        }

        public Criteria andEridBetween(Integer value1, Integer value2) {
            addCriterion("erid between", value1, value2, "erid");
            return (Criteria) this;
        }

        public Criteria andEridNotBetween(Integer value1, Integer value2) {
            addCriterion("erid not between", value1, value2, "erid");
            return (Criteria) this;
        }

        public Criteria andRoleFkIsNull() {
            addCriterion("role_fk is null");
            return (Criteria) this;
        }

        public Criteria andRoleFkIsNotNull() {
            addCriterion("role_fk is not null");
            return (Criteria) this;
        }

        public Criteria andRoleFkEqualTo(Integer value) {
            addCriterion("role_fk =", value, "roleFk");
            return (Criteria) this;
        }

        public Criteria andRoleFkNotEqualTo(Integer value) {
            addCriterion("role_fk <>", value, "roleFk");
            return (Criteria) this;
        }

        public Criteria andRoleFkGreaterThan(Integer value) {
            addCriterion("role_fk >", value, "roleFk");
            return (Criteria) this;
        }

        public Criteria andRoleFkGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_fk >=", value, "roleFk");
            return (Criteria) this;
        }

        public Criteria andRoleFkLessThan(Integer value) {
            addCriterion("role_fk <", value, "roleFk");
            return (Criteria) this;
        }

        public Criteria andRoleFkLessThanOrEqualTo(Integer value) {
            addCriterion("role_fk <=", value, "roleFk");
            return (Criteria) this;
        }

        public Criteria andRoleFkIn(List<Integer> values) {
            addCriterion("role_fk in", values, "roleFk");
            return (Criteria) this;
        }

        public Criteria andRoleFkNotIn(List<Integer> values) {
            addCriterion("role_fk not in", values, "roleFk");
            return (Criteria) this;
        }

        public Criteria andRoleFkBetween(Integer value1, Integer value2) {
            addCriterion("role_fk between", value1, value2, "roleFk");
            return (Criteria) this;
        }

        public Criteria andRoleFkNotBetween(Integer value1, Integer value2) {
            addCriterion("role_fk not between", value1, value2, "roleFk");
            return (Criteria) this;
        }

        public Criteria andEmpFkIsNull() {
            addCriterion("emp_fk is null");
            return (Criteria) this;
        }

        public Criteria andEmpFkIsNotNull() {
            addCriterion("emp_fk is not null");
            return (Criteria) this;
        }

        public Criteria andEmpFkEqualTo(Integer value) {
            addCriterion("emp_fk =", value, "empFk");
            return (Criteria) this;
        }

        public Criteria andEmpFkNotEqualTo(Integer value) {
            addCriterion("emp_fk <>", value, "empFk");
            return (Criteria) this;
        }

        public Criteria andEmpFkGreaterThan(Integer value) {
            addCriterion("emp_fk >", value, "empFk");
            return (Criteria) this;
        }

        public Criteria andEmpFkGreaterThanOrEqualTo(Integer value) {
            addCriterion("emp_fk >=", value, "empFk");
            return (Criteria) this;
        }

        public Criteria andEmpFkLessThan(Integer value) {
            addCriterion("emp_fk <", value, "empFk");
            return (Criteria) this;
        }

        public Criteria andEmpFkLessThanOrEqualTo(Integer value) {
            addCriterion("emp_fk <=", value, "empFk");
            return (Criteria) this;
        }

        public Criteria andEmpFkIn(List<Integer> values) {
            addCriterion("emp_fk in", values, "empFk");
            return (Criteria) this;
        }

        public Criteria andEmpFkNotIn(List<Integer> values) {
            addCriterion("emp_fk not in", values, "empFk");
            return (Criteria) this;
        }

        public Criteria andEmpFkBetween(Integer value1, Integer value2) {
            addCriterion("emp_fk between", value1, value2, "empFk");
            return (Criteria) this;
        }

        public Criteria andEmpFkNotBetween(Integer value1, Integer value2) {
            addCriterion("emp_fk not between", value1, value2, "empFk");
            return (Criteria) this;
        }

        public Criteria andErdisIsNull() {
            addCriterion("erdis is null");
            return (Criteria) this;
        }

        public Criteria andErdisIsNotNull() {
            addCriterion("erdis is not null");
            return (Criteria) this;
        }

        public Criteria andErdisEqualTo(String value) {
            addCriterion("erdis =", value, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisNotEqualTo(String value) {
            addCriterion("erdis <>", value, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisGreaterThan(String value) {
            addCriterion("erdis >", value, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisGreaterThanOrEqualTo(String value) {
            addCriterion("erdis >=", value, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisLessThan(String value) {
            addCriterion("erdis <", value, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisLessThanOrEqualTo(String value) {
            addCriterion("erdis <=", value, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisLike(String value) {
            addCriterion("erdis like", value, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisNotLike(String value) {
            addCriterion("erdis not like", value, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisIn(List<String> values) {
            addCriterion("erdis in", values, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisNotIn(List<String> values) {
            addCriterion("erdis not in", values, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisBetween(String value1, String value2) {
            addCriterion("erdis between", value1, value2, "erdis");
            return (Criteria) this;
        }

        public Criteria andErdisNotBetween(String value1, String value2) {
            addCriterion("erdis not between", value1, value2, "erdis");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}