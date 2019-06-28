package com.spb.consumer1.test.stream;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class StreamController {

/*    @Autowired
    private StreamClient streamClient;

    @GetMapping("/stream")
    public void setStreamClient() {
        streamClient.output().send(MessageBuilder.withPayload("hello,stream").build());
    }*/
}
