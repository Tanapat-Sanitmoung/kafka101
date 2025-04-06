package com.example.kafka_apache.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@Getter
public class DuplicationEmailException extends Exception{

    public String email;

    public DuplicationEmailException(String email)
    {
        super("Duplicate Email address := " + email);

        this.email = email;
    }
}
