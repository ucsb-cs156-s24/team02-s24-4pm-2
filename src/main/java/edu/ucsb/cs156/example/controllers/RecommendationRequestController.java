// package edu.ucsb.cs156.example.controllers;

// import edu.ucsb.cs156.example.entities.RecommendationRequest;
// import edu.ucsb.cs156.example.errors.EntityNotFoundException;
// import edu.ucsb.cs156.example.repositories.RecommendationRequest;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.Parameter;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import lombok.extern.slf4j.Slf4j;

// import com.fasterxml.jackson.core.JsonProcessingException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import javax.validation.Valid;

// import java.time.LocalDateTime;

// @Tag(name = "RecommendationRequest")
// @RequestMapping("/api/recommendationrequest")
// @RestController
// @Slf4j
// public class RecommendationRequestController extends ApiController {

// @Autowired
// RecommendationRequest recommendationRequestsRepository;

// @Operation(summary = "List all ucsb dates")
// @PreAuthorize("hasRole('ROLE_USER')")
// @GetMapping("/all")
// public Iterable<RecommendationRequest> allRecommendationRequests() {
// Iterable<RecommendationRequest> dates =
// recommendationRequestsRepository.findAll();
// return dates;
// }

// @Operation(summary = "Create a new date")
// @PreAuthorize("hasRole('ROLE_ADMIN')")
// @PostMapping("/post")
// public RecommendationRequest postRecommendationRequests(
// @Parameter(name = "quarterYYYYQ") @RequestParam String quarterYYYYQ,
// @Parameter(name = "name") @RequestParam String name,
// @Parameter(name = "date (in iso format, e.g. YYYY-mm-ddTHH:MM:SS; see
// https://en.wikipedia.org/wiki/ISO_8601)") @RequestParam("localDateTime")
// @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime
// localDateTime)
// throws JsonProcessingException {

// // For an explanation of @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
// // See: https://www.baeldung.com/spring-date-parameters

// log.info("localDateTime={}", localDateTime);

// RecommendationRequest recommendationRequest = new RecommendationRequest();
// recommendationRequest.setQuarterYYYYQ(quarterYYYYQ);
// recommendationRequest.setName(name);
// recommendationRequest.setLocalDateTime(localDateTime);

// RecommendationRequest savedrecommendationRequests =
// recommendationRequestsRepository
// .save(recommendationRequest);

// return savedrecommendationRequests;
// }

// @Operation(summary = "Get a single date")
// @PreAuthorize("hasRole('ROLE_USER')")
// @GetMapping("")
// public RecommendationRequest getById(
// @Parameter(name = "id") @RequestParam Long id) {
// RecommendationRequest recommendationRequest =
// recommendationRequestsRepository.findById(id)
// .orElseThrow(() -> new EntityNotFoundException(RecommendationRequest.class,
// id));

// return recommendationRequest;
// }

// @Operation(summary = "Delete a RecommendationRequest")
// @PreAuthorize("hasRole('ROLE_ADMIN')")
// @DeleteMapping("")
// public Object deleteRecommendationRequests(
// @Parameter(name = "id") @RequestParam Long id) {
// RecommendationRequest recrequests = recrequestsRepository.findById(id)
// .orElseThrow(() -> new EntityNotFoundException(RecommendationRequest.class,
// id));

// recommendationRequestsRepository.delete(recommendationRequest);
// return genericMessage("RecommendationRequest with id %s
// deleted".formatted(id));
// }

// @Operation(summary = "Update a single date")
// @PreAuthorize("hasRole('ROLE_ADMIN')")
// @PutMapping("")
// public RecommendationRequest updateRecommendationRequests(
// @Parameter(name = "id") @RequestParam Long id,
// @RequestBody @Valid RecommendationRequest incoming) {

// RecommendationRequest recommendationRequest =
// recommendationRequestsRepository.findById(id)
// .orElseThrow(() -> new EntityNotFoundException(RecommendationRequest.class,
// id));

// recommendationRequest.setQuarterYYYYQ(incoming.getQuarterYYYYQ());
// recommendationRequest.setName(incoming.getName());
// recommendationRequest.setLocalDateTime(incoming.getLocalDateTime());

// recommendationRequestsRepository.save(recommendationRequest);

// return RecommendationRequest;
// }
// }
