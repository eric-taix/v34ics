package org.jared.v34.ics;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value={"/", "index"})
    public String index() {
        return "index";
    }

    @RequestMapping("features")
    public String features() { return "features"; }

    @RequestMapping("calendars")
    public String calendars() { return "calendars"; }
}
