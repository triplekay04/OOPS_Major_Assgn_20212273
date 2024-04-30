package com.example.oopsassgn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommRepository commRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody Map<String, Object> ReqBody) {
        String CommBody = (String) ReqBody.get("commentBody");
        int PID = (int) ReqBody.get("postID");
        int UID = (int) ReqBody.get("userID");



        Post post = postRepository.findById(PID).orElse(null);
        if (post == null) {
            return ResponseEntity.status(404).body("Post does not exist");
        }


        UserSetup userSetup = userRepository.findById(UID).orElse(null);
        if (userSetup == null) {
            return ResponseEntity.status(404).body("User does not exist");
        }


        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(userSetup);
        comment.setCommBody(CommBody);

        commRepository.save(comment);

        return ResponseEntity.ok("Comment creation was successful");
    }


    @GetMapping
    public ResponseEntity<Object> getComment(@RequestParam("commentID") int CommID) {
        Comment comment = commRepository.findById(CommID).orElse(null);

        if (comment == null) {
            return ResponseEntity.status(404).body("Comment does not exist");
        }

        return ResponseEntity.ok(comment);
    }


    @PatchMapping
    public ResponseEntity<String> editComment(@RequestBody Map<String, Object> ReqBody) {
        int CommID = (int) ReqBody.get("commentID");
        String CommBody = (String) ReqBody.get("commentBody");

        Comment newComm = commRepository.findById(CommID).orElse(null);

        if (newComm == null) {
            return ResponseEntity.status(404).body("Comment does not exist");
        }


        newComm.setCommBody(CommBody);
        commRepository.save(newComm);

        return ResponseEntity.ok("Comment edited successfully");
    }


    @DeleteMapping
    public ResponseEntity<String> deleteComment(@RequestParam("commentID") int CommID) {
        Comment newComm = commRepository.findById(CommID).orElse(null);

        if (newComm == null) {
            return ResponseEntity.status(404).body("Comment does not exist");
        }

        commRepository.delete(newComm);

        return ResponseEntity.ok("Comment deleted successfully");
    }
}

