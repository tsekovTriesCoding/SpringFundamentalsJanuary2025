package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.enums.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;

    @Autowired
    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }


    @Override
    public void initStyles() {
        if (styleRepository.count() > 0) {
            return;
        }

        for (StyleEnum styleName : StyleEnum.values()) {
            Style style = new Style(styleName);

            styleRepository.save(style);
        }
    }
}
