package org.wso2.ballerina;

public abstract class Bridge {
    private String message = null;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public abstract String getExtendedClassName();
}
