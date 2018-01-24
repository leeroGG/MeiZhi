package com.example.leero.meizhi.http;

import com.example.leero.meizhi.bean.MeiZhi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author : Leero
 * e-mail : 925230519@qq.com
 * time  : 2018-01-23
 */
public interface Apis {

    /**
     * 获取妹纸图片列表
     */
    @GET(Urls.GET_GIRLS_DATA)
    Observable<MeiZhi> getMMs(@Path("per") int per, @Path("page") int page);
}
