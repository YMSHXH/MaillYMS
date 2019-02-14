package com.king.maillyms.apis;

import com.example.lib_core.common.Apis;

public class LoginApis {
    public static final String LOGIN_API = Apis.BASE_URL + "small/user/v1/login";
    public static final String REG_API = Apis.BASE_URL +  "small/user/v1/register";

    //查询信息
    public static final String FIND_API = Apis.BASE_URL +  "small/user/verify/v1/getUserById";
}
