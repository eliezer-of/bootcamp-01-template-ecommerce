package com.br.ecommerce.utils;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderFake implements Uploader{

    public Set<String> enviar(List<MultipartFile> imagens) {
        return imagens.stream().map(file -> "http://imagens.io/"+file.getOriginalFilename()).collect(Collectors.toSet());
    }
}
