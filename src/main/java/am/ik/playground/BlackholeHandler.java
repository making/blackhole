package am.ik.playground;

import java.net.InetSocketAddress;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.accepted;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public class BlackholeHandler {

	private static final Logger log = LoggerFactory.getLogger(BlackholeHandler.class);

	public RouterFunction<ServerResponse> routes() {
		return route(__ -> true, this::accept);
	}

	Mono<ServerResponse> accept(ServerRequest req) {
		final ServerWebExchange exchange = req.exchange();
		final ServerHttpRequest request = exchange.getRequest();
		return req.bodyToMono(String.class) //
				.switchIfEmpty(Mono.just("")) //
				.doOnNext(s -> {
					final String host = Optional.ofNullable(request.getHeaders().getHost())
							.map(InetSocketAddress::getHostString)
							.orElse("-");
					log.info("{}\t{}\t{}\t{}", request.getMethod(), host, request.getPath(), s);
				}) //
				.flatMap(__ -> ((request.getMethod() == HttpMethod.GET || request.getMethod() == HttpMethod.HEAD) ? ok() : accepted()).build());
	}
}
