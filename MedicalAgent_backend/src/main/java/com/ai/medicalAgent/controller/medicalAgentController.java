package com.ai.medicalAgent.controller;

import com.ai.medicalAgent.assistant.MedicalAgent;
import com.ai.medicalAgent.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "医小助")
@RestController
@RequestMapping("/medicalAgent")
public class medicalAgentController {
    @Autowired
    private MedicalAgent medicalAgent;

    @Operation(summary = "对话")
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return medicalAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
