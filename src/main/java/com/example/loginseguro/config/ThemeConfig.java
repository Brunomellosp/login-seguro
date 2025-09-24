package com.example.loginseguro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ThemeConfig {
    @Value("${app.theme:default}")
    private String appTheme;

    @ModelAttribute("appTheme")
    public String appTheme() {
        return appTheme;
    }
}
