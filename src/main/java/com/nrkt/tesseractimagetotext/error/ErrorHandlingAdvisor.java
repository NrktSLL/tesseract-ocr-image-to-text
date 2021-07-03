package com.nrkt.tesseractimagetotext.error;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@RestControllerAdvice
public class ErrorHandlingAdvisor implements ProblemHandling {
}
