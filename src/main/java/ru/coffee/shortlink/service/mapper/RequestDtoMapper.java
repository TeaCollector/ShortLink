package ru.coffee.shortlink.service.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
