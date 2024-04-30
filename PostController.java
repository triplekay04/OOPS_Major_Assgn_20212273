package com.example.oopsassgn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Map<String, Object> ReqBody) {
        int UID = (int) ReqBody.get("userID");
        String postBody = (String) ReqBody.get("postBody");


        UserSetup userSetup = userRepository.findById(UID).orElse(null);
        if (userSetup == null) {
            return ResponseEntity.status(404).body("User does not exist");
        }


        Post newPost = new Post();
        newPost.setUser(userSetup);
        newPost.setPostBody(postBody);
        newPost.setDate(LocalDate.now());

        postRepository.save(newPost);

        return ResponseEntity.ok("Post created successfully");
    }


    @GetMapping
    public ResponseEntity<Object> getPost(@RequestParam("postID") int PID) {
        Post newPost = postRepository.findById(PID).orElse(null);

        if (newPost == null) {
            return ResponseEntity.status(404).body("Post does not exist");
        }

        return ResponseEntity.ok(newPost);
    }


    @PatchMapping
    public ResponseEntity<String> editPost(@RequestBody Map<String, Object> ReqBody) {
        int PID = (int) ReqBody.get("postID");
        String postBody = (String) ReqBody.get("postBody");

        Post newPost = postRepository.findById(PID).orElse(null);

        if (newPost == null) {
            return ResponseEntity.status(404).body("Post does not exist");
        }


        newPost.setPostBody(postBody);
        postRepository.save(newPost);

        return ResponseEntity.ok("Post edited successfully");
    }


    @DeleteMapping
    public ResponseEntity<String> deletePost(@RequestParam("postID") int PID) {
        Post post = postRepository.findById(PID).orElse(null);

        if (post == null) {
            return ResponseEntity.status(404).body("Post does not exist");
        }

        postRepository.delete(post);

        return ResponseEntity.ok("Post deleted successfully");
    }
}
