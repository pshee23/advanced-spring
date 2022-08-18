package hello.advanced.app.v2;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

	private final HelloTraceV2 trace;
	
	public void save(TraceId traceId, String itemId) {
		
		TraceStatus status = null;
		try {
			status = trace.beginSync(traceId, "OrderRepository.save()");
			
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
