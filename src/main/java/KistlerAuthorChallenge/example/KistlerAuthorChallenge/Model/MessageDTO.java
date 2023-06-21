package KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Create a new class for serialization purposes
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDTO {
    private Long message_id;
    private String author_name;
    private String message_content;

    // Constructor, getters, and setters
}


