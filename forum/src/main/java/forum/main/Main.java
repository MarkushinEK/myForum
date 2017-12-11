package forum.main;

import forum.service.*;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

public class Main {
    public static void main(String[] args) throws Exception {
        DBService dbService = DBServiceImpl.instance();
        FileHandlerService fileHandlerService = FileHandlerServiceImpl.instance();
        ForumService forumService = ForumServiceImpl.instance();

        forumService.setNumOfThreads(dbService.getListTread());

        //dbService.save(new ImageProfileUser("1.png"));
        ProtectionDomain domain = Main.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setWar(location.toExternalForm());

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
