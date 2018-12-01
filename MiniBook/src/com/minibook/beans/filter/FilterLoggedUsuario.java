package com.minibook.beans.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minibook.model.entity.Usuario;

@WebFilter(filterName = "FilterLoggedUsuario", urlPatterns = {"/pagesLogger/*"})
public class FilterLoggedUsuario implements Filter {

  private static final boolean debug = true;
  private FilterConfig filterConfig = null;

  public FilterLoggedUsuario() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogged");
    String url = req.getRequestURL().toString();

    if (u != null
        || (url.contains("index.xhtml") || url.contains("confirmUsuario.xhtml") || url
            .contains("dadosUsuario.xhtml"))) {
      chain.doFilter(request, response);
    } else {
      resp.sendRedirect("/MiniBook/index.xhtml");
    }
  }

  public FilterConfig getFilterConfig() {
    return (this.filterConfig);
  }

  public void setFilterConfig(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  @Override
  public void destroy() {}

  @Override
  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
    if (filterConfig != null) {
      if (debug) {
        log("FilterLoggedUsuario:Initializing filter");
      }
    }
  }

  @Override
  public String toString() {
    if (filterConfig == null) {
      return ("FilterLoggedUsuario()");
    }
    StringBuilder sb = new StringBuilder("FilterLoggedUsuario(");
    sb.append(filterConfig);
    return (sb.toString());
  }

  public void log(String msg) {
    filterConfig.getServletContext().log(msg);
  }
}
