package com.user.service.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private Long ratingId;
    private Long userId;
    private Long hotelId;
    private BigInteger rating;
    private String feedback;

    private Hotel hotel;
}
