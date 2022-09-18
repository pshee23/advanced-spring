package hello.proxy.pureproxy.concreteproxy;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;

public class ConcreteProxyTest {

	@Test
	void noProxy() {
		ConcreteLogic concreateLogic = new ConcreteLogic();
		ConcreteClient client = new ConcreteClient(concreateLogic);
		client.execute();
	}
}
