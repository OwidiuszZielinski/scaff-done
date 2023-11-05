package com.dev.scaffdone.config;

import com.vaadin.flow.spring.SpringServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;



public class ExampleWebAppInitializer implements WebApplicationInitializer {


    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        registerConfiguration(context);
        servletContext.addListener(new ContextLoaderListener(context));

        // Register the VaadinServlet here
        registerVaadinServlet(servletContext, context);

        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
    }

    private void registerConfiguration(AnnotationConfigWebApplicationContext context) {
        // Register your configuration classes here
    }

    private void registerVaadinServlet(ServletContext servletContext, AnnotationConfigWebApplicationContext context) {
        SpringServlet vaadinServlet = new SpringServlet(context, true);

        ServletRegistration.Dynamic registration = servletContext.addServlet("vaadinServlet", vaadinServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/vaadinServlet/*");
    }
}
