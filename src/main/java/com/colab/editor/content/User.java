package com.colab.editor.content;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection = "users")
@Data
public class User {

    @Id
    private String uid;

    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    private List<String> roles = new ArrayList<>();

    @DBRef
    private List<Doc> doclist = new LinkedList<>();

}
