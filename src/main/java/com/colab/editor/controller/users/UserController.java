package com.colab.editor.controller.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colab.editor.content.Doc;
import com.colab.editor.content.RequestAccess;
import com.colab.editor.content.User;
import com.colab.editor.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @DeleteMapping 
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if(userService.deleteUser(userService.getUserByName(name).getUid())) {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/access")
    public ResponseEntity<?> postMethodName(@RequestBody RequestAccess request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        Doc doc = null;
        for(Doc x : user.getDoclist()) {
            if (x.getId().equals(request.getDocId())) {
                doc = x;
            }
        }
        if (doc == null) {
            throw new Exception("Doc Not found");
        }
        User user2 = userService.getUserByName(request.getUsername());
        if (user2!=null) {
            user2.getDoclist().add(doc);
            userService.saveUserUnsafe(user2);
            return new ResponseEntity<RequestAccess>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<RequestAccess>(HttpStatus.NOT_FOUND);
        }
    }
    
}
