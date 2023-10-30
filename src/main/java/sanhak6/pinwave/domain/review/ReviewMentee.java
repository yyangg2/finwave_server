package sanhak6.pinwave.domain.review;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Mentee")
@Getter @Setter
public class ReviewMentee extends Review{
}
