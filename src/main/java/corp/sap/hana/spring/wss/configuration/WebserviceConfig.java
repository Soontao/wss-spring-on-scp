package corp.sap.hana.spring.wss.configuration;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
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
		EndpointImpl endpoint = new EndpointImpl(bus, service);
		endpoint.publish("/student");
		return endpoint;
	}

}
