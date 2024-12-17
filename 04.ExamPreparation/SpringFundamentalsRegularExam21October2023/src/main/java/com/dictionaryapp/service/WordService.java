package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.Word;

import java.util.Set;

public interface WordService {
    void addWord(AddWordDTO data, long id);

    Set<Word> getAllGermanWords();

    Set<Word> getAllFrenchWords();

    Set<Word> getAllSpanishWords();

    Set<Word> getAllItalianWords();

    void removeWordById(Long id);

    long getAllCount();
}
