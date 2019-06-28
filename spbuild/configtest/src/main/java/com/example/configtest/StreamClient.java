package com.example.configtest;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    String MSG="myFirstStream";

    @Input(StreamClient.MSG)
    SubscribableChannel input();

    @Output(StreamClient.MSG)
    MessageChannel output();

}
