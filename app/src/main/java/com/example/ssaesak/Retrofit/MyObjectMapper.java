package com.example.ssaesak.Retrofit;

import android.util.Log;

import com.example.ssaesak.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyObjectMapper {

/* Response -> DTO (리스트가 아닐 때, 하나일 때 e.g. 로그인) -> 예시는 MainActivity login
ObjectMapper mapper = new ObjectMapper();
String body = response.body().getData().toString();
String json = body.substring(1, body.length()-1).replace("\\", "");
Log.e("login", " string -> " + json);
try {
    User.setInstance(mapper.readValue(json, User.class));
} catch (Exception e1) {e1.printStackTrace();}
 */

/* Response -> List<DTO> (리스트일 때, e.g. 게시글 리스트) -> 예시는 MainActivity
ObjectMapper mapper = new ObjectMapper();
String body = response.body().getData().toString();
String json = body.substring(1, body.length()-1).replace("\\", "");
Log.e("login", " string -> " + json);
try {
User.setInstance(mapper.readValue(json, User.class));
} catch (Exception e1) {e1.printStackTrace();}
*/

}
