package org.tinlone.demo.rxjavasample.http;


import org.tinlone.demo.rxjavasample.bean.StudentBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by ydy on 2017/03/31
 * http://www.1688wan.com/majax.action?method=getGiftList&pageno=1
 */
public interface HttpService {

    // /majax.action?method=getGiftList&pageno=a
//    @POST("/majax.action?method=getGiftList")
//    Call<GiftBean> getGiftData(@Query("pageno")int pageno);

    @POST("majax.action?method=getGiftList")
    Observable<StudentBean> getGiftData(@Query("pageno") int pageno);

    /**
     * 通过 List<MultipartBody.Part> 传入多个part实现多文件上传
     *
     * @param parts 每个part代表一个
     * @return 状态信息
     */
    @Multipart
    @POST("users/image")
    Call<StudentBean> uploadFilesWithParts(@Part() List<MultipartBody.Part> parts);

    /**
     * 通过 MultipartBody和@body作为参数来上传
     *
     * @param multipartBody MultipartBody包含多个Part
     * @return 状态信息
     */
    @POST("users/image")
    Call<StudentBean> uploadFileWithRequestBody(@Body MultipartBody multipartBody);

}
