package com.kyonggi.Capstone_Develop.support;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@RequiredArgsConstructor
@Component
public class TestUuidHolder implements UuidHolder{
    @Override
    public String random() {
        return "1234";
    }
}
