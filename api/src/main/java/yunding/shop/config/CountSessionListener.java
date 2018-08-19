package yunding.shop.config;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CountSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Integer onLineCount = (Integer) context.getAttribute("onLineCount");
        if(onLineCount==null){
            context.setAttribute("onLineCount", 1);
        }else{
            onLineCount++;
            context.setAttribute("onLineCount", onLineCount);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Integer onLineCount = (Integer) context.getAttribute("onLineCount");
        if(onLineCount==null){
            context.setAttribute("onLineCount", 1);
        }else{
            onLineCount--;
            context.setAttribute("onLineCount", onLineCount);
        }
    }
}
