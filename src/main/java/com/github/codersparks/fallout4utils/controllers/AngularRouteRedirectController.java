package com.github.codersparks.fallout4utils.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by codersparks on 12/12/2015.
 */
@Controller
public class AngularRouteRedirectController {

    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}
