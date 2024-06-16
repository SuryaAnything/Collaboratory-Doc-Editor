package com.colab.editor.controller.docs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colab.editor.content.Doc;
import com.colab.editor.content.TextToSpeech;
import com.colab.editor.content.User;
import com.colab.editor.services.DocService;
import com.colab.editor.services.SpeechService;
import com.colab.editor.services.Summarizer;
import com.colab.editor.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private DocService docService;
    @Autowired
    private UserService userService;
    @Autowired
    private SpeechService speechService;

    @Autowired
    private Summarizer summarizer;

    @PostMapping("/new")
    public ResponseEntity<?> addDoc(@RequestBody Doc doc) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        if (docService.saveDoc(doc) && user!=null) {
            user.getDoclist().add(doc);
            System.out.println(user.getPassword());
            userService.saveUserUnsafe(user);
            return new ResponseEntity<Doc>(HttpStatus.ACCEPTED);
        }
        
        return new ResponseEntity<Doc>(HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDoc(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        if(docService.deleteDoc(docService.getDocById(id)) && user != null) {
            user.getDoclist().remove(docService.getDocById(id));
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/voice-translate")
    public void getMethodName(@RequestBody TextToSpeech textToSpeech) throws Exception {

        String id = textToSpeech.getDocId();
        String type = textToSpeech.getVoiceType();
        String outputPath = textToSpeech.getOutputPath();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        if (user != null) {
            Doc doc = docService.getDocById(id);
            if (doc == null) throw new Exception("Document not found");
            System.out.println(outputPath);
            speechService.textToSpeech(doc.getContent(), type, outputPath);

        }
    }

    @GetMapping("/summarize/{id}")
    public String getSummary(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        if(user!=null) {
            Doc doc = docService.getDocById(id);
            String summarizedText = summarizer.summarizeText(doc.getContent());
            return summarizedText;
        }
        return null;
    }
    
    

}
