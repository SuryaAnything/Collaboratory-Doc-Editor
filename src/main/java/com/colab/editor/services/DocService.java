package com.colab.editor.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colab.editor.content.Doc;
import com.colab.editor.repository.DocRepository;

@Component
public class DocService {

    @Autowired
    private DocRepository docRepository;

    public boolean saveDoc(Doc doc) {
        try {
            docRepository.save(doc);
            return true;
        }
        catch(Exception e) {
            return false;
        } 
    }

    public boolean deleteDoc(Doc doc) {
        try {
            docRepository.delete(doc);
            return true;
        }
        catch(Exception e) {
            return false;
        } 
    }
    
    public Doc getDocById(String id) {
        return docRepository.findById(id).orElse(null);
    }

    public List<Doc> getAllDoc() {
        return docRepository.findAll();

    }

    public Doc getDoc(String title) {
        List<Doc> docList = docRepository.findAll();
        for(Doc doc : docList) {
            if (doc.getTitle().equals(title)) return doc;
        }
        return null;
    }
}
