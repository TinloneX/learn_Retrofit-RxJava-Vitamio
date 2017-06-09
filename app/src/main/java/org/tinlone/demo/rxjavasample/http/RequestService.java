package org.tinlone.demo.rxjavasample.http;


import org.tinlone.demo.rxjavasample.bean.StudentBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public interface RequestService {

    @GET("pyq/api/user/student")
    Observable<StudentBean> getStudent(@Query("token") String token, @Query("id") int id);
}
