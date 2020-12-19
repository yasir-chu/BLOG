package com.chuyx.filter;

import com.chuyx.pojo.dto.LoginUserDTO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
   filterName = "accessFilter",
   urlPatterns = {"/*"}
)
public class AccessFilter implements Filter {
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest servletRequest = (HttpServletRequest)request;
      HttpServletResponse servletResponse = (HttpServletResponse)response;
      HttpSession session = servletRequest.getSession();
      LoginUserDTO userMsg = (LoginUserDTO)session.getAttribute("userMsg");
      String requestURI = ((HttpServletRequest)request).getRequestURI();
      if (userMsg != null) {
         if (requestURI.contains("admin") && userMsg.getCapacity() != 2) {
            servletRequest.getRequestDispatcher("/failed").forward(request, response);
         } else if (requestURI.contains("author") && userMsg.getCapacity() < 1) {
            servletRequest.getRequestDispatcher("/failed").forward(request, response);
         } else if (requestURI.contains("updateUserMsg")) {
            if (userMsg.getUid() != Integer.parseInt(requestURI.substring(requestURI.indexOf("updateUserMsg") + 14))) {
               servletRequest.getRequestDispatcher("/failed").forward(request, response);
            } else {
               chain.doFilter(request, response);
            }
         } else {
            chain.doFilter(request, response);
         }
      } else if (!requestURI.contains("admin") && !requestURI.contains("author") && !requestURI.contains("updateUserMsg")) {
         chain.doFilter(request, response);
      } else if (requestURI.contains("updateUserMsg")) {
         session.setAttribute("errMsg", "您还未登陆！！！");
         servletResponse.sendRedirect("/ordinary/toLogin");
      } else {
         servletRequest.getRequestDispatcher("/failed").forward(request, response);
      }

   }
}
