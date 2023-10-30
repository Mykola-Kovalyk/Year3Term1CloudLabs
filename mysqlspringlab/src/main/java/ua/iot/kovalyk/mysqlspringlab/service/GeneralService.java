package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iot.kovalyk.mysqlspringlab.exception.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

public abstract class GeneralService<T, ID> {

    protected JpaRepository<T, ID> repository;

    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<T> findAll() {
        return repository.findAll();
    }


    @Transactional
    public T create(T book) {
        repository.save(book);
        return book;
    }

    public abstract void update(ID id, T resourceToUpdate);

    @Transactional
    public void delete(ID id) {
        T book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        repository.delete(book);
    }
}
