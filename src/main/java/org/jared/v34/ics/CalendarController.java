package org.jared.v34.ics;

import org.jared.v34.ics.exception.V34Exception;
import org.jared.v34.ics.model.Event;
import org.jared.v34.ics.services.Calendar;
import org.jared.v34.ics.services.DataService;
import org.jared.v34.ics.services.EventFilterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CalendarController {

    private static final Logger LOG = LoggerFactory.getLogger(CalendarController.class);

    @Autowired
    private DataService dataService;
    @Autowired
    private Calendar calendar;
    @Autowired
    private EventFilterFactory filterFactory;

    @RequestMapping("/{teamCode}.ics")
    @ResponseBody
    public String getICS(@PathVariable String teamCode, @RequestParam String name, @RequestParam String type) throws V34Exception {
        LOG.debug("Requesting ICS calendar for team {}", teamCode);
        List<Event> events = dataService.getAllEvents(teamCode);
        events = filterFactory.filter(events, type);
        String response = calendar.getCalendar(name, events);
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    public String handleDefaultException(Model model, Exception ex) {
        LOG.error("Error while requesting ICS stream", ex);
        model.addAttribute("message", "Aie ! Je n'avais à priori pas prévu ce problème.");
        return "error";
    }

}
