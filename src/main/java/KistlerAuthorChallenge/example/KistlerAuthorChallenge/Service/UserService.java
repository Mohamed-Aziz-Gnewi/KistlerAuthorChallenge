package KistlerAuthorChallenge.example.KistlerAuthorChallenge.Service;

import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model.Message;
import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model.MessageDTO;
import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model.User;
import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model.UserDTO;
import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Repository.MessageRepository;
import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;


    public User addUser(User user)
    {
        userRepository.save(user);
        return user;
    }

    public UserDTO getUser(Long user_id)
    {
        User user = userRepository.findById(user_id).orElse(null);
        if(user == null)
            return null;
        UserDTO userDTO = new UserDTO(user.getUser_id(), user.getUsername());
        return userDTO;
    }

    //Get all the authors that the user with id == user_id is subscribed to
    public List<UserDTO> subscribedAuthors(Long user_id)
    {
        User user = userRepository.findById(user_id).orElse(null);
        if(user == null)
            return null;

        List<UserDTO> userDTOList = new ArrayList<>();
        for(User author:user.getSubscribedAuthors())
        {
            UserDTO userDTO = new UserDTO(author.getUser_id(), author.getUsername());
            userDTOList.add(userDTO);
        }
        return userDTOList;

    }

    //Subscribe user to author
    public List<UserDTO> SubscribeToAuthor(Long user_id,Long author_id)
    {
        User user = userRepository.findById(user_id).orElse(null);
        User author = userRepository.findById(author_id).orElse(null);
        if(user == null || author == null)
            return null;
        user.addSubscription(author);
        userRepository.save(user);
        return subscribedAuthors(user_id);

    }
    //Add new message
    public MessageDTO addMessage(Long author_id,String message_content)
    {
        Message message = new Message();
        message.setMessage_content(message_content);
        User author = userRepository.findById(author_id).orElse(null);
        if(author == null)
                return null;
        message.setAuthor(author);
        messageRepository.save(message);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setAuthor_name(author.getUsername());
        messageDTO.setMessage_content(message_content);

        return messageDTO;

    }



    // get a message by message Id
    public MessageDTO getMessage(Long message_id) {
        Message message = messageRepository.findById(message_id).orElse(null);
        if (message == null) {
            return null;
        }
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage_id(message.getMessage_id());
        messageDTO.setMessage_content(message.getMessage_content());
        messageDTO.setAuthor_name(message.getAuthor().getUsername());

        return messageDTO;
    }
    // get messages by their author id
    public List<MessageDTO> getMessagesByAuthorId(Long author_id) {
        List<Message> messages = messageRepository.getMessageByAuthorId(author_id);
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for(Message message: messages)
        {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessage_id(message.getMessage_id());
            messageDTO.setMessage_content(message.getMessage_content());
            messageDTO.setAuthor_name(message.getAuthor().getUsername());
            messageDTOList.add(messageDTO);

        }

        return messageDTOList;
    }

    //get all messages of the user with the messages of all the users he subscribes to
    public List<MessageDTO> getAllMessagesByUserId(Long user_id)
    {
        User user = userRepository.findById(user_id).get();
        List<MessageDTO> messageDTOList = getMessagesByAuthorId(user_id);

        for(User author: user.getSubscribedAuthors())
        {
            for(Message message: author.getMessages())
            {
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setMessage_id(message.getMessage_id());
                messageDTO.setMessage_content(message.getMessage_content());
                messageDTO.setAuthor_name(message.getAuthor().getUsername());
                messageDTOList.add(messageDTO);
            }
        }
        return messageDTOList;
    }


}
