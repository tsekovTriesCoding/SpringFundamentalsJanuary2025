package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageEnum;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitLanguages implements CommandLineRunner {
    private final Map<LanguageEnum, String> languageDescriptions = Map.of(
            LanguageEnum.GERMAN, "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.",
            LanguageEnum.SPANISH, "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.",
            LanguageEnum.FRENCH, "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.",
            LanguageEnum.ITALIAN, "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history."
    );
    private final LanguageRepository languageRepository;

    public InitLanguages(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = this.languageRepository.count();

        if (count == 0) {
            for (LanguageEnum languageEnum : this.languageDescriptions.keySet()) {
                Language language = new Language(languageEnum, this.languageDescriptions.get(languageEnum));

                this.languageRepository.save(language);
            }
        }
    }
}
