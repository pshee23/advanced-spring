package hello.advanced.app.v1;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

	private final HelloTraceV1 trace;
	
	public void save(String itemId) {
		
		TraceStatus status = null;
		try {
			status = trace.begin("OrderRepository.save()");
			
			// 저장 로직
			if (itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생!");
			}
			sleep(1000);
			
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져주어야 한다. 요구 사항에 원래 프로그램에 영향을 끼치면 안되기 때문
		}
	}

	private void sleep(int millies) {
		try {
			Thread.sleep(millies);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
