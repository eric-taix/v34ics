package org.jared.v34.ics;

import org.jared.v34.ics.exception.V34Exception;
import org.jared.v34.ics.services.V34Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@org.springframework.stereotype.Controller
public class UIController {

    @Autowired
    private V34Service service;

    @RequestMapping(value={"/", "index"})
    public String index() {
        return "index";
    }

    @RequestMapping("features")
    public String features() { return "features"; }

    @RequestMapping("calendars")
    public String calendars(Model model) throws Exception {
        model.addAttribute("teams", service.getTeams());
        return "calendars";
    }

    @RequestMapping(value = "link")
    public String link(Model model) {
        return "link";
    }

    @ExceptionHandler(value = V34Exception.class)
    public String handleV34Exception() {
        return "error";
    }


}
