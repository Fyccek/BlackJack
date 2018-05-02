package dao;

/*-
 * #%L
 * BlackJack
 * %%
 * Copyright (C) 2018 University of Debrecen
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import lombok.Data;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;

@Entity
@Table(name = "AI", schema = "MY_OWN_SCHEMA")
@NamedQueries({
        @NamedQuery(name = "AiEntity.findAisCredit", query = "SELECT e.credit FROM AiEntity e where e.Id = :Id")
})

/**
 * AiEntity represents an entity for JPA.
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
public class AiEntity implements Serializable{

    /**
     * Automatic generated firs key for entity. Obligatory.
     */
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false, updatable = false)
    private Long Id;

    /**
     * Getter for Id variable.
     *
     */
    public Long getId() { return Id; }

    /**
     * Setter for credit variable.
     *
     * @param amount
     */
    public void setCredit(Integer amount) { this.credit = amount; }

    /**
     * Getter for credit variable.
     */
    public Integer getCredit(){ return this.credit; }

    /**
     * Representing Ai's credit in the database.
     */
    @Column(name = "CREDIT")
    private Integer credit;

}
