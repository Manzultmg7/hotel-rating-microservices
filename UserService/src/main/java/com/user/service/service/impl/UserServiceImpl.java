package com.user.service.service.impl;

import com.user.service.Repository.UserRepository;
import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.external.service.HotelService;
import com.user.service.service.UserService;
import com.user.service.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(), Rating[].class);
        log.info("{} ", (Object) ratingOfUser);
        assert ratingOfUser != null;
        List<Rating> ratings= Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {

           // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
        //   log.info("response status code:{} ", forEntity.getStatusCode());

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
