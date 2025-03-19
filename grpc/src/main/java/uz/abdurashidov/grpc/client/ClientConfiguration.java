package uz.abdurashidov.grpc.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;
import uz.abdurashidov.grpc.SimpleGrpc;

@Configuration
public class ClientConfiguration {
    @Bean
    SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channels) {
        return SimpleGrpc.newBlockingStub(channels.createChannel("hello-service"));
    }
}
