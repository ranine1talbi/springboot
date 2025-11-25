    package tn.esprit.spring.tpcafe_talbiranine.entites;

    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.FieldDefaults;

    import java.util.List;

    @Entity
    @Table(name = "Detail_Commande")
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @EqualsAndHashCode
    public class Detail_Commande {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long idDetail_Commande;

        @ManyToOne
        private Article article;

        @ManyToOne
        private Commande commande;

        @Column(name = "quantiteArticle")
        private int quantiteArticle;

        @Column(name = "sousTotalDetailArticle")
        private float sousTotalDetailArticle;

        @Column(name = "sousTotalDetailArticleApresPromo")
        private float sousTotalDetailArticleApresPromo;
    }
