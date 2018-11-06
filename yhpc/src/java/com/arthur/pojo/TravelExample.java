package com.arthur.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TravelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TravelExample() {
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

        protected void addCriterionForJDBCDate(String condition, String value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, value, property);
        }

        protected void addCriterionForJDBCDate(String condition, List<String> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<String> dateList = new ArrayList<>();
            Iterator<String> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(iter.next());
            }
            addCriterion(condition, dateList, property);
        }

//        protected void addCriterionForJDBCDate(String condition, String value1, String value2, String property) {
//            if (value1 == null || value2 == null) {
//                throw new RuntimeException("Between values for " + property + " cannot be null");
//            }
//            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
//        }

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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andType_IsNull() {
            addCriterion("type_ is null");
            return (Criteria) this;
        }

        public Criteria andType_IsNotNull() {
            addCriterion("type_ is not null");
            return (Criteria) this;
        }

        public Criteria andType_EqualTo(Boolean value) {
            addCriterion("type_ =", value, "type_");
            return (Criteria) this;
        }

        public Criteria andType_NotEqualTo(Boolean value) {
            addCriterion("type_ <>", value, "type_");
            return (Criteria) this;
        }

        public Criteria andType_GreaterThan(Boolean value) {
            addCriterion("type_ >", value, "type_");
            return (Criteria) this;
        }

        public Criteria andType_GreaterThanOrEqualTo(Boolean value) {
            addCriterion("type_ >=", value, "type_");
            return (Criteria) this;
        }

        public Criteria andType_LessThan(Boolean value) {
            addCriterion("type_ <", value, "type_");
            return (Criteria) this;
        }

        public Criteria andType_LessThanOrEqualTo(Boolean value) {
            addCriterion("type_ <=", value, "type_");
            return (Criteria) this;
        }

        public Criteria andType_In(List<Boolean> values) {
            addCriterion("type_ in", values, "type_");
            return (Criteria) this;
        }

        public Criteria andType_NotIn(List<Boolean> values) {
            addCriterion("type_ not in", values, "type_");
            return (Criteria) this;
        }

        public Criteria andType_Between(Boolean value1, Boolean value2) {
            addCriterion("type_ between", value1, value2, "type_");
            return (Criteria) this;
        }

        public Criteria andType_NotBetween(Boolean value1, Boolean value2) {
            addCriterion("type_ not between", value1, value2, "type_");
            return (Criteria) this;
        }

        public Criteria andDeparture_IsNull() {
            addCriterion("departure_ is null");
            return (Criteria) this;
        }

        public Criteria andDeparture_IsNotNull() {
            addCriterion("departure_ is not null");
            return (Criteria) this;
        }

        public Criteria andDeparture_EqualTo(String value) {
            addCriterion("departure_ =", value, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_NotEqualTo(String value) {
            addCriterion("departure_ <>", value, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_GreaterThan(String value) {
            addCriterion("departure_ >", value, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_GreaterThanOrEqualTo(String value) {
            addCriterion("departure_ >=", value, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_LessThan(String value) {
            addCriterion("departure_ <", value, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_LessThanOrEqualTo(String value) {
            addCriterion("departure_ <=", value, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_Like(String value) {
            addCriterion("departure_ like", value, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_NotLike(String value) {
            addCriterion("departure_ not like", value, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_In(List<String> values) {
            addCriterion("departure_ in", values, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_NotIn(List<String> values) {
            addCriterion("departure_ not in", values, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_Between(String value1, String value2) {
            addCriterion("departure_ between", value1, value2, "departure_");
            return (Criteria) this;
        }

        public Criteria andDeparture_NotBetween(String value1, String value2) {
            addCriterion("departure_ not between", value1, value2, "departure_");
            return (Criteria) this;
        }

        public Criteria andArrival_IsNull() {
            addCriterion("arrival_ is null");
            return (Criteria) this;
        }

        public Criteria andArrival_IsNotNull() {
            addCriterion("arrival_ is not null");
            return (Criteria) this;
        }

        public Criteria andArrival_EqualTo(String value) {
            addCriterion("arrival_ =", value, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_NotEqualTo(String value) {
            addCriterion("arrival_ <>", value, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_GreaterThan(String value) {
            addCriterion("arrival_ >", value, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_GreaterThanOrEqualTo(String value) {
            addCriterion("arrival_ >=", value, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_LessThan(String value) {
            addCriterion("arrival_ <", value, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_LessThanOrEqualTo(String value) {
            addCriterion("arrival_ <=", value, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_Like(String value) {
            addCriterion("arrival_ like", value, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_NotLike(String value) {
            addCriterion("arrival_ not like", value, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_In(List<String> values) {
            addCriterion("arrival_ in", values, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_NotIn(List<String> values) {
            addCriterion("arrival_ not in", values, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_Between(String value1, String value2) {
            addCriterion("arrival_ between", value1, value2, "arrival_");
            return (Criteria) this;
        }

        public Criteria andArrival_NotBetween(String value1, String value2) {
            addCriterion("arrival_ not between", value1, value2, "arrival_");
            return (Criteria) this;
        }

        public Criteria andTravel_Time_IsNull() {
            addCriterion("travel_time_ is null");
            return (Criteria) this;
        }

        public Criteria andTravel_Time_IsNotNull() {
            addCriterion("travel_time_ is not null");
            return (Criteria) this;
        }

        public Criteria andTravel_Time_EqualTo(String value) {
            addCriterionForJDBCDate("travel_time_ =", value, "travel_time_");
            return (Criteria) this;
        }

        public Criteria andTravel_Time_NotEqualTo(String value) {
            addCriterionForJDBCDate("travel_time_ <>", value, "travel_time_");
            return (Criteria) this;
        }

        public Criteria andTravel_Time_GreaterThan(String value) {
            addCriterionForJDBCDate("travel_time_ >", value, "travel_time_");
            return (Criteria) this;
        }

        public Criteria andTravel_Time_GreaterThanOrEqualTo(String value) {
            addCriterionForJDBCDate("travel_time_ >=", value, "travel_time_");
            return (Criteria) this;
        }

        public Criteria andTravel_Time_LessThan(String value) {
            addCriterionForJDBCDate("travel_time_ <", value, "travel_time_");
            return (Criteria) this;
        }

        public Criteria andTravel_Time_LessThanOrEqualTo(String value) {
            addCriterionForJDBCDate("travel_time_ <=", value, "travel_time_");
            return (Criteria) this;
        }

        public Criteria andTravel_Time_In(List<String> values) {
            addCriterionForJDBCDate("travel_time_ in", values, "travel_time_");
            return (Criteria) this;
        }

        public Criteria andTravel_Time_NotIn(List<String> values) {
            addCriterionForJDBCDate("travel_time_ not in", values, "travel_time_");
            return (Criteria) this;
        }

//        public Criteria andTravel_Time_Between(Date value1, Date value2) {
//            addCriterionForJDBCDate("travel_time_ between", value1, value2, "travel_time_");
//            return (Criteria) this;
//        }

//        public Criteria andTravel_Time_NotBetween(Date value1, Date value2) {
//            addCriterionForJDBCDate("travel_time_ not between", value1, value2, "travel_time_");
//            return (Criteria) this;
//        }

        public Criteria andAddition_IsNull() {
            addCriterion("addition_ is null");
            return (Criteria) this;
        }

        public Criteria andAddition_IsNotNull() {
            addCriterion("addition_ is not null");
            return (Criteria) this;
        }

        public Criteria andAddition_EqualTo(String value) {
            addCriterion("addition_ =", value, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_NotEqualTo(String value) {
            addCriterion("addition_ <>", value, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_GreaterThan(String value) {
            addCriterion("addition_ >", value, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_GreaterThanOrEqualTo(String value) {
            addCriterion("addition_ >=", value, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_LessThan(String value) {
            addCriterion("addition_ <", value, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_LessThanOrEqualTo(String value) {
            addCriterion("addition_ <=", value, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_Like(String value) {
            addCriterion("addition_ like", value, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_NotLike(String value) {
            addCriterion("addition_ not like", value, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_In(List<String> values) {
            addCriterion("addition_ in", values, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_NotIn(List<String> values) {
            addCriterion("addition_ not in", values, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_Between(String value1, String value2) {
            addCriterion("addition_ between", value1, value2, "addition_");
            return (Criteria) this;
        }

        public Criteria andAddition_NotBetween(String value1, String value2) {
            addCriterion("addition_ not between", value1, value2, "addition_");
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