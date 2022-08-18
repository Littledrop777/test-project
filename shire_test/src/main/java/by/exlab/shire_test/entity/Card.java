package by.exlab.shire_test.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "card")
public class Card extends AuditingEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String topic;
  @Enumerated(EnumType.STRING)
  private Status status;
  private String description;
  private String offer;
  @Builder.Default
  @OneToMany(mappedBy = "card")
  private List<Chat> chat = new ArrayList<>();
  @ManyToOne(fetch = FetchType.LAZY)
  private AppUser appUser;

  public void setAppUser(AppUser appUser) {
    this.appUser = appUser;
    this.appUser.getCards().add(this);
  }
}
