package com.usermanagement.service;

import com.usermanagement.model.User;
import com.usermanagement.repository.IRepository;
import com.usermanagement.repository.Repository;

import java.util.List;

public class Service implements IService {
    IRepository repository = new Repository();

    @Override
    public List<User> getList() {
        return repository.getList();
    }

    @Override
    public void add(User user) {
        repository.add(user);
    }

    @Override
    public List<User> searchByCountry(String country) {
        return repository.searchByCountry(country);
    }

    @Override
    public List<User> getSortList() {
        return repository.getSortList();
    }

    @Override
    public User findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void update(User user) {
        repository.update(user);
    }

    @Override
    public List<User> searchByName(String name) {
        return repository.searchByName(name);
    }
}