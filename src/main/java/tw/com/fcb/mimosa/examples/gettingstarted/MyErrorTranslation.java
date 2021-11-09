package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.domain.t9n.Term;
import tw.com.fcb.mimosa.domain.t9n.Translated;
import tw.com.fcb.mimosa.domain.t9n.TranslationService;

@Service
public class MyErrorTranslation implements TranslationService{
	public Optional<Translated> translate(@NonNull Term term){
		if(term.getCode().equals("ERR1")) {
			return Optional.of(
					new MyTranslation(term.getCategory(), term.getCode(), "查無姓名")
					);
			/*return Optional.of(new Translated() {
				
				@Override
				public @NotBlank String getCode() {
					// TODO Auto-generated method stub
					return term.getCode();
				}
				
				@Override
				public @NotBlank String getCategory() {
					// TODO Auto-generated method stub
					return term.getCategory();
				}
				
				@Override
				public String getTranslation() {
					// TODO Auto-generated method stub
					return "查無姓名";
				}
			});*/
			
		}
		return Optional.empty();
	}
	
	@Getter
	@RequiredArgsConstructor
	static class MyTranslation implements Translated {
		final String category;
		final String code;
		final String translation;
	}
}
