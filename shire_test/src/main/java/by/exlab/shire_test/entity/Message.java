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
@Table(name = "message")
public class Message implements BaseEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private AppUser sender;
  @ManyToOne(fetch = FetchType.LAZY)
  private AppUser recipient;
  private String content;
  @ManyToOne(fetch = FetchType.LAZY)
  private Chat chat;
  private LocalDateTime createTime;

  public void setChat(Chat chat) {
    this.chat = chat;
    this.chat.getMessages().add(this);
  }
}
