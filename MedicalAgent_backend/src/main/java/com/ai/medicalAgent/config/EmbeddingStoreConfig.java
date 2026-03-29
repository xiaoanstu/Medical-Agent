package com.ai.medicalAgent.config;

import dev.langchain4j.community.store.embedding.redis.RedisEmbeddingStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmbeddingStoreConfig {
    @Autowired
    private EmbeddingModel embeddingModel;
    @Autowired
    private RedisEmbeddingStore redisEmbeddingStore;


    // @Bean
    // public EmbeddingStore<TextSegment> embeddingStore() {
    // //创建向量存储
    //     EmbeddingStore<TextSegment> embeddingStore = PineconeEmbeddingStore.builder()
    //     .apiKey(System.getenv("DASHSCOPE_API_KEY"))
    //     .index("medicalAgent-index")//如果指定的索引不存在，将创建一个新的索引
    //     .nameSpace("medicalAgent-namespace") //如果指定的名称空间不存在，将创建一个新的名称 空间
    //     .createIndex(PineconeServerlessIndexConfig.builder()
    //     .cloud("AWS") //指定索引部署在 AWS 云服务上。
    //     .region("us-east-1") //指定索引所在的 AWS 区域为 us-east-1。
    //     .dimension(embeddingModel.dimension()) //指定索引的向量维度，该维度与embeddedModel 生成的向量维度相同
    //     .build())
    //     .build();
    //     return embeddingStore;
    // }


    @Bean
    public EmbeddingStore store(){
        //加载文档进入内存
        // List<Document> documents = ClassPathDocumentLoader.loadDocuments("content/md");

        List<Document> documents = ClassPathDocumentLoader.loadDocuments("content/pdf",new ApachePdfBoxDocumentParser());

        //构建向量数据库操作对象
        // InMemoryEmbeddingStore store = new InMemoryEmbeddingStore();

        //构建文档分割器
        DocumentSplitter ds = DocumentSplitters.recursive(500, 100);

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                // .embeddingStore(store)
                .embeddingStore(redisEmbeddingStore)
                .documentSplitter(ds)
                .embeddingModel(embeddingModel)
                .build();
        ingestor.ingest(documents);

        return redisEmbeddingStore;
    }
    //构建向量数据库检索对象
    @Bean
    public ContentRetriever contentRetriever(/* EmbeddingStore store */){
        return EmbeddingStoreContentRetriever.builder()
                // .embeddingStore(store)
                .embeddingStore(redisEmbeddingStore)
                .minScore(0.5)
                .maxResults(3)
                .embeddingModel(embeddingModel)
                .build();

    }
}
