package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {
	@Override
	public String operation() {
		log.info("���� ��ü ȣ��");
		sleep(1000);
		return "data";
	}
	
	private void sleep(int millies) {
		try {
			Thread.sleep(millies);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
