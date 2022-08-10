package by.exlab.shire_test.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_chat")
public class UserChat implements BaseEntity<Long>{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private AppUser firstUser;
  @ManyToOne(fetch = FetchType.LAZY)
  private AppUser secondUser;
  @Builder.Default
  @OneToMany(mappedBy = "userChat")
  private List<Chat> chats = new ArrayList<>();
  private Instant createDate;

}