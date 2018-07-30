package sample.controller;

import com.flock.EventHandlerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/sample")
public class SampleController {
    private EventHandlerClient eventHandlerClient;
    private String appId = "0951f74a-4e6a-4884-803c-b434453371ff";
    private String appSecret = "8f221431-7a5a-42a6-bc19-42713e40d155";

    @PostConstruct
    public void initMethodAfterBeansSet() throws Exception {
        eventHandlerClient = new EventHandlerClient(appId, appSecret);
        eventHandlerClient.setAppInstallListener((event) -> {
            System.out.println("App Install event Occurred");
        });
        eventHandlerClient.setAppUninstallListener((event) -> {
            System.out.println("App Uninstall event Occurred");
        });
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    @ResponseBody
    public void event(HttpServletRequest request, HttpServletResponse response) {
        try {
            eventHandlerClient.handleRequest(request, response);
        } catch (Exception ex) {
            System.out.println("Some Exception");
        }
    }
}

