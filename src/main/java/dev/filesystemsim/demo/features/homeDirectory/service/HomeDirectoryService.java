package dev.filesystemsim.demo.features.homeDirectory.service;

import java.util.Optional;

import dev.filesystemsim.demo.features.homeDirectory.definition.HomeDirectoryDto;

public interface HomeDirectoryService {

    HomeDirectoryDto save(HomeDirectoryDto homeDirectoryDto);

    Optional<HomeDirectoryDto> getHomeDirectoryById(Integer id);

    HomeDirectoryDto update(Integer id, HomeDirectoryDto homeDirectoryDto);

    void delete(Integer id);
}
