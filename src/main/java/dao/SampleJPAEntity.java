package dao;

import lombok.Data;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;

@Entity
@Table(name = "STATISTIC")
@NamedQueries({
        @NamedQuery(name = "SampleJPA.findPlayerByID", query = "SELECT e FROM SampleJPAEntity e WHERE e.id = :id ORDER BY e.score")
        ,
        @NamedQuery(name = "SampleJPA.findPlayerByName", query = "SELECT e FROM SampleJPAEntity e WHERE LOWER(e.playerName) LIKE LOWER(:playerName) ORDER BY e.score")
        ,
        @NamedQuery(name = "SampleJPA.findHighScore", query = "SELECT MAX(e.score) FROM SampleJPAEntity e")
})

@Data
@EqualsAndHashCode
@ToString(callSuper = true)
public class SampleJPAEntity implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false, updatable = false)
    private Integer Id;

    @Column(name = "PLAYER_NAME")
    private String playerName;

    public Integer getId() {
        return Id;
    }

    @Column(name = "CREDIT")
    private int credit;

}
