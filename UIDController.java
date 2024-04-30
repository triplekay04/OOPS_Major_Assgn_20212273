package com.example.oopsassgn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UIDController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Map<String, String> ReqBody) {
        String MailID = ReqBody.get("email");
        String Username = ReqBody.get("name");
        String Psswd = ReqBody.get("password");

        // Validate input
        if (MailID == null || Username == null || Psswd == null) {
            return ResponseEntity.status(400).body("Enter Valid Details");
        }

        UserSetup existusersetup = userRepository.findByMailID(MailID);
        if (existusersetup != null) {
            return ResponseEntity.status(403).body("Forbidden, Account already exists");
        }

        UserSetup newUserSetup = new UserSetup();
        newUserSetup.setMailID(MailID);
        newUserSetup.setUsername(Username);
        newUserSetup.setPsswD(Psswd); // Ensure you hash the password in production

        userRepository.save(newUserSetup);

        return ResponseEntity.ok("Account Creation Successful");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> reqbody) {
        String email = reqbody.get("email");
        String password = reqbody.get("password");

        if (email == null || password == null) {
            return ResponseEntity.status(400).body("Enter valid details");
        }

        UserSetup userSetup = userRepository.findByMailID(email);
        if (userSetup == null) {
            return ResponseEntity.status(404).body("User does not exist");
        }

        if (!userSetup.getPsswD().equals(password)) {
            return ResponseEntity.status(401).body("Username or Password is not entered correctly");
        }

        return ResponseEntity.ok("Login Successful");
    }


    @GetMapping("/user")
    public ResponseEntity<Object> getUserDetails(@RequestParam("userID") int userID) {
        Optional<UserSetup> userOptional = userRepository.findById(userID);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("User does not exist");
        }

        UserSetup userSetup = userOptional.get();

        // Create a response object with user details
        UserSetup userSetupDetail = new UserSetup(userSetup.getUID(), userSetup.getUsername(), userSetup.getMailID());

        return ResponseEntity.ok(userSetupDetail);
    }


    @GetMapping("/")
    public ResponseEntity<List<Post>> getUserFeed() {
        // Fetch all posts and sort by date in reverse chronological order
        List<Post> posts = postRepository.findAll()
                .stream()
                .sorted((p1, p2) -> p2.getDate().compareTo(p1.getDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserSetup> userSetups = userRepository.findAll();


        List<UserDTO> userDTOs = userSetups.stream()
                .map(user -> new UserDTO(user.getUID(), user.getUsername(), user.getMailID()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }
}


