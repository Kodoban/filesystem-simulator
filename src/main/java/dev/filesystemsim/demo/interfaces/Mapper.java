package dev.filesystemsim.demo.interfaces;

public interface Mapper<A,B> {

    B mapEntityToDto(A a);

    A mapDtoToEntity(B b);
}
