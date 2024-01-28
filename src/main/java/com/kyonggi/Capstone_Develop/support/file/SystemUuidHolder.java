package com.kyonggi.Capstone_Develop.support.file;

import com.kyonggi.Capstone_Develop.support.UuidHolder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Profile("local")
public class SystemUuidHolder implements UuidHolder {

    @Override
    public String random() {
        return UUID.randomUUID().toString();
    }
}
