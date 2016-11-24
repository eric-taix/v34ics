package org.jared.v34.ics;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.jared.v34.ics.exception.V34Exception;
import org.jared.v34.ics.services.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;

@org.springframework.stereotype.Controller
public class UIController {

    private static final Logger LOG = LoggerFactory.getLogger(UIController.class);

    @Autowired
    private DataService service;

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
    public String link(Model model,
                       @RequestParam String team,
                       @RequestParam String teamName,
                       @RequestParam(value = "matchs", defaultValue = "false") boolean match,
                       @RequestParam(value = "tournois", defaultValue = "false") boolean tournment,
                       @RequestParam(value = "federaux", defaultValue = "false") boolean federal,
                       @RequestParam(value = "reunions", defaultValue = "false") boolean meeting,
                       @RequestParam(value = "autres", defaultValue = "false") boolean other,
                       HttpServletRequest request) throws UnsupportedEncodingException {
        String link = getUrl(request) + team + ".ics?type=";
        link += (match ? "M":"") + (tournment ? "T":"") + (federal ? "F":"") + (meeting ? "R":"") + (other ? "A":"");
        link += "&name=" + UriUtils.encodeQueryParam(teamName, "UTF-8");
        model.addAttribute("teamName", teamName);
        String events = (match ? "Matchs, ":"") + (tournment ? "Tournois, ":"") + (federal ? "Fédéraux, ":"") + (meeting ? "Réunions, ":"") + (other ? "Autres, ":"");
        if (events == null || events.isEmpty()) {
            model.addAttribute("message", "Vous n'avez sélectionné aucun événement");
            return "error";
        }
        model.addAttribute("events", events.substring(0, events.length() - 2));
        model.addAttribute("link", link);
        return "link";
    }

    private String getUrl(HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80 || request.getScheme().equalsIgnoreCase("http")) {
            url += ":" + request.getServerPort() + "/";
        }
        url += (request.getContextPath() != null && !request.getContextPath().isEmpty() ? request.getContextPath() + "/" : "");
        return url;
    }

    @ExceptionHandler(value = V34Exception.class)
    public String handleV34Exception(Model model, Exception ex) {
        LOG.error("Error wit UI", ex);
        model.addAttribute("message", "Il semble y avoir un problème quand je discute avec le site Volley34 !");
        return "error";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleDefaultException(Model model, Exception ex) {
        LOG.error("Error wit UI", ex);
        model.addAttribute("message", "Aie ! Je n'avais à priori pas prévu ce problème.");
        return "error";
    }


}
