package ru.coffee.shortlink.service.mapper;

public interface ResponseDtoMapper<D, T>{
    D mapToDto(T model);
}
