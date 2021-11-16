package tw.com.fcb.mimosa.examples.gettingstarted;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class ErrorCode {
	
	@Id
	@GeneratedValue
	Long id;
	String category;
	String code;
	String translation;
}
