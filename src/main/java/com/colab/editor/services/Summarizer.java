package com.colab.editor.services;

import com.textrazor.TextRazor;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Entity;
import com.textrazor.annotations.Sentence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Summarizer {

    @Value("${textrazor.api.key}")
    private String apiKey;

    public String summarizeText(String text) {
        TextRazor client = new TextRazor(apiKey);
        client.addExtractor("entities");
        client.addExtractor("topics");
        client.addExtractor("words");

        try {
            AnalyzedText response = client.analyze(text);

            // Extract sentences and entities
            List<String> sentences = new ArrayList<>();
            for (Sentence sentence : response.getResponse().getSentences()) {
                sentences.add(sentence.getWords().stream()
                        .map(word -> word.getToken())
                        .collect(Collectors.joining(" ")));
            }

            List<Entity> entities = response.getResponse().getEntities();

            // Basic summarization logic: Select sentences containing the most frequent entities
            String summary = sentences.stream()
                    .filter(sentence -> entities.stream()
                            .anyMatch(entity -> sentence.contains(entity.getEntityId())))
                    .collect(Collectors.joining(" "));

            return summary.isEmpty() ? text : summary;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error summarizing text: " + e.getMessage();
        }
    }
}
