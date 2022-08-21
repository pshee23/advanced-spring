package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> { // 부모 클래스 패턴이고, 템플릿 역할을 함

	private final LogTrace trace;
	
	public AbstractTemplate(LogTrace trace) {
		this.trace = trace;
	}
	
	public T execute(String message) {
		TraceStatus status = null;
		try {
			status = trace.begin(message);
			
			//로직 호출
			T result = call();
			
			trace.end(status);
			return result;
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}

	protected abstract T call(); // 변하는 부분을 처리하는 메서드이다. 이 부분은 상속으로 구현해야 한다.
}
