package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductExample() {
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

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonIsNull() {
            addCriterion("product_descripton is null");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonIsNotNull() {
            addCriterion("product_descripton is not null");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonEqualTo(String value) {
            addCriterion("product_descripton =", value, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonNotEqualTo(String value) {
            addCriterion("product_descripton <>", value, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonGreaterThan(String value) {
            addCriterion("product_descripton >", value, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonGreaterThanOrEqualTo(String value) {
            addCriterion("product_descripton >=", value, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonLessThan(String value) {
            addCriterion("product_descripton <", value, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonLessThanOrEqualTo(String value) {
            addCriterion("product_descripton <=", value, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonLike(String value) {
            addCriterion("product_descripton like", value, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonNotLike(String value) {
            addCriterion("product_descripton not like", value, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonIn(List<String> values) {
            addCriterion("product_descripton in", values, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonNotIn(List<String> values) {
            addCriterion("product_descripton not in", values, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonBetween(String value1, String value2) {
            addCriterion("product_descripton between", value1, value2, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDescriptonNotBetween(String value1, String value2) {
            addCriterion("product_descripton not between", value1, value2, "productDescripton");
            return (Criteria) this;
        }

        public Criteria andProductDimIsNull() {
            addCriterion("product_dim is null");
            return (Criteria) this;
        }

        public Criteria andProductDimIsNotNull() {
            addCriterion("product_dim is not null");
            return (Criteria) this;
        }

        public Criteria andProductDimEqualTo(String value) {
            addCriterion("product_dim =", value, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimNotEqualTo(String value) {
            addCriterion("product_dim <>", value, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimGreaterThan(String value) {
            addCriterion("product_dim >", value, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimGreaterThanOrEqualTo(String value) {
            addCriterion("product_dim >=", value, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimLessThan(String value) {
            addCriterion("product_dim <", value, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimLessThanOrEqualTo(String value) {
            addCriterion("product_dim <=", value, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimLike(String value) {
            addCriterion("product_dim like", value, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimNotLike(String value) {
            addCriterion("product_dim not like", value, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimIn(List<String> values) {
            addCriterion("product_dim in", values, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimNotIn(List<String> values) {
            addCriterion("product_dim not in", values, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimBetween(String value1, String value2) {
            addCriterion("product_dim between", value1, value2, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductDimNotBetween(String value1, String value2) {
            addCriterion("product_dim not between", value1, value2, "productDim");
            return (Criteria) this;
        }

        public Criteria andProductViewIsNull() {
            addCriterion("product_view is null");
            return (Criteria) this;
        }

        public Criteria andProductViewIsNotNull() {
            addCriterion("product_view is not null");
            return (Criteria) this;
        }

        public Criteria andProductViewEqualTo(Integer value) {
            addCriterion("product_view =", value, "productView");
            return (Criteria) this;
        }

        public Criteria andProductViewNotEqualTo(Integer value) {
            addCriterion("product_view <>", value, "productView");
            return (Criteria) this;
        }

        public Criteria andProductViewGreaterThan(Integer value) {
            addCriterion("product_view >", value, "productView");
            return (Criteria) this;
        }

        public Criteria andProductViewGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_view >=", value, "productView");
            return (Criteria) this;
        }

        public Criteria andProductViewLessThan(Integer value) {
            addCriterion("product_view <", value, "productView");
            return (Criteria) this;
        }

        public Criteria andProductViewLessThanOrEqualTo(Integer value) {
            addCriterion("product_view <=", value, "productView");
            return (Criteria) this;
        }

        public Criteria andProductViewIn(List<Integer> values) {
            addCriterion("product_view in", values, "productView");
            return (Criteria) this;
        }

        public Criteria andProductViewNotIn(List<Integer> values) {
            addCriterion("product_view not in", values, "productView");
            return (Criteria) this;
        }

        public Criteria andProductViewBetween(Integer value1, Integer value2) {
            addCriterion("product_view between", value1, value2, "productView");
            return (Criteria) this;
        }

        public Criteria andProductViewNotBetween(Integer value1, Integer value2) {
            addCriterion("product_view not between", value1, value2, "productView");
            return (Criteria) this;
        }

        public Criteria andProductRankIsNull() {
            addCriterion("product_rank is null");
            return (Criteria) this;
        }

        public Criteria andProductRankIsNotNull() {
            addCriterion("product_rank is not null");
            return (Criteria) this;
        }

        public Criteria andProductRankEqualTo(Integer value) {
            addCriterion("product_rank =", value, "productRank");
            return (Criteria) this;
        }

        public Criteria andProductRankNotEqualTo(Integer value) {
            addCriterion("product_rank <>", value, "productRank");
            return (Criteria) this;
        }

        public Criteria andProductRankGreaterThan(Integer value) {
            addCriterion("product_rank >", value, "productRank");
            return (Criteria) this;
        }

        public Criteria andProductRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_rank >=", value, "productRank");
            return (Criteria) this;
        }

        public Criteria andProductRankLessThan(Integer value) {
            addCriterion("product_rank <", value, "productRank");
            return (Criteria) this;
        }

        public Criteria andProductRankLessThanOrEqualTo(Integer value) {
            addCriterion("product_rank <=", value, "productRank");
            return (Criteria) this;
        }

        public Criteria andProductRankIn(List<Integer> values) {
            addCriterion("product_rank in", values, "productRank");
            return (Criteria) this;
        }

        public Criteria andProductRankNotIn(List<Integer> values) {
            addCriterion("product_rank not in", values, "productRank");
            return (Criteria) this;
        }

        public Criteria andProductRankBetween(Integer value1, Integer value2) {
            addCriterion("product_rank between", value1, value2, "productRank");
            return (Criteria) this;
        }

        public Criteria andProductRankNotBetween(Integer value1, Integer value2) {
            addCriterion("product_rank not between", value1, value2, "productRank");
            return (Criteria) this;
        }

        public Criteria andProductStatusIsNull() {
            addCriterion("product_status is null");
            return (Criteria) this;
        }

        public Criteria andProductStatusIsNotNull() {
            addCriterion("product_status is not null");
            return (Criteria) this;
        }

        public Criteria andProductStatusEqualTo(Integer value) {
            addCriterion("product_status =", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotEqualTo(Integer value) {
            addCriterion("product_status <>", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusGreaterThan(Integer value) {
            addCriterion("product_status >", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_status >=", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusLessThan(Integer value) {
            addCriterion("product_status <", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusLessThanOrEqualTo(Integer value) {
            addCriterion("product_status <=", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusIn(List<Integer> values) {
            addCriterion("product_status in", values, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotIn(List<Integer> values) {
            addCriterion("product_status not in", values, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusBetween(Integer value1, Integer value2) {
            addCriterion("product_status between", value1, value2, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("product_status not between", value1, value2, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNull() {
            addCriterion("product_count is null");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNotNull() {
            addCriterion("product_count is not null");
            return (Criteria) this;
        }

        public Criteria andProductCountEqualTo(Integer value) {
            addCriterion("product_count =", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotEqualTo(Integer value) {
            addCriterion("product_count <>", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThan(Integer value) {
            addCriterion("product_count >", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_count >=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThan(Integer value) {
            addCriterion("product_count <", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThanOrEqualTo(Integer value) {
            addCriterion("product_count <=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountIn(List<Integer> values) {
            addCriterion("product_count in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotIn(List<Integer> values) {
            addCriterion("product_count not in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountBetween(Integer value1, Integer value2) {
            addCriterion("product_count between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotBetween(Integer value1, Integer value2) {
            addCriterion("product_count not between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductUrlIsNull() {
            addCriterion("product_url is null");
            return (Criteria) this;
        }

        public Criteria andProductUrlIsNotNull() {
            addCriterion("product_url is not null");
            return (Criteria) this;
        }

        public Criteria andProductUrlEqualTo(String value) {
            addCriterion("product_url =", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotEqualTo(String value) {
            addCriterion("product_url <>", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlGreaterThan(String value) {
            addCriterion("product_url >", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlGreaterThanOrEqualTo(String value) {
            addCriterion("product_url >=", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlLessThan(String value) {
            addCriterion("product_url <", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlLessThanOrEqualTo(String value) {
            addCriterion("product_url <=", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlLike(String value) {
            addCriterion("product_url like", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotLike(String value) {
            addCriterion("product_url not like", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlIn(List<String> values) {
            addCriterion("product_url in", values, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotIn(List<String> values) {
            addCriterion("product_url not in", values, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlBetween(String value1, String value2) {
            addCriterion("product_url between", value1, value2, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotBetween(String value1, String value2) {
            addCriterion("product_url not between", value1, value2, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickIsNull() {
            addCriterion("product_url_click is null");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickIsNotNull() {
            addCriterion("product_url_click is not null");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickEqualTo(Integer value) {
            addCriterion("product_url_click =", value, "productUrlClick");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickNotEqualTo(Integer value) {
            addCriterion("product_url_click <>", value, "productUrlClick");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickGreaterThan(Integer value) {
            addCriterion("product_url_click >", value, "productUrlClick");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_url_click >=", value, "productUrlClick");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickLessThan(Integer value) {
            addCriterion("product_url_click <", value, "productUrlClick");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickLessThanOrEqualTo(Integer value) {
            addCriterion("product_url_click <=", value, "productUrlClick");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickIn(List<Integer> values) {
            addCriterion("product_url_click in", values, "productUrlClick");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickNotIn(List<Integer> values) {
            addCriterion("product_url_click not in", values, "productUrlClick");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickBetween(Integer value1, Integer value2) {
            addCriterion("product_url_click between", value1, value2, "productUrlClick");
            return (Criteria) this;
        }

        public Criteria andProductUrlClickNotBetween(Integer value1, Integer value2) {
            addCriterion("product_url_click not between", value1, value2, "productUrlClick");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkIsNull() {
            addCriterion("product_price_mark is null");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkIsNotNull() {
            addCriterion("product_price_mark is not null");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkEqualTo(String value) {
            addCriterion("product_price_mark =", value, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkNotEqualTo(String value) {
            addCriterion("product_price_mark <>", value, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkGreaterThan(String value) {
            addCriterion("product_price_mark >", value, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkGreaterThanOrEqualTo(String value) {
            addCriterion("product_price_mark >=", value, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkLessThan(String value) {
            addCriterion("product_price_mark <", value, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkLessThanOrEqualTo(String value) {
            addCriterion("product_price_mark <=", value, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkLike(String value) {
            addCriterion("product_price_mark like", value, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkNotLike(String value) {
            addCriterion("product_price_mark not like", value, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkIn(List<String> values) {
            addCriterion("product_price_mark in", values, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkNotIn(List<String> values) {
            addCriterion("product_price_mark not in", values, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkBetween(String value1, String value2) {
            addCriterion("product_price_mark between", value1, value2, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductPriceMarkNotBetween(String value1, String value2) {
            addCriterion("product_price_mark not between", value1, value2, "productPriceMark");
            return (Criteria) this;
        }

        public Criteria andProductUnitIsNull() {
            addCriterion("product_unit is null");
            return (Criteria) this;
        }

        public Criteria andProductUnitIsNotNull() {
            addCriterion("product_unit is not null");
            return (Criteria) this;
        }

        public Criteria andProductUnitEqualTo(String value) {
            addCriterion("product_unit =", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitNotEqualTo(String value) {
            addCriterion("product_unit <>", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitGreaterThan(String value) {
            addCriterion("product_unit >", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitGreaterThanOrEqualTo(String value) {
            addCriterion("product_unit >=", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitLessThan(String value) {
            addCriterion("product_unit <", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitLessThanOrEqualTo(String value) {
            addCriterion("product_unit <=", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitLike(String value) {
            addCriterion("product_unit like", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitNotLike(String value) {
            addCriterion("product_unit not like", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitIn(List<String> values) {
            addCriterion("product_unit in", values, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitNotIn(List<String> values) {
            addCriterion("product_unit not in", values, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitBetween(String value1, String value2) {
            addCriterion("product_unit between", value1, value2, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitNotBetween(String value1, String value2) {
            addCriterion("product_unit not between", value1, value2, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIsNull() {
            addCriterion("product_type_id is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIsNotNull() {
            addCriterion("product_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdEqualTo(Long value) {
            addCriterion("product_type_id =", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotEqualTo(Long value) {
            addCriterion("product_type_id <>", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdGreaterThan(Long value) {
            addCriterion("product_type_id >", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_type_id >=", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLessThan(Long value) {
            addCriterion("product_type_id <", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("product_type_id <=", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIn(List<Long> values) {
            addCriterion("product_type_id in", values, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotIn(List<Long> values) {
            addCriterion("product_type_id not in", values, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdBetween(Long value1, Long value2) {
            addCriterion("product_type_id between", value1, value2, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("product_type_id not between", value1, value2, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Long value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Long value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Long value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Long value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Long value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Long> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Long> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Long value1, Long value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Long value1, Long value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNull() {
            addCriterion("product_price is null");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNotNull() {
            addCriterion("product_price is not null");
            return (Criteria) this;
        }

        public Criteria andProductPriceEqualTo(Long value) {
            addCriterion("product_price =", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotEqualTo(Long value) {
            addCriterion("product_price <>", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThan(Long value) {
            addCriterion("product_price >", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("product_price >=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThan(Long value) {
            addCriterion("product_price <", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThanOrEqualTo(Long value) {
            addCriterion("product_price <=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceIn(List<Long> values) {
            addCriterion("product_price in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotIn(List<Long> values) {
            addCriterion("product_price not in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceBetween(Long value1, Long value2) {
            addCriterion("product_price between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotBetween(Long value1, Long value2) {
            addCriterion("product_price not between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeIsNull() {
            addCriterion("currencies_type is null");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeIsNotNull() {
            addCriterion("currencies_type is not null");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeEqualTo(String value) {
            addCriterion("currencies_type =", value, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeNotEqualTo(String value) {
            addCriterion("currencies_type <>", value, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeGreaterThan(String value) {
            addCriterion("currencies_type >", value, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeGreaterThanOrEqualTo(String value) {
            addCriterion("currencies_type >=", value, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeLessThan(String value) {
            addCriterion("currencies_type <", value, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeLessThanOrEqualTo(String value) {
            addCriterion("currencies_type <=", value, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeLike(String value) {
            addCriterion("currencies_type like", value, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeNotLike(String value) {
            addCriterion("currencies_type not like", value, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeIn(List<String> values) {
            addCriterion("currencies_type in", values, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeNotIn(List<String> values) {
            addCriterion("currencies_type not in", values, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeBetween(String value1, String value2) {
            addCriterion("currencies_type between", value1, value2, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andCurrenciesTypeNotBetween(String value1, String value2) {
            addCriterion("currencies_type not between", value1, value2, "currenciesType");
            return (Criteria) this;
        }

        public Criteria andProductEnNameIsNull() {
            addCriterion("product_en_name is null");
            return (Criteria) this;
        }

        public Criteria andProductEnNameIsNotNull() {
            addCriterion("product_en_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductEnNameEqualTo(String value) {
            addCriterion("product_en_name =", value, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameNotEqualTo(String value) {
            addCriterion("product_en_name <>", value, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameGreaterThan(String value) {
            addCriterion("product_en_name >", value, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_en_name >=", value, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameLessThan(String value) {
            addCriterion("product_en_name <", value, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameLessThanOrEqualTo(String value) {
            addCriterion("product_en_name <=", value, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameLike(String value) {
            addCriterion("product_en_name like", value, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameNotLike(String value) {
            addCriterion("product_en_name not like", value, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameIn(List<String> values) {
            addCriterion("product_en_name in", values, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameNotIn(List<String> values) {
            addCriterion("product_en_name not in", values, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameBetween(String value1, String value2) {
            addCriterion("product_en_name between", value1, value2, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnNameNotBetween(String value1, String value2) {
            addCriterion("product_en_name not between", value1, value2, "productEnName");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionIsNull() {
            addCriterion("product_en_description is null");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionIsNotNull() {
            addCriterion("product_en_description is not null");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionEqualTo(String value) {
            addCriterion("product_en_description =", value, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionNotEqualTo(String value) {
            addCriterion("product_en_description <>", value, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionGreaterThan(String value) {
            addCriterion("product_en_description >", value, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("product_en_description >=", value, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionLessThan(String value) {
            addCriterion("product_en_description <", value, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionLessThanOrEqualTo(String value) {
            addCriterion("product_en_description <=", value, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionLike(String value) {
            addCriterion("product_en_description like", value, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionNotLike(String value) {
            addCriterion("product_en_description not like", value, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionIn(List<String> values) {
            addCriterion("product_en_description in", values, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionNotIn(List<String> values) {
            addCriterion("product_en_description not in", values, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionBetween(String value1, String value2) {
            addCriterion("product_en_description between", value1, value2, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDescriptionNotBetween(String value1, String value2) {
            addCriterion("product_en_description not between", value1, value2, "productEnDescription");
            return (Criteria) this;
        }

        public Criteria andProductEnDimIsNull() {
            addCriterion("product_en_dim is null");
            return (Criteria) this;
        }

        public Criteria andProductEnDimIsNotNull() {
            addCriterion("product_en_dim is not null");
            return (Criteria) this;
        }

        public Criteria andProductEnDimEqualTo(String value) {
            addCriterion("product_en_dim =", value, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimNotEqualTo(String value) {
            addCriterion("product_en_dim <>", value, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimGreaterThan(String value) {
            addCriterion("product_en_dim >", value, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimGreaterThanOrEqualTo(String value) {
            addCriterion("product_en_dim >=", value, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimLessThan(String value) {
            addCriterion("product_en_dim <", value, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimLessThanOrEqualTo(String value) {
            addCriterion("product_en_dim <=", value, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimLike(String value) {
            addCriterion("product_en_dim like", value, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimNotLike(String value) {
            addCriterion("product_en_dim not like", value, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimIn(List<String> values) {
            addCriterion("product_en_dim in", values, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimNotIn(List<String> values) {
            addCriterion("product_en_dim not in", values, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimBetween(String value1, String value2) {
            addCriterion("product_en_dim between", value1, value2, "productEnDim");
            return (Criteria) this;
        }

        public Criteria andProductEnDimNotBetween(String value1, String value2) {
            addCriterion("product_en_dim not between", value1, value2, "productEnDim");
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

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdIsNull() {
            addCriterion("product_subtype_id is null");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdIsNotNull() {
            addCriterion("product_subtype_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdEqualTo(Long value) {
            addCriterion("product_subtype_id =", value, "productSubtypeId");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdNotEqualTo(Long value) {
            addCriterion("product_subtype_id <>", value, "productSubtypeId");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdGreaterThan(Long value) {
            addCriterion("product_subtype_id >", value, "productSubtypeId");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_subtype_id >=", value, "productSubtypeId");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdLessThan(Long value) {
            addCriterion("product_subtype_id <", value, "productSubtypeId");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdLessThanOrEqualTo(Long value) {
            addCriterion("product_subtype_id <=", value, "productSubtypeId");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdIn(List<Long> values) {
            addCriterion("product_subtype_id in", values, "productSubtypeId");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdNotIn(List<Long> values) {
            addCriterion("product_subtype_id not in", values, "productSubtypeId");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdBetween(Long value1, Long value2) {
            addCriterion("product_subtype_id between", value1, value2, "productSubtypeId");
            return (Criteria) this;
        }

        public Criteria andProductSubtypeIdNotBetween(Long value1, Long value2) {
            addCriterion("product_subtype_id not between", value1, value2, "productSubtypeId");
            return (Criteria) this;
        }

        public Criteria andProductTagIsNull() {
            addCriterion("product_tag is null");
            return (Criteria) this;
        }

        public Criteria andProductTagIsNotNull() {
            addCriterion("product_tag is not null");
            return (Criteria) this;
        }

        public Criteria andProductTagEqualTo(String value) {
            addCriterion("product_tag =", value, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagNotEqualTo(String value) {
            addCriterion("product_tag <>", value, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagGreaterThan(String value) {
            addCriterion("product_tag >", value, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagGreaterThanOrEqualTo(String value) {
            addCriterion("product_tag >=", value, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagLessThan(String value) {
            addCriterion("product_tag <", value, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagLessThanOrEqualTo(String value) {
            addCriterion("product_tag <=", value, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagLike(String value) {
            addCriterion("product_tag like", value, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagNotLike(String value) {
            addCriterion("product_tag not like", value, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagIn(List<String> values) {
            addCriterion("product_tag in", values, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagNotIn(List<String> values) {
            addCriterion("product_tag not in", values, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagBetween(String value1, String value2) {
            addCriterion("product_tag between", value1, value2, "productTag");
            return (Criteria) this;
        }

        public Criteria andProductTagNotBetween(String value1, String value2) {
            addCriterion("product_tag not between", value1, value2, "productTag");
            return (Criteria) this;
        }

        public Criteria andReadFlagIsNull() {
            addCriterion("read_flag is null");
            return (Criteria) this;
        }

        public Criteria andReadFlagIsNotNull() {
            addCriterion("read_flag is not null");
            return (Criteria) this;
        }

        public Criteria andReadFlagEqualTo(String value) {
            addCriterion("read_flag =", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagNotEqualTo(String value) {
            addCriterion("read_flag <>", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagGreaterThan(String value) {
            addCriterion("read_flag >", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagGreaterThanOrEqualTo(String value) {
            addCriterion("read_flag >=", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagLessThan(String value) {
            addCriterion("read_flag <", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagLessThanOrEqualTo(String value) {
            addCriterion("read_flag <=", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagLike(String value) {
            addCriterion("read_flag like", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagNotLike(String value) {
            addCriterion("read_flag not like", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagIn(List<String> values) {
            addCriterion("read_flag in", values, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagNotIn(List<String> values) {
            addCriterion("read_flag not in", values, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagBetween(String value1, String value2) {
            addCriterion("read_flag between", value1, value2, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagNotBetween(String value1, String value2) {
            addCriterion("read_flag not between", value1, value2, "readFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
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