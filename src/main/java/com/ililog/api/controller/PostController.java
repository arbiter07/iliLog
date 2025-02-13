package com.ililog.ililog.controller;

import com.ililog.ililog.dto.PostCreate;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class PostController {

    @PostMapping("/posts")
    public Map<String,String> post(@RequestBody @Valid PostCreate param, BindingResult result){

        if(result.hasErrors()){
            List<FieldError> error = result.getFieldErrors();
            FieldError firstFieldError = error.get(0);
            String name = firstFieldError.getField();
            String message = firstFieldError.getDefaultMessage();

            var errorMap = new HashMap<String, String>();
            errorMap.put(name, message);
            return errorMap;

        }
        log.info("param " + param.toString());
        return Map.of();
    }
}
