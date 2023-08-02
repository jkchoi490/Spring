package com.springboot.actuator.config.actuator;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

// 커스텀 엔드포인트 생성 - 엔드포인트 클래스
@Component
@Endpoint(id = "note", enableByDefault = true)
public class NoteEndpoint {

    private Map<String, Object> noteContent = new HashMap<>();

    @ReadOperation
    public Map<String, Object> getNote(){
        return noteContent;
    }

    @WriteOperation
    public Map<String, Object> writeNote(String key, Object value){
        noteContent.put(key,value);
        return noteContent;
    }

    @DeleteOperation
    public Map<String, Object> deleteNote(String key){
        noteContent.remove(key);
        return noteContent;
    }

}