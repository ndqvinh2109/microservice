package com.nab.assignment.shopping.model;


public class ProductSearchParam {


    public static class Builder {

        // NAME,BRANCH_NAME,PRICE ASC|DESC
        private String sortBy;
        private String searchKey;
        // NAME,BRANCH_NAME,COLOR,PRICE
        private String searchBy;

        public Builder(String searchKey) {
            this.searchKey = searchKey;
        }

        public Builder sortBy(String sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public Builder searchBy(String searchBy) {
            this.searchBy = searchBy;
            return this;
        }

        public ProductSearchParam build() {
            return new ProductSearchParam(this.searchKey, this.sortBy, this.searchBy);
        }

    }

    private String sortBy;
    private String searchKey;
    private String searchBy;
    private Integer first;
    private Integer limit;

    private ProductSearchParam() {
    }

    public static Builder withSearchKey(String searchKey) {
        return new Builder(searchKey);
    }

    public ProductSearchParam(String searchKey, String sortBy, String searchBy) {
        this.sortBy = sortBy;
        this.searchBy = searchBy;
        this.searchKey = searchKey;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public String getSearchBy() {
        return searchBy;
    }

}
