package org.delivery.api.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.comon.api.Api;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me() {
        RequestAttributes requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        Long userId = Long.parseLong((String) Objects.requireNonNull(requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST)));
        UserResponse response = userBusiness.me(userId);
        return Api.OK(response);
    }
}
