package com.softeer.backend.fo_domain.fcfs.dto.result;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
public class FcfsSuccessResponseDto implements FcfsResponseDto {

    private String title;

    private String subTitle;

    private String qrCode;

    private String codeWord;

    private String fcfsCode;

    private String expirationDate;

    private String caution;

}
