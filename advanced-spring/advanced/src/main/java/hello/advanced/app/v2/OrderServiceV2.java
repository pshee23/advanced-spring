package hello.advanced.app.v2;

import org.springframework.stereotype.Service;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // TODO 생성자 자동 생성. 내용 정리
public class OrderServiceV2 {
	private final OrderRepositoryV2 orderRepository;
	private final HelloTraceV2 trace;
	
	public void orderItem(TraceId traceId, String itemId) {
		TraceStatus status = null;
		try {
			status = trace.beginSync(traceId, "OrderService.orderItem()");
			orderRepository.save(status.getTraceId(), itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져주어야 한다. 요구 사항에 원래 프로그램에 영향을 끼치면 안되기 때문
		}
	}
}
