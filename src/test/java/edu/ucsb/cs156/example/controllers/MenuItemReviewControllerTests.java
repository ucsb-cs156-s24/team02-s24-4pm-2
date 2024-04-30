package edu.ucsb.cs156.example.controllers;

import edu.ucsb.cs156.example.repositories.UserRepository;
import edu.ucsb.cs156.example.testconfig.TestConfig;
import edu.ucsb.cs156.example.ControllerTestCase;
import edu.ucsb.cs156.example.entities.MenuItemReview;
import edu.ucsb.cs156.example.entities.UCSBDate;
import edu.ucsb.cs156.example.repositories.MenuItemReviewRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.time.LocalDateTime;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = MenuItemReviewController.class)
@Import(TestConfig.class)
public class MenuItemReviewControllerTests extends ControllerTestCase{

    @MockBean
        MenuItemReviewRepository menuItemReviewRepository;

        @MockBean
        UserRepository userRepository;
    
     // Tests for GET /api/ucsbdiningcommons/all

     @Test
     public void logged_out_users_cannot_get_all() throws Exception {
             mockMvc.perform(get("/api/ucsbdiningcommons/all"))
                             .andExpect(status().is(403)); // logged out users can't get all
     }

     @WithMockUser(roles = { "USER" })
     @Test
     public void logged_in_users_can_get_all() throws Exception {
             mockMvc.perform(get("/api/ucsbdiningcommons/all"))
                             .andExpect(status().is(200)); // logged
     }

     @WithMockUser(roles = { "USER" })
     @Test
     public void test_that_logged_in_user_can_get_by_id_when_the_id_does_not_exist() throws Exception {

        // arrange

        when(menuItemReviewRepository.findById(eq((long)7))).thenReturn(Optional.empty());

        // act
        MvcResult response = mockMvc.perform(get("/api/menuitemreview?id=7"))
                        .andExpect(status().isNotFound()).andReturn();

        // assert

        verify(menuItemReviewRepository, times(1)).findById(eq((long)7));
        Map<String, Object> json = responseToJson(response);
        assertEquals("EntityNotFoundException", json.get("type"));
        assertEquals("MenuItemREview with id 7 not found", json.get("message"));
}

    @WithMockUser(roles = { "USER" })
    @Test
    public void logged_in_user_can_get_all_reviews() throws Exception {

                // arrange
                LocalDateTime reviewDate1 = LocalDateTime.parse("2022-01-03T00:00:00");

                MenuItemReview menuItemReview1 = MenuItemReview.builder()
                                .itemId((long)1)
                                .reviewerEmail("reviewer1@gmail.com")
                                .star(4)
                                .dateReviewed(reviewDate1)
                                .comments("Great dish.")
                                .build();

                LocalDateTime reviewDate2 = LocalDateTime.parse("2022-03-11T00:00:00");

                MenuItemReview menuItemReview2 = MenuItemReview.builder()
                                .itemId((long)1)
                                .reviewerEmail("reviewer2@gmail.com")
                                .star(5)
                                .dateReviewed(reviewDate2)
                                .comments("Loved it!")
                                .build();

                ArrayList<MenuItemReview> expectedReviews = new ArrayList<>();
                expectedReviews.addAll(Arrays.asList(menuItemReview1, menuItemReview2));

                when(menuItemReviewRepository.findAll()).thenReturn(expectedReviews);

                // act
                MvcResult response = mockMvc.perform(get("/api/menuitemreview/all"))
                                .andExpect(status().isOk()).andReturn();

                // assert

                verify(menuItemReviewRepository, times(1)).findAll();
                String expectedJson = mapper.writeValueAsString(expectedReviews);
                String responseString = response.getResponse().getContentAsString();
                assertEquals(expectedJson, responseString);
        }

    // Tests for POST /api/ucsbdates/post...

    @Test
    public void logged_out_users_cannot_post() throws Exception {
            mockMvc.perform(post("/api/ucsbdates/post"))
                            .andExpect(status().is(403));
    }
}
