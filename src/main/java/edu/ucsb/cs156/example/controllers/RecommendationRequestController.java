package edu.ucsb.cs156.example.controllers;

import edu.ucsb.cs156.example.entities.RecommendationRequest;
import edu.ucsb.cs156.example.errors.EntityNotFoundException;
import edu.ucsb.cs156.example.repositories.RecommendationRequestRepository;

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

@Tag(name = "RecommendationRequest")
@RequestMapping("/api/recommendationrequest")
@RestController
@Slf4j
public class RecommendationRequestController extends ApiController {

    // @Autowired
    // RecommendationRequestRepository recommendationRequestRepository;

    // @Operation(summary = "List all requests for letters of rec")
    // @PreAuthorize("hasRole('ROLE_USER')")
    // @GetMapping("/all")
    // public Iterable<RecommendationRequest> allRecommendationRequests() {
    // Iterable<RecommendationRequest> requests =
    // recommendationRequestRepository.findAll();
    // return requests;
    // }

    // @Operation(summary = "Create a new request for letter of rec")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // @PostMapping("/post")
    // public RecommendationRequest postRecommendationRequests(
    // @Parameter(name = "requesterEmail") @RequestParam String requesterEmail,
    // @Parameter(name = "professorEmail") @RequestParam String professorEmail,
    // @Parameter(name = "explanation") @RequestParam String explanation,
    // @Parameter(name = "dateRequested (in iso format, e.g. YYYY-mm-ddTHH:MM:SS;see
    // https://en.wikipedia.org/wiki/ISO_8601)") @RequestParam("dateRequested")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime
    // dateRequested,
    // @Parameter(name = "dateNeeded (in iso format, e.g. YYYY-mm-ddTHH:MM:SS; see
    // https://en.wikipedia.org/wiki/ISO_8601)") @RequestParam("dateNeeded")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateNeeded,
    // @Parameter(name = "done") @RequestParam boolean done)

    // throws JsonProcessingException {

    // // For an explanation of @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    // // See: https://www.baeldung.com/spring-date-parameters

    // log.info("localDateTime={}", dateRequested);

    // RecommendationRequest recommendationRequest = new RecommendationRequest();
    // recommendationRequest.setRequesterEmail(requesterEmail);
    // recommendationRequest.setProfessorEmail(professorEmail);
    // recommendationRequest.setExplanation(explanation);
    // recommendationRequest.setDateRequested(dateRequested);
    // recommendationRequest.setDateNeeded(dateNeeded);
    // recommendationRequest.setDone(done);

    // RecommendationRequest savedrecommendationRequests =
    // recommendationRequestRepository.save(recommendationRequest);

    // return savedrecommendationRequests;
    // }

    // @Operation(summary = "Get a single request for letter of rec")
    // @PreAuthorize("hasRole('ROLE_USER')")
    // @GetMapping("")
    // public RecommendationRequest getById(
    // @Parameter(name = "id") @RequestParam Long id) {
    // RecommendationRequest recommendationRequest =
    // recommendationRequestRepository.findById(id)
    // .orElseThrow(() -> new EntityNotFoundException(RecommendationRequest.class,
    // id));

    // return recommendationRequest;
    // }

    // @Operation(summary = "Delete a request for letter of rec")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // @DeleteMapping("")
    // public Object deleteRecommendationRequests(
    // @Parameter(name = "id") @RequestParam Long id) {
    // RecommendationRequest recrequests = recrequestsRepository.findById(id)
    // .orElseThrow(() -> new EntityNotFoundException(RecommendationRequest.class,
    // id));

    // recommendationRequestRepository.delete(recommendationRequest);
    // return genericMessage("Recommendation request with id %s
    // deleted".formatted(id));
    // }

    // @Operation(summary = "Update a single request for letter of rec")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // @PutMapping("")
    // public RecommendationRequest updateRecommendationRequests(
    // @Parameter(name = "id") @RequestParam Long id,
    // @RequestBody @Valid RecommendationRequest incoming) {

    // RecommendationRequest recommendationRequest =
    // recommendationRequestRepository.findById(id)
    // .orElseThrow(() -> new EntityNotFoundException(RecommendationRequest.class,
    // id));

    // recommendationRequest.setQuarterYYYYQ(incoming.getQuarterYYYYQ());
    // recommendationRequest.setName(incoming.getName());
    // recommendationRequest.setLocalDateTime(incoming.getLocalDateTime());

    // recommendationRequest.setRequesterEmail(incoming.getRequesterEmail());
    // recommendationRequest.setProfessorEmail(incoming.getProfessorEmail());
    // recommendationRequest.setExplanation(incoming.getExplanation());
    // recommendationRequest.setDateRequested(incoming.getDateRequested());
    // recommendationRequest.setDateNeeded(incoming.getDateNeeded());
    // recommendationRequest.setDone(incoming.getDone());

    // recommendationRequestRepository.save(recommendationRequest);

    // return RecommendationRequest;
    // }
}
