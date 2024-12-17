package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageEnum;

public interface LanguageService {
    Language findLanguage(LanguageEnum language);
}
