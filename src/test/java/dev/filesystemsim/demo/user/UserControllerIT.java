package dev.filesystemsim.demo.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.filesystemsim.demo.TestDataUtil;
import dev.filesystemsim.demo.features.user.definition.UserDto;
import dev.filesystemsim.demo.features.user.definition.UserEntity;
import dev.filesystemsim.demo.features.user.service.UserService;
import dev.filesystemsim.demo.urlMappings.UrlMapping;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerIT {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private UserService userService;

    @Autowired
    public UserControllerIT(MockMvc mockMvc, UserService userService) {
        this.mockMvc = mockMvc;
        this.userService = userService;
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateUserSuccessfullyReturnsHttp201Created() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserEntityA();
        testUserEntityA.setId(null);
        String userJson = objectMapper.writeValueAsString(testUserEntityA);

        mockMvc.perform(
            MockMvcRequestBuilders.post(UrlMapping.USER_REST_CONTROLLER_URL + "")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userJson)
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateUserSuccessfullyReturnsSavedUser() throws Exception {
        // TODO: Find out how and if objectMapper.writeValueAsString can return the passwordHash in the JSON
        // UserDto testUserA = TestDataUtil.createTestUserDtoA();
        // testUserA.setId(null);
        // String userJson = objectMapper.writeValueAsString(testUserA);
        String userJson = "{\"username\": \"Sam\", \"passwordHash\": \"123123\"}";

        mockMvc.perform(
                MockMvcRequestBuilders.post(UrlMapping.USER_REST_CONTROLLER_URL + "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.username").value("Sam")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.passwordHash").doesNotExist()
        );
    }

    @Test
    public void testThatListUsersReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get(UrlMapping.USER_REST_CONTROLLER_URL + "")
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListUsersReturnsListOfUsers() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserEntityA();
        userService.save(testUserEntityA);

        mockMvc.perform(
            MockMvcRequestBuilders.get(UrlMapping.USER_REST_CONTROLLER_URL + "")
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].id").value(testUserEntityA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].username").value("Sam")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].passwordHash").doesNotExist()
        );
    }

    @Test
    public void testThatGetOneUserReturnsHttpStatus200WhenUserExists() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserEntityA();
        userService.save(testUserEntityA);

        mockMvc.perform(
            MockMvcRequestBuilders.get(UrlMapping.USER_REST_CONTROLLER_URL + "/" + testUserEntityA.getId())
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetOneUserReturnsHttpStatus404WhenUserNotExists() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get(UrlMapping.USER_REST_CONTROLLER_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetOneUserReturnsUserIfExists() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserEntityA();
        userService.save(testUserEntityA);

        mockMvc.perform(
            MockMvcRequestBuilders.get(UrlMapping.USER_REST_CONTROLLER_URL + "/" + testUserEntityA.getId())
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.id").value(testUserEntityA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.username").value("Sam")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.passwordHash").doesNotExist()
        );
    }

    @Test
    public void testThatGetPatchOneUserReturnsHttpStatus200WhenUserExistsAndUsernameIsUnique() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserEntityA();
        UserEntity savedUserA = userService.save(testUserEntityA);

        UserDto testUserDtoA = TestDataUtil.createTestUserDtoA();
        testUserDtoA.setUsername("Samuel");
        String userAUpdatedJson = objectMapper.writeValueAsString(testUserDtoA);

        mockMvc.perform(
            MockMvcRequestBuilders.patch(UrlMapping.USER_REST_CONTROLLER_URL + "/" + savedUserA.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userAUpdatedJson)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetPatchOneUserReturnsHttpStatus404WhenUserNotFound() throws Exception {
        UserDto testUserDtoA = TestDataUtil.createTestUserDtoA();
        testUserDtoA.setUsername("Samuel");
        String userAUpdatedJson = objectMapper.writeValueAsString(testUserDtoA);

        mockMvc.perform(
            MockMvcRequestBuilders.patch(UrlMapping.USER_REST_CONTROLLER_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userAUpdatedJson)
        ).andExpect(
            MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetPatchOneUserReturnsHttpStatus403WhenUserExistsButUsernameIsTlane() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserEntityA();
        UserEntity savedUserA = userService.save(testUserEntityA);

        UserEntity testUserEntityB = TestDataUtil.createTestUserEntityB();
        UserEntity savedUserB = userService.save(testUserEntityB);
        
        UserDto testUserDtoA = TestDataUtil.createTestUserDtoA();
        testUserDtoA.setUsername("Tom");
        String userAUpdatedJson = objectMapper.writeValueAsString(testUserDtoA);

        mockMvc.perform(
            MockMvcRequestBuilders.patch(UrlMapping.USER_REST_CONTROLLER_URL + "/" + savedUserA.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userAUpdatedJson)
        ).andExpect(
            MockMvcResultMatchers.status().isConflict()
        );
    }

    @Test
    public void testThatGetPatchOneUserReturnsUpdatedUser() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserEntityA();
        UserEntity savedUserA = userService.save(testUserEntityA);

        UserDto testUserDtoA = TestDataUtil.createTestUserDtoA();
        testUserDtoA.setUsername("Samuel");
        String userAUpdatedJson = objectMapper.writeValueAsString(testUserDtoA);

        mockMvc.perform(
            MockMvcRequestBuilders.patch(UrlMapping.USER_REST_CONTROLLER_URL + "/" + savedUserA.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userAUpdatedJson)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.id").value(testUserEntityA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.username").value(testUserDtoA.getUsername())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.passwordHash").doesNotExist()
        );
    }

    @Test
    public void testThatDeleteUserReturnsHttpStatus200() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserEntityA();
        UserEntity savedUserA = userService.save(testUserEntityA);

        mockMvc.perform(
            MockMvcRequestBuilders.delete(UrlMapping.USER_REST_CONTROLLER_URL + "/" + savedUserA.getId())
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void testThatDeleteUserReturnsHttpStatus204IfUserNotExists() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.delete(UrlMapping.USER_REST_CONTROLLER_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isNoContent()
        );
    }
}
