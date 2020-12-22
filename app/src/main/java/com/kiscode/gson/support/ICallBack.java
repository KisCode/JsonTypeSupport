package com.kiscode.gson.support;


/**** 
 * Description: 
 * Author:  keno
 * CreateDate: 2020/12/17 21:02
 */

public interface ICallBack<T> {
     T convert(String json);
}

