package com.hoxwi.android.sample.data.remote;

/**
 * Created by Cezar on 19/12/2017.
 */

public class HoxWiRequestBody {

    private String secretkey;
    private String container;
    private String storageType;

    private Object document;

    private String orderby;
    private Integer limit;

    public HoxWiRequestBody() {
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public Object getDocument() {
        return document;
    }

    public void setDocument(Object document) {
        this.document = document;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public static final class Builder {

        private final HoxWiRequestBody body = new HoxWiRequestBody();

        public Builder() {
        }

        public Builder secretKey(String secretKey){
            body.setSecretkey(secretKey);
            return this;
        }
        public Builder container(String container){
            body.setContainer(container);
            return this;
        }
        public Builder storageType(String storageType){
            body.setStorageType(storageType);
            return this;
        }
        public Builder document(Object document){
            body.setDocument(document);
            return this;
        }
        public Builder orderby(String orderby){
            body.setOrderby(orderby);
            return this;
        }
        public Builder limit(Integer limit){
            body.setLimit(limit);
            return this;
        }

        public HoxWiRequestBody build(){
            return body;
        }
    }
}
