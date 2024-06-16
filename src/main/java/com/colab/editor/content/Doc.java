package com.colab.editor.content;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "document")
@Data
@NoArgsConstructor
public class Doc {

    @Id
    private String id;

    @NonNull
    private String title;
    
    private String content;

    @JsonCreator
    public Doc(@JsonProperty("id") String id, 
               @JsonProperty("title") String title, 
               @JsonProperty("content") String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    
}
