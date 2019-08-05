package com.sight.WebServer.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class record_childExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public record_childExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andParentTokenIsNull() {
            addCriterion("parent_token is null");
            return (Criteria) this;
        }

        public Criteria andParentTokenIsNotNull() {
            addCriterion("parent_token is not null");
            return (Criteria) this;
        }

        public Criteria andParentTokenEqualTo(String value) {
            addCriterion("parent_token =", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenNotEqualTo(String value) {
            addCriterion("parent_token <>", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenGreaterThan(String value) {
            addCriterion("parent_token >", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenGreaterThanOrEqualTo(String value) {
            addCriterion("parent_token >=", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenLessThan(String value) {
            addCriterion("parent_token <", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenLessThanOrEqualTo(String value) {
            addCriterion("parent_token <=", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenLike(String value) {
            addCriterion("parent_token like", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenNotLike(String value) {
            addCriterion("parent_token not like", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenIn(List<String> values) {
            addCriterion("parent_token in", values, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenNotIn(List<String> values) {
            addCriterion("parent_token not in", values, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenBetween(String value1, String value2) {
            addCriterion("parent_token between", value1, value2, "parentToken");
            return (Criteria) this;
        }

        public Criteria andParentTokenNotBetween(String value1, String value2) {
            addCriterion("parent_token not between", value1, value2, "parentToken");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIsNull() {
            addCriterion("record_time is null");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIsNotNull() {
            addCriterion("record_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecordTimeEqualTo(Date value) {
            addCriterion("record_time =", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotEqualTo(Date value) {
            addCriterion("record_time <>", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeGreaterThan(Date value) {
            addCriterion("record_time >", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("record_time >=", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeLessThan(Date value) {
            addCriterion("record_time <", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeLessThanOrEqualTo(Date value) {
            addCriterion("record_time <=", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIn(List<Date> values) {
            addCriterion("record_time in", values, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotIn(List<Date> values) {
            addCriterion("record_time not in", values, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeBetween(Date value1, Date value2) {
            addCriterion("record_time between", value1, value2, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotBetween(Date value1, Date value2) {
            addCriterion("record_time not between", value1, value2, "recordTime");
            return (Criteria) this;
        }

        public Criteria andChildStatusIsNull() {
            addCriterion("child_status is null");
            return (Criteria) this;
        }

        public Criteria andChildStatusIsNotNull() {
            addCriterion("child_status is not null");
            return (Criteria) this;
        }

        public Criteria andChildStatusEqualTo(Integer value) {
            addCriterion("child_status =", value, "childStatus");
            return (Criteria) this;
        }

        public Criteria andChildStatusNotEqualTo(Integer value) {
            addCriterion("child_status <>", value, "childStatus");
            return (Criteria) this;
        }

        public Criteria andChildStatusGreaterThan(Integer value) {
            addCriterion("child_status >", value, "childStatus");
            return (Criteria) this;
        }

        public Criteria andChildStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("child_status >=", value, "childStatus");
            return (Criteria) this;
        }

        public Criteria andChildStatusLessThan(Integer value) {
            addCriterion("child_status <", value, "childStatus");
            return (Criteria) this;
        }

        public Criteria andChildStatusLessThanOrEqualTo(Integer value) {
            addCriterion("child_status <=", value, "childStatus");
            return (Criteria) this;
        }

        public Criteria andChildStatusIn(List<Integer> values) {
            addCriterion("child_status in", values, "childStatus");
            return (Criteria) this;
        }

        public Criteria andChildStatusNotIn(List<Integer> values) {
            addCriterion("child_status not in", values, "childStatus");
            return (Criteria) this;
        }

        public Criteria andChildStatusBetween(Integer value1, Integer value2) {
            addCriterion("child_status between", value1, value2, "childStatus");
            return (Criteria) this;
        }

        public Criteria andChildStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("child_status not between", value1, value2, "childStatus");
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