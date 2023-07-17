package com.os.config;

import java.util.Properties;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.os.api.APIProxyServlet;

@Configuration
public class APIProxyServletConfig implements EnvironmentAware {

	private BindResult bindResult;

    @Override
    public void setEnvironment(Environment environment) {
        Iterable sources = ConfigurationPropertySources.get(environment);
        Binder binder = new Binder(sources);
        BindResult bindResult = binder.bind("api.proxy", Properties.class);
        this.bindResult = bindResult;
    }

	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        Properties properties= (Properties) bindResult.get();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new APIProxyServlet(), properties.getProperty("servlet_url"));
        servletRegistrationBean.addInitParameter(APIProxyServlet.P_TARGET_URI, properties.getProperty("target_url"));
        servletRegistrationBean.addInitParameter(APIProxyServlet.P_LOG, properties.getProperty("logging_enabled", "false"));
        return servletRegistrationBean;
    }
}
