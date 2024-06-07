package dev.filesystemsim.demo.features.file.definition;

import dev.filesystemsim.demo.features.directory.definition.DirectoryEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @SuperBuilder
@Entity
@Table(name="file")
@Inheritance(strategy = InheritanceType.JOINED)
public class FileEntity {

    // TODO: Change to composite key (Filesystem UUID + File id)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_id_seq")
    private Integer id;

    @NotNull @NotBlank
    private String name;

    private String content;

    // @ManyToOne
    // // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "parent_directory_id")
    // private DirectoryEntity parentDirectory;
}
