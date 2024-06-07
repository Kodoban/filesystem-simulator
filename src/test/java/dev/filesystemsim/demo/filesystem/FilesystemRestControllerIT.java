package dev.filesystemsim.demo.filesystem;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.filesystemsim.demo.features.filesystem.definition.FilesystemEntity;
import dev.filesystemsim.demo.features.filesystem.service.FilesystemService;
import dev.filesystemsim.demo.features.role.Role;
import dev.filesystemsim.demo.features.role.RoleRepository;
import dev.filesystemsim.demo.features.user.UserRepository;
import dev.filesystemsim.demo.features.user.definition.UserEntity;
import dev.filesystemsim.demo.urlMappings.UrlMapping;
import dev.filesystemsim.demo.user.UserTestDataUtil;

@SpringBootTest
// @WebMvcTest(FilesystemRestController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class FilesystemRestControllerIT {

    // private MockMvc mockMvc;
    // private ObjectMapper objectMapper;
    // private FilesystemService filesystemService;

    // @Autowired
    // private UserRepository userRepository;
    // @Autowired
    // private RoleRepository roleRepository;
    // private UserEntity testUser;
    // private Set<Role> testRoles;

    // @Autowired
    // public FilesystemRestControllerIT(MockMvc mockMvc, FilesystemService filesystemService) {
    //     this.mockMvc = mockMvc;
    //     this.filesystemService = filesystemService;
    //     objectMapper = new ObjectMapper();
    // }

    // @BeforeEach
    // void setUpUser() {
    //     Role adminRole = roleRepository.save(new Role("ADMIN"));
    //     Role userRole = roleRepository.save(new Role("USER"));
    //     roleRepository.save(new Role("USER"));

    //     testRoles = new HashSet<>();
    //     testRoles.add(adminRole);
    //     testRoles.add(userRole);
    //     testUser = userRepository.save(UserTestDataUtil.createTestUserEntityA(testRoles));
    // }

    // @Test
    // public void testThatCreateFilesystemSuccessfullyReturnsHttp201Created() throws Exception {
    //     FilesystemEntity testFilesystemEntityA = FilesystemTestDataUtil.createTestFilesystemEntityA(testUser);
    //     String filesystemJson = objectMapper.writeValueAsString(testFilesystemEntityA);

    //     mockMvc.perform(
    //         MockMvcRequestBuilders.post(UrlMapping.FILESYSTEM_REST_CONTROLLER_URL + "")
    //                 .contentType(MediaType.APPLICATION_JSON)
    //                 .content(filesystemJson)
    //     ).andExpect(
    //         MockMvcResultMatchers.status().isCreated()
    //     );
    // }

    // @Test
    // public void testThatListFilesystemsReturnsHttpStatus200() throws Exception {
    //     mockMvc.perform(
    //         MockMvcRequestBuilders.get(UrlMapping.FILESYSTEM_REST_CONTROLLER_URL + "")
    //                 .contentType(MediaType.APPLICATION_JSON)
    //     ).andExpect(
    //         MockMvcResultMatchers.status().isOk()
    //     );
    // }
}
