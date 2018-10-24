package corp.sap.hana.spring.wss.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.DefaultCryptoCoverageChecker;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import corp.sap.hana.spring.wss.services.MainService;

@Configuration
public class WebserviceConfig {

	@Autowired
	private MainService service;

	@Autowired
	private Bus bus;

	public MainService getService() {
		return service;
	}

	public void setService(MainService service) {
		this.service = service;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Bean
	public Endpoint endpoint() {
		
		Map<String,Object> inProps = new HashMap<String,Object>();
		
		inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE +" "+ WSHandlerConstants.ENCRYPT);
		inProps.put(WSHandlerConstants.SIG_PROP_FILE, "ws-in-sign.properties");
		inProps.put(WSHandlerConstants.DEC_PROP_FILE, "ws-in-decrypt.properties");
		inProps.put(WSHandlerConstants.ENC_KEY_ID, "IssuerSerial");
		inProps.put(WSHandlerConstants.SIG_KEY_ID, "IssuerSerial");
		
		EndpointImpl endpoint = new EndpointImpl(bus, service);
		
		endpoint.publish("/main");
		
		endpoint.getInInterceptors().add(new WSS4JInInterceptor(inProps));

        DefaultCryptoCoverageChecker coverageChecker = new DefaultCryptoCoverageChecker();
        coverageChecker.setEncryptBody(true);
        coverageChecker.setSignBody(true);
        endpoint.getInInterceptors().add(coverageChecker);
		
		return endpoint;
	}

}
