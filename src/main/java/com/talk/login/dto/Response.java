package com.talk.login.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

@Component
public class Response {

    @Getter
    @Builder
    private static class Body {

        private int state;
        private String result;
        private String message;
        private Object data;
        private Object error;

    }

//    public ResponseEntity<?> success(Object data, String msg, HttpStatus status) {
//        Body body = Body.builder().state(status.value()).data(data).result("success")
//                .message(msg).error(Collections.emptyList()).build();
//        return ResponseEntity.ok(body);
//    }

    @ResponseBody
    public HashMap<String, Object> success(String token, Long id, String name) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("userId", id);
        map.put("userName", name);
        return map;
    }


     //매세지만 가진 성공 응답을 반환한다
//    public ResponseEntity<?> success(String msg) {
//        return success(Collections.emptyList(), msg, HttpStatus.OK);
//    }

    // 데이터만 가진 성공 응답을 반환한다
//    public ResponseEntity<?> success(Object data) {
//        return success(data, null, HttpStatus.OK);
//    }

     // 성공 응답만 반응한다
//    public HashMap<String, Object> success() {
//        return success(Collections.emptyList(), null, HttpStatus.OK);
//    }

//    public ResponseEntity<?> fail(Object data, String msg, HttpStatus status) {
//        Body body = Body.builder().state(status.value()).data(data).result("fail")
//                .message(msg).error(Collections.emptyList()).build();
//        return ResponseEntity.ok(body);
//    }

    // 매세지를 가진 실패 응답을 반환한다
//    public ResponseEntity<?> fail(String msg, HttpStatus status) {
//        return fail(Collections.emptyList(), msg, status);
//    }

    public ResponseEntity<?> invalidFields(LinkedList<LinkedHashMap<String, String>> errors) {
        Body body = Body.builder().state(HttpStatus.BAD_REQUEST.value())
                .data(Collections.emptyList()).result("fail").message("")
                .error(errors).build();
        return ResponseEntity.ok(body);
    }

    @ResponseBody
    public HashMap<String, Object> fail(String msg) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("error", msg);
        return map;
    }

}
