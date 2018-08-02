package sample.controller;

import com.flock.EventHandlerClient;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${appId}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

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
            ex.printStackTrace();
            System.out.println("Some Exception" + ex);
        }
    }
}

