package com.db.services;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Hello {

	public static void main(String[] args) throws IOException {
	
		System.out.print(false);
		Hello.hello().subscribe(i-> System.out.println(i));
		
//		List<Integer> in=Hello.helloFlux().toStream().toList();
//		System.out.println(in);
		
		Hello.helloFlux().subscribe(new MySubscriber<>());
		
		System.in.read();
	}
	
	public static Flux<Integer> helloFlux(){
		return Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
	}
	
	public static Mono<Integer> helloMono(){
		return Mono.just(32);
	}
	
	public static Flux<Integer> hello(){
		return Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
	}
	

}


class MySubscriber<T> extends BaseSubscriber<T>{

	@Override
	protected void hookOnComplete() {
		// TODO Auto-generated method stub
		System.out.println("completed Successfully");
		
		super.hookOnComplete();
	}


	public void hookOnSubscribe(Subscription subscription) {
		//super.hookOnSubscribe(subscription);
		System.out.println("Subscribed Successfully");
		request(3);
	}

	
	public void hookOnNext(T value) {
//		// TODO Auto-generated method stub
//		super.hookOnNext(value);
		
		System.out.println(value.toString()+" Recived");
		request(1);
		
	}
	
	
	
}
