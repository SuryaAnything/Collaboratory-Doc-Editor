package com.colab.editor.content;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestAccess {

    @JsonProperty("username")
    private String username;

    @JsonProperty("docId")
    private String docId;

}
