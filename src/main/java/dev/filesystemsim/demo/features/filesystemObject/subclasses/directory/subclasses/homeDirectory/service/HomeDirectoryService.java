package dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.service;

import java.util.Optional;

import dev.filesystemsim.demo.features.filesystemObject.subclasses.directory.subclasses.homeDirectory.definition.HomeDirectoryDto;

public interface HomeDirectoryService {

    HomeDirectoryDto save(HomeDirectoryDto homeDirectoryDto);

    Optional<HomeDirectoryDto> getHomeDirectoryById(Integer id);

    HomeDirectoryDto update(Integer id, HomeDirectoryDto homeDirectoryDto);

    void delete(Integer id);
}
