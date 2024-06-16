package com.colab.editor.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.colab.editor.content.Doc;

@Component
public interface DocRepository extends MongoRepository<Doc, String>{

}
