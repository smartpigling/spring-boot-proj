package com.proj.core.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.proj.core.CoreRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CoreRunner.class)
@WebAppConfiguration
@DirtiesContext
@IntegrationTest({"server.port=0"})
public class TomcatSslApplicationTests {

	@Value("${local.server.port}")
	private int port;

	@Test
	public void testTrans() throws Exception {
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
				new SSLContextBuilder()
						.loadTrustMaterial(null, new TrustSelfSignedStrategy()).build());

		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
				.build();

		TestRestTemplate testRestTemplate = new TestRestTemplate();
		((HttpComponentsClientHttpRequestFactory) testRestTemplate.getRequestFactory())
				.setHttpClient(httpClient);
		ResponseEntity<String> entity = testRestTemplate
				.getForEntity("https://localhost:" + this.port+"/testTrans", String.class);
		assertThat(entity.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(entity.getBody(), equalTo("HTTPS OK!"));
	}

}
