package com.sight.WebServer.data.model;

import java.util.ArrayList;
import java.util.List;

public class formsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public formsExample() {
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

        public Criteria andSocIsNull() {
            addCriterion("soc is null");
            return (Criteria) this;
        }

        public Criteria andSocIsNotNull() {
            addCriterion("soc is not null");
            return (Criteria) this;
        }

        public Criteria andSocEqualTo(String value) {
            addCriterion("soc =", value, "soc");
            return (Criteria) this;
        }

        public Criteria andSocNotEqualTo(String value) {
            addCriterion("soc <>", value, "soc");
            return (Criteria) this;
        }

        public Criteria andSocGreaterThan(String value) {
            addCriterion("soc >", value, "soc");
            return (Criteria) this;
        }

        public Criteria andSocGreaterThanOrEqualTo(String value) {
            addCriterion("soc >=", value, "soc");
            return (Criteria) this;
        }

        public Criteria andSocLessThan(String value) {
            addCriterion("soc <", value, "soc");
            return (Criteria) this;
        }

        public Criteria andSocLessThanOrEqualTo(String value) {
            addCriterion("soc <=", value, "soc");
            return (Criteria) this;
        }

        public Criteria andSocLike(String value) {
            addCriterion("soc like", value, "soc");
            return (Criteria) this;
        }

        public Criteria andSocNotLike(String value) {
            addCriterion("soc not like", value, "soc");
            return (Criteria) this;
        }

        public Criteria andSocIn(List<String> values) {
            addCriterion("soc in", values, "soc");
            return (Criteria) this;
        }

        public Criteria andSocNotIn(List<String> values) {
            addCriterion("soc not in", values, "soc");
            return (Criteria) this;
        }

        public Criteria andSocBetween(String value1, String value2) {
            addCriterion("soc between", value1, value2, "soc");
            return (Criteria) this;
        }

        public Criteria andSocNotBetween(String value1, String value2) {
            addCriterion("soc not between", value1, value2, "soc");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andFormDataIsNull() {
            addCriterion("form_data is null");
            return (Criteria) this;
        }

        public Criteria andFormDataIsNotNull() {
            addCriterion("form_data is not null");
            return (Criteria) this;
        }

        public Criteria andFormDataEqualTo(String value) {
            addCriterion("form_data =", value, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataNotEqualTo(String value) {
            addCriterion("form_data <>", value, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataGreaterThan(String value) {
            addCriterion("form_data >", value, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataGreaterThanOrEqualTo(String value) {
            addCriterion("form_data >=", value, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataLessThan(String value) {
            addCriterion("form_data <", value, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataLessThanOrEqualTo(String value) {
            addCriterion("form_data <=", value, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataLike(String value) {
            addCriterion("form_data like", value, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataNotLike(String value) {
            addCriterion("form_data not like", value, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataIn(List<String> values) {
            addCriterion("form_data in", values, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataNotIn(List<String> values) {
            addCriterion("form_data not in", values, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataBetween(String value1, String value2) {
            addCriterion("form_data between", value1, value2, "formData");
            return (Criteria) this;
        }

        public Criteria andFormDataNotBetween(String value1, String value2) {
            addCriterion("form_data not between", value1, value2, "formData");
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