package com.ai.medicalAgent.assistant;
import dev.langchain4j.service.*;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;


@AiService(
    wiringMode = AiServiceWiringMode.EXPLICIT,
    // chatModel = "openAiChatModel",
    streamingChatModel = "openAiStreamingChatModel",
    chatMemoryProvider = "chatMemoryProviderMedicalAgent",
    tools = "appointmentTools",
    contentRetriever = "contentRetriever" //配置向量存储
)
public interface MedicalAgent {
    @SystemMessage(fromResource = "medical-prompt-template.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
