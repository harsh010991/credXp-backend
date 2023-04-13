package com.credXp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferDetailsRequest {
    private String email;
    private String googleAccessToken;
    private String authToken;
}
