package org.example.casestudymodule4shoestore.services;

import java.util.List;

public interface IGenerateService<T> {

    List<T> findAll();

}
