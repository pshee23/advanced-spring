package hello.advanced.app.v3;

import org.springframework.stereotype.Service;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // TODO 생성자 자동 생성. 내용 정리
public class OrderServiceV3 {
	private final OrderRepositoryV3 orderRepository;
	private final LogTrace trace;
	
	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = trace.begin("OrderService.orderItem()");
			orderRepository.save(itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져주어야 한다. 요구 사항에 원래 프로그램에 영향을 끼치면 안되기 때문
		}
	}
}
