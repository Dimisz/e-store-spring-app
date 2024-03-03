package com.uningen.catalogservice;

import com.uningen.catalogservice.config.CatalogProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final CatalogProperties catalogProperties;

    public HomeController(CatalogProperties catalogProperties) {
        this.catalogProperties = catalogProperties;
    }

    @GetMapping("/")
    public String getGreeting(){
        return catalogProperties.getGreeting();
    }
}
