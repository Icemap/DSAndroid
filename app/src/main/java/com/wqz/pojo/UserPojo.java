package com.wqz.pojo;

/**
 * Created by WangQiZhi on 2017/3/30.
 */

public class UserPojo
{

    /**
     * errorCode : 0
     * errorMsg : 成功
     * result : {"id":5,"username":"root","password":"root","level":0,"brandId":0,"businessUnitId":0,"storeId":0,"hold":"最高权限"}
     */

    private int errorCode;
    private String errorMsg;
    private ResultBean result;

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public ResultBean getResult()
    {
        return result;
    }

    public void setResult(ResultBean result)
    {
        this.result = result;
    }

    public static class ResultBean
    {
        /**
         * id : 5
         * username : root
         * password : root
         * level : 0
         * brandId : 0
         * businessUnitId : 0
         * storeId : 0
         * hold : 最高权限
         */

        private int id;
        private String username;
        private String password;
        private int level;
        private int brandId;
        private int businessUnitId;
        private int storeId;
        private String hold;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }

        public int getLevel()
        {
            return level;
        }

        public void setLevel(int level)
        {
            this.level = level;
        }

        public int getBrandId()
        {
            return brandId;
        }

        public void setBrandId(int brandId)
        {
            this.brandId = brandId;
        }

        public int getBusinessUnitId()
        {
            return businessUnitId;
        }

        public void setBusinessUnitId(int businessUnitId)
        {
            this.businessUnitId = businessUnitId;
        }

        public int getStoreId()
        {
            return storeId;
        }

        public void setStoreId(int storeId)
        {
            this.storeId = storeId;
        }

        public String getHold()
        {
            return hold;
        }

        public void setHold(String hold)
        {
            this.hold = hold;
        }
    }
}
