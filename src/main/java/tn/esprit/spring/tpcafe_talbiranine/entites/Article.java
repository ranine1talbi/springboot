package tn.esprit.spring.tpcafe_talbiranine.entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "Article")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idArticle;

    private String nomArticle;
    private float prixArticle;

    @Enumerated(EnumType.STRING)
    private TypeArticle typeArticle;

    // ✅ One-to-Many with Detail_Commande
    @OneToMany(mappedBy = "article")
    private List<Detail_Commande> detailCommandes;

    // ✅ Many-to-Many with Promotion (this will create table article_promotion)
    @ManyToMany(cascade = CascadeType.ALL)
     List<Promotion> promotions;

}
