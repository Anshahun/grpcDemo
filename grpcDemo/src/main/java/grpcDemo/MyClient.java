package grpcDemo;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.GreeterGrpc.GreeterBlockingStub;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;

public class MyClient {
	
	String host;
	int port;
	
	public MyClient(String host, int port) {
		ManagedChannelBuilder<?> builder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		ManagedChannel channel = builder.build();
		GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
		HelloReply helloReply = stub.sayHello(HelloRequest.newBuilder().setName("hello").build());
		channel.shutdown();
		System.out.println(helloReply.getMessage());
	}

	public static void main(String[] args) {
		new MyClient("127.0.0.1", 1234);
	}

}
