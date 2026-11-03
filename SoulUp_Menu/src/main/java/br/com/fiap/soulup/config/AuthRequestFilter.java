package br.com.fiap.soulup.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        boolean auth=request.getRequestURI().startsWith("/auth/");
        boolean register=request.getRequestURI().equals("/usuarios") && request.getMethod().equals("POST");
        if (auth || register) {
            response.setHeader("Cache-Control","no-store");
            String origin=request.getHeader("Origin");
            if (!request.getMethod().equals("GET") && origin!=null && !allowed(origin,request)) { response.sendError(403); return; }
        }
        chain.doFilter(request,response);
    }
    private boolean allowed(String origin,HttpServletRequest request) {
        try {
            URI uri=URI.create(origin);
            if (!Arrays.asList("http","https").contains(uri.getScheme())) return false;
            String forwarded=request.getHeader("X-Forwarded-Host");
            String host=forwarded==null ? request.getHeader("Host") : forwarded;
            if (uri.getAuthority()!=null && uri.getAuthority().equals(host)) return true;
            String configured=System.getenv("FRONTEND_ORIGINS");
            String origins=configured==null ? "http://localhost:5173,http://127.0.0.1:5173,http://localhost:4173,http://127.0.0.1:4173" : configured;
            return Arrays.stream(origins.split(",")).map(String::trim).anyMatch(origin::equals);
        } catch (IllegalArgumentException e) { return false; }
    }
}
