package com.my.ws_encheres.fiche;

import com.my.ws_encheres.model.Photo;
import com.my.ws_encheres.model.enchere.Enchere;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnchereParam {
    private Enchere enchere;
    private List<Photo> pic;
}
