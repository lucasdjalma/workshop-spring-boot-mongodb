package com.workshopmongo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshopmongo.domain.Post;
import com.workshopmongo.repository.PostRepository;
import com.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        // você pode usar o método customizado com @Query
        return repo.searchTitle(text);
        // ou se preferir:
        // return repo.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        // adiciona 1 dia ao maxDate para incluir o limite final
        maxDate = new Date(maxDate.getTime() + 24L * 60L * 60L * 1000L);
        return repo.fullSearch(text, minDate, maxDate);
    }
}
