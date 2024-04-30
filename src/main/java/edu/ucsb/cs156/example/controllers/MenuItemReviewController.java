package edu.ucsb.cs156.example.controllers;

import edu.ucsb.cs156.example.entities.MenuItemReview;
import edu.ucsb.cs156.example.errors.EntityNotFoundException;
import edu.ucsb.cs156.example.repositories.MenuItemReviewRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.time.LocalDateTime;

@Tag(name = "MenuItemReview")
@RequestMapping("/api/menuitemreview") //name is maybe not this TODO
@RestController
@Slf4j
public class MenuItemReviewController extends ApiController {
    @Autowired
    MenuItemReviewRepository menuItemReviewRepository;
    
    @Operation(summary= "List all reviews")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/all")
    public Iterable<MenuItemReview> allUCSBDates() {
        Iterable<MenuItemReview> reviews = menuItemReviewRepository.findAll();
        return reviews;
    }

    @Operation(summary= "Create a new review")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/post")
    public MenuItemReview postReview(
        @Parameter(name="itemId") @RequestParam Long itemId,
        @Parameter(name="reviewerEmail") @RequestParam String reviewerEmail,
        @Parameter(name="stars") @RequestParam int stars,
        @Parameter(name="date (in iso format, e.g. YYYY-mm-ddTHH:MM:SS; see https://en.wikipedia.org/wiki/ISO_8601)") @RequestParam("localDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateReviewed,
        @Parameter(name="comments") @RequestParam String comments
        )
        {

        MenuItemReview review = new MenuItemReview();
        review.setItemId(itemId);
        review.setReviewerEmail(reviewerEmail);
        review.setStar(stars);
        review.setDateReviewed(dateReviewed);
        review.setComments(comments);

        MenuItemReview savedReview = menuItemReviewRepository.save(review);

        return savedReview;
    }

    @Operation(summary= "Get a single review")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("")
    public MenuItemReview getById(
            @Parameter(name="id") @RequestParam Long id) {
                MenuItemReview review = menuItemReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MenuItemReview.class, id));

        return review;
    }

    @Operation(summary= "Get all reviews for a specific item")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/byItemId")
    public Iterable<MenuItemReview> getByItemId(
        @Parameter(name="itemId") @RequestParam Long itemId) {
            Iterable<MenuItemReview> reviews = menuItemReviewRepository.findAllReviewsForItem(itemId);
        return reviews;
    }

    @Operation(summary= "Delete a review")
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("")
    public Object deleteCommons(
        @Parameter(name="id") @RequestParam Long id) {
            MenuItemReview review = menuItemReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MenuItemReview.class, id));

                menuItemReviewRepository.delete(review);
        return genericMessage("UCSBDiningCommons with id %s deleted".formatted(id));
    }

    @Operation(summary= "Update comment")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("")
    public MenuItemReview updateReview(
            @Parameter(name="id") @RequestParam Long id,
            @RequestBody @Valid MenuItemReview incoming) {

                MenuItemReview review = menuItemReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MenuItemReview.class, id));


                review.setComments(incoming.getComments());  
                review.setDateReviewed(incoming.getDateReviewed());

        menuItemReviewRepository.save(review);

        return review;
    }

    @Operation(summary= "Update rating")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("")
    public MenuItemReview updateRating(
            @Parameter(name="id") @RequestParam Long id,
            @RequestBody @Valid MenuItemReview incoming) {

                MenuItemReview review = menuItemReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MenuItemReview.class, id));


                review.setStar(incoming.getStar());  
                review.setDateReviewed(incoming.getDateReviewed());

        menuItemReviewRepository.save(review);

        return review;
    }
}
