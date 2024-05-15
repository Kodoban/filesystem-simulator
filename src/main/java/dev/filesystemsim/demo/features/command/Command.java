package dev.filesystemsim.demo.features.command;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="command")
public class Command {

    // TODO: Add more attributes: E.g. Map for flags?
    @Id
    @NotNull @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    private String description;
}
