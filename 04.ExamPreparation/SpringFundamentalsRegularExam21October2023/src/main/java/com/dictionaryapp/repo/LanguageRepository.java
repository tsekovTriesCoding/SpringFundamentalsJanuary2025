package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByLanguageName(LanguageEnum language);
}
