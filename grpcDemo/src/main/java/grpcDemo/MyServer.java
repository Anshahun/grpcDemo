package grpcDemo;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.examples.helloworld.GreeterGrpc.GreeterImplBase;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;

public class MyServer extends GreeterImplBase {

	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		HelloReply helloReply = HelloReply.newBuilder().setMessage("echo:======"+request.getName()).build();
		responseObserver.onNext(helloReply);
		responseObserver.onCompleted();
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(1234).addService(new MyServer()).build().start();
		server.awaitTermination();
	}

}
