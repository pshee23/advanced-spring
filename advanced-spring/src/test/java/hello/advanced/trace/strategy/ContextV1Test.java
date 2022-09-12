package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.ContextV1;
import hello.advanced.trace.strategy.code.Strategy;
import hello.advanced.trace.strategy.code.StrategyLogic1;
import hello.advanced.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1Test {
	/*	
	 * 전략 패턴 사용
	 */
	@Test
	void strategyV1() {
		StrategyLogic1 strategyLogic1 = new StrategyLogic1();
		ContextV1 context1 = new ContextV1(strategyLogic1);
		context1.execute();
		
		StrategyLogic2 strategyLogic2 = new StrategyLogic2();
		ContextV1 context2 = new ContextV1(strategyLogic2);
		context2.execute();
	}
	
	@Test
	void strategyV2() {
		Strategy strategyLogic1 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		};
		ContextV1 contextV1 = new ContextV1(strategyLogic1);
		log.info("strategyLogic1={}", strategyLogic1.getClass()); // 익명 내부 클래스 생성 확인
		contextV1.execute();
		

		Strategy strategyLogic2 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		};
		ContextV1 contextV2 = new ContextV1(strategyLogic2);
		log.info("strategyLogic2={}", strategyLogic2.getClass());
		contextV2.execute();
	}
	
	@Test
	void strategyV3() { // 익명 클래스를 편하게 만드는법
		ContextV1 contextV1 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		});
		contextV1.execute();
		

		ContextV1 contextV2 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		});
		contextV2.execute();
	}
	
	@Test
	void strategyV4() { // lambda를 사용해서 익명 클래스를 편하게 만드는법
		ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
		contextV1.execute();

		ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
		contextV2.execute();
	}
}
