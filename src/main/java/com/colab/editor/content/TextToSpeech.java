package com.colab.editor.content;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextToSpeech {


    @JsonProperty("docid")
    private String docId;
    
    @JsonProperty("voicetype")
    private String voiceType;

    @JsonProperty("outputpath")
    private String outputPath;
    
}
