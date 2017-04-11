package com.zhangyan.recycleview.http;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zhang_yan on 2017/4/11.
 */

public interface Service {
    //http://www.tngou.net/api/cook/list(菜单展示)
    @POST("/api/cook/list")
    Call<Tngou> getList(@Query("id") int id, @Query("page") int page, @Query("rows") int rows);
}
