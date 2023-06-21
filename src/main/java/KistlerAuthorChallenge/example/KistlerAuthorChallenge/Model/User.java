package KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;


    @ManyToMany
    @JoinTable(
            name = "subscription",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<User> subscribedAuthors;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> messages;

    public void addSubscription(User author)
    {
        if(!subscribedAuthors.contains(author))
            subscribedAuthors.add(author);
    }

    public List<Message> addMessage(Message message) {
        messages.add(message);
        return messages;
    }
}
