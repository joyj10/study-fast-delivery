package org.delivery.api.account;

import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.api.comon.api.Api;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/me")
    public Api<AccountMeResponse> me() {
        AccountMeResponse response = AccountMeResponse.builder()
                .name("박자바")
                .email("java@test.com")
                .registeredAt(LocalDateTime.now())
                .build();

        return Api.OK(response);
    }
}
