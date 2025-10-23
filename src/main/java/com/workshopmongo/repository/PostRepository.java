package com.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    // Busca pelo título (usando regex, case-insensitive)
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchTitle(String text);

    // Alternativa com método padrão do Spring Data
    List<Post> findByTitleContainingIgnoreCase(String text);

    // Busca completa (titulo, corpo e comentários) dentro de um intervalo de datas
    @Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
