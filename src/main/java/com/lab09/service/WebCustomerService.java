package com.lab09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.lab09.dto.CustomerRequest;
import com.lab09.dto.CustomerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class WebCustomerService {
    @Autowired
     private final WebClient webClient;
     @Autowired
     public WebCustomerService(WebClient webClient) {
            this.webClient = webClient;
     }
     
     
     //get request 
     public Flux<CustomerResponse> getAllCustomers() {// List<Author>
            return webClient.get()
                    .uri("")
                    .retrieve()
                    .bodyToFlux(CustomerResponse.class) ;
        }
     public Mono<CustomerResponse> getCustomerById(Long id) {
            return webClient.get()
                    .uri("/{id}", id)
                    .retrieve()
                    .bodyToMono(CustomerResponse.class);
        }
     public Mono<CustomerResponse> createCustomer(CustomerRequest cus) {
            System.out.println("Sending Author: " + cus);

            return webClient.post()
                    .uri("")
                    .body(Mono.just(cus), CustomerResponse.class)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> 
                        Mono.error(
                                new RuntimeException("Client error during createCustomer")))
                    .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> 
                        Mono.error(new RuntimeException("Server error during createCustomer")))
                    .bodyToMono(CustomerResponse.class)
                    .doOnNext(response ->
                          System.out.println("Response received: " + response))
                    .doOnError(error ->
                           System.out.println("Error occurred: " + error.getMessage()));
        }


        public Mono<CustomerResponse> updateCustomer(Long id, CustomerRequest cus) {
            System.out.println("Edited customer "+ cus.toString());
            return webClient.put().uri("/"+id)
                    .bodyValue(cus).retrieve().bodyToMono(CustomerResponse.class);
        }

       
        public Mono<Void> deleteCustomerById(Long id) {
            //System.out.println("deleting an author having id ="+id);
            return webClient.delete()
                    .uri("/authors/{id}", id)
                    .retrieve()
                    .bodyToMono(Void.class);
        }
}