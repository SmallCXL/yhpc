package com.arthur.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPhone_IsNull() {
            addCriterion("phone_ is null");
            return (Criteria) this;
        }

        public Criteria andPhone_IsNotNull() {
            addCriterion("phone_ is not null");
            return (Criteria) this;
        }

        public Criteria andPhone_EqualTo(String value) {
            addCriterion("phone_ =", value, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_NotEqualTo(String value) {
            addCriterion("phone_ <>", value, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_GreaterThan(String value) {
            addCriterion("phone_ >", value, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_GreaterThanOrEqualTo(String value) {
            addCriterion("phone_ >=", value, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_LessThan(String value) {
            addCriterion("phone_ <", value, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_LessThanOrEqualTo(String value) {
            addCriterion("phone_ <=", value, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_Like(String value) {
            addCriterion("phone_ like", value, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_NotLike(String value) {
            addCriterion("phone_ not like", value, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_In(List<String> values) {
            addCriterion("phone_ in", values, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_NotIn(List<String> values) {
            addCriterion("phone_ not in", values, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_Between(String value1, String value2) {
            addCriterion("phone_ between", value1, value2, "phone_");
            return (Criteria) this;
        }

        public Criteria andPhone_NotBetween(String value1, String value2) {
            addCriterion("phone_ not between", value1, value2, "phone_");
            return (Criteria) this;
        }

        public Criteria andName_IsNull() {
            addCriterion("name_ is null");
            return (Criteria) this;
        }

        public Criteria andName_IsNotNull() {
            addCriterion("name_ is not null");
            return (Criteria) this;
        }

        public Criteria andName_EqualTo(String value) {
            addCriterion("name_ =", value, "name_");
            return (Criteria) this;
        }

        public Criteria andName_NotEqualTo(String value) {
            addCriterion("name_ <>", value, "name_");
            return (Criteria) this;
        }

        public Criteria andName_GreaterThan(String value) {
            addCriterion("name_ >", value, "name_");
            return (Criteria) this;
        }

        public Criteria andName_GreaterThanOrEqualTo(String value) {
            addCriterion("name_ >=", value, "name_");
            return (Criteria) this;
        }

        public Criteria andName_LessThan(String value) {
            addCriterion("name_ <", value, "name_");
            return (Criteria) this;
        }

        public Criteria andName_LessThanOrEqualTo(String value) {
            addCriterion("name_ <=", value, "name_");
            return (Criteria) this;
        }

        public Criteria andName_Like(String value) {
            addCriterion("name_ like", value, "name_");
            return (Criteria) this;
        }

        public Criteria andName_NotLike(String value) {
            addCriterion("name_ not like", value, "name_");
            return (Criteria) this;
        }

        public Criteria andName_In(List<String> values) {
            addCriterion("name_ in", values, "name_");
            return (Criteria) this;
        }

        public Criteria andName_NotIn(List<String> values) {
            addCriterion("name_ not in", values, "name_");
            return (Criteria) this;
        }

        public Criteria andName_Between(String value1, String value2) {
            addCriterion("name_ between", value1, value2, "name_");
            return (Criteria) this;
        }

        public Criteria andName_NotBetween(String value1, String value2) {
            addCriterion("name_ not between", value1, value2, "name_");
            return (Criteria) this;
        }

        public Criteria andSex_IsNull() {
            addCriterion("sex_ is null");
            return (Criteria) this;
        }

        public Criteria andSex_IsNotNull() {
            addCriterion("sex_ is not null");
            return (Criteria) this;
        }

        public Criteria andSex_EqualTo(String value) {
            addCriterion("sex_ =", value, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_NotEqualTo(String value) {
            addCriterion("sex_ <>", value, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_GreaterThan(String value) {
            addCriterion("sex_ >", value, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_GreaterThanOrEqualTo(String value) {
            addCriterion("sex_ >=", value, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_LessThan(String value) {
            addCriterion("sex_ <", value, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_LessThanOrEqualTo(String value) {
            addCriterion("sex_ <=", value, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_Like(String value) {
            addCriterion("sex_ like", value, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_NotLike(String value) {
            addCriterion("sex_ not like", value, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_In(List<String> values) {
            addCriterion("sex_ in", values, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_NotIn(List<String> values) {
            addCriterion("sex_ not in", values, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_Between(String value1, String value2) {
            addCriterion("sex_ between", value1, value2, "sex_");
            return (Criteria) this;
        }

        public Criteria andSex_NotBetween(String value1, String value2) {
            addCriterion("sex_ not between", value1, value2, "sex_");
            return (Criteria) this;
        }

        public Criteria andPassword_IsNull() {
            addCriterion("password_ is null");
            return (Criteria) this;
        }

        public Criteria andPassword_IsNotNull() {
            addCriterion("password_ is not null");
            return (Criteria) this;
        }

        public Criteria andPassword_EqualTo(String value) {
            addCriterion("password_ =", value, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_NotEqualTo(String value) {
            addCriterion("password_ <>", value, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_GreaterThan(String value) {
            addCriterion("password_ >", value, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_GreaterThanOrEqualTo(String value) {
            addCriterion("password_ >=", value, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_LessThan(String value) {
            addCriterion("password_ <", value, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_LessThanOrEqualTo(String value) {
            addCriterion("password_ <=", value, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_Like(String value) {
            addCriterion("password_ like", value, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_NotLike(String value) {
            addCriterion("password_ not like", value, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_In(List<String> values) {
            addCriterion("password_ in", values, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_NotIn(List<String> values) {
            addCriterion("password_ not in", values, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_Between(String value1, String value2) {
            addCriterion("password_ between", value1, value2, "password_");
            return (Criteria) this;
        }

        public Criteria andPassword_NotBetween(String value1, String value2) {
            addCriterion("password_ not between", value1, value2, "password_");
            return (Criteria) this;
        }

        public Criteria andSalt_IsNull() {
            addCriterion("salt_ is null");
            return (Criteria) this;
        }

        public Criteria andSalt_IsNotNull() {
            addCriterion("salt_ is not null");
            return (Criteria) this;
        }

        public Criteria andSalt_EqualTo(String value) {
            addCriterion("salt_ =", value, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_NotEqualTo(String value) {
            addCriterion("salt_ <>", value, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_GreaterThan(String value) {
            addCriterion("salt_ >", value, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_GreaterThanOrEqualTo(String value) {
            addCriterion("salt_ >=", value, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_LessThan(String value) {
            addCriterion("salt_ <", value, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_LessThanOrEqualTo(String value) {
            addCriterion("salt_ <=", value, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_Like(String value) {
            addCriterion("salt_ like", value, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_NotLike(String value) {
            addCriterion("salt_ not like", value, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_In(List<String> values) {
            addCriterion("salt_ in", values, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_NotIn(List<String> values) {
            addCriterion("salt_ not in", values, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_Between(String value1, String value2) {
            addCriterion("salt_ between", value1, value2, "salt_");
            return (Criteria) this;
        }

        public Criteria andSalt_NotBetween(String value1, String value2) {
            addCriterion("salt_ not between", value1, value2, "salt_");
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