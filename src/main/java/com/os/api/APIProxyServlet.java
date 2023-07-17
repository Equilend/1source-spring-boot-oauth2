package com.os.api;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "APIProxyServlet", urlPatterns = "/proxy")
public class APIProxyServlet extends org.mitre.dsmiley.httpproxy.ProxyServlet {

	private static final long serialVersionUID = 6735555087512000502L;

	private static final Logger logger = LoggerFactory.getLogger(APIProxyServlet.class);

	protected void copyRequestHeaders(HttpServletRequest servletRequest, HttpRequest proxyRequest) {

		super.copyRequestHeaders(servletRequest, proxyRequest);

	}

	protected HttpClient createHttpClient(final RequestConfig requestConfig) {

		HttpClient sslClient = null;

		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
			sslClient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(requestConfig).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sslClient;

	}

	protected HttpResponse doExecute(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			HttpRequest proxyRequest) throws IOException {

		long startTime = System.currentTimeMillis();

		HttpResponseFactory factory = new DefaultHttpResponseFactory();

		if ("OPTIONS".equalsIgnoreCase(servletRequest.getMethod())) {
			logger.info("Intercepted OPTIONS request");
			Enumeration<String> enumerationOfHeaderNames = servletRequest.getHeaderNames();
			while (enumerationOfHeaderNames.hasMoreElements()) {
				String headerName = enumerationOfHeaderNames.nextElement();
				if ("Access-Control-Request-Headers".equalsIgnoreCase(headerName)) {
					String controlHeaderVal = servletRequest.getHeader(headerName);
					if (controlHeaderVal != null && controlHeaderVal.contains("authorization")) {
						logger.info("CORS pre-flight request");
						HttpResponse response = factory.newHttpResponse(
								new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null), null);
						response.setHeader("Access-Control-Allow-Origin", "*");
						response.setHeader("Access-Control-Allow-Headers", "authorization, content-type");
						response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
						HttpEntity entity = new StringEntity("{}", ContentType.APPLICATION_JSON);
						response.setEntity(entity);
						return response;
					}
				}
			}
		}

		proxyRequest.addHeader("Authorization", "Bearer " + servletRequest.getSession().getAttribute("auth_token"));
		
		HttpResponse response = getProxyClient().execute(getTargetHost(servletRequest), proxyRequest);
		response.setHeader("Access-Control-Allow-Origin", "*");

		long elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("URI: " + servletRequest.getRequestURI() + " in " + elapsedTime);

		return response;
	}

}
