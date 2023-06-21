package KistlerAuthorChallenge.example.KistlerAuthorChallenge.Controller;

import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model.Message;
import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model.MessageDTO;
import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model.User;
import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model.UserDTO;
import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @GetMapping("/getUser/{id}")
    public UserDTO getUser(@PathVariable("id")Long id)
    {
        return userService.getUser(id);
    }

    @GetMapping("/getSubscribedAuthors/{user_id}")
    public List<UserDTO> subscribedAuthors(@PathVariable("user_id")Long user_id)
    {
        return userService.subscribedAuthors(user_id);
    }


    @PostMapping("/addAuthorSubscription/{user_id}/{author_id}")
    public List<UserDTO> subscribedAuthors(@PathVariable("user_id")Long user_id, @PathVariable("author_id")Long author_id)
    {
        return userService.SubscribeToAuthor(user_id,author_id);
    }

    @PostMapping("/addMessage/{author_id}")
    public Message addMessage(@PathVariable("author_id")Long author_id, @RequestParam String message_content)
    {
        return userService.addMessage(author_id,message_content);
    }

    @GetMapping("/getMessages/{author_id}")
    public List<MessageDTO> getMessages(@PathVariable("author_id")Long author_id)
    {
        return userService.getMessagesByAuthorId(author_id);
    }

    @GetMapping("/getMessage/{message_id}")
    public MessageDTO getMessage(@PathVariable("message_id")Long message_id)
    {
        return userService.getMessage(message_id);
    }
    @GetMapping("/getAllMessages/{user_id}")
    public List<MessageDTO> getAllMessagesByUserId(@PathVariable("user_id")Long user_id)
    {
        return userService.getAllMessagesByUserId(user_id);
    }
}
