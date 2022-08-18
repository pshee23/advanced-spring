package hello.advanced.app.v0;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // TODO 생성자 자동 생성. 내용 정리
public class OrderServiceV0 {
	private final OrderRepositoryV0 orderRepository;
	
	public void orderItem(String itemId) {
		orderRepository.save(itemId);
	}
}
