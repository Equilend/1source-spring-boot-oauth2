package com.os.config;

import java.util.Properties;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.os.api.APIProxyServlet;

@Configuration
public class APIProxyServletConfig implements EnvironmentAware {

	private BindResult<Properties> bindResult;

    @Override
    public void setEnvironment(Environment environment) {
        Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(environment);
        Binder binder = new Binder(sources);
        this.bindResult = binder.bind("api.proxy", Properties.class);
    }

	@Bean
    public ServletRegistrationBean<APIProxyServlet> servletRegistrationBean() {
        Properties properties = bindResult.get();
        ServletRegistrationBean<APIProxyServlet> servletRegistrationBean = new ServletRegistrationBean<>(new APIProxyServlet(), properties.getProperty("servlet_url"));
        servletRegistrationBean.addInitParameter(ProxyServlet.P_TARGET_URI, properties.getProperty("target_url"));
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, properties.getProperty("logging_enabled", "false"));
        return servletRegistrationBean;
    }
}
