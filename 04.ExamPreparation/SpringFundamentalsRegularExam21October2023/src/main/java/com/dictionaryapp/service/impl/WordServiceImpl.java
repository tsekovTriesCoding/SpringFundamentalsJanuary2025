package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageEnum;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final UserService userService;
    private  final LanguageService languageService;

    public WordServiceImpl(WordRepository wordRepository,
                           UserRepository userRepository,
                           LanguageRepository languageRepository,
                           UserService userService,
                           LanguageService languageService) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.userService = userService;
        this.languageService = languageService;
    }

    @Transactional
    @Override
    public void addWord(AddWordDTO addWordDTO, long id) {
        Word word = new Word();
        Language language = this.languageService.findLanguage(addWordDTO.getLanguage());
        User userById = userService.findById(id).orElse(null);

        word.setTerm(addWordDTO.getTerm())
                .setTranslation(addWordDTO.getTranslation())
                .setExample(addWordDTO.getExample())
                .setInputDate(addWordDTO.getInputDate())
                .setLanguage(language)
                .setAddedBy(userById);

        Set<Word> userByIdAssignedWords = userById.getAddedWords();
        userByIdAssignedWords.add(word);
        userById.setAddedWords(userByIdAssignedWords);

        this.wordRepository.save(word);
        this.userRepository.save(userById);
    }

    @Override
    public Set<Word> getAllGermanWords() {
        Language languageGerman = this.languageRepository.findByLanguageName(LanguageEnum.GERMAN).orElse(null);
        Set<Word> allGermanWords = this.wordRepository.findAllByLanguage(languageGerman);

        return allGermanWords;
    }

    @Override
    public Set<Word> getAllFrenchWords() {
        Language languageFrench = this.languageRepository.findByLanguageName(LanguageEnum.FRENCH).orElse(null);

        return this.wordRepository.findAllByLanguage(languageFrench);
    }

    @Override
    public Set<Word> getAllSpanishWords() {
        Language languageSpanish = this.languageRepository.findByLanguageName(LanguageEnum.SPANISH).orElse(null);

        return this.wordRepository.findAllByLanguage(languageSpanish);
    }

    @Override
    public Set<Word> getAllItalianWords() {
        Language languageItalian = this.languageRepository.findByLanguageName(LanguageEnum.ITALIAN).orElse(null);

        return this.wordRepository.findAllByLanguage(languageItalian);
    }

    @Transactional
    @Override
    public void removeWordById(Long id) {
        Word word = this.wordRepository.findById(id).orElse(null);
        User addedBy = word.getAddedBy();
        Set<Word> assignedWords = addedBy.getAddedWords();
        assignedWords.remove(word);

        userRepository.save(addedBy);
    }

    @Override
    public long getAllCount() {
        return this.wordRepository.count();
    }
}
