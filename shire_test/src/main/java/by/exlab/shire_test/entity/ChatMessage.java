package by.exlab.shire_test.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "chat_message")
public class ChatMessage implements BaseEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String message;
  @ManyToOne(fetch = FetchType.LAZY)
  private AppUser sender;
  @ManyToOne(fetch = FetchType.LAZY)
  private AppUser recipient;
  @ManyToOne(fetch = FetchType.LAZY)
  private UserChat userChat;
  private LocalDateTime createTime;

  public void setUserChat(UserChat userChat) {
    this.userChat = userChat;
    this.userChat.getChatMessages().add(this);
  }
}
